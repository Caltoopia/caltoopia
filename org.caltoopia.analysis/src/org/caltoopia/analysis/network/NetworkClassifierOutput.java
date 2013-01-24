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
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.caltoopia.analysis.actor.McdfActorAnalysis;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.actor.McdfActorAnalysis.McdfActorInstanceType;
import org.caltoopia.analysis.actor.GenericActorAnalysis.ActorInstanceType;
import org.caltoopia.analysis.network.GenericNetworkAnalysis;
import org.caltoopia.analysis.network.McdfNetworkAnalysis;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMState;
import org.caltoopia.analysis.network.ScenarioFSM.ScenarioFSMTransition;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortInstance.Direction;
import org.caltoopia.ast2ir.Stream;

public class NetworkClassifierOutput {

	protected Stream stream;
	protected String outputFolder;
	
	public NetworkClassifierOutput() {
		outputFolder="."+File.separator;
	}
	
	public void print(GenericNetworkAnalysis analysis) {
		System.out.println();
		System.out.println("network "+analysis.getNetwork().getName());
		if (analysis!=null) {
			if(analysis.isSingleRateStaticDaflowGraph())
				System.out.println("HSDF network identified.");
			else if(analysis.isMultiRateStaticDaflowGraph())
				System.out.println("SDF network identified.");
			else if(analysis.isCycloStaticDaflowGraph())
				System.out.println("CSDF network identified.");
			else
				System.out.println("No known network identified.");
		}
	}
	
	public void print(McdfNetworkAnalysis analysis) {
		System.out.println();
		System.out.println("network "+analysis.getNetwork().getName());
		if (analysis!=null) {
			System.out.println("is MCDF: "+(analysis.isModeControlledDataflow()? "yes" : "no/perhaps"));
		}
	}
	
	public void print(ScenarioAwareNetworkAnalysis analysis) {
		System.out.println();
		System.out.println("network "+analysis.getNetwork().getName());
		if (analysis!=null) {
			System.out.println("is SADF: "+(analysis.isScenarioAwareDataflowGraph()? "yes" : "no/perhaps"));
		}
	}
	
	public String getOutputFolder() {
		return outputFolder;
	}
	
	public void setOutputFolder(String folder) {
		outputFolder=folder;
	}

	class XMLNode {
		private String nodeName;
		private Map<String, String> attributes = new HashMap<String,String>();
		private List<XMLNode> childNodes = new ArrayList<XMLNode>();
		private String value = null;
		
		XMLNode(String name){
			nodeName=name;
		}
		
		public void addAttribute(String attribute, String value){
			attributes.put(attribute,value);
		}
		
		public void addXMLNode(XMLNode node){
			childNodes.add(node);
		}
		
		public void addValue(String v){
			value = v;
		}
		
		
		public void printXMLNode(Stream s){
			openTag(s);
			printAttributes(s);
			printValue(s);
			s.inc();
			printChildNodes(s);			
			s.dec();
			closeTag(s);
		}
		
		public void openTag(Stream s){
			s.print("<"+nodeName);
		}
		
		public void printAttributes(Stream s){
			for(String attribute: attributes.keySet()){
				s.print(" "+attribute+"=\""+attributes.get(attribute)+"\"");
			}		
			if(childNodes.isEmpty())
				s.print("/>");			
			else
				s.print(">");			
			s.println();
				
		}
		
		public void printValue(Stream s){
			if(value!=null)
				s.print(value);
		}
		
		public void printChildNodes(Stream s){
			for(XMLNode node: childNodes){
				node.printXMLNode(s);
			}
		}
		
		public void closeTag(Stream s){
			if(!childNodes.isEmpty()){	
				s.println("</" + nodeName +">");
			}				
		}
	}
	
	public void printSDFXML(Network network, GenericNetworkAnalysis analysis) {
		Stream s = new Stream(outputFolder + File.separator + network.getName() + "_sdf.xml");
		
		XMLNode sdf3Root = new XMLNode("sdf3");
		sdf3Root.addAttribute("type","sdf");
		sdf3Root.addAttribute("version", "1.0");
		//sdf3Root.addAttribute("xsi:noNamespaceSchemaLocation","http://www.es.ele.tue.nl/sdf3/xsd/sdf3-sdf.xsd");
		
		XMLNode applicationNode = new XMLNode("applicationGraph");
		applicationNode.addAttribute("name", network.getName());
		sdf3Root.addXMLNode(applicationNode);
		
		XMLNode sdfNode = new XMLNode("sdf");
		sdfNode.addAttribute("name", network.getName());
		sdfNode.addAttribute("type", network.getName());
		applicationNode.addXMLNode(sdfNode);
		
		XMLNode sdfPropertiesNode = new XMLNode("sdfProperties");
		applicationNode.addXMLNode(sdfPropertiesNode);
		
		//add each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorNode = new XMLNode("actor");
			actorNode.addAttribute("name", actor.getInstanceName());
			actorNode.addAttribute("type", actor.getInstanceName());
			for(PortInstance p: actor.getPorts()){
				XMLNode portNode = new XMLNode("port");
				portNode.addAttribute("name", p.getName());
				portNode.addAttribute("type", p.getDirection()==Direction.IN?"in":"out");		
				assert(analysis.getRate(p)!=null);
				portNode.addAttribute("rate", analysis.getRateAsString(p));
				actorNode.addXMLNode(portNode);
			}
			sdfNode.addXMLNode(actorNode);
		}
		
		//add each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionNode = new XMLNode("channel");
			connectionNode.addAttribute("name", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcActor", c.getProducerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcPort", c.getProducerPort().getName());
			connectionNode.addAttribute("dstActor", c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("dstPort", c.getConsumerPort().getName());
			if(analysis.getConnectionAnalysis(c).getInitialTokenSize()!=null)
				connectionNode.addAttribute("initialTokens", analysis.getConnectionAnalysis(c).getInitialTokenSize().toString());
			sdfNode.addXMLNode(connectionNode);
		}
		
		//add properties each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorPropertiesNode = new XMLNode("actorProperties");
			actorPropertiesNode.addAttribute("actor", actor.getInstanceName());
			//add processor
			XMLNode processorNode = new XMLNode("processor");
			processorNode.addAttribute("type", "arm");
			processorNode.addAttribute("default", "true");
			
			//add execution time
			XMLNode executionTimeNode = new XMLNode("executionTime");			
			executionTimeNode.addAttribute("time", analysis.getExecutionTimeAsString(actor));
			processorNode.addXMLNode(executionTimeNode);
			
			//add memory
			XMLNode memoryNode = new XMLNode("memory");
			XMLNode stateSizeNode = new XMLNode("stateSize");
			stateSizeNode.addAttribute("max", analysis.getGenericActorAnalysis(actor).getStateSize().toString());
			memoryNode.addXMLNode(stateSizeNode);
			processorNode.addXMLNode(memoryNode);
			
			actorPropertiesNode.addXMLNode(processorNode);
			sdfPropertiesNode.addXMLNode(actorPropertiesNode);
		}
		
		//add properties of each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionPropertyNode = new XMLNode("channelProperties");
			connectionPropertyNode.addAttribute("channel", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			XMLNode tokenSizeNode = new XMLNode("tokenSize");
			tokenSizeNode.addAttribute("size", analysis.getConnectionAnalysis(c).getTokenSize().toString());
			connectionPropertyNode.addXMLNode(tokenSizeNode);
			sdfPropertiesNode.addXMLNode(connectionPropertyNode);
		}
		
		//add timing constraints
		XMLNode graphPropertiesNode = new XMLNode("graphProperties");
		XMLNode timingConstraintsNode = new XMLNode("timeConstraints");
		XMLNode throughputNode = new XMLNode("throughput");
		throughputNode.addValue("0.000003");
		timingConstraintsNode.addXMLNode(throughputNode);
		graphPropertiesNode.addXMLNode(timingConstraintsNode);	
		sdfPropertiesNode.addXMLNode(graphPropertiesNode);
		
		//print root
		sdf3Root.printXMLNode(s);
	}
	
	public void printCSDFXML(Network network, GenericNetworkAnalysis analysis) {
		Stream s = new Stream(outputFolder + File.separator + network.getName() + "_csdf.xml");
		
		XMLNode sdf3Root = new XMLNode("sdf3");
		sdf3Root.addAttribute("type","csdf");
		sdf3Root.addAttribute("version", "1.0");
		//sdf3Root.addAttribute("xsi:noNamespaceSchemaLocation","http://www.es.ele.tue.nl/sdf3/xsd/sdf3-sdf.xsd");
		
		XMLNode applicationNode = new XMLNode("applicationGraph");
		applicationNode.addAttribute("name", network.getName());
		sdf3Root.addXMLNode(applicationNode);
		
		XMLNode sdfNode = new XMLNode("csdf");
		sdfNode.addAttribute("name", network.getName());
		sdfNode.addAttribute("type", network.getName());
		applicationNode.addXMLNode(sdfNode);
		
		XMLNode sdfPropertiesNode = new XMLNode("csdfProperties");
		applicationNode.addXMLNode(sdfPropertiesNode);
		
		//add each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorNode = new XMLNode("actor");
			actorNode.addAttribute("name", actor.getInstanceName());
			actorNode.addAttribute("type", actor.getInstanceName());
			for(PortInstance p: actor.getPorts()){
				XMLNode portNode = new XMLNode("port");
				portNode.addAttribute("name", p.getName());
				portNode.addAttribute("type", p.getDirection()==Direction.IN?"in":"out");		
				assert(analysis.getRate(p)!=null);
				portNode.addAttribute("rate", analysis.getRateAsString(p));
				actorNode.addXMLNode(portNode);
			}
			sdfNode.addXMLNode(actorNode);
		}
		
		//add each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionNode = new XMLNode("channel");
			connectionNode.addAttribute("name", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcActor", c.getProducerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcPort", c.getProducerPort().getName());
			connectionNode.addAttribute("dstActor", c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("dstPort", c.getConsumerPort().getName());
			if(analysis.getConnectionAnalysis(c).getInitialTokenSize()!=null)
				connectionNode.addAttribute("initialTokens", analysis.getConnectionAnalysis(c).getInitialTokenSize().toString());
			sdfNode.addXMLNode(connectionNode);
		}
		
		//add properties each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorPropertiesNode = new XMLNode("actorProperties");
			actorPropertiesNode.addAttribute("actor", actor.getInstanceName());
			//add processor
			XMLNode processorNode = new XMLNode("processor");
			processorNode.addAttribute("type", "arm");
			processorNode.addAttribute("default", "true");
			
			//add execution time
			XMLNode executionTimeNode = new XMLNode("executionTime");
			executionTimeNode.addAttribute("time", analysis.getExecutionTimeAsString(actor));
			processorNode.addXMLNode(executionTimeNode);
			
			//add memory
			XMLNode memoryNode = new XMLNode("memory");
			XMLNode stateSizeNode = new XMLNode("stateSize");
			stateSizeNode.addAttribute("max", analysis.getGenericActorAnalysis(actor).getStateSize().toString());
			memoryNode.addXMLNode(stateSizeNode);
			processorNode.addXMLNode(memoryNode);
			
			actorPropertiesNode.addXMLNode(processorNode);
			sdfPropertiesNode.addXMLNode(actorPropertiesNode);
		}
		
		//add properties of each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionPropertyNode = new XMLNode("channelProperties");
			connectionPropertyNode.addAttribute("channel", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			XMLNode tokenSizeNode = new XMLNode("tokenSize");
			tokenSizeNode.addAttribute("size", Integer.toString(analysis.getConnectionAnalysis(c).getTokenSize()));
			connectionPropertyNode.addXMLNode(tokenSizeNode);
			sdfPropertiesNode.addXMLNode(connectionPropertyNode);
		}
		
		//add timing constraints
		XMLNode graphPropertiesNode = new XMLNode("graphProperties");
		XMLNode timingConstraintsNode = new XMLNode("timeConstraints");
		XMLNode throughputNode = new XMLNode("throughput");
		throughputNode.addValue("0.000003");
		timingConstraintsNode.addXMLNode(throughputNode);
		graphPropertiesNode.addXMLNode(timingConstraintsNode);		
		sdfPropertiesNode.addXMLNode(graphPropertiesNode);
		
		//print root
		sdf3Root.printXMLNode(s);
	}
	
	public void printMcdfXML(Network network, McdfNetworkAnalysis analysis) {
		Stream s = new Stream(outputFolder + File.separator + network.getName() + "_mcdf.xml");
		
		XMLNode sdf3Root = new XMLNode("sdf3");
		sdf3Root.addAttribute("type","fsmsadf");
		sdf3Root.addAttribute("version", "1.0");
		//sdf3Root.addAttribute("xsi:noNamespaceSchemaLocation=\"http://www.es.ele.tue.nl/sdf3/xsd/sdf3-mcdf.xsd");
		
		XMLNode applicationNode = new XMLNode("applicationGraph");
		applicationNode.addAttribute("name", network.getName());
		sdf3Root.addXMLNode(applicationNode);
		
		XMLNode sdfNode = new XMLNode("mcdfGraph");
		sdfNode.addAttribute("name", network.getName());
		sdfNode.addAttribute("type", network.getName());
		sdfNode.addAttribute("modes", analysis.getNumberOfModes().toString());
		applicationNode.addXMLNode(sdfNode);
		
		XMLNode sdfPropertiesNode = new XMLNode("mcdfProperties");
		XMLNode sdfDefaultPropertiesNode = new XMLNode("defaultProperties");
		sdfPropertiesNode.addXMLNode(sdfDefaultPropertiesNode);
		applicationNode.addXMLNode(sdfPropertiesNode);	
		
		
		//add each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorNode = new XMLNode("actor");
			actorNode.addAttribute("name", actor.getInstanceName());
			actorNode.addAttribute("type", analysis.getMcdfActorAnalysis(actor).getMcdfActorInstanceTypeAsString());
			for(PortInstance p: actor.getPorts()){
				XMLNode portNode = new XMLNode("port");
				portNode.addAttribute("name", p.getName());
				portNode.addAttribute("type", p.getDirection()==Direction.IN?"in":"out");		
				assert(analysis.getRate(p)!=null);
				portNode.addAttribute("rate", "1");
				actorNode.addXMLNode(portNode);
			}
			sdfNode.addXMLNode(actorNode);
		}
		
		//add each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionNode = new XMLNode("channel");
			connectionNode.addAttribute("name", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcActor", c.getProducerPort().getActor().getInstanceName());
			connectionNode.addAttribute("srcPort", c.getProducerPort().getName());
			connectionNode.addAttribute("dstActor", c.getConsumerPort().getActor().getInstanceName());
			connectionNode.addAttribute("dstPort", c.getConsumerPort().getName());
			if(analysis.getConnectionAnalysis(c).getInitialTokenSize()!=null)
				connectionNode.addAttribute("initialTokens", analysis.getConnectionAnalysis(c).getInitialTokenSize().toString());
			sdfNode.addXMLNode(connectionNode);
		}

		//add properties each actor
		for(ActorInstance actor: network.getActors()){
			XMLNode actorPropertiesNode = new XMLNode("actorProperties");
			actorPropertiesNode.addAttribute("actor", actor.getInstanceName());
			//add processor
			XMLNode processorNode = new XMLNode("processor");
			processorNode.addAttribute("type", "arm");
			processorNode.addAttribute("default", "true");
			
			//add execution time
			XMLNode executionTimeNode = new XMLNode("executionTime");			
			executionTimeNode.addAttribute("time", analysis.getExecutionTimeAsString(actor));
			processorNode.addXMLNode(executionTimeNode);
			
			//add memory
			XMLNode memoryNode = new XMLNode("memory");
			XMLNode stateSizeNode = new XMLNode("stateSize");
			stateSizeNode.addAttribute("max", analysis.getGenericActorAnalysis(actor).getStateSize().toString());
			memoryNode.addXMLNode(stateSizeNode);
			processorNode.addXMLNode(memoryNode);
			
			actorPropertiesNode.addXMLNode(processorNode);
			sdfDefaultPropertiesNode.addXMLNode(actorPropertiesNode);
		}
		
		//add properties of each connection
		for(Connection c: network.getConnections()){
			XMLNode connectionPropertyNode = new XMLNode("channelProperties");
			connectionPropertyNode.addAttribute("channel", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			XMLNode tokenSizeNode = new XMLNode("tokenSize");
			tokenSizeNode.addAttribute("size", Integer.toString(analysis.getConnectionAnalysis(c).getTokenSize()));
			connectionPropertyNode.addXMLNode(tokenSizeNode);
			sdfDefaultPropertiesNode.addXMLNode(connectionPropertyNode);
		}
		
		//add timing constraints
		XMLNode graphPropertiesNode = new XMLNode("graphProperties");
		XMLNode timingConstraintsNode = new XMLNode("timeConstraints");
		XMLNode throughputNode = new XMLNode("throughput");
		throughputNode.addValue("0.000003");
		timingConstraintsNode.addXMLNode(throughputNode);
		graphPropertiesNode.addXMLNode(timingConstraintsNode);		
		sdfPropertiesNode.addXMLNode(graphPropertiesNode);	

		//print scenario dependencies from Tunnel actors
		XMLNode scenarioDependenciesNode = new XMLNode("scenarioDependencies"); 
		for(Connection masterC: network.getConnections()){
			ActorInstance consumer=masterC.getConsumerPort().getActor();
			McdfActorInstanceType consumerType=analysis.getMcdfActorAnalysis(consumer).getMcdfActorInstanceType();
			ActorInstance producer=masterC.getProducerPort().getActor();
			McdfActorInstanceType producerType=analysis.getMcdfActorAnalysis(producer).getMcdfActorInstanceType();
			if (consumerType==McdfActorInstanceType.TUNNEL 
					&& producerType!=McdfActorInstanceType.MC){				
				ActorInstance master = masterC.getProducerPort().getActor();
				ActorInstance tunnel = masterC.getConsumerPort().getActor();
				for(Connection slaveC: network.getConnections()){
					if(slaveC.getProducerPort().getActor().equals(tunnel)){
						ActorInstance slave = slaveC.getConsumerPort().getActor() ;
						XMLNode masterNode = new XMLNode("master");
						masterNode.addAttribute("scenario", analysis.getMcdfActorAnalysis(master).getMcdfActorInstanceTypeAsString());
						masterNode.addAttribute("actor", master.getInstanceName());
						XMLNode slaveNode = new XMLNode("slave");
						slaveNode.addAttribute("scenario", analysis.getMcdfActorAnalysis(slave).getMcdfActorInstanceTypeAsString());
						slaveNode.addAttribute("actor", slave.getInstanceName());
						masterNode.addXMLNode(slaveNode);
						scenarioDependenciesNode.addXMLNode(masterNode);
					}
				}
			}
		}
		applicationNode.addXMLNode(scenarioDependenciesNode);
		
		//Print fsm
		//At this point we don't know the possible mode transitions. 
		//Hence, we assume a fully connected FSM.
		XMLNode fsmNode = new XMLNode("fsm");
		fsmNode.addAttribute("initialstate", "state0");
		for(int i=0; i < analysis.getNumberOfModes(); i++){
			XMLNode stateNode = new XMLNode("state");
			stateNode.addAttribute("name", "state"+i);
			stateNode.addAttribute("scenario", "mode"+(i+1));			
			for(int j=0; j < analysis.getNumberOfModes(); j++){
				XMLNode transitionNode = new XMLNode("transition");
				transitionNode.addAttribute("destination", "state"+j);
				stateNode.addXMLNode(transitionNode);
			}
			fsmNode.addXMLNode(stateNode);
		}
		
		applicationNode.addXMLNode(fsmNode);
		//print root
		sdf3Root.printXMLNode(s);
	}
	
	public void printSaXML(Network network, ScenarioAwareNetworkAnalysis analysis) {
		Stream s = new Stream(outputFolder + File.separator + network.getName() + "_fsmsadf.xml");
		
		XMLNode sdf3Root = new XMLNode("sdf3");
		sdf3Root.addAttribute("type","fsmsadf");
		sdf3Root.addAttribute("version", "1.0");
		//sdf3Root.addAttribute("xsi:noNamespaceSchemaLocation=\"http://www.es.ele.tue.nl/sdf3/xsd/sdf3-mcdf.xsd");
		
		XMLNode applicationNode = new XMLNode("applicationGraph");
		applicationNode.addAttribute("name", network.getName());
		sdf3Root.addXMLNode(applicationNode);
		
		XMLNode fsmsadfNode = new XMLNode("fsmsadf");
		applicationNode.addXMLNode(fsmsadfNode);
		
		XMLNode fsmsadfPropertiesNode = new XMLNode("fsmsadfProperties");
		applicationNode.addXMLNode(fsmsadfPropertiesNode);	
		
		XMLNode fsmNode = new XMLNode("fsm");
		fsmNode.addAttribute("initialstate", "InitialState");
		applicationNode.addXMLNode(fsmNode);	
		
		for(ScenarioGraph scenarioGraph: analysis.getScenarioFSM().getScenarioGraphs()){
			XMLNode scenarioGraphNode = new XMLNode("scenariograph");
			scenarioGraphNode.addAttribute("name", scenarioGraph.getName());
			scenarioGraphNode.addAttribute("type", "t");
			fsmsadfNode.addXMLNode(scenarioGraphNode);								
			
			//add each actor
			for(Map.Entry<ActorInstance, ScenarioAwareActorAnalysis.Scenario> a: 
					scenarioGraph.getActors().entrySet()){
				ActorInstance actor = a.getKey();
				ScenarioAwareActorAnalysis.Scenario scenario = a.getValue();
				XMLNode actorNode = new XMLNode("actor");
				actorNode.addAttribute("name", actor.getInstanceName());
				String type="";
				if(scenario.getTransition()==null){
					if(analysis.getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()==
							ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType.SA_STATIC){
						type = "static";
					}else if(analysis.getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()==
							ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType.SA_DYNAMIC ||
							analysis.getScenarioAwareActorAnalysis(actor).getScenarioAwareActorInstanceType()==
							ScenarioAwareActorAnalysis.ScenarioAwareActorInstanceType.SA_DETECTOR)
					{
						if(analysis.getScenarioAwareActorAnalysis(actor).isTypeAnnotated()){
							type = "annotated_dynamic";
						}
					}
				}
				else
					type = scenario.getTransition().getAction().getName();
				actorNode.addAttribute("type", type);
				for(PortInstance p: actor.getPorts()){
					if(scenario.getPortRates().containsKey(p)){
						if(scenario.getPortRate(p).intValue() > 0){
							for(Connection c: network.getIncidentConnections(p)){
								XMLNode portNode = new XMLNode("port");
								if(p.getDirection()==Direction.IN){
									portNode.addAttribute("name", p.getName()+"_from_"+c.getProducerPort().getActor().getInstanceName());
									portNode.addAttribute("type", "in");
								}
								else{
									portNode.addAttribute("name", p.getName()+"_to_"+c.getConsumerPort().getActor().getInstanceName());
									portNode.addAttribute("type", "out");
								}
								portNode.addAttribute("rate", scenario.getPortRate(p).toString());
								actorNode.addXMLNode(portNode);
							}
						}
					}
				}
				scenarioGraphNode.addXMLNode(actorNode);
			}
			
			//add each connection
			for(Connection c: network.getConnections()){
				XMLNode connectionNode = new XMLNode("channel");
				//if source and destination actors are in the scenario graph
				if(scenarioGraph.getActors().containsKey(c.getProducerPort().getActor()) && 
						scenarioGraph.getActors().containsKey(c.getConsumerPort().getActor())){
					
					//if the corresponding actions of the source and destination actors have 
					//non-zero rates in the connecting ports of this connection
					ScenarioAwareActorAnalysis.Scenario spSrc = 
							scenarioGraph.getActors().get(c.getProducerPort().getActor());
					ScenarioAwareActorAnalysis.Scenario spDst = 
							scenarioGraph.getActors().get(c.getConsumerPort().getActor());
					
					if(spSrc.getPortRates().containsKey(c.getProducerPort()) && 
							spDst.getPortRates().containsKey(c.getConsumerPort())){
						if(spSrc.getPortRate(c.getProducerPort()).intValue() > 0 && 
								spDst.getPortRate(c.getConsumerPort()).intValue() > 0){							
							connectionNode.addAttribute("name", c.getProducerPort().getActor().getInstanceName()+
									"2"+c.getConsumerPort().getActor().getInstanceName());
							connectionNode.addAttribute("srcActor", c.getProducerPort().getActor().getInstanceName());
							connectionNode.addAttribute("srcPort", c.getProducerPort().getName()+"_to_"+c.getConsumerPort().getActor().getInstanceName());
							connectionNode.addAttribute("dstActor", c.getConsumerPort().getActor().getInstanceName());
							connectionNode.addAttribute("dstPort", c.getConsumerPort().getName()+"_from_"+c.getProducerPort().getActor().getInstanceName());
							connectionNode.addAttribute("initialTokens", "0");
							scenarioGraphNode.addXMLNode(connectionNode);
						}
					}
				}
			}
		}
		

		XMLNode fsmsadfDefaultPropertiesNode = new XMLNode("defaultProperties");
		fsmsadfPropertiesNode.addXMLNode(fsmsadfDefaultPropertiesNode);
		
		//add properties each actor
		for(ActorInstance actor: network.getActors()){
			if(!actor.hasImplementation())
				continue;
			XMLNode actorPropertiesNode = new XMLNode("actorProperties");
			actorPropertiesNode.addAttribute("actor", actor.getInstanceName());
			//add processor
			XMLNode processorNode = new XMLNode("processor");
			processorNode.addAttribute("type", "arm");
			processorNode.addAttribute("default", "true");
			
			//add execution time
			XMLNode executionTimeNode = new XMLNode("executionTime");			
			executionTimeNode.addAttribute("time", analysis.getExecutionTimeAsString(actor));
			processorNode.addXMLNode(executionTimeNode);
			
			//add memory
			XMLNode memoryNode = new XMLNode("memory");
			XMLNode stateSizeNode = new XMLNode("stateSize");
			stateSizeNode.addAttribute("max", analysis.getGenericActorAnalysis(actor).getStateSize().toString());
			memoryNode.addXMLNode(stateSizeNode);
			processorNode.addXMLNode(memoryNode);
			
			actorPropertiesNode.addXMLNode(processorNode);
			fsmsadfDefaultPropertiesNode.addXMLNode(actorPropertiesNode);
		}
		
		//add scenarios
		XMLNode scenariosNode = new XMLNode("scenarios");
		fsmsadfPropertiesNode.addXMLNode(scenariosNode);
		for(ScenarioGraph scenarioGraph: analysis.getScenarioFSM().getScenarioGraphs()){
			XMLNode scenarioNode = new XMLNode("scenario");
			scenarioNode.addAttribute("name",  scenarioGraph.getName());
			scenarioNode.addAttribute("graph", scenarioGraph.getName());
			scenariosNode.addXMLNode(scenarioNode);
		}
		
		//add properties of each connection
		for(Connection c: network.getConnections()){
			if(!c.getProducerPort().getActor().hasImplementation() ||
					!c.getConsumerPort().getActor().hasImplementation())
				continue;
			XMLNode connectionPropertyNode = new XMLNode("channelProperties");
			connectionPropertyNode.addAttribute("channel", c.getProducerPort().getActor().getInstanceName()+
					"2"+c.getConsumerPort().getActor().getInstanceName());
			XMLNode tokenSizeNode = new XMLNode("tokenSize");
			tokenSizeNode.addAttribute("size", Integer.toString(analysis.getConnectionAnalysis(c).getTokenSize()));
			connectionPropertyNode.addXMLNode(tokenSizeNode);
			fsmsadfDefaultPropertiesNode.addXMLNode(connectionPropertyNode);
		}
		
		//add timing constraints
		XMLNode graphPropertiesNode = new XMLNode("graphProperties");
		XMLNode timingConstraintsNode = new XMLNode("timeConstraints");
		XMLNode throughputNode = new XMLNode("throughput");
		throughputNode.addValue("0.000003");
		timingConstraintsNode.addXMLNode(throughputNode);
		graphPropertiesNode.addXMLNode(timingConstraintsNode);		
		fsmsadfPropertiesNode.addXMLNode(graphPropertiesNode);	

		//add fsm
		//Print fsm
		for(ScenarioFSMState fsmState: analysis.getScenarioFSM().getScenarioFSMStates()){
			XMLNode stateNode = new XMLNode("state");
			stateNode.addAttribute("name", fsmState.getName());
			if(fsmState.getScenarioGraph()!=null)
				stateNode.addAttribute("scenario", fsmState.getScenarioGraph().getName());
			else
				stateNode.addAttribute("scenario", "none");
			for(ScenarioFSMTransition fsmTransition: analysis.getScenarioFSM().getScenarioFSMTransitions()){
				if(fsmTransition.getSourceState() == fsmState){
					XMLNode transitionNode = new XMLNode("transition");
					transitionNode.addAttribute("destination", fsmTransition.getTargetState().getName());
					stateNode.addXMLNode(transitionNode);
				}
			}
			fsmNode.addXMLNode(stateNode);
		}
				
		//print root
		sdf3Root.printXMLNode(s);
	}
}
