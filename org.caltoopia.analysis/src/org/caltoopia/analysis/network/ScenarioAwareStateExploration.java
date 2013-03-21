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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.actor.GenericActorAnalysis.ActorInstanceType;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.analysis.network.ControlToken;
import org.caltoopia.analysis.network.ControlTokensPerAction;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMState;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMTransition;
import org.caltoopia.analysis.util.collections.CartesianProduct;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ast2ir.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScenarioAwareStateExploration {
	
	public class FiringNode{
		public String name;
		public ActorInstance detectorActor = null;
		public Action firing = null;
		public Map<String, UnionOfDisjointIntervals> controlTokens = 
				new HashMap<String, UnionOfDisjointIntervals>();
		public Set<String> outGoingTransitions = new HashSet<String>();
		public FiringNode(String n, ActorInstance d){
			name = n;
			detectorActor = d;
		}
		
		public void print(PrintStream stream){
			stream.println("\t\tState: "+name);
			for(Map.Entry<String, UnionOfDisjointIntervals> e: 
				controlTokens.entrySet()){
				stream.println("\t\t\tControlToken: "+
						e.getKey()+", Value: "+e.getValue().toString());
			}
			
			for(String s: outGoingTransitions){
				stream.println("\t\t\tTransits to: "+s);
			}
		}
	}
	
	/**
	 * control token transition
	 */
	public class FiringSchedule{		
		public ActorInstance detectorActor;
		public String initialFiring;
		public Set<FiringNode> firings = new HashSet<FiringNode>();
		
		public FiringSchedule(ActorInstance i){
			detectorActor = i;
		}
		
		public FiringNode getFiring(String n){
			for(FiringNode s: firings){
				if(s.name.equals(n))
					return s;
			}
			return null;
		}
		
		public FiringNode getInitialFiring(){
			return getFiring(initialFiring);
		}
		
		public void print(PrintStream stream){
			stream.println("Detector: "+detectorActor.getInstanceName());
			stream.println("\tIntialFiring: "+initialFiring);
			for(FiringNode s: firings){
				s.print(stream);
			}
		}
	}
	
	/**
	 * control token transition
	 */
	public class ControlTokenTransition{
		
		private Pair<String, Long> srcControlToken;
		
		private Pair<String, Long> dstControlToken;
		
		public ControlTokenTransition(String ss, Long sl, String ds, Long dl){
			setSrcControlToken(ss, sl);
			setDstControlToken(ds, dl);
		}
		
		private void setSrcControlToken(String s, Long l){
			srcControlToken = new Pair<String, Long>(s,l);
		}
		
		private void setDstControlToken(String s, Long l){
			dstControlToken = new Pair<String, Long>(s,l);
		}
		
		public Pair<String, Long> getSrcControlToken(){
			return srcControlToken;
		}
		
		public Pair<String, Long> getDstControlToken(){
			return dstControlToken;
		}
		
		public boolean equals(ControlTokenTransition t){
			if(!getSrcControlToken().equals(t.getSrcControlToken()))
				return false;
			if(!getDstControlToken().equals(t.getDstControlToken()))
				return false;
			return true;
		}
	}
	
	/*
	 * number of eliminated scenario graph due to duplication
	 * or non-existence
	 */	
	private int numberOfEliminatedScenarioGraphs = 0;
	
	//the scenario-aware network analysis object 
	private ScenarioAwareNetworkAnalysis analysis = null;
	
	//control tokens of the network
	private Set<ControlToken> controlTokens  = new HashSet<ControlToken>();
	
	//detector fsm
	private List<FiringSchedule> firingSchedules = new ArrayList<FiringSchedule>();
	
	//non-existing control token transitions
	private List<ControlTokenTransition> nonExistingTokenTransitions =
			new ArrayList<ControlTokenTransition>();
	
	//the scenario FSM to be constructed
	private ScenarioFSM scenarioFSM = new ScenarioFSM();	
	
	//constructor
	public ScenarioAwareStateExploration(ScenarioAwareNetworkAnalysis a){
		analysis = a;
		populateControlTokens();
		populateDetectorFSMs();
	}
	
	public ScenarioAwareNetworkAnalysis getAnalysis(){
		return analysis;
	}
	
	public void setAnalysis(ScenarioAwareNetworkAnalysis a){
		analysis = a;
	}
	
	public ScenarioFSM getScenarioFSM(){
		return scenarioFSM;
	}
	
	public int getNumberOfEliminatedScenarioGraphs(){
		return numberOfEliminatedScenarioGraphs;
	}
	
	//print network control tokens
	public void printNetworkControlTokens(Stream stream){		
		for(ControlToken s : controlTokens){
			s.print(stream);
		}
	}
	
	//print network control tokens
	public void printDetectorFSMs(PrintStream stream){		
		for(FiringSchedule d : firingSchedules){
			d.print(stream);
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
	public boolean controlTokensExist(Set<FiringNode> configuration, 
			Set<ControlTokensPerAction> ctActions){	
		for(FiringNode firingNode: configuration){	
			Action action = firingNode.firing;
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
						numberOfEliminatedScenarioGraphs++;
						return false;
					}
				}				
			}
		}		
		return true;
	}
	
	
	/**
	 * returns the all possible tuples of control tokens of a configuration
	 * @param configuration: a tuple of firings
	 */
	public Set<Set<ControlTokensPerAction>> getTuplesOfControlTokens(Set<FiringNode> configuration){
		String msg = "";
		//a list of tuples of control tokens
		List<Set<ControlTokensPerAction>> listOfControlTokens = new ArrayList<Set<ControlTokensPerAction>>();	
		Set<Set<ControlTokensPerAction>> tuplesOfConfiguration = new HashSet<Set<ControlTokensPerAction>>();
		try{
			for(FiringNode firingNode: configuration){				
				//set of tuples of control tokens of one firing
				Set<ControlTokensPerAction> controlTokensOfFiring = new HashSet<ControlTokensPerAction>();
				
				Map<String, UnionOfDisjointIntervals> controlTokens = firingNode.controlTokens;
				List<Set<Pair<String, Long>>> tokenRanges = new ArrayList<Set<Pair<String, Long>>>();
				for(Map.Entry<String,UnionOfDisjointIntervals> e: controlTokens.entrySet()){					
					Set<Pair<String, Long>> tokenRange = new HashSet<Pair<String, Long>>();
					for(Long scenario: e.getValue().asSet()){
						tokenRange.add(new Pair<String, Long>(e.getKey().trim(), scenario));
					}
					tokenRanges.add(tokenRange);
				}						
						
				for(Set<Pair<String, Long>> str: CartesianProduct.cartesianProduct(tokenRanges)){
					ControlTokensPerAction sta = new ControlTokensPerAction(firingNode.detectorActor, firingNode.firing);
					for(Pair<String, Long> e: str){
						if(sta.getControlTokens().containsKey(e.getFirst())){
							msg = "nonExistingConfiguration: ";
							msg += "duplicate entry of a control token in ControlTokensPerAction detected.";
							throw new Exception( msg );
						}
						sta.addControlToken(e.getFirst(),e.getSecond());
					}
					controlTokensOfFiring.add(sta);
				}
				if(controlTokensOfFiring.size()>0)
					listOfControlTokens.add(controlTokensOfFiring);
			}	
			tuplesOfConfiguration = CartesianProduct.cartesianProduct(listOfControlTokens);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		return tuplesOfConfiguration;
	}
	
	/**
	 * checks if a configuration exists
	 * @param configuration: a tuple of firings
	 * @return true if the configuration exists or false otherwise
	 */
	public boolean isDetectorFiringEnabled(FiringNode firingNode, Set<FiringNode> configuration){			
		Set<Set<ControlTokensPerAction>> tuplesOfControlTokens = getTuplesOfControlTokens(configuration);		
		Action action = firingNode.firing;
		if(action.hasGuard()){
			Guard guard = action.getGuard();
			Map<InputLookAhead, UnionOfDisjointIntervals> modeSet 
						= guard.matchScenarioAwareGuard();
			// If an action does not have a scenario-aware guard, skip it.
			// The rationale is the following. If a firing does not have,
			// a scenario-aware guard, then it is not driven by another detector actor.
			// Thus, the firing is independent and for the sake of conservativeness,
			// it is assumed 'active'. If the firing has a scenario-aware guard, then
			// it will be checked later if it is driven by a detector actor.
			if (modeSet == null){
				return true;		
			}
			
			//Otherwise, each input look-ahead of the guard must be satisfied
			for(InputLookAhead lookAhead: modeSet.keySet()){
				PortInstance detectorPort = lookAhead.getPort();
				PortInstance producerPort = null;
				ActorInstance act = detectorPort.getActor();
				if(analysis.getScenarioAwareActorAnalysis(act).isTypeAnnotated())
					producerPort = detectorPort;
				else{
					//port is input and it has only one incident connection
					Connection c = analysis.getNetwork().getIncidentConnections(detectorPort).iterator().next();
					producerPort = c.getProducerPort();
				}				
				boolean exists = false;
				
				for(Set<ControlTokensPerAction> ctTuple : tuplesOfControlTokens){
					//Check if one of the ctActions satisfy the port's requirement
					for(ControlTokensPerAction ctAction: ctTuple){							
						if(ctAction.getDetectorActor()!=producerPort.getActor())
							continue;
							
						Action detectorAction = ctAction.getDetectorAction();
						PortSignature ps = detectorAction.getPortSignature();
						if(ps.getPortRate(producerPort) > 0)
							exists = true;
						break;						
					}
					
					if(exists)
						break;
				}
				if(!exists)
					return false;
			}				
		}
				
		return true;
	}
	
	/**
	 * checks if a configuration exists
	 * @param configuration: a tuple of firings
	 * @return true if the configuration exists or false otherwise
	 */
	public boolean existentConfiguration(Set<FiringNode> configuration){			
		Set<Set<ControlTokensPerAction>> tuplesOfControlTokens = getTuplesOfControlTokens(configuration);		
		for(FiringNode firingNode: configuration){	
			Action action = firingNode.firing;
			if(action.hasGuard()){
				Guard guard = action.getGuard();
				Map<InputLookAhead, UnionOfDisjointIntervals> modeSet 
							= guard.matchScenarioAwareGuard();
				// If an action does not have a scenario-aware guard, skip it.
				// The rationale is the following. If a firing does not have,
				// a scenario-aware guard, then it is not driven by another detector actor.
				// Thus, the firing is independent and for the sake of conservativeness,
				// it is assumed 'active'. If the firing has a scenario-aware guard, then
				// it will be checked later if it is driven by a detector actor.
				if (modeSet == null){
					continue;		
				}
				
				//Otherwise, each input look-ahead of the guard must be satisfied
				for(InputLookAhead lookAhead: modeSet.keySet()){
					PortInstance detectorPort = lookAhead.getPort();
					boolean exists = false;
					//at least one of the tupelOfControlTokens should exist
					for(Set<ControlTokensPerAction> ctTuple : tuplesOfControlTokens){
						if(isIntervalInActionScenarioTokens(detectorPort, 
								modeSet.get(lookAhead), ctTuple)){											
							exists = true;
							break;
						}						
					}
					if(!exists){
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
	public Set<ScenarioGraph> constructScenarioGraphs(List<ActorInstance> sources, 
			    Set<FiringNode> configuration,
				Set<Set<ControlTokensPerAction>> ctActions){
		
		Set<ScenarioGraph> scenarioGraphs = new HashSet<ScenarioGraph>();
		for(Set<ControlTokensPerAction> st: ctActions){
			ScenarioGraph g = analysis.constructScenarioGraph(sources, configuration, st);
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
		
		if(type == ScenarioAwareActorInstanceType.SA_DETECTOR){	
			//check if the detector actor is static
			 if(aa.getActorInstanceType()==ActorInstanceType.SINGLE_RATE_STATIC ||
						aa.getActorInstanceType()==ActorInstanceType.MULTI_RATE_STATIC){
				 return aa.getScenarios().get(0);
			 }
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
	private void generateCompleteFSMStates(Set<ExplorationState> visitedStates){
		String str;
		//Create FSM states and scenario graphs
		for(ExplorationState eState: visitedStates){			
			for(ScenarioGraph sGraph: eState.getScenarioGraphs()){
				//create new FSM state
				str = "State_"+Integer.toString(scenarioFSM.getScenarioFSMStates().size());
				ScenarioFSMState fsmState = scenarioFSM.new ScenarioFSMState(str);
				ScenarioGraph existingSGraph = null;
				existingSGraph = ExplorationState.findScenarioGraph(sGraph, 
						                  scenarioFSM.getScenarioGraphs(), false);				
				//if new scenario graph, add to the list
				if(existingSGraph==null){		
					//str = "ScenarioGraph_"+scenarioFSM.getScenarioGraphs().size();
					str = sGraph.getControlTokensAsString();
					sGraph.setName(str);
					fsmState.setScenarioGraph(sGraph);
					scenarioFSM.addScenarioGraph(sGraph);					
				}
				else{
					//change the name of the g
					sGraph.setName(existingSGraph.getName());
					fsmState.setScenarioGraph(existingSGraph);
				}

				eState.addScenarioFSMState(fsmState);
				scenarioFSM.addScenarioFSMState(fsmState);
			}
		}
		
		//create initial state with just a random scenario graph (for the time-being)
		ScenarioFSMState initialFsmState = scenarioFSM.new ScenarioFSMState("InitialState");
		initialFsmState.setScenarioGraph(scenarioFSM.getScenarioGraphs().iterator().next());
		scenarioFSM.addScenarioFSMState(initialFsmState);
	}
	
	/**
	 * generates scenario FSM states from a given set of ExplorationStates
	 * @param visitedStates
	 */
	private void generateConservativeFSMStates(Set<ExplorationState> visitedStates){
		String str;
		//Create FSM states and scenario graphs
		for(ExplorationState eState: visitedStates){			
			for(ScenarioGraph sGraph: eState.getScenarioGraphs()){
				//create new FSM state
				str = eState.getName();				
				ScenarioGraph existingSGraph = null;
				existingSGraph = ExplorationState.findScenarioGraph(sGraph, 
						              scenarioFSM.getScenarioGraphs(), false);				
				//if new scenario graph, add to the list
				if(existingSGraph==null){	
					str = "State_"+Integer.toString(scenarioFSM.getScenarioFSMStates().size());
					ScenarioFSMState fsmState = scenarioFSM.new ScenarioFSMState(str);
					str = sGraph.getControlTokensAsString();
					sGraph.setName(str);
					fsmState.setScenarioGraph(sGraph);
					scenarioFSM.addScenarioGraph(sGraph);
					eState.addScenarioFSMState(fsmState);
					scenarioFSM.addScenarioFSMState(fsmState);
				}
				else{
					//change the name of the g
					sGraph.setName(existingSGraph.getName());		
					ScenarioFSMState fsmState = scenarioFSM.getState(existingSGraph);
					eState.addScenarioFSMState(fsmState);
				}				
			}
		}
		
//		//create initial state with just a random scenario graph (for the time-being)
		ScenarioFSMState initialFsmState = scenarioFSM.new ScenarioFSMState("InitialState");
		initialFsmState.setScenarioGraph(scenarioFSM.getScenarioGraphs().iterator().next());
		scenarioFSM.addScenarioFSMState(initialFsmState);
	}
	
	/**
	 * generates scenario FSM transitions from a given set of ExplorationStates
	 * @param visitedStates
	 */
	private void generateFSMTransitions(Set<ExplorationState> visitedStates){
		String str;
		//Create FSM transitions
		for(ExplorationState eState: visitedStates){
			
			//connect the initial state of the scenario FSM with
			//every FSM state of the initial exploration state
			if(eState.getName().equals("InitialState")){			
				for(ScenarioFSMState targetFSMState: eState.getScenarioFSMStates()){
					str = "Transition"+scenarioFSM.getScenarioFSMTransitions().size();
					ScenarioFSMTransition fsmTransition = scenarioFSM.new ScenarioFSMTransition(str);
					fsmTransition.setSourceState(scenarioFSM.getState("InitialState"));
					fsmTransition.setTargetState(targetFSMState);
					if(fsmTransition.getSourceState()==null || fsmTransition.getTargetState()==null){
						str = "generateFSMTransitions: transition has no source/target state.";
						throw new NullPointerException(str);
					}
					if(!scenarioFSM.transitionExists(fsmTransition)){
						scenarioFSM.addScenarioFSMTransition(fsmTransition);
					}
					else{
						str = "generateFSMTransitions: duplicated transitions detected.";
						throw new NullPointerException(str);
					}						
				}
			}

			//add a transition from every FSM state of every incidentState to 
			//every FSM state of the current state
			for(ExplorationState ieState: eState.getIncidentStates()){
				for(ScenarioFSMState srcFSMState: ieState.getScenarioFSMStates()){
					for(ScenarioFSMState targetFSMState: eState.getScenarioFSMStates()){
						str = "Transition"+scenarioFSM.getScenarioFSMTransitions().size();								
						ScenarioFSMTransition fsmTransition = scenarioFSM.new ScenarioFSMTransition(str);
						fsmTransition.setSourceState(srcFSMState);
						fsmTransition.setTargetState(targetFSMState);
						ScenarioGraph srcG = srcFSMState.getScenarioGraph();
						ScenarioGraph dstG = targetFSMState.getScenarioGraph();
						//check for non-existing token transition
						if(!scenarioFSM.transitionExists(fsmTransition)){
							scenarioFSM.addScenarioFSMTransition(fsmTransition);
						}
//						else{
//							str = "generateFSMTransitions: duplicated transitions detected.";
//							throw new NullPointerException(str);
//						}
						
					}
				}
			}			
		}			
	}
	
	public boolean isNonExistingTokenTransition(
			Set<ControlTokensPerAction> f, Set<ControlTokensPerAction> s){
		for(ControlTokensPerAction cf: f){
			for(ControlTokensPerAction cs: s){
				for(Map.Entry<String, Long> ef: cf.getControlTokens().entrySet()){
					for(Map.Entry<String, Long> es: cs.getControlTokens().entrySet()){
						ControlTokenTransition ctt = new ControlTokenTransition(
								ef.getKey(), ef.getValue(),es.getKey(), es.getValue());
						for(ControlTokenTransition t: nonExistingTokenTransitions){
							if(ctt.equals(t))
								return true;
						}
					}
				}
			}
		}
		return false;
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
									ControlToken controlToken = new ControlToken(actor, st[0].trim());	
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
							
							// the 'Filters' tag specifies, non-existing token transitions
							if (actor.getAnnotationArgumentValue("ActorProperty", "Filters")!=null){
								String s = actor.getAnnotationArgumentValue("ActorProperty", "Filters");
								for(String t: s.split(";")){
									String st[] = t.split("-");
									String srcSt[] = st[0].split(":");
									String dstSt[] = st[1].split(":");
								    ControlTokenTransition ct = new ControlTokenTransition(
								    		srcSt[0].trim(), Long.parseLong(srcSt[1].trim()),
								    		dstSt[0].trim(), Long.parseLong(dstSt[1].trim()));
								    nonExistingTokenTransitions.add(ct);
								}
							}
							
						}
						//Otherwise, assume all output ports belong to the same token type					
						if(!hasAnnotation){
							ControlToken controlToken = new ControlToken(actor, "d");	
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
	
	private void populateDetectorFSMs(){
		for(ActorInstance dActor: analysis.getDetectorActors()){
			String filePath = analysis.getResourcePath()+File.separator+ 
					dActor.getInstanceName()+".sched";
			FiringSchedule firingSchedule = new FiringSchedule(dActor);
			Document document; 
			DocumentBuilderFactory factory =
			            DocumentBuilderFactory.newInstance();
			 factory.setIgnoringElementContentWhitespace(true);
	        try {
	           DocumentBuilder builder = factory.newDocumentBuilder();
	           document = builder.parse(new File(filePath));
	           if(document.getDocumentElement().getTagName().equalsIgnoreCase("firingSchedule")){	        	   
	        	   NamedNodeMap fsmAttributes = document.getDocumentElement().getAttributes();
	        	   Node initialFiringNode = fsmAttributes.getNamedItem("initialnode");
                   if(initialFiringNode==null)
                	   throw new Exception("No initial node in firing schedule");
                   firingSchedule.initialFiring = initialFiringNode.getNodeValue();                    
                   
	        	   NodeList nodes = document.getDocumentElement().getChildNodes();
	        	   for (int i=0; i<nodes.getLength() ; i++)
	               {   
	        		   Node node = nodes.item(i);
		               if (node.getNodeType() == Node.ELEMENT_NODE){
		            	   if(((Element) node).getTagName().equalsIgnoreCase("node")){
		            		   Node stateNameNode = node.getAttributes().getNamedItem("name");		                  
		            		   FiringNode firingNode = new FiringNode(
		            				   stateNameNode.getNodeValue(), dActor);
		            		   
		            		   //set action
		            		   Node actionNameNode = node.getAttributes().getNamedItem("action");	
		            		   for(Action a: dActor.getImplementation().getActions()){
	            				   String aName = a.getName().split("__")[1];
		            			   if(aName.equals(actionNameNode.getNodeValue())){
		            				   firingNode.firing = a;
		            				   break;
		            			   }		            				   
		            		   }
		            		   
		            		   if(firingNode.firing==null)
		            			   throw new Exception("Firing node has no action.");
		            		   
		            		   NodeList childNodes = node.getChildNodes();
		            		   int numChildNodes = childNodes.getLength();
		            		   for (int ix=0; ix< numChildNodes; ix++){  
		            			   Node childNode = childNodes.item(ix);
		            			   if (childNode.getNodeType() == Node.ELEMENT_NODE){
		            				   if(((Element) childNode).getTagName().equalsIgnoreCase("ControlToken")){
		            					   Node valueNode = childNode.getAttributes().getNamedItem("value");
		            					   Node nameNode = childNode.getAttributes().getNamedItem("name");
		            					   UnionOfDisjointIntervals l = 
		            							   ControlToken.parseIntervals(valueNode.getNodeValue().trim());
		            					   firingNode.controlTokens.put(nameNode.getNodeValue(),l);
		            				   }
		                		 
		            				   if(((Element) childNode).getTagName().equalsIgnoreCase("Transition")){
		            					   Node tNode = childNode.getAttributes().getNamedItem("node");
		            					   firingNode.outGoingTransitions.add(tNode.getNodeValue());
		            				   }
		            			   }
		            		   }
		            		   firingSchedule.firings.add(firingNode);
		            	   }
		               }
	               }
	           }
	        } catch (Throwable e) {
	           e.printStackTrace();
	        }
	        firingSchedules.add(firingSchedule);
		}
	}
	
	
	public FiringSchedule getFiringSchedule(ActorInstance a){
		for(FiringSchedule f: firingSchedules){
			if(f.detectorActor == a)
				return f;
		}
		return null;
	}
	
	/**
	 * prints possible scenario configurations and the tuples of control tokens of 
	 * each scenario configuration. 
	 */
	public void printScenarioConfigurations(){	
		//the set of detector actors
		Set<ActorInstance> dActors = analysis.getScenarioAwareDetectorActors();
		
		List<Set<Action>> setOfSetsOfFirings = new ArrayList<Set<Action>>();
		for(ActorInstance detector : dActors){
			Set<Action> firings = new HashSet<Action>();
			for(Action action : detector.getImplementation().getActions()){
				firings.add(action);
			}
			System.out.println(detector.getInstanceName()+" has "+firings.size()+" firings.");
			setOfSetsOfFirings.add(firings);
		}
		
		Set<Set<Action>> configurations = CartesianProduct.cartesianProduct(setOfSetsOfFirings);
		System.out.println(configurations.size()+" configurations found!");
		for(Set<Action> configuration: configurations){
			for(Action action: configuration){
				System.out.print(action.getName()+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Extracts scenarios and constructs the FSM using the firing schedules
	 * of detector actors. The firing schedules are constructed from file.
	 * The FSM is constructed through state-space exploration. A state is 
	 * uniquely identified by a scenario configuration, which is a tuple of
	 * detector actor firings.
	 * @return
	 */
	public List<ExplorationState> constructConfigurationSpace(){		
		
		//a list of states to be visited
		List<ExplorationState> statesToBeVisited = new ArrayList<ExplorationState>();
		
		//a list of visited states
		List<ExplorationState> visitedStates = new ArrayList<ExplorationState>();
		
		//the set of detector actors
		Set<ActorInstance> detectorActors = analysis.getScenarioAwareDetectorActors();
			
		try{
			//list of source actors
			List<ActorInstance> sources = analysis.getSourceActors();
			
			if(sources.size()==0){
				String msg = "constructScenarioFSM: no source actor found.";
				msg += "Use annotation: @ActorProperty(Source=\"true\")";
				throw new NullPointerException(msg);
			}
			
			//initialExplorationState:= set of the initial firings of detector actors
			Set<FiringNode> ieState = new HashSet<FiringNode>();
			for(ActorInstance d: detectorActors){
				ieState.add(getFiringSchedule(d).getInitialFiring());
			}			
			ExplorationState initialState = new ExplorationState(ieState, "InitialState");			
			statesToBeVisited.add(initialState);	
			
			//as long as there are states to visited
			while(!statesToBeVisited.isEmpty()){
				
				//pick a state 
				ExplorationState state = statesToBeVisited.remove(0);	
				
				//test if the configuration of the state has already been visited
				ExplorationState existingState = null;
				existingState = ExplorationState.findVistedState(visitedStates, state.configuration);	
				
				// if already visited, update only the list of incident states of the
				// existing state 
				if(existingState != null){
					for(ExplorationState es: state.getIncidentStates()){
						existingState.addIncidentState(es);
					}
				}
				else{
					// if the configuration is new, create a new state
					state.setName("State_"+visitedStates.size());
					visitedStates.add(state);	
					System.out.println(state.getConfigurationAsString());
					
					//a list of target firings of each firing of the configuration
					List<Set<FiringNode>> targetFiringsList = new ArrayList<Set<FiringNode>>();
					for(FiringNode firingNode: state.configuration){
						Set<FiringNode> targetFirings = new HashSet<FiringNode>();
						for(String transition: firingNode.outGoingTransitions){
							targetFirings.add(
									getFiringSchedule(firingNode.detectorActor).getFiring(transition));
						}						
						targetFiringsList.add(targetFirings);
					}

					Set<Set<FiringNode>> nextConfigurations = computeNextConfigurations(state.configuration, targetFiringsList);	
					
					boolean deadEnd = true;
					for(Set<FiringNode> nextConfiguration: nextConfigurations){
						//System.out.print("Testing: "+ExplorationState.getConfigurationAsString(nextConfiguration));
						if(existentConfiguration(nextConfiguration)){
							ExplorationState newEState = new ExplorationState(nextConfiguration,"EState");
							newEState.addIncidentState(state);	
							statesToBeVisited.add(newEState);	
							deadEnd = false;
							//System.out.println(" -- exists.");
						}
						//else
							//System.out.println(" -- does not exist.");
					}	
					
					
					//Find the tuplesOfControlTokens of the configuration	
					Set<Set<ControlTokensPerAction>> ctaSet = getTuplesOfControlTokens(state.configuration);
					Set<ScenarioGraph> sgs = constructScenarioGraphs(sources, state.configuration, ctaSet);				
					if(!sgs.isEmpty()){
						for(ScenarioGraph sg: sgs){
							if(ExplorationState.findScenarioGraph(sg, state.getScenarioGraphs(), false)==null)
								state.addScenarioGraph(sg);
							else
								numberOfEliminatedScenarioGraphs++;
						}
					}
					
					if(deadEnd){
						if(state.getScenarioGraphs().size() > 0){
							//System.out.println(state.getConfigurationAsString());
							System.out.println("\t\t\t --- DeadEnd");
						}
					}
				}
			}	
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		return visitedStates;
	}
	
	
	/**
	 * constructs the complete FSM. The set of states of the 
	 * complete FSM comprises a state for each scenario graph 
	 * of each scenario configuration. The set of transitions 
	 * comprises a transition between any two FSM states of two 
	 * connected scenario configurations. 
	 * @param states
	 */
	public void constructCompleteFSM(){
		Set<ExplorationState> states = new HashSet<ExplorationState>();
		states.addAll(constructConfigurationSpace());		
		try{
			//Remove all Exploration States which have no ScenarioGraphs
			states.removeAll(getZeroGraphStates(states));	
			
			if(states.size() == 0){
				throw new Exception("constructCompleteFSM: No states found.");
			}
				
			//generate the FSM states
			generateCompleteFSMStates(states);		
			
			//generate the FSM transitions
			generateFSMTransitions(states);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}
	
	/**
	 * constructs the complete FSM. The set of states of the 
	 * complete FSM comprises a state for each scenario graph 
	 * of each scenario configuration. The set of transitions 
	 * comprises a transition between any two FSM states of two 
	 * connected scenario configurations. 
	 * @param states
	 */
	public void constructConservativeFSM(){
		Set<ExplorationState> states = new HashSet<ExplorationState>();
		states.addAll(constructConfigurationSpace());		
		try{
			//Remove all Exploration States which have no ScenarioGraphs
			states.removeAll(getZeroGraphStates(states));	
			
			if(states.size() == 0){
				throw new Exception("constructCompleteFSM: No states found.");
			}
				
			//generate the FSM states
			generateConservativeFSMStates(states);		
			
			//generate the FSM transitions
			generateFSMTransitions(states);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	
	public Set<Set<FiringNode>> computeNextConfigurations(Set<FiringNode> currentConfiguration,
			List<Set<FiringNode>> targetFiringsList){
		Set<Set<FiringNode>> nextConfigurations = new HashSet<Set<FiringNode>>();
		for(Set<FiringNode> nextConfiguration: CartesianProduct.cartesianProduct(targetFiringsList)){
			System.out.println("Testing: "+ExplorationState.getConfigurationAsString(nextConfiguration));
			if(existentConfiguration(nextConfiguration)){
				Set<FiringNode> newConfiguration = new HashSet<FiringNode>();
				for(FiringNode firingNode: nextConfiguration){
					//if firing is not enabled, revert the transition
					if(!isDetectorFiringEnabled(firingNode, nextConfiguration)){
						//find the firing in the current configuration
						for(FiringNode cfiringNode: currentConfiguration){
							if(cfiringNode.detectorActor==firingNode.detectorActor){
								//replace it with the current firing 
								newConfiguration.add(cfiringNode);
								break;
							}
						}
					}
					else
						newConfiguration.add(firingNode);
				}
				System.out.println("ATesting: "+ExplorationState.getConfigurationAsString(newConfiguration));
				nextConfigurations.add(newConfiguration);
			}
		}
		
		//remove redundant configurations
		Set<Set<FiringNode>> nextUniqueConfigurations = new HashSet<Set<FiringNode>>();
		for(Set<FiringNode> nextConfiguration: nextConfigurations){
			// check if nextConfiguration is not redundant
			boolean exists = false;
			for(Set<FiringNode> nextConfigurationUnique: nextUniqueConfigurations){
				if(ExplorationState.areEqual(nextConfiguration, nextConfigurationUnique)){
					exists = true;
					break;
				}
			}
			
			if(!exists)
				nextUniqueConfigurations.add(nextConfiguration);
		}
		return nextUniqueConfigurations;
	}
}
