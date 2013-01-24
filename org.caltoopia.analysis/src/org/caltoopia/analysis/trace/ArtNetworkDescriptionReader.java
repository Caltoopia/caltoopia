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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.iradapter.VanillaAction;
import org.caltoopia.analysis.iradapter.VanillaActorInstance;
import org.caltoopia.analysis.iradapter.VanillaConnection;
import org.caltoopia.analysis.iradapter.VanillaPortInstance;
import org.caltoopia.analysis.iradapter.VanillaPortSignature;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.io.ErrorConsole;
import org.caltoopia.analysis.util.io.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Reads a NetworkDescription from the network description file ("net_trace.xml") of a trace 
 * that is produced by the ACTORS run-time.
 */
public class ArtNetworkDescriptionReader extends XmlReader {

	public ArtNetworkDescriptionReader(ErrorConsole errConsole) {
		super(errConsole);
	}
	
	public ArtNetworkDescription readNetworkDescription(File file) {
		Document doc=readDocument(file);
		return readNetworkDescription(doc.getFirstChild());
	}
	
	private ArtNetworkDescription readNetworkDescription(Node node) {
		for (; node!=null; node=node.getNextSibling()) {
			if (isTag(node,"execution-trace")) {
				return readNetworkDescription(node.getFirstChild());
			}
			else if (isTag(node,"network")) {
				return readNetworkElement((Element) node);
			}
		}
		
		return null;
	}

	private ArtNetworkDescription readNetworkElement(Element element) {
		String name=element.getAttribute("name");
		List<ActorInstance> actors=new ArrayList<ActorInstance>();
		Map<Integer,Action> actionMap=new LinkedHashMap<Integer,Action>();
		Map<Integer,PortInstance> inputs=new LinkedHashMap<Integer,PortInstance>();
		Map<Integer,PortInstance> outputs=new LinkedHashMap<Integer,PortInstance>();
		List<Pair<Integer,Integer>> protoConnections=new ArrayList<Pair<Integer,Integer>>();
		Map<Action,ActorInstance> action2ActorMap=new HashMap<Action,ActorInstance>();
		
		// Read the actors
		for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
			if (isTag(child,"actor")) {
				ActorInstance a=readActor((Element) child, actionMap, action2ActorMap, inputs, outputs, protoConnections);
				if (a!=null) {
					actors.add(a);
				}
			}
		}
		
		// Create the connections
		Set<Connection> connections=new LinkedHashSet<Connection>();
		for (Pair<Integer,Integer> pair: protoConnections) {
			PortInstance producer=outputs.get(pair.getFirst());
			PortInstance consumer=inputs.get(pair.getSecond());
			
			if (producer!=null && consumer!=null) {
				Connection c=new VanillaConnection(producer,consumer);
				connections.add(c);
			}
			else {
				if (producer!=null)
					error("no such output port: id=\""+pair.getFirst()+"\"");
				if (consumer!=null)
					error("no such input port: id=\""+pair.getSecond()+"\"");
				note("mentioned in <input id=\""+pair.getSecond()+"\" source=\""+pair.getFirst()+"\"/>");
			}
		}
		
		return (!getErrorConsole().hasErrors())?  
				  new ArtNetworkDescription(name, actors, connections, actionMap, action2ActorMap) 
		        : null;
	}
	
	private ActorInstance readActor(Element element, 
			                        Map<Integer,Action> actionMap,
			                        Map<Action,ActorInstance> action2ActorMap,
			                        Map<Integer,PortInstance> inputs, Map<Integer,PortInstance> outputs, 
			                        List<Pair<Integer,Integer>> connections) {
		String instanceName=element.getAttribute("instance-name");
		
		if (instanceName!=null) {
			instanceName=fixWeirdName(instanceName);
			
			Integer id=getIntegerAttribute(element,"id");
			String className=element.getAttribute("class");
			VanillaActorInstance actor=new VanillaActorInstance(instanceName);

			// Read port declarations
			for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
				if (isTag(child,"input")) {
					readPort((Element) child, PortInstance.Direction.IN, actor, inputs, actor.getInputPorts());

					// Record the connection aswell
					Integer inputId=getIntegerAttribute((Element) child, "id");
					Integer outputId=getIntegerAttribute((Element) child, "source");

					if (inputId!=null && outputId!=null) {
						connections.add(Pair.create(outputId, inputId));
					}
				}
				else if (isTag(child,"output")) {
					readPort((Element) child, PortInstance.Direction.OUT, actor, outputs,  actor.getOutputPorts());
				}
			}


			// Read actions and connections, once port-ids have been read
			for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
				if (isTag(child,"action")) {
					Action a=readAction((Element) child, inputs, outputs, actionMap, instanceName);
					if (a!=null) {
						action2ActorMap.put(a,actor);
					}
				}
			}

			return actor;
		}
		else {
			error("<actor> element without \"instance-Name\" attribute");
			return null;
		}
	}
	
	/**
	 * @param weirdName  actor instance name, as printed by ACTORS run-time
	 * @return actor instance name, without suffix /0
	 */
	private String fixWeirdName(String weirdName) {
		// FIXME: remove once run-time uses/prints correct instance names
		return (weirdName.endsWith("/0"))? weirdName.substring(0, weirdName.length()-2) : weirdName;
	}
	
	private void readPort(Element element, PortInstance.Direction direction, ActorInstance inActor,
			              Map<Integer,PortInstance> portMap, Collection<PortInstance> portList) {
		Integer id=getIntegerAttribute(element,"id");
		String name=element.getAttribute("name");
		
		if (id!=null) {
			PortInstance port=new VanillaPortInstance(inActor,name,direction);
			
			if (portMap.containsKey(id))
				error("Duplicate port identifier id=\""+id+"\" (name=\""+name+"\"");
			else {
				portMap.put(id, port);
				portList.add(port);
			}
			
		}
		else {
			error("Port declaration missing \"id\" attribute");
		}		
	}
	
	private Action readAction(Element element, 
			                  Map<Integer,PortInstance> inputs, Map<Integer,PortInstance> outputs, 
			                  Map<Integer,Action> actionMap, String inActor) {
		Integer id=getIntegerAttribute(element,"id");
		String name=element.getAttribute("name");
		Map<PortInstance,Integer> rates=new LinkedHashMap<PortInstance,Integer>();
		
		for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
			if (isTag(child,"produces") || isTag(child,"consumes")) {
				Map<Integer,PortInstance> ports=isTag(child,"produces")? outputs : inputs;
				
				readPortRate((Element) child, ports, rates);
			}
		}

		PortSignature portSignature=new VanillaPortSignature(rates);
		Action action=new VanillaAction(inActor+"."+name, portSignature);
		
		if (id!=null) {
			actionMap.put(id,action);
		}
		else {
			error("<action> (name=\""+name+"\") missing \"id\" attribute");
		}
		
		return action;
	}
	
	private void readPortRate(Element element, Map<Integer,PortInstance> ports, Map<PortInstance,Integer> rates) {
		Integer count=getIntegerAttribute(element,"count");
		Integer p=getIntegerAttribute(element,"port");
		PortInstance port=ports.get(p);
		
		if (port!=null && count!=null) {
			rates.put(port, count);
		}
		else {
			if (port==null)
				error("no such port: "+p);
			if (count==null) {
				error("<produces> or <consumes> element missing \"count\" attribute");
			}
		}
	}
}
