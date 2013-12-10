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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;

/**
 * The collection execution traces that are produced by the ACTORS run-time:
 * 1) Definition actors and actions with per-port production/consumption rates (a file called "net_trace.xml").
 * 2) One or several per-CPU traces with time stamped action firings (one or several files called "trace_[0-9].xml").
 */
public class ArtTraceBundle extends TraceBundle<ArtTraceEvent> {

	private ArtNetworkDescription mNetwork;
	private Map<ActorInstance,SimpleTrace<ArtTraceEvent>> mActorSubTraces;
	private DecorationMap<ArtTraceEvent,String> mDecorations=new DecorationMap<ArtTraceEvent,String>();
	
	public ArtTraceBundle(ArtNetworkDescription network) {
		mNetwork=network;
	}
	
	public ArtNetworkDescription getNetwork() {
		return mNetwork;
	}
	
	/**
	 * @param e
	 * @return the action associated with the given trace event
	 */
	public Action getAction(ArtTraceEvent e) {
		return mNetwork.getAction(e.getAction());
	}

	/**
	 * @param e
	 * @return the actor associated with the given trace event
	 */	
	public ActorInstance getActor(ArtTraceEvent e) {
		Action action=getAction(e);
		return (action!=null)? mNetwork.getActor(action) : null;
	}
	
	/**
	 * @param e
	 * @return the event that denotes the next firing of the (same) actor,
	 *         which associated with the given trace event, e
	 */
	public ArtTraceEvent getNextActorFiring(ArtTraceEvent e) {
		long timestamp=e.getTimeStamp();
		Trace<ArtTraceEvent> trace=getActorSubTrace(getActor(e)).tailSubTrace(timestamp);
		Iterator<ArtTraceEvent> pEvent=trace.iterator();
		while (pEvent.next()!=e) {
			// Skip any number of events with the same timestamp
			// including the event e, itself
		}
		return pEvent.hasNext()? pEvent.next() : null;
	}
	
	/**
	 * @param e
	 * @return the event that denotes the previous firing of the (same) actor,
	 *         which associated with the given trace event, e
	 */
	public ArtTraceEvent getPreviousActorFirirng(ArtTraceEvent e) {
		long timestamp=e.getTimeStamp();
		SimpleTrace<ArtTraceEvent> trace=mActorSubTraces.get(getActor(e)).headSubTrace(timestamp+1);
		// Look for the event, e, skip any number of events with the same timestamp
		int i=trace.size()-1;
		while (trace.get(i)!=e) {
			--i;
		}
		return (i>0)? trace.get(i-1) : null;
	}
	
	/**
	 * @param actor  an actor instance of the network
	 * @return the sub-set of trace events that correspond to actions of the given actor
	 */
	public Trace<ArtTraceEvent> getActorSubTrace(ActorInstance actor) {
		if (mActorSubTraces==null) {
			buildActorSubTraces();
		}
		return mActorSubTraces.get(actor);
	}
	
	@Override
	public void add(SimpleTrace<ArtTraceEvent> trace) {
		// Invalidates any actor sub-traces that have been computed
		mActorSubTraces=null;
		super.add(trace);
	}
	
	public DecorationMap<ArtTraceEvent,String> getDecorations() {
		return mDecorations;
	}
	
	private void buildActorSubTraces() {
		// Associate a list of events with each actor and use it to create the per-actor sub-trace
		Map<ActorInstance,List<ArtTraceEvent>> eventLists=new HashMap<ActorInstance,List<ArtTraceEvent>>();
		
		mActorSubTraces=new HashMap<ActorInstance,SimpleTrace<ArtTraceEvent>>();
		for (ActorInstance actor: mNetwork.getActors()) {
			List<ArtTraceEvent> events=new ArrayList<ArtTraceEvent>();
			
			eventLists.put(actor, events);
			mActorSubTraces.put(actor, new SimpleTrace<ArtTraceEvent>(events));
		}
		
		// Insert each event in the proper actor sub-trace
		for (ArtTraceEvent e: this) {
			ActorInstance actor=getActor(e);
			List<ArtTraceEvent> events=eventLists.get(actor);
			
			events.add(e);
		}
	}
}
