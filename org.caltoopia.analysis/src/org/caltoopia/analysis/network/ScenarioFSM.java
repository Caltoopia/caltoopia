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
package org.caltoopia.analysis.network;

import java.util.HashSet;
import java.util.Set;

import org.caltoopia.analysis.network.ScenarioGraph;

public class ScenarioFSM {
	/**
	 * encapsulates a scenario FSM state 
	 */
	public class ScenarioFSMState{
		private ScenarioGraph scenarioGraph = null;
		
		private String name;
		
		public ScenarioFSMState(String n){
			name = n;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String n){
			name = n;
		}
		
		public ScenarioGraph getScenarioGraph(){
			return scenarioGraph;
		}
		
		public void setScenarioGraph(ScenarioGraph sg){
			scenarioGraph = sg;
		}
	}
	
	/**
	 * encapsulates a scenario FSM transition 
	 */
	public class ScenarioFSMTransition{
		private ScenarioFSMState sourceState;
		
		private ScenarioFSMState targetState;
		
		private String name;
		
		public ScenarioFSMTransition(String n){
			name = n;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String n){
			name = n;
		}
		
		public ScenarioFSMState getSourceState(){
			return sourceState;
		}
		
		public ScenarioFSMState getTargetState(){
			return targetState;
		}
		
		public void setSourceState(ScenarioFSMState source){
			sourceState = source;
		}
		
		public void setTargetState(ScenarioFSMState target){
			targetState = target;
		}
		
	}

	//the set of scenario fsm states
	private Set<ScenarioFSMState> fsmStates = new HashSet<ScenarioFSMState>();
	
	//the set of scenario fsm transitions
	private Set<ScenarioFSMTransition> fsmTransitions = new HashSet<ScenarioFSMTransition>();
	
	//the set of scenario graphs
	private Set<ScenarioGraph> scenarioGraphs = new HashSet<ScenarioGraph>();
	
	public void setScenarioFSMStates(Set<ScenarioFSMState> states){
		fsmStates = states;
	}
	
	public void setScenarioFSMTransitions(Set<ScenarioFSMTransition> transitions){
		fsmTransitions = transitions;
	}
	
	public void setScenarioScenarioGraphs(Set<ScenarioGraph> graphs){
		scenarioGraphs = graphs;
	}
	
	public Set<ScenarioFSMState> getScenarioFSMStates(){
		return fsmStates;
	}
	
	public Set<ScenarioFSMTransition>  getScenarioFSMTransitions(){
		return fsmTransitions;
	}
	
	public Set<ScenarioGraph> getScenarioGraphs(){
		return scenarioGraphs;
	}
	
	public void addScenarioFSMState(ScenarioFSMState state){
		fsmStates.add(state);
	}
	
	public void addScenarioFSMTransition(ScenarioFSMTransition transition){
		fsmTransitions.add(transition);
	}
	
	public void addScenarioGraph(ScenarioGraph graph){
		scenarioGraphs.add(graph);
	}
	
	/**
	 * checks if a given transition already exists in the FSM
	 * @param transition
	 * @return true if the FSM transition exists or false otherwise.
	 */
	public boolean transitionExists(ScenarioFSMTransition transition){
		for(ScenarioFSMTransition t: fsmTransitions){
			if(t.sourceState==transition.sourceState && 
					t.targetState == transition.targetState)
				return true;
		}
		return false;
	}
	
	/**
	 * gets a FSM state of a given name
	 * @param name
	 * @return a state if it exists or null otherwise.
	 */
	public ScenarioFSMState getState(String name){
		for(ScenarioFSMState fsmState: fsmStates){
			if(fsmState.name.equals(name))
				return fsmState;
		}
		return null;
	}
	
	/**
	 * gets the set of outgoing transitions of a given state
	 * @param state
	 * @return the set of outgoing transitions of a given state. The set can be empty.
	 */
	public Set<ScenarioFSMTransition> getOutgoingTransitions(ScenarioFSMState state){
		Set<ScenarioFSMTransition> outgoingTransitions = new HashSet<ScenarioFSMTransition>();
		for(ScenarioFSMTransition fsmTransition: fsmTransitions){
			if(fsmTransition.sourceState.equals(state))
				outgoingTransitions.add(fsmTransition);
		}
		return outgoingTransitions;
	}
	
	/**
	 * gets the set of incoming transitions of a given state
	 * @param state
	 * @return the set of incoming transitions of a given state. The set can be empty.
	 */
	public Set<ScenarioFSMTransition> getIncomingTransitions(ScenarioFSMState state){
		Set<ScenarioFSMTransition> inComingTransitions = new HashSet<ScenarioFSMTransition>();
		for(ScenarioFSMTransition fsmTransition: fsmTransitions){
			if(fsmTransition.targetState.equals(state))
				inComingTransitions.add(fsmTransition);
		}
		return inComingTransitions;
	}
	
	//TODO: it is assumed that we have one state per scenario graph. This method 
	// needs to revisited if this assumption no more holds. 
	/**
	 * gets a FSM state which is annotated with a given scenario graph 
	 * @param scenarioGraph
	 * @return a state if it is annotated with the given scenario graph or null otherwise.
	 */
	public ScenarioFSMState getState(ScenarioGraph scenarioGraph){
		for(ScenarioFSMState fsmState: fsmStates){
			if(fsmState.scenarioGraph!=null)
				if(scenarioGraph.getName().equals(fsmState.scenarioGraph.getName()))
					return fsmState;
		}
		return null;
	}
	
}
