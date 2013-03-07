/*  
 * Copyright (c) 2012, Ericsson AB
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the Ericsson AB nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package org.caltoopia.analysis.actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.ActorSchedule;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.PriorityRelation;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;

/**
 * This is a low-complexity/low-precision actor classifier 
 * (sacrificing precision for simplicity and fast execution). As always, misclassification is on the "safe" side:
 * a) Indicating possible termination for actors that run indefinitely
 * b) Indicating non-deterministic/underspecified action selection for actors that are determinate.
 * c) Indicating timing dependence for actors that are not dependent on input arrival times. 
 * d) Indicating the need of dynamic (run-time) scheduling decisions for actors that can be scheduled statically.
 *
 * Simple tests are used to produce the classification. In particular, the value of guard expressions
 * are not analyzed (correlation, reachability in state space etc).
 * 1) Termination is assumed unless every state of the FSM has a unguarded/catch-all action
 *    (thus, if firing is subject only to input availability, it's found to "execute indefinitely").
 * 2) Non-deterministic action selection assumed unless the eligible actions of every FSM state
 *    are totally ordered by the priority relation.
 * 3) Timing dependence is assumed if there is a pair of eligible actions such that one of them
 *    consumes less input from some port, but has not higher priority than the other action.
 * 4) Dynamic scheduling is assumed to be required unless, each FSM state has a unique transition
 *    in terms of consumption/production rates (or no transition in the case of termination). 
 */

public class SneakyActorAnalyzer implements ActorAnalyzer {

	@Override
	public ActorAnalysis analyze(ActorImplementation actor) {
		return analyze(actor,actor.getSchedule());	
	}
	
	/**
	 * @param actor     actor to be classified
	 * @param schedule  schedule (FSM)
	 * @return analysis of the actor
	 * 
	 * This variant of the analyze method allows for a transformed schedule to be used
	 * (rather than the schedule that is originally specified for the actor)
	 */
	public ActorAnalysis analyze(ActorInstance actor, ActorSchedule schedule) {
		SneakyAnalysis result=new SneakyAnalysis(actor,schedule);
		result.classify();
		return result;
	}
}

class SneakyAnalysis implements ActorAnalysis {

	private ActorInstance mActor;
	private ActorSchedule mSchedule;
	private StaticActorSchedule mStaticSchedule;
	private List<State> mPossiblyTerminalStates=new ArrayList<State>();
	private List<State> mPossiblyTimingDependentStates=new ArrayList<State>();
	private List<State> mPossiblyNonDeterminateStates=new ArrayList<State>();
	private List<State> mPossiblyInputDependentStates=new ArrayList<State>();

	SneakyAnalysis(ActorInstance actor, ActorSchedule schedule) {
		mActor=actor;
		mSchedule=schedule;		
	}

	@Override
	public boolean executesIndefinitely() {
		return mPossiblyTerminalStates.isEmpty();
	}

	@Override
	public List<State> getPotentiallyTerminalStates() {
		return mPossiblyTerminalStates;
	}

	@Override
	public boolean isTimingIndependent() {
		return mPossiblyTimingDependentStates.isEmpty();
	}

	@Override
	public List<State> getPotentiallyTimingDependentStates() {
		return mPossiblyTimingDependentStates;
	}


	@Override
	public boolean hasDeterminateRates() {
		return mPossiblyNonDeterminateStates.isEmpty();
	}

	@Override
	public List<State> getPotentiallyNonDeterminateStates() {
		return mPossiblyNonDeterminateStates;
	}

	@Override
	public boolean hasStaticSchedule() {
		return mStaticSchedule!=null;
	}

	@Override
	public StaticActorSchedule getStaticSchedule() {
		return mStaticSchedule;
	}

	@Override
	public Collection<State> getPotentiallyInputDependentStates() {
		return mPossiblyInputDependentStates;
	}

	@Override
	public ActorSchedule getSchedule() {
		return mSchedule;
	}

	/**
	 * Fills in the lists of possibly terminal, input-dependent, timing-dependent and nondeterminate states. 
	 */
	protected void classify() {	
		// Iterate over each state and check the various properties
		for (State state: mSchedule.getStates()) {
			boolean dynamicInThisState=false;
			
			// (a) Termination
			if (!hasCatchAll(state)) {
				// Unless there is a catch-all [else] transition
				// We assume that the actor may terminate (we're not looking at the actual conditions)
				mPossiblyTerminalStates.add(state);
				if (state.getTransitions().isEmpty()==false) {
					// State with both transitions and possible termination
					dynamicInThisState=true;
				}
			}

			// (b) Dynamic decisions
			if (dynamicInThisState || state.getTransitions().size()>1) {
				mPossiblyInputDependentStates.add(state);

				// (c) Timing dependence
				if (findTimingDependence(state)) {
					mPossiblyTimingDependentStates.add(state);
				} 
				
				// (d) Non-determinate rates 
				if (findNonDeterminism(state)) {
					mPossiblyNonDeterminateStates.add(state);
				}
			}
			// else: if no dynamic decision, there's neither timing dependence nor non-determinism			
		}
		
		// Try to find static schedule
		mStaticSchedule=findStaticSchedule();
	}

	private boolean hasCatchAll(State state) {
		for (Transition transition: state.getTransitions()) {
			if (transition.hasGuard()==false) {
				return true;  // Unguarded transition found
			}
		}
		return false; // All transitions guarded
	}


	/**
	 * @param t a transition
	 * @return the input rates (PortSignature of input ports only)
	 */
	private PortSignature getInputRates(Transition t) {
		return t.getAction().getPortSignature().project(mActor.getInputPorts());
	}
	
	private boolean findTimingDependence(State state) { 
		PriorityRelation priority=state.getPriorityRelation();
		
		for (Transition t1: state.getTransitions()) {
			PortSignature inputs1=getInputRates(t1);
			
			for (Transition t2: state.getTransitions()) {
				if (t1!=t2) {
					PortSignature inputs2=getInputRates(t2);
				
					if (priority.isAncestorOf(t1, t2)) {
						// t1 has higher priority than t2
						
						if (hasPortWithHigherRate(inputs1,inputs2)) {
							// t1 requires more input (on some port) than t2
							// this means checking for absence of input: potential timing-dependence
							return true;
						}
					}
					else if (!priority.isAncestorOf(t2, t1) && !inputs1.equals(inputs2)) {
						// t1 and t2 are unordered and differ in input rates
						// this means checking for absence of input: potential timing-dependence
						return true;
					}
					// else: t2 has higher priority (will be taken care of by first "then" clause)
				}
			}
		}
	
		// not timing dependent
		return false;
	}

	/**
	 * @param s1 a PortSignature
	 * @param s2 a PortSignature
	 * @return true iff the is a port, p, such that s1 has a greater rate w.r.t. p than s2.  
	 */
	private boolean hasPortWithHigherRate(PortSignature s1, PortSignature s2) {
		for (PortInstance port: s1.getPorts()) {
			if (s1.getPortRate(port) > s2.getPortRate(port)) {
				return true;
			}
		}
		return false; // No port with higher rate than in s1
	}
	
	private boolean findNonDeterminism(State state) {
		PriorityRelation priority=state.getPriorityRelation();
		
		for (Transition t: state.getTransitions()) {
			if (!priority.getUnordered(t).isEmpty()) {
				return true;
			}
		}
		return false;
	}


	 /** Identifies a StaticActorSchedule by following the Transitions of the FSM.
	 * Identifies a StaticActorSchedule by following the Transitions of the FSM.
	 * Eventually we will either:
	 * a) Find a state with multiple Transitions, in which case the verdict is "dynamic"
	 * b) Reach a terminal state with no successor, in which case a finite static schedule has been found
	 * c) Reach a state that has already been visited, in which case a repeated static schedule has been found.
	 * 
	 * @return a StaticActorSchedule or null if none was found 
	 */
	private StaticActorSchedule findStaticSchedule() {
		if (!mPossiblyInputDependentStates.isEmpty()) {
			// Input dependence means that there are state with multiple successors
			return null;
		}

		Map<State,Integer> phaseMap=new HashMap<State,Integer>();
		List<StaticFiringSequence> phaseSequence=new ArrayList<StaticFiringSequence>();
		State state=mSchedule.getInitialState();

		// iterate over states until the terminal state is found or we loop back to a previous state
		while (!mPossiblyTerminalStates.contains(state) && !phaseMap.containsKey(state)) {
			// Check that the successor is unique
			if (state.getTransitions().size()!=1) {
				return null;
			}
			
			// Determine the pair <PortSignature,next State> that signifies the static transition
			Transition t=state.getTransitions().iterator().next();
			phaseMap.put(state, phaseSequence.size());
			phaseSequence.add(new TrivialFiringSequence(t.getAction()));
			state=t.getTargetState();
		}
			
		// Split the phaseSequence into:
		// initial sequence [0,startRepeat) and
		// repeatedSequence [startRepeat,length)
		int startRepeat=(mPossiblyTerminalStates.contains(state))?
		  	            phaseSequence.size()   // Initial sequence only/no repeated sequence
		  	            : phaseMap.get(state); // Repeated sequence (possibly also an initial sequence)
			
		StaticFiringSequence initialSequence=createFiringSequence(phaseSequence,0,startRepeat);
		StaticFiringSequence repeatedSequence=createFiringSequence(phaseSequence,startRepeat,phaseSequence.size());
		return new StaticActorSchedule(initialSequence,repeatedSequence);
	}

	private StaticFiringSequence createFiringSequence(List<StaticFiringSequence> subSequences, int from, int to) {
		if (from<to) {
			if (from+1==to) {
				return subSequences.get(from); // Single element
			}
			else {
				// Sequence [from,to) of length at least 2
				return new LoopedFiringSequence(1,subSequences.subList(from, to));
			}
		}
		else
			return null; // empty/no sequence
	}

// TODO: Move the part that merges transitions into a separate utility, which transforms ActorImplementations
//
//	private ConflictingTransitions<OriginalState> findDynamicDecisions(OriginalState state) {
//		//
//		// Partition the transitions after PortSignature and next State
//		// (transitions considered equivalent if PortSignature and State are identical)
//		//
//		Map<Pair<PortSignature,OriginalState>,Set<Action>> partition=
//			new LinkedHashMap<Pair<PortSignature,OriginalState>,Set<Action>>();
//
//		for (OriginalTransition transition: mFsm.getIncidentFrom(state)) {
//			Action action=transition.getAction();
//			OriginalState nextState=transition.getTerminus();
//			Pair<PortSignature,OriginalState> key=Pair.create(action.getPortSignature(),nextState);
//			Set<Action> equTransitions=partition.get(key);
//
//			if (equTransitions==null) {
//				// No transition (yet) with this key, create an entry
//				equTransitions=new LinkedHashSet<Action>();
//				partition.put(key, equTransitions);
//			}
//			equTransitions.add(action);
//		}
//
//		// Add all conflicting transitions
//		ConflictingTransitions<OriginalState> dynamicDecisions=new ConflictingTransitions<OriginalState>(mActor,state);
//		if (partition.size()>1) {
//			for (Set<Action> equTransitions1: partition.values()) {
//				// Determine the transitions, which are in conflict with the transitions in equTransitions1 
//				Set<Action> conflictingActions=new LinkedHashSet<Action>();
//
//				for (Set<Action> equTransitions2: partition.values()) {
//					if (equTransitions1!=equTransitions2) {
//						conflictingActions.addAll(equTransitions2);
//					}
//				}
//
//				for (Action action: equTransitions1) {
//					dynamicDecisions.setConflictingActions(action,conflictingActions);
//				}
//			}
//		}
//		return dynamicDecisions;
//	}
//
//
//	private ConflictingTransitions<OriginalState> findNonDeterminateRates(OriginalState state, ConflictingTransitions<OriginalState> dynamicDecisions) {
//		ConflictingTransitions<OriginalState> nonDeterminism=new ConflictingTransitions<OriginalState>(mActor,state);
//		PriorityRelation priority=mActor.getPriorityRelation();
//
//		for (Action action1: dynamicDecisions.getActionsWithConflict()) {
//			Set<Action> conflicts=new LinkedHashSet<Action>();
//			for (Action action2: dynamicDecisions.getConflictingActions(action1)) {
//
//				if (priority.areUnordered(action1, action2)) {
//					conflicts.add(action2);
//				}
//			}
//
//			if (conflicts.isEmpty()==false) {
//				nonDeterminism.setConflictingActions(action1,conflicts);
//			}
//		}
//
//		return nonDeterminism;
//	}
}
