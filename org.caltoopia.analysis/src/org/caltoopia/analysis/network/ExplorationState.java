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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMState;
import org.caltoopia.analysis.util.collections.CartesianProduct;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.ast2ir.Stream;

/**
 * represents a state of the state-space exploration. A state is uniquely defined 
 * by a tuple of FSM states of detector actors ('detectorStatesTuple'). The tuple has exactly 
 * one state from each detector actor. All other member variables such the set of 
 * scenario graphs, the set of fsm states and the set of transition tuples can be 
 * generated from the 'detectorStatesTuple'.
 */
public class ExplorationState{
	//assigned name
	private String name;
	
	//a tuple of FSM states of detector actors, exactly one fsm state per detector
	private Map<ActorInstance, State> detectorStatesTuple = new HashMap<ActorInstance, State>();
	
	//the set of all possible transition combinations from 'detectorStatesTuple'
	private List<Set<Pair<ActorInstance, Transition>>> transitionTuples = 
			new ArrayList<Set<Pair<ActorInstance, Transition>>>();	
	
	//the set of ExplorationStates that may lead to this ExplorationState
	private Set<ExplorationState> incidentStates = new HashSet<ExplorationState>();
	
	//the set of all possible scenario graphs that can be executed at 'detectorStatesTuple'
	private Set<ScenarioGraph> scenarioGraphs = new HashSet<ScenarioGraph>();
	
	//the set of all possible fsm states that can be executed at 'detectorStatesTuple'
	private Set<ScenarioFSMState> scenarioFsmStates = new HashSet<ScenarioFSMState>();
	
	//constructor
	public ExplorationState(Map<ActorInstance, State> s, String n){
		detectorStatesTuple = s;
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public Map<ActorInstance, State> getDetectorStatesTuple(){
		return detectorStatesTuple;
	}
	
	public void setDetectorStatesTuple(Map<ActorInstance, State> n){
		detectorStatesTuple = n;
	}
	
	public List<Set<Pair<ActorInstance, Transition>>> getTransitionTuples(){
		return transitionTuples;
	}
	
	public void setTransitionTuples(List<Set<Pair<ActorInstance, Transition>>> t){
		transitionTuples = t;
	}
	
	public void addTransitionTuple(Set<Pair<ActorInstance, Transition>> t){
		transitionTuples.add(t);
	}
	
	public Set<ExplorationState> getIncidentStates(){
		return incidentStates;
	}
	
	public void setIncidentStates(Set<ExplorationState> inStates){
		incidentStates = inStates;
	}
	
	public void addIncidentState(ExplorationState incidentState){
		incidentStates.add(incidentState);
	}
	
	public void removeIncidentState(ExplorationState incidentState){
		incidentStates.remove(incidentState);
	}
	
	public Set<ScenarioGraph> getScenarioGraphs(){
		return scenarioGraphs;
	}
	
	public void setScenarioGraphs(Set<ScenarioGraph> graph){
		scenarioGraphs = graph;
	}
	
	public void addScenarioGraph(ScenarioGraph graph){
		scenarioGraphs.add(graph);
	}
	
	public Set<ScenarioFSMState> getScenarioFSMStates(){
		return scenarioFsmStates;
	}
	
	public void setScenarioFSMStates(Set<ScenarioFSMState> state){
		scenarioFsmStates = state;
	}
	
	public void addScenarioFSMState(ScenarioFSMState state){
		scenarioFsmStates.add(state);
	}
	
	/**
	 * traverses all possible transition combinations at a given detectorStatesTuple
	 * @param dStates
	 * @return the set of all possible tuples of fsm transtions of detector actors
	 */
	public static Set<Set<Pair<ActorInstance, Transition>>> traverseTransitions(Map<ActorInstance, State> dStates){
		List<Set<Pair<ActorInstance, Transition>>> transitions = new ArrayList<Set<Pair<ActorInstance, Transition>>>();
		for(Map.Entry<ActorInstance, State> e: dStates.entrySet()){
			State state = e.getValue();
			ActorInstance detector = e.getKey();
			Set<Pair<ActorInstance, Transition>> set = new HashSet<Pair<ActorInstance, Transition>>();				
			for(Transition t: state.getTransitions()){
				set.add(new Pair<ActorInstance, Transition>(detector, t));
			}
			transitions.add(set);
		}
		Set<Set<Pair<ActorInstance, Transition>>> transitionTuples = CartesianProduct.cartesianProduct(transitions);	
		return transitionTuples;
	}
	
	//TODO: use a hash implementation for faster search 
	/**
	 * searches a matching scenario graph from a given set of scenario graphs.
	 * Two scenario graphs match if they have the same set of actors and if 
	 * the scenarios of each of these actors are the same.
	 * @param scenarioGraph
	 * @param scenarioGraphs
	 * @return a matching scenario graph if it exists or null otherwise.
	 */
	public static ScenarioGraph findScenarioGraph(ScenarioGraph scenarioGraph, 
			Set<ScenarioGraph> scenarioGraphs){
		boolean exists = false;
		for(ScenarioGraph sg: scenarioGraphs){
			exists = true;
			if(sg.getActors().size() != scenarioGraph.getActors().size())
				exists = false;
				
			if(exists){
				for(ActorInstance a: sg.getActors().keySet()){
					if(!scenarioGraph.getActors().containsKey(a)){
						exists = false;
						break;
					}

					if(sg.getActors().get(a) != scenarioGraph.getActors().get(a)){						
						exists = false;
						break;
					}
				}
			}
				
			if(exists)
				return sg;			
		}
		return null;
	}
	
	//TODO: use a hash implementation for a faster search
	/**
	 * checks if recurrent state
	 * Two ExplorationStates are equivalent if they have the same detectorStatesTuple.
	 * @param vistedExplorationStates
	 * @param explorationState
	 * @return true if the ExplorationState is already visited or false otherwise.
	 */
	public static boolean isVisited(Set<Map<ActorInstance, State>> vistedExplorationStates, 
			Map<ActorInstance, State> explorationState){
		boolean visited;
		for(Map<ActorInstance, State> existingState: vistedExplorationStates){
			visited = true;
			for(ActorInstance detector: explorationState.keySet()){
				if(existingState.get(detector)!=explorationState.get(detector)){
					visited = false;
					break;
				}
			}
			if(visited)
				return true;
		}
		return false;
	}
	
	//TODO: use a hash implementation for a faster search
	/**
	 * finds the ExplorationState a transitionTuple belongs to from a given set of states.
	 * a transition belongs to exactly one ExplorationSstate by construction.
	 * hence, the first matching ExplorationState is returned.
	 * @param explorationStates
	 * @param transitionTuple
	 * @return the ExplorationState of a given transitionTuple if it exists or null otherwise.
	 */
	public static ExplorationState findExplorationStateOfTransition(Set<ExplorationState> explorationStates, 
			Set<Pair<ActorInstance, Transition>> transitionTuple){
		for(ExplorationState explorationState: explorationStates){
			for(Set<Pair<ActorInstance, Transition>> t : explorationState.getTransitionTuples()){
				if(t == transitionTuple)
					return explorationState;
			}
		}
		return null;
	}
	
	//TODO: use a hash implementation of a faster search
	/**
	 * finds the ExplorationState by its detectorStatesTuple from a given set of states.
	 * a detectorStatesTuple leads to exactly one ExplorationSstate by construction.
	 * hence, the first matching ExplorationState is returned.
	 * @param explorationStates
	 * @param detectorStatesTuple
	 * @return the ExplorationState of a given detectorStatesTuple if it exists or null otherwise.
	 */
	public static ExplorationState findVistedState(Set<ExplorationState> explorationStates, 
			Map<ActorInstance, State> detectorStatesTuple){
		for(ExplorationState explorationState: explorationStates){
			if(explorationState.getDetectorStatesTuple().size()==detectorStatesTuple.size()){
				boolean found = true;
				for(ActorInstance a: explorationState.getDetectorStatesTuple().keySet()){
					if(explorationState.getDetectorStatesTuple().get(a)!=detectorStatesTuple.get(a)){
						found = false;
						break;
					}
				}
				if(found)
					return explorationState;
			}
		}		
		return null;
	}
	
	/**
	 * prints the exploration state to a given stream
	 * @param s
	 */
	public void print(Stream s){
		s.println("ExplorationState: "+name);
		
		s.print("\tPreceeding states: ");
		for(ExplorationState statePair: incidentStates){
			s.print(statePair.name+" ");
		}
		s.println();
		s.print("\t ScenarioGraphs: ");
		for(ScenarioGraph sg: scenarioGraphs){
			s.print(sg.getName()+" ");
			sg.print(s);
		}
		s.println();			
	}
}