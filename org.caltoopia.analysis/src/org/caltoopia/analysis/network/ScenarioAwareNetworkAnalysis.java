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
import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.iradapter.VanillaInputLookAhead;
import org.caltoopia.analysis.network.ScenarioAwareStateExploration.FiringNode;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

public class ScenarioAwareNetworkAnalysis extends GenericNetworkAnalysis {	
	
	//scenario-aware actor analysis objects per actor
	private Map<ActorInstance,ScenarioAwareActorAnalysis> mScenarioAwareActorMap =
			new HashMap<ActorInstance,ScenarioAwareActorAnalysis>();
	
	ScenarioAwareStateExploration confSpaceExploration = null;
	
	//scenario FSM
	private ScenarioFSM fsm = new ScenarioFSM();	

	//path to additional resources and files
	private String resourcePath;
	
	//constructor
	public ScenarioAwareNetworkAnalysis(Network n, NetworkAnalysis na, String actionSchedulePAth) {
		//Generic network analysis constructor
		super(n,na);
		
		//Instantiate scenario-aware actor analysis objects
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
			//if the destination actor has implementation
			if(connection.getConsumerPort().getActor().hasImplementation()){
				PortAnalysis pAnalysis = null;
				pAnalysis = getScenarioAwarePortAnalysis(connection.getConsumerPort());	
				//if consumer port is a control port, we have a control channel
				if (pAnalysis.getPortType()==PortType.CONTROL){					
					getConnectionAnalysis(connection).setConnectionType(ConnectionType.CONTROL);
					//the producer actor is automatically a detector actor
					ActorInstance srcActor = connection.getProducerPort().getActor();
					if(srcActor.hasImplementation()){
						ScenarioAwareActorAnalysis aAnalysis = null;
						aAnalysis = getScenarioAwareActorAnalysis(srcActor);
						aAnalysis.setScenarioAwareActorInstanceType(
								ScenarioAwareActorInstanceType.SA_DETECTOR);
					}
				}
				else{
					getConnectionAnalysis(connection).setConnectionType(ConnectionType.DATA);
				}
			}
		}				
	

		setResourcePath(actionSchedulePAth);
		
		// state-space exploration object to construct the FSM
		confSpaceExploration = new ScenarioAwareStateExploration(this);
		
		// construct FSM
		confSpaceExploration.constructConservativeFSM();
				
		
		//TODO: Debug print - remove later
		List<ExplorationState> states = confSpaceExploration.constructConfigurationSpace();
		int sumOfTuples = 0;
		for(ExplorationState s: states){
			//s.print(System.out);
			Set<Set<ControlTokensPerAction>> ctt = confSpaceExploration.getTuplesOfControlTokens(s.configuration);
			for(FiringNode firing: s.configuration){
				System.out.print(firing.firing.getName()+" ");
			}
			sumOfTuples+=ctt.size();
			System.out.println();
			System.out.print(ctt.size()+" tuples and ");
			System.out.println(s.getScenarioGraphs().size()+" scenario graphs ");
			
			//check if every state is an
		}
		System.out.println(states.size()+" configurations and " + sumOfTuples +" tuples found.");		
			
		fsm = confSpaceExploration.getScenarioFSM();	
		System.out.println(fsm.getScenarioFSMStates().size() + " FSM States, " + 
							fsm.getScenarioFSMTransitions().size() + " FSM Transtions, " +
							fsm.getScenarioGraphs().size() + " scenario graphs," +
							confSpaceExploration.getNumberOfEliminatedScenarioGraphs() + " eliminated graphs");	
		// End of debug print
	}
	
	/**
	 * @return path to additional files and resources
	 */
	public String getResourcePath(){
		return resourcePath;
	}
	
	/**
	 * @param path : the path to additional files and resources
	 */
	public void setResourcePath(String path){
		resourcePath = path;
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
 			ScenarioAwareActorInstanceType actorType = 
 					getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType();
			if(actorType==ScenarioAwareActorInstanceType.SA_DYNAMIC){
				dataDependentActors.add(actor);
			}
		}
 		return dataDependentActors;
 	}
 	
 	/**
 	 * @return the list of scenario-aware detector actors of the network
 	 */
 	public List<ActorInstance> getDetectorActors(){
 		List<ActorInstance> detectorActors = new ArrayList<ActorInstance>();
 		for (ActorInstance actor: network.getActors()) {
 			if(actor.hasImplementation()){
	 			ScenarioAwareActorInstanceType actorType = 
	 					getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType();
				if(actorType==ScenarioAwareActorInstanceType.SA_DETECTOR){
					detectorActors.add(actor);
				}
 			}
		}
 		return detectorActors;
 	}

 	/**
 	 * @return true if a FSM-SADF network can be extracted from the network
 	 */
	public boolean isScenarioAwareDataflowGraph(){
		//Check if all actors are classified
		for (ActorInstance actor: network.getActors()) {
			if(actor.hasImplementation())
				if(getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()
						== ScenarioAwareActorInstanceType.UNCLASSIFIED){
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
	 * @return the source actors of the network. It returns null if 
	 * there is no source actor.
	 */
	public List<ActorInstance> getSourceActors(){
		List<ActorInstance> srcs = new ArrayList<ActorInstance>();
		for(ActorInstance actor: network.getActors()){
			if(actor.hasImplementation()){
				if(getScenarioAwareActorAnalysis(actor).isSource()){
					srcs.add(actor);
				}
			}
		}
		return srcs;
	}	
	
	/**
	 * constructs the scenarios of an actor from annotations.
	 * @param actor
	 */
	public void constructScenarioThroughAnnotations(ActorInstance actor){
		ScenarioAwareActorAnalysis actorAnalysis = getScenarioAwareActorAnalysis(actor);
		String msg;
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
						String prop[] = properties.split("=");
						if(prop[0].trim().equalsIgnoreCase("SCENARIO")){
							String scenarioSpecs = prop[1];
							for(String scenarioSpec: scenarioSpecs.split("\\+")){
								String scenarioSpecPair[] = scenarioSpec.split(":");
								String actorAndPort[] = scenarioSpecPair[0].split("\\.");
								ActorInstance det = this.getActorInstance(actorAndPort[0].trim());
								if (det==null){
									msg = "Scenario tag error. Actor '";
									msg += actorAndPort[0].trim() + "' does not exist.";
									throw new NullPointerException(msg);
								}
								PortInstance port = det.getPort(actorAndPort[1]);
								if (port == null){
									msg = "Scenario tag error. Actor '";
									msg += actorAndPort[0].trim()+"' does have port '";
									msg += actorAndPort[1]+"'.";
									throw new NullPointerException(msg);
								}
								InputLookAhead ila = new VanillaInputLookAhead(port,0);	
								UnionOfDisjointIntervals it = ControlToken.parseIntervals(scenarioSpecPair[1]);
								if(it==null){
									msg = "Scenario tag error. Parsing interval '";
									msg += scenarioSpecPair[1]+"'";
									throw new NullPointerException(msg);
								}
									scenario.addInputLookAheadInterval(ila,it);
							}
						}
					    //find WCET
						else if (prop[0].trim().equalsIgnoreCase("WCET")){							
								scenario.setExecutionTime(Integer.parseInt(prop[1].trim()));
						}						
						// otherwise it must be a port 
						else{
							if(actorAnalysis.getPortAnalysis(prop[0].trim()) != null){
								if(actorAnalysis.getPortAnalysis(prop[0].trim()).getPortInstance() != null){
									scenario.setPortRate(
											actorAnalysis.getPortAnalysis(prop[0].trim()).getPortInstance(),
											Integer.parseInt(prop[1].trim()));
								}
								else{				
									msg = "Scenario tag error. Port '";
									msg += prop[0].trim() + "' does not exist";
									throw new NullPointerException(msg);
								}
							}
							else								
								throw new NullPointerException("Scenario tag error. Port analysis for port '"+
										prop[0].trim()+ "' does not exist.");
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
	 * extracts a scenario given a tupleOfControlTokens
	 * @param source: source actors 
	 * @param sTokens: tupleOfControlTokens
	 * @return ScenarioGraph: a SDF graph of a scenario
	 */
	public ScenarioGraph constructScenarioGraph(List<ActorInstance> sources, 
											Set<FiringNode> configuration,
											Set<ControlTokensPerAction> sTokens){
		if(sTokens.isEmpty()){
			return null;
		}
		
		if(!confSpaceExploration.controlTokensExist(configuration, sTokens)){
			return null;
		}
		
		String name = null;
		for(ControlTokensPerAction sta: sTokens){
			name+= sta.getControlTokens().toString();
		}
		ScenarioGraph sg = new ScenarioGraph(name);			
			
		List<ActorInstance> visitedActors = new ArrayList<ActorInstance>();		
		List<ActorInstance> actorsQueue = new ArrayList<ActorInstance>();	
		actorsQueue.addAll(sources);	
		while(!actorsQueue.isEmpty()){
			ActorInstance actor = actorsQueue.remove(0);			
			if(!visitedActors.contains(actor)){
				visitedActors.add(actor);
				ScenarioAwareActorAnalysis.Scenario sp = null;
				if(!getScenarioAwareActorAnalysis(actor).isActorIgnored())
					sp = confSpaceExploration.getScenario(actor, sTokens);
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
		
		sg.setControlTokens(ControlTokensPerAction.filterControlTokens(sTokens,sg));
		
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
}

