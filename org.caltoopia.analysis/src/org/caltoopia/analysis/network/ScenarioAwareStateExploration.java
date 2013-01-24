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

import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis.ControlToken;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis.ControlTokensPerAction;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMState;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMTransition;
import org.caltoopia.analysis.util.collections.CartesianProduct;
import org.caltoopia.analysis.util.collections.Interval;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ast2ir.Stream;

public class ScenarioAwareStateExploration {
	
	/*
	 * number of eliminated scenario graph due to duplication
	 * or non-existence
	 */	
	private int eliminatedScenarioGraphs = 0;
	
	//the scenario-aware network analysis object 
	private ScenarioAwareNetworkAnalysis analysis = null;
	
	//control tokens of the network
	private Set<ControlToken> controlTokens  = new HashSet<ControlToken>();
	
	//the scenario FSM to be constructed
	private ScenarioFSM scenarioFSM = new ScenarioFSM();	
	
	//constructor
	public ScenarioAwareStateExploration(ScenarioAwareNetworkAnalysis a){
		analysis = a;
		populateControlTokens();
	}
	
	public ScenarioAwareNetworkAnalysis getAnalysis(){
		return analysis;
	}
	
	public void setAnalysis(ScenarioAwareNetworkAnalysis a){
		analysis = a;
	}
	
	//print network control tokens
	public void printNetworkControlTokens(Stream stream){		
		for(ControlToken s : controlTokens){
			s.print(stream);
		}
	}
	
	/**
	 * searches a control token by name and detector actor 
	 * @param name - name of the control token
	 * @param detector - the detector actor instance
	 * @return the ControlToken if it exists or null otherwise.
	 */
	public ControlToken getScenarioToken(String name, ActorInstance detector){
		for(ControlToken networkScenarioToken: controlTokens){
			if(networkScenarioToken.getName().equals(name) &&
					networkScenarioToken.getDetectorActor() == detector){
				return networkScenarioToken;
			}
		}
		return null;
	}	

	/**
	 * checks if a set of ControlTokensPerAction satisfies a guard condition 
	 * of an input port instance. 
	 * @param port: the input look-ahead port
	 * @param req: the guard requirement 
	 * @param ctAction: a ControlTokensPerAction
	 * @return true if it satisfies the condition or false otherwise.
	 */
	public boolean isIntervalInActionScenarioTokens(PortInstance port,
		UnionOfDisjointIntervals req, Set<ControlTokensPerAction> ctActions){
		
		//Check if one of the ctActions satisfy the port's requirement
		for(ControlTokensPerAction ctAction: ctActions){	
			if(isIntervalInControlTokensPerAction(port, req, ctAction)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * checks if a ControlTokensPerAction satisfies a guard condition 
	 * of an input port instance. 
	 * @param port: the input look-ahead port
	 * @param req: the guard requirement 
	 * @param ctAction: a ControlTokensPerAction
	 * @return true if it satisfies the condition or false otherwise.
	 */
	public boolean isIntervalInControlTokensPerAction(PortInstance port,
			UnionOfDisjointIntervals req, ControlTokensPerAction ctAction){
		
		PortInstance producerPort = null;
		ActorInstance act = port.getActor();
		if(analysis.getScenarioAwareActorAnalysis(act).isTypeAnnotated())
			producerPort = port;
		else{
			//port is input and it has only one incident connection
			Connection c = analysis.getNetwork().getIncidentConnections(port).iterator().next();
			producerPort = c.getProducerPort();
		}
		
		
		if(ctAction.getDetectorActor()!=producerPort.getActor())
			return false;
			
		Action detectorAction = ctAction.getDetectorAction();
		PortSignature ps = detectorAction.getPortSignature();
		if(ps.getPortRate(producerPort) <= 0){
			return true;
		}
		
		//Check if one of the ControlToken tags satisfy the requirement
		for(Map.Entry<String, Long> entry:
			ctAction.getControlTokens().entrySet()){
			String n = entry.getKey();
			Long scenario = entry.getValue();
			
			//find the ContorlToken given by the name 'n'
			ControlToken ct = getScenarioToken(n, ctAction.getDetectorActor());
			
			if(ct!=null){
				if(ct.getControlPorts().contains(producerPort)){
					if(req.containsAll(scenario, scenario)){
						return true;
					}
				}
			}			
		}		
		return false;
	}

	/**
	 * checks if a combination of control tokens exist
	 * @param tr: a combination of fsm transitions
	 * @param ctActions: a combination of control tokens
	 * @return true if the combination exists or false otherwise
	 */
	public boolean controlTokensExist(Set<Pair<ActorInstance, Transition>> tr, 
			Set<ControlTokensPerAction> ctActions){	
		for(Pair<ActorInstance, Transition> transition: tr){	
			Action action = transition.getSecond().getAction();
			if(action.hasGuard()){
				Guard guard = action.getGuard();
				Map<InputLookAhead, UnionOfDisjointIntervals> modeSet 
							= guard.matchScenarioAwareGuard();
				if (modeSet == null)
					continue;			
				//each input look-ahead must be satisfied
				for(InputLookAhead lookAhead: modeSet.keySet()){
					PortInstance detectorPort = lookAhead.getPort();
					if(!isIntervalInActionScenarioTokens(detectorPort, 
							modeSet.get(lookAhead), ctActions)){
						eliminatedScenarioGraphs++;
						return false;
					}
				}				
			}
		}		
		return true;
	}
	
	/**
	 * constructs a set of scenario graphs from a given set of combinations of 
	 * control tokens. Each combination defines a unique scenario graph.
	 * @param source: the source actor to start exploring the network
	 * @param tr: a combination of fsm transitions that produce ctActions
	 * @param ctActions: a set of combinations of control tokens
	 * @return a set of ScenarioGraph, possibly empty.
	 */
	public Set<ScenarioGraph> constructScenarioGraphs(ActorInstance source, 
				Set<Pair<ActorInstance, Transition>> tr,
				Set<Set<ControlTokensPerAction>> ctActions){
		
		Set<ScenarioGraph> scenarioGraphs = new HashSet<ScenarioGraph>();
		for(Set<ControlTokensPerAction> st: ctActions){
			ScenarioGraph g = analysis.constructScenarioGraph(source, tr, st);
			if(g!=null){
				//check if it is not duplicate
				boolean exists = false;
				for(ScenarioGraph sg: scenarioGraphs){
					exists = true;
					if(sg.getActors().size() != g.getActors().size())
						exists = false;
						
					if(exists){
						for(ActorInstance a: sg.getActors().keySet()){							
							if(!g.getActors().containsKey(a)){
								exists = false;
								break;
							}
							
							if(sg.getActors().get(a) != g.getActors().get(a)){						
								exists = false;
								break;
							}
						}
					}					
					if(exists)
						break;					
				}				
				if(!exists)
					scenarioGraphs.add(g);
			}
		}
		return scenarioGraphs;
	}
	
	/**
	 * searches scenarios of an actor for a a given set of ControlTokens. 
	 * Each control token specifies the detector actor and action it is 
	 * generated from and the possible interval of values it may have. Each 
	 * scenario of the actor is tested if satisfies the control tokens.
	 * @param actor : the actor to be tested
	 * @param ctActions: a list of action control tokens
	 * @return a scenario if it exists or null otherwise.
	 */
	public ScenarioAwareActorAnalysis.Scenario getScenario(ActorInstance actor, 
			Set<ControlTokensPerAction> ctActions){
		if(!actor.hasImplementation())
			return null;
		
		//a scenario_aware_static actor has only one scenario 
		ScenarioAwareActorAnalysis aa = null;		
		aa = analysis.getScenarioAwareActorAnalysis(actor);		
		ScenarioAwareActorInstanceType type = null;		
		type = aa.getScenarioAwareActorInstanceType();		
		if(type == ScenarioAwareActorInstanceType.SA_STATIC){			
			return aa.getScenarios().get(0);
		}
		
		//If an actor is scenario_aware_dynamic, test each of its scenarios
		for(ScenarioAwareActorAnalysis.Scenario s: aa.getScenarios()){	
			boolean match = false;				
			for(Map.Entry<InputLookAhead, UnionOfDisjointIntervals> entry: 
					s.getInputLookAheadIntervals().entrySet()){
				PortInstance port = entry.getKey().getPort();
				match = false;
				for(ControlTokensPerAction ctAction: ctActions){	
					PortInstance p = null;
					ActorInstance pA = port.getActor();
					ScenarioAwareActorAnalysis pAA = null;
					pAA = analysis.getScenarioAwareActorAnalysis(pA);
					if(pAA.isTypeAnnotated()){
						p = port;
					}
					else{
						//port is input and it has only one incident connection
						Connection c = null;
						c = analysis.getNetwork().getIncidentConnections(port).iterator().next();
						p = c.getProducerPort();
					}
					
					Action action = ctAction.getDetectorAction();
					PortSignature ps = action.getPortSignature();
					if(ps.getPortRate(p) > 0){						
						if (isIntervalInControlTokensPerAction(port,
								entry.getValue(), ctAction)) {
							match = true;
							break;
						}
					}
				}
				
				if(!match)
					break;
			}

			if(match)
				return s;		
		}						
		return null;
	}
	
	/**
	 * constructs the FSM using a state-space exploration. A state of the 
	 * exploration (ExplorationState) is a map that assigns each detector
	 * one of its 'CAL FSM state'. Exploration terminates when all possible 
	 * ExplorationStates are found. Every ExplorationState may have multiple 
	 * combinations of transitions possible. Each combination of transitions 
	 * corresponds to a unique combination of actions that comprises one-action 
	 * per detector. Each combination of actions may further define multiple 
	 * combinations of control tokens. Each combination of control tokens
	 * defines exactly one scenario graph of the network. 
	 * @return
	 */
	public ScenarioFSM constructScenarioFSM(){		
		//the set of visited states
		Set<ExplorationState> visitedStates = new HashSet<ExplorationState>();
		
		//the set of detector actors
		Set<ActorInstance> dActors = analysis.getScenarioAwareDetectorActors();
		
		//a queue of combinations of transitions to be analyzed
		List<Set<Pair<ActorInstance, Transition>>> ttQueue = null; 
		ttQueue = new ArrayList<Set<Pair<ActorInstance, Transition>>>();
		
		try{
			ActorInstance source = analysis.getSourceActor();
			if(source==null){
				String message = "constructScenarioFSM: no source actor found.";
				message += "Use annotation: @ActorProperty(Source=\"true\")";
				throw new NullPointerException(message);
			}
			
			// initialStateSpaceState = the initial states of detector actors
			Map<ActorInstance, State> s0 = new HashMap<ActorInstance, State>();
			for(ActorInstance d: dActors){
				s0.put(d,d.getImplementation().getSchedule().getInitialState());
			}				
			ExplorationState state = new ExplorationState(s0, "InitialState");	
			
			//Find all possible transitions of the initialStateSpaceState
			for(Set<Pair<ActorInstance, Transition>> s: ExplorationState.traverseTransitions(s0)){
				ttQueue.add(s);
				state.getTransitionTuples().add(s);
			}
			visitedStates.add(state);			
			
			while(!ttQueue.isEmpty()){
				//take a transition from the queue
				Set<Pair<ActorInstance, Transition>> tt = ttQueue.remove(0);	
				
				//find the parent exploration state of the transition
				ExplorationState statett = ExplorationState.findExplorationStateOfTransition(visitedStates, tt);			
				if(statett==null){
					String msg = "constructScenarioFSM:transition has no state";
					throw new NullPointerException(msg);
				}
				List<Set<ControlTokensPerAction>> sTokens = new ArrayList<Set<ControlTokensPerAction>>();
				Map<ActorInstance, State> newState = new HashMap<ActorInstance, State>();
				
				//Find the set of ControlTokensPerActions produced by each detector actor
				for(Pair<ActorInstance, Transition> transition: tt){
					Set<ControlTokensPerAction> sta = analysis.getScenarioOfDetectorAction(
							transition.getFirst(), transition.getSecond().getAction());
					if(sta!=null){
						sTokens.add(sta);
					}
					newState.put(transition.getFirst(), transition.getSecond().getTargetState());	
				}			
				Set<Set<ControlTokensPerAction>> staSet = CartesianProduct.cartesianProduct(sTokens);
				Set<ScenarioGraph> sgs = constructScenarioGraphs(source, tt, staSet);				
				if(!sgs.isEmpty()){
					for(ScenarioGraph sg: sgs){
						if(ExplorationState.findScenarioGraph(sg, statett.getScenarioGraphs())==null)
							statett.getScenarioGraphs().add(sg);
						else
							eliminatedScenarioGraphs++;
					}
				}				
			
				//If a newStateSpaceState is found, put it in the list of visited states.
				ExplorationState existingState = ExplorationState.findVistedState(visitedStates, newState);				
				if(existingState==null){						
					ExplorationState newEState = new ExplorationState(newState, "EState"+visitedStates.size());
					newEState.addIncidentState(statett);					
					for(Set<Pair<ActorInstance, Transition>> s:ExplorationState.traverseTransitions(newState)){
						ttQueue.add(s);
						newEState.addTransitionTuple(s);
					}					
					visitedStates.add(newEState);
				}
				else{
					existingState.addIncidentState(statett);
				}
			}	
		
		//Remove all Exploration States which have no ScenarioGraphs
		visitedStates.removeAll(getZeroGraphStates(visitedStates));	
		
		if(visitedStates.size() == 0){
			throw new Exception("constructScenarioFSM: No valid exploration states found.");
		}
			
		//generate the FSM states
		generateFSMStates(visitedStates);		
		
		//generate the FSM transitions
		generateFSMTransitions(visitedStates);		
		
		System.out.println(scenarioFSM.getScenarioFSMStates().size() + " FSM States, " + 
							scenarioFSM.getScenarioFSMTransitions().size() + " FSM Transtions, " +
							scenarioFSM.getScenarioGraphs().size() + " scenario graphs," +
							eliminatedScenarioGraphs + " eliminated graphs");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}	
		
		return scenarioFSM;
	}
	
	/**
	 * searches states that have no ScenarioGraphs
	 * @param states: a set of ExplorationStates to be tested
	 * @return a set of ExplorationStates that have no ScenarioGraphs
	 */
	private Set<ExplorationState> getZeroGraphStates(Set<ExplorationState> states){
		Set<ExplorationState> zeroScenarioStatePairs = new HashSet<ExplorationState>();
		for(ExplorationState sp: states){
			if(sp.getScenarioGraphs().size() == 0){
				//Remove from all incidentStates list first
				for(ExplorationState spInc: states){
					if(spInc.getIncidentStates().contains(sp)){
						spInc.removeIncidentState(sp);
					}
				}
				zeroScenarioStatePairs.add(sp);
			}
		}
		return zeroScenarioStatePairs;
	}
	
	/**
	 * generates scenario FSM states from a given set of ExplorationStates
	 * @param visitedStates
	 */
	private void generateFSMStates(Set<ExplorationState> visitedStates){
		//Create FSM states and scenario graphs
		for(ExplorationState sp: visitedStates){			
			for(ScenarioGraph sg: sp.getScenarioGraphs()){				
				//if new scenario graph, add to the list
				//create new FSM state
				String name = "State_"+scenarioFSM.getScenarioFSMStates().size();
				ScenarioFSMState fsmState = scenarioFSM.new ScenarioFSMState(name);
				ScenarioGraph existingSg = null;
				existingSg = ExplorationState.findScenarioGraph(sg, scenarioFSM.getScenarioGraphs());
				if(existingSg==null){		
					String n = "ScenarioGraph_"+scenarioFSM.getScenarioGraphs().size();
					sg.setName(n);
					fsmState.setScenarioGraph(sg);
					scenarioFSM.addScenarioGraphs(sg);					
				}
				else{
					sg.setName(existingSg.getName());
					fsmState.setScenarioGraph(existingSg);
					for(Set<ControlTokensPerAction> t: sg.getControlTokens())
						existingSg.addControlToken(t);
				}

				sp.addScenarioFSMState(fsmState);
				scenarioFSM.addScenarioFSMState(fsmState);
			}
		}
		
		//create initial state with just a random scenario graph
		ScenarioFSMState spfsmState = scenarioFSM.new ScenarioFSMState("InitialState");
		spfsmState.setScenarioGraph(scenarioFSM.getScenarioGraphs().iterator().next());
		scenarioFSM.addScenarioFSMState(spfsmState);
	}
	
	/**
	 * generates scenario FSM transitions from a given set of ExplorationStates
	 * @param visitedStates
	 */
	private void generateFSMTransitions(Set<ExplorationState> visitedStates){
		//Create FSM transitions
		for(ExplorationState sp: visitedStates){
			//Add a transition from every FSM state of every incidentState to 
			//every FSM state of this state
			if(sp.getName().equals("InitialState")){			
				for(ScenarioFSMState targetFSMState: sp.getScenarioFSMStates()){
					String n = "Transition"+scenarioFSM.getScenarioFSMTransitions().size();
					ScenarioFSMTransition fsmTransition = scenarioFSM.new ScenarioFSMTransition(n);
					fsmTransition.setSourceState(scenarioFSM.getState("InitialState"));
					fsmTransition.setTargetState(targetFSMState);
					if(fsmTransition.getSourceState()==null || fsmTransition.getTargetState()==null){
						String msg = "constructScenarioFSM: transition has no source/target state.";
						throw new NullPointerException(msg);
					}
					if(!scenarioFSM.transitionExists(fsmTransition))
						scenarioFSM.addScenarioFSMTransition(fsmTransition);
				}
			}
			
			for(ExplorationState spInc: sp.getIncidentStates()){
				for(ScenarioFSMState srcFSMState: spInc.getScenarioFSMStates()){
					for(ScenarioFSMState targetFSMState: sp.getScenarioFSMStates()){
						String n = "Transition"+scenarioFSM.getScenarioFSMTransitions().size();								
						ScenarioFSMTransition fsmTransition = scenarioFSM.new ScenarioFSMTransition(n);
						fsmTransition.setSourceState(srcFSMState);
						fsmTransition.setTargetState(targetFSMState);
						if(!scenarioFSM.transitionExists(fsmTransition))
							scenarioFSM.addScenarioFSMTransition(fsmTransition);
					}
				}
			}			
		}			
	}
	
	/**
	 * populates the control tokens of the network. Each control token has a name, 
	 * a parent detector actor and a list of (broadcast) control ports. Hence, all
	 * control ports under this control name always relay the same token value. 
	 */
	private void populateControlTokens(){
		//For each Detector, construct scenario tokens
		for(ActorInstance actor: analysis.getNetwork().getActors()){
			if(actor.hasImplementation()){
				ScenarioAwareActorAnalysis sa = analysis.getScenarioAwareActorAnalysis(actor);
				if(sa.getScenarioAwareActorInstanceType()==
						ScenarioAwareActorInstanceType.SA_DETECTOR){
					boolean hasAnnotation = false;
					try{
						//if it has ScenarioToken annotated types, parse
						if(actor.hasAnnotation("ActorProperty")){
							if (actor.getAnnotationArgumentValue("ActorProperty", "ScenarioTokens")!=null){
								String s = actor.getAnnotationArgumentValue("ActorProperty", "ScenarioTokens");
								hasAnnotation = true;
								for(String t: s.split(";")){
									String st[] = t.split(":");
									ControlToken controlToken = analysis.new ControlToken(actor, st[0].trim());	
									//Add common ports
									for(String p: st[1].split(",")){
										PortInstance pi = actor.getPort(p.trim());
										if(pi==null){
											String msg = "Scenario tag error. Actor '";
											msg += actor.getInstanceName()+ "' does not have port " + p.trim();
											throw new NullPointerException(msg);
										}
										controlToken.addControlPort(pi);
									}
									
									controlTokens.add(controlToken);
								}
							}
						}
						//Otherwise, assume all output ports belong to the same token type					
						if(!hasAnnotation){
							ControlToken controlToken = analysis.new ControlToken(actor, "default");	
							for(PortInstance pi: actor.getOutputPorts()){
								controlToken.addControlPort(pi);
							}
							controlTokens.add(controlToken);
						}	
					}catch(Exception e){
						System.out.println(e.getMessage());
						System.exit(1);
					}
				}
			}
		}
	}
}
