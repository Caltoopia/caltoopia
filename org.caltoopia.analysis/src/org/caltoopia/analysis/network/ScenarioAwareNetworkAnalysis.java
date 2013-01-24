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

import org.caltoopia.analysis.actor.ActorAnalyzer;
import org.caltoopia.analysis.actor.ConnectionAnalysis;
import org.caltoopia.analysis.actor.PortAnalysis;
import org.caltoopia.analysis.actor.SneakyActorAnalyzer;
import org.caltoopia.analysis.actor.ConnectionAnalysis.ConnectionType;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType;
import org.caltoopia.analysis.actor.PortAnalysis.PortType;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.iradapter.VanillaInputLookAhead;
import org.caltoopia.analysis.util.collections.CartesianProduct;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ast2ir.Stream;

public class ScenarioAwareNetworkAnalysis extends GenericNetworkAnalysis {	
	/**
	 * represents a control token of produced by a detector actor.
	 */
	public class ControlToken{
		//the detector actor
		private ActorInstance detectorActor;
		
		//the token name as given by annotation in the CAL actor
		private String tokenName;
		
		//the list of control ports that produce this control token
		private List<PortInstance> controlPorts = new ArrayList<PortInstance>();
		
		//constructor
		public ControlToken(ActorInstance a,  String t){
			detectorActor = a;
			tokenName = t;
		}
		
		public void setDetectorActor(ActorInstance a){
			detectorActor = a;
		}
		
		public void setName(String name){
			tokenName = name;
		}
		
		public void setControlPorts( List<PortInstance> p){
			controlPorts = p;
		}
		
		public ActorInstance getDetectorActor(){
			return detectorActor;
		}
		
		public String getName(){
			return tokenName;
		}
		
		public List<PortInstance> getControlPorts(){
			return controlPorts;
		}
		
		public void addControlPort( PortInstance p){
			controlPorts.add(p);
		}
		
		//print to a stream
		void print(Stream stream){
			stream.println("DetectorActor: "+detectorActor.getInstanceName());
			stream.println("\tTokenName: "+tokenName);
			stream.print("\tContorlPorts: ");
			for(PortInstance p: controlPorts){
				stream.print(p.getName()+" ");
			}
			stream.println();
		}
	};
	
	/**
	 * represents the control tokens produced by an action of a detector actor.
	 * A control token is identified by a name given by annotation in the CAL action
	 */
	public class ControlTokensPerAction{
		//the detector actor
		private ActorInstance detectorActor = null;
		
		//the action
		private Action detectorAction;
		
		//the list of control tokens and their corresponding values
		private Map<String, Long> controlTokens= new HashMap<String, Long>();
				
		public ControlTokensPerAction(ActorInstance actor, Action action){
			detectorActor = actor;
			detectorAction = action;
		};
		
		public void setDetectorActor(ActorInstance a){
			detectorActor = a;
		}
		
		public void setDetectorAction(Action a){
			detectorAction = a;
		}
		
		public void setControlTokens(Map<String, Long> c){
			controlTokens = c;
		}
		
		public ActorInstance getDetectorActor(){
			return detectorActor;
		}
		
		public Action getDetectorAction(){
			return detectorAction;
		}
		
		public Map<String, Long> getControlTokens(){
			return controlTokens;
		}
		
		public void print(Stream stream){
			stream.println("\t"+detectorActor.getInstanceName()+"\t"+detectorAction.getName());
			for(Map.Entry<String, Long> e: controlTokens.entrySet()){
				stream.println("\t\t"+e.getKey()+"\t"+e.getValue().longValue());
			}
		}
	};	
	
	
	//scenario-aware actor analysis objects per actor
	private Map<ActorInstance,ScenarioAwareActorAnalysis> mScenarioAwareActorMap=
			new HashMap<ActorInstance,ScenarioAwareActorAnalysis>();
	
	ScenarioAwareStateExploration stateSpaceExploration = null;
	
	//scenario FSM
	private ScenarioFSM fsm = new ScenarioFSM();	

	//constructor
	public ScenarioAwareNetworkAnalysis(Network n, NetworkAnalysis na) {
		super(n,na);
		ActorAnalyzer actorAnalyzer=new SneakyActorAnalyzer();
		for (ActorInstance actor: network.getActors()) {			
			if (actor.hasImplementation()) {
				ActorImplementation actorImpl=actor.getImplementation();
				ScenarioAwareActorAnalysis analysis = 
						new ScenarioAwareActorAnalysis(actor,actorAnalyzer.analyze(actorImpl));
				mScenarioAwareActorMap.put(actor, analysis);		
			}			
		}	

		//Set scenario properties for non-annotated actors 
		for (ActorInstance actor: network.getActors()) {			
			if (actor.hasImplementation()) {
				if(!getScenarioAwareActorAnalysis(actor).isTypeAnnotated()){
					getScenarioAwareActorAnalysis(actor).constructScenarios();
				}
			}
		}
		
		//Set scenario properties for annotated actors 
		for (ActorInstance actor: network.getActors()) {			
			if (actor.hasImplementation()) {
				if(getScenarioAwareActorAnalysis(actor).isTypeAnnotated()){
					constructScenarioThroughAnnotations(actor);
				}
			}
		}
			
		//Set connection types and detector actors
		for(Connection connection: network.getConnections()){
			if(connection.getConsumerPort().getActor().hasImplementation()){
				PortAnalysis dstPortAnalysis = getScenarioAwarePortAnalysis(connection.getConsumerPort());	
				if (dstPortAnalysis.getPortType()==PortType.CONTROL){					
					getConnectionAnalysis(connection).setConnectionType(ConnectionType.CONTROL);
					//If a control port is found, the producer actor is automatically a detector actor.
					ActorInstance srcActor = connection.getProducerPort().getActor();
					if(srcActor.hasImplementation()){
						ScenarioAwareActorAnalysis actorAnalysis = getScenarioAwareActorAnalysis(srcActor);
						actorAnalysis.setScenarioAwareActorInstanceType(ScenarioAwareActorInstanceType.SA_DETECTOR);
					}
				}
				else{
					getConnectionAnalysis(connection).setConnectionType(ConnectionType.DATA);
				}
			}
		}				
	
		stateSpaceExploration = new ScenarioAwareStateExploration(this);
		
		fsm = stateSpaceExploration.constructScenarioFSM();
	}
	
	//get the fsm
	public ScenarioFSM getScenarioFSM(){
		return fsm;
	}	
	
	/**
	 * @param actor
	 * @return the scenario-aware actor analysis object of the given actor instance
	 */
	public ScenarioAwareActorAnalysis getScenarioAwareActorAnalysis(ActorInstance actor){
		assert(mScenarioAwareActorMap.get(actor)!=null);
		return mScenarioAwareActorMap.get(actor);
	}
	
	/**
	 * @param portInstance
	 * @return the scenario-aware port analysis object of the given port instance
	 */
	public PortAnalysis getScenarioAwarePortAnalysis(PortInstance portInstance){
		assert(getScenarioAwareActorAnalysis(portInstance.getActor())!=null);
		return getScenarioAwareActorAnalysis(portInstance.getActor()).getPortAnalysis(portInstance);
	}
	
	/**
	 * @return the list of control connections of the network
	 */
 	public Set<Connection> getControlConnections(){
 		Set<Connection> controlConnections= new HashSet<Connection>();
 		for (Connection connection: network.getConnections()) {	
			if(getConnectionAnalysis(connection).getConnectionType()==
					ConnectionAnalysis.ConnectionType.CONTROL)
				controlConnections.add(connection);				
		}
 		return controlConnections;
 	}
	
 	/**
 	 * @return the list of detector actors of the network
 	 */
 	public Set<ActorInstance> getScenarioAwareDetectorActors(){
 		Set<ActorInstance> mcActors = new HashSet<ActorInstance>();
 		for (ActorInstance actor: network.getActors()) {
 			if(actor.hasImplementation()){
 				if(getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()
					==ScenarioAwareActorInstanceType.SA_DETECTOR)
					mcActors.add(actor);
 			}
		}
 		return mcActors;
 	}
	
 	/**
 	 * @return the list of scenario-aware dynamic actors of the network
 	 */
 	public List<ActorInstance> getDynamicActors(){
 		List<ActorInstance> dataDependentActors = new ArrayList<ActorInstance>();
 		for (ActorInstance actor: network.getActors()) {
 			ScenarioAwareActorInstanceType actorType=
 					getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType();
			if(actorType==ScenarioAwareActorInstanceType.SA_DYNAMIC){
				dataDependentActors.add(actor);
			}
		}
 		return dataDependentActors;
 	}

 	/**
 	 * @return true if a FSM-SADF network can be extracted from the network
 	 */
	public boolean isScenarioAwareDataflowGraph(){
		//Check if all actors are classified
		for (ActorInstance actor: network.getActors()) {
			if(actor.hasImplementation())
				if(getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()
						==ScenarioAwareActorInstanceType.UNCLASSIFIED){
					return false;
				}
		}
		
		//Check if there is at least one detector actor
		if(getScenarioAwareDetectorActors().size() <= 0){
			return false;
		}		
		
		return true;
	}			
	
	/**
	 * It is assumed that there is one source actor in the network. 
	 * @return the source actor of the network. It returns null if 
	 * there is no source actor.
	 */
	public ActorInstance getSourceActor(){
		for(ActorInstance actor: network.getActors()){
			if(actor.hasImplementation()){
				if(getScenarioAwareActorAnalysis(actor).isSource()){
					return actor;
				}
			}
		}
		return null;
	}	
	
	/**
	 * constructs the scenarios of an actor from annotations.
	 * @param actor
	 */
	public void constructScenarioThroughAnnotations(ActorInstance actor){
		ScenarioAwareActorAnalysis actorAnalysis = getScenarioAwareActorAnalysis(actor);
		try{
			if(actorAnalysis.isTypeAnnotated()){				
				String scenarios = actor.getAnnotationArgumentValue("ActorProperty","Scenarios");
				if(scenarios==null)
					throw new NullPointerException("scenarios tag must exist " +
							"if actor type is annotated with SA_DYNAMIC/SA_DETECTOR.");
				ScenarioAwareActorAnalysis.Scenario scenario;
				for(String scenarioString: scenarios.split(";")){
					scenario = actorAnalysis.new Scenario();
					for(String properties: scenarioString.split(",")){
						String propertyPair[] = properties.split("=");
						if(propertyPair[0].trim().equalsIgnoreCase("SCENARIO")){
							String scenarioSpecifications = propertyPair[1];
							for(String scenarioSpecification: scenarioSpecifications.split("\\+")){
								String scenarioSpecificationPair[] = scenarioSpecification.split(":");
								String actorAndPortName[] = scenarioSpecificationPair[0].split("\\.");
								ActorInstance detector = this.getActorInstance(actorAndPortName[0].trim());
								if (detector==null)
									throw new NullPointerException("Scenario tag error. Actor '"+actorAndPortName[0].trim()+
											"' does not exist.");
								PortInstance port = detector.getPort(actorAndPortName[1]);
								if (port == null)
									throw new NullPointerException("Scenario tag error. Actor '"+actorAndPortName[0].trim()+
											"' does have port '"+actorAndPortName[1]+"'.");
								InputLookAhead ila = new VanillaInputLookAhead(port,0);	
								UnionOfDisjointIntervals intervals = parseIntervals(scenarioSpecificationPair[1]);
								if(intervals==null)
									throw new NullPointerException("Scenario tag error. Parsing interval '"+scenarioSpecificationPair[1]+"'");
								scenario.addInputLookAheadInterval(ila,intervals);
							}
						}
					    //find WCET
						else if (propertyPair[0].trim().equalsIgnoreCase("WCET")){							
								scenario.setExecutionTime(Integer.parseInt(propertyPair[1].trim()));
						}						
						// otherwise it must be a port 
						else{
							if(actorAnalysis.getPortAnalysis(propertyPair[0].trim()) != null){
								if(actorAnalysis.getPortAnalysis(propertyPair[0].trim()).getPortInstance() != null){
									scenario.setPortRate(actorAnalysis.getPortAnalysis(propertyPair[0].trim()).getPortInstance(),
												Integer.parseInt(propertyPair[1].trim()));
								}
								else								
									throw new NullPointerException("Scenario tag error. Port '"+propertyPair[0].trim()+
											"' does not exist");
							}
							else								
								throw new NullPointerException("Scenario tag error. Port analysis for port '"+
										propertyPair[0].trim()+ "' does not exist.");
						}
					}
					actorAnalysis.addScenario(scenario);
				}
			}
		}
		catch (Exception e){
			 System.out.println(e.getMessage());
			 System.exit(1);
		}				
	}
	
	/**
	 * computes the set all possible tuples of ControlTokenPerAction that are possible at a 
	 * given transitionTuple. A tuple of ControlTokenPerAction has exactly one control token 
	 * per detector actor. 
	 * @param transitionTuple
	 * @return the set of all possible control token combinations 
	 */
	public Set<Set<ControlTokensPerAction>> getControlTokensPerActions(Set<Pair<ActorInstance, Transition>> transitionTuple){
		List<Set<ControlTokensPerAction>> sTokens = new ArrayList<Set<ControlTokensPerAction>>();
		for(Pair<ActorInstance, Transition> tPair: transitionTuple){
			ActorInstance detector = tPair.getFirst();
			Action detectorAction = tPair.getSecond().getAction();
			Set<ControlTokensPerAction> sta = getScenarioOfDetectorAction(detector, detectorAction);
			if(sta!=null)
				sTokens.add(sta);
		}	
		
		return  CartesianProduct.cartesianProduct(sTokens);
	}
	
	/**
	 * parses a 'string' and generates a union of disjoint intervals.
	 * 'string' must consist of sub-strings separated by a slash(/): 
	 * string = sub-string1/sub-string2/sub-string3
	 * A sub-string can only be either a numeric letter (e.g. 2) or 
	 * hyphen separated two numeric letters (e.g. 4-7 ). 
	 * Example: The string 2/4-7/13/8-9 produces the union of intervals
	 * given by {[2,2],[4-9],[13-13]}
	 * @param string
	 * @return UnionOfDisjointIntervals if the input string is valid.
	 * 	It returns null otherwise.
	 */
	public UnionOfDisjointIntervals parseIntervals(String string){
		UnionOfDisjointIntervals intervals = new UnionOfDisjointIntervals();
		for(String interval: string.split("/")){
			String s[] = interval.split("-");
			
			if(s.length==1 && Long.parseLong(s[0].trim()) >= 0){
				intervals.add(Long.parseLong(s[0].trim()), Long.parseLong(s[0].trim()));
			}
			else if(s.length==2 && Long.parseLong(s[0].trim()) >= 0 
					&& Long.parseLong(s[1].trim()) >= 0){
				intervals.add(Long.parseLong(s[0].trim()), Long.parseLong(s[1].trim()));
			}
			else
				return null;									
		}
		return intervals;
	}
	
	public ScenarioGraph constructScenarioGraph(ActorInstance source, Set<Pair<ActorInstance, Transition>> transitionTuple,
			Set<ControlTokensPerAction> sTokens){
		if(sTokens.isEmpty()){
			return null;
		}
		
		if(!stateSpaceExploration.controlTokensExist(transitionTuple, sTokens)){
			return null;
		}
		
		String name = null;
		for(ControlTokensPerAction sta: sTokens){
			name+= sta.getControlTokens().toString();
		}
		ScenarioGraph sg = new ScenarioGraph(name);			
			
		List<ActorInstance> visitedActors = new ArrayList<ActorInstance>();		
		List<ActorInstance> actorsQueue = new ArrayList<ActorInstance>();	
		actorsQueue.add(source);	
		while(!actorsQueue.isEmpty()){
			ActorInstance actor = actorsQueue.remove(0);			
			if(!visitedActors.contains(actor)){
				visitedActors.add(actor);
				ScenarioAwareActorAnalysis.Scenario sp = stateSpaceExploration.getScenario(actor, sTokens);
				if(sp != null){
					sg.getActors().put(actor, sp);
					for(PortInstance p: actor.getOutputPorts()){
						if(sp.getPortRate(p) > 0){
							for(Connection c: this.getNetwork().getIncidentConnections(p)){
								ActorInstance a = c.getConsumerPort().getActor();
								if(!actorsQueue.contains(a)){
									actorsQueue.add(a);
								}
							}
						}
					}
				}
			}
		}
		
		if(sg.getActors().size() == 0)
			return null;
		
		sg.addControlToken(filterActionScenarioTokens(sTokens,sg));
		
		//add each connection
		for(Connection c: this.getNetwork().getConnections()){
			//if source and destination actors are in the scenario graph
			if(sg.getActors().containsKey(c.getProducerPort().getActor()) && 
					sg.getActors().containsKey(c.getConsumerPort().getActor())){
				
				//if the corresponding actions of the source and destination actors have 
				//non-zero rates in the connecting ports of this connection
				ScenarioAwareActorAnalysis.Scenario spSrc = 
						sg.getActors().get(c.getProducerPort().getActor());
				ScenarioAwareActorAnalysis.Scenario spDst = 
						sg.getActors().get(c.getConsumerPort().getActor());
				
				if(spSrc.getPortRates().containsKey(c.getProducerPort()) && 
						spDst.getPortRates().containsKey(c.getConsumerPort())){
					int prodRate = spSrc.getPortRate(c.getProducerPort()).intValue();
					int consRate = spDst.getPortRate(c.getConsumerPort()).intValue();
					if(prodRate > 0 && consRate > 0){		
						Integer i = new Integer(prodRate - consRate);
						sg.addConnection(c, i);
					}
				}
			}
		}
		return sg;
	}
	
	public static Set<ControlTokensPerAction> filterActionScenarioTokens(Set<ControlTokensPerAction> actionScenarioTokens, ScenarioGraph sg){	
		Set<ControlTokensPerAction> filteredSet = new HashSet<ControlTokensPerAction>(actionScenarioTokens);
		for(ControlTokensPerAction ast: actionScenarioTokens){	
			ActorInstance actor = ast.getDetectorActor();
			if(!sg.getActors().containsKey(actor))
				filteredSet.add(ast);			
		}
		
		return filteredSet;
	}
	/**
	 * Each detector action produces a set of control tokens. This information
	 * is obtained from the action annotation. 
	 * @param detector
	 * @param action
	 * @return a set of control tokens produced by the detector action. It returns
	 * null if the detector action is not annotated.
	 */
	public Set<ControlTokensPerAction> getScenarioOfDetectorAction(ActorInstance detector, Action action){
		try{
			//if it has ControlToken annotated types, parse
			if(action.hasAnnotation("ActionProperty")){
				if (action.getAnnotationArgumentValue("ActionProperty", "ScenarioTokens")!=null){
					Set<ControlTokensPerAction> scenarioTokenActions = new HashSet<ControlTokensPerAction>();
					String s = action.getAnnotationArgumentValue("ActionProperty", "ScenarioTokens");
					List<Set<Pair<String, Long>>> tokenRanges = 
							new ArrayList<Set<Pair<String, Long>>>();
					for(String t: s.split(";")){
						String st[] = t.split(":");
						UnionOfDisjointIntervals intervals = new UnionOfDisjointIntervals();
						for(String interval: st[1].split("/")){
							String str[] = interval.split("-");
							
							if(str.length==1 && Long.parseLong(str[0].trim()) >= 0){
								intervals.add(Long.parseLong(str[0].trim()), Long.parseLong(str[0].trim()));
							}
							else if(str.length==2 && Long.parseLong(str[0].trim()) >= 0 
									&& Long.parseLong(str[1].trim()) >= 0){
								intervals.add(Long.parseLong(str[0].trim()), Long.parseLong(str[1].trim()));
							}
							else
								throw new NullPointerException("Scenario tag error. Syntax error for scenario interval '"
										+interval+"'.");										
						}							
						Set<Pair<String, Long>> tokenRange = new 
								HashSet<Pair<String, Long>>();
						for(Long scenario: intervals.asSet()){
							tokenRange.add(new Pair<String, Long>(st[0].trim(), scenario));
						}
						tokenRanges.add(tokenRange);
					}
					
				
					for(Set<Pair<String, Long>> str: CartesianProduct.cartesianProduct(tokenRanges)){
						ControlTokensPerAction sta = new ControlTokensPerAction(detector, action);
						for(Pair<String, Long> e: str){
							if(sta.getControlTokens().containsKey(e.getFirst()))
								throw new Exception("getScenarioOfDetectorAction: duplicate entry for a scenario token detected.");
							sta.getControlTokens().put(e.getFirst(), e.getSecond());
						}
						scenarioTokenActions.add(sta);
					}					
					return scenarioTokenActions;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}
}
