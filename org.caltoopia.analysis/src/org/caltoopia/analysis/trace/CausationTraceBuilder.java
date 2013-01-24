/* 
 * Copyright (c) Ericsson AB, 2013
 * All rights reserved.
 *
 * License terms:
 *
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the 
 *       following disclaimer.
 *     * Redistributions in binary form must reproduce the 
 *       above copyright notice, this list of conditions and 
 *       the following disclaimer in the documentation and/or 
 *       other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names 
 *       of its contributors may be used to endorse or promote 
 *       products derived from this software without specific 
 *       prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.caltoopia.analysis.trace;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.util.graph.DiGraph;
import org.caltoopia.analysis.util.graph.Graphs;

public class CausationTraceBuilder {

	/**
	 * @param traceBundle  linear (possible multicore) trace of action firings
	 * @param stateDep     per-action state dependence
	 * @return Causation trace, which is built by playing back the given trace
	 */
	public static DiGraph<ArtTraceEvent> build(ArtTraceBundle traceBundle, SchedulingConstraints stateDep) {
		
		CausationTraceBuilder builder=new CausationTraceBuilder();
		return builder.playback(traceBundle, stateDep);
	}

	private DiGraph<ArtTraceEvent> mCausationTrace;
	
	/**
	 * @param trace
	 * @param stateDep per-action state dependence
	 * @return Causation trace, which is built by playing back the given trace
	 */
	protected DiGraph<ArtTraceEvent> playback(ArtTraceBundle trace, SchedulingConstraints stateDep) {
		Map<Integer,BuilderAction> builderActions=createBuilderActions(trace.getNetwork(), stateDep);
		
		mCausationTrace=Graphs.createDiGraph();		
		for (ArtTraceEvent event: trace) {
			// Each trace event corresponds to an array of "builder actions"
			// which get executed as a result of the event
			BuilderAction action=builderActions.get(event.getAction());
			
			action.execute(event);
		}
		return mCausationTrace;
	}
	
	/**
	 * @param network
	 * @param stateDep per-action state dependence
	 * @return the Map from action-index (given by trace) to BuilderAction (used to construct the causation trace)
	 */
	protected Map<Integer,BuilderAction> createBuilderActions(ArtNetworkDescription network, 
			                                                  SchedulingConstraints stateDep) {
		Map<Integer,BuilderAction> builderActionMap=new HashMap<Integer,BuilderAction>();
		Map<PortInstance,BuilderPort> portMap=new HashMap<PortInstance,BuilderPort>();
		Map<PortInstance,BuilderToken> output2Fifo=new HashMap<PortInstance,BuilderToken>();
		Map<Object,BuilderVariable> variableMap=new HashMap<Object,BuilderVariable>();
		
		// First map all the output ports to BuilderPorts
		for (ActorInstance a: network.getActors()) {
			for (PortInstance p: a.getOutputPorts()) {
				BuilderToken sentinel=new BuilderToken();
				BuilderPort builderPort=new Producer(sentinel);

				output2Fifo.put(p, sentinel);
				portMap.put(p, builderPort);
			}
		}
		
		// Then map all the input ports to BuilderPorts, which share BuilderToken with output.
		// The FIFOs are implemented in this way: initially a producer and its consumers point
		// to the same head/sentinel, then each BuilderPort progresses separately
		for (Connection c: network.getConnections()) {
			PortInstance p=c.getConsumerPort();
			BuilderToken fifo=output2Fifo.get(c.getProducerPort());
			BuilderPort builderPort=new Consumer(fifo);
			
			portMap.put(p, builderPort);
		}
		
		// ...and all variables to BuilderVariables
		for (Action action: network.getActionMap().values()) {
			for (Object var: stateDep.getDefinitions(action)) {
				if (!variableMap.containsKey(var)) {
					variableMap.put(var, new BuilderVariable());
				}
			}
		}
		
		// Now map action indices 0,1,2,... to arrays of BuilderAccesses (one per port and variable accessed)
		for (Map.Entry<Integer,Action> entry: network.getActionMap().entrySet()) {
			// get the port signature (ports with non-zero production/consumption rates)
			Integer index=entry.getKey();
			Action action=entry.getValue();
			PortSignature portSignature=action.getPortSignature();
			
			// get the variables that are accessed
			Set<Object> variables=new HashSet<Object>();
			for (Object var: stateDep.getUses(action)) {
				if (variableMap.containsKey(var))
					variables.add(var);
				// else: the variable is constant (used/not defined in the actions)
			}
			variables.addAll(stateDep.getDefinitions(action));
			
			int numAccess=portSignature.getPorts().size() + variables.size();
			BuilderAccess[] builderAccess=new BuilderAccess[numAccess];
			int i=0;
			
			// Create a BuilderPortAccess for each port that the action accesses
			for (PortInstance p: portSignature.getPorts()) {
				int numTokens=portSignature.getPortRate(p);
				BuilderPort builderPort=portMap.get(p);
				assert(builderPort!=null);
				builderAccess[i++]=new BuilderPortAccess(numTokens,builderPort);
			}
			
			// Create a BuilderStateAccess for each variable
			for (Object var: variables) {
				boolean isUse=stateDep.getUses(action).contains(var);
				boolean isDef=stateDep.getDefinitions(action).contains(var);
				BuilderVariable bv=variableMap.get(var);
				assert(bv!=null && (isUse || isDef));
				builderAccess[i++]=new BuilderStateAccess(bv, isUse, isDef);
			}
			
			// Finally, create the BuilderAction and insert it into then action-index-map
			assert(i==builderAccess.length);
			BuilderAction builderAction=new BuilderAction(builderAccess);
			builderActionMap.put(index, builderAction);
		}
		return builderActionMap;
	}
	
	protected class BuilderAction {
		BuilderAccess mAccess[];
		
		BuilderAction(BuilderAccess access[]) {
			mAccess=access;
		}
		
		void execute(ArtTraceEvent event) {
			for (BuilderAccess access: mAccess) {
				access.execute(event);
			}
		}
	} 
	
	protected interface BuilderAccess {
		/**
		 * Models token production/consumption, use or definition of 
		 * state that is associated with a particular event  
		 * @param event
		 */
		void execute(ArtTraceEvent event);
	}
	

	protected static class BuilderStateAccess implements BuilderAccess {
		
		BuilderVariable mUses, mDefines;
		
		BuilderStateAccess(BuilderVariable storage, boolean uses, boolean defines) {
			if (uses)
				mUses=storage;
			if (defines)
				mDefines=storage;
		}

		@Override
		public void execute(ArtTraceEvent event) {
			if (mUses!=null)
				mUses.executeUse(event);
			if (mDefines!=null)
				mDefines.executeDef(event);
		}
	}
	
	/**
	 * A BuilderAction is either the production or the consumption
	 * of a given number of tokens, to/from a given BuilderPort
	 */
	protected static class BuilderPortAccess implements BuilderAccess {
		
		int mNumTokens;
		BuilderPort mPort;
	
		BuilderPortAccess(int numTokens, BuilderPort port) {
			mNumTokens=numTokens;
			mPort=port;
		}
		
		public void execute(ArtTraceEvent event) {
			mPort.fire(event, mNumTokens);
		}
	}

	/**
	 * A BuilderPort corresponds to a PortInstance of the dataflow graph
	 * (thus either an output or an input port).
	 * A BuilderPort is either a producer or a consumer of an associated 
	 * FIFO on which the trace events are queued 
	 */
	protected abstract class BuilderPort {
		abstract void fire(ArtTraceEvent event, int numTokens); 
	}
	
	protected class Producer extends BuilderPort {
		BuilderToken mFifoSentinel;
		
		Producer(BuilderToken fifoSentinel) {
			mFifoSentinel=fifoSentinel;
		}
		
		void fire(ArtTraceEvent event, int numTokens) {
			BuilderToken next=new BuilderToken();
			mFifoSentinel.mProducerEvent=event;
			mFifoSentinel.mNumTokensProduced=numTokens;
			mFifoSentinel.mNext=next;
			mFifoSentinel=next;
		}
	}
	
	protected class Consumer extends BuilderPort {
		BuilderToken mHead;
		int mTokensConsumed;
		
		Consumer(BuilderToken head) {
			mHead=head;
			mTokensConsumed=0;
		}
		
		int advance() {
			assert(mHead.mNext!=null); // Otherwise, FIFO is empty (wrong firing order!)
			mHead=mHead.mNext;
			return mHead.mNumTokensProduced;
		}
		
		void fire(ArtTraceEvent event, int numTokens) {
			int tokensLeft=mHead.mNumTokensProduced-mTokensConsumed;

			if (tokensLeft==0) {
				tokensLeft=advance();
			}
			
			while (numTokens>tokensLeft) {
				// Here all remaining tokens that were produced by mHead.mEvent are consumed
				assert(mHead.mProducerEvent!=null);
				mCausationTrace.addEdge(mHead.mProducerEvent, event);
				numTokens-=tokensLeft;
				tokensLeft=advance();
			}
			
			if (numTokens>0) {
				tokensLeft-=numTokens;
				assert(mHead.mProducerEvent!=null);
				mCausationTrace.addEdge(mHead.mProducerEvent, event);
			}
			mTokensConsumed=mHead.mNumTokensProduced-tokensLeft;
		}
	}
	
	protected static class BuilderToken {
		ArtTraceEvent mProducerEvent;
		int mNumTokensProduced;
		BuilderToken mNext;		
	}	
	
	/**
	 * Models a piece of storage, which can be used and defined, causing state dependence
	 */
	protected class BuilderVariable {
		
		ArtTraceEvent mLastDef;
		
		void executeUse(ArtTraceEvent event) {
			// Insert dependence edge
			if (mLastDef!=null) {
				CausationTraceBuilder.this.mCausationTrace.addEdge(mLastDef, event);
			}
			// else: use of initial value (prior to first definition)
		}
		
		void executeDef(ArtTraceEvent event) {
			mLastDef=event;
		}
	}
}
