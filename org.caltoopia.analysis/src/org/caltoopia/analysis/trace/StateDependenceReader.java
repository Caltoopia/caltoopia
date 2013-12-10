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
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.io.ErrorConsole;
import org.caltoopia.analysis.util.io.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class reads an XML file that declares the effect of actions on actor state.
 * This information is used when creating a "causation trace" from a (linear) execution trace.
 */
public class StateDependenceReader extends XmlReader {

	public StateDependenceReader(ErrorConsole errConsole) {
		super(errConsole);
	}

	public SchedulingConstraints readNetwork(File file, ArtNetworkDescription network) {
		Document doc=readDocument(file);
		return readNetworkDescription(doc.getFirstChild(), network);
	}
	
	private static class Variable extends Pair<String,String> {
		public Variable(String actor, String variable) {
			super(actor,variable);
		}
		
		public String getActorInstanceName() {
			return mFirst;
		}
		
		public String getVariableName() {
			return mSecond;
		}
		
		@Override
		public String toString() {
			return mFirst+"."+mSecond;
		}
	}
	
	private SchedulingConstraints readNetworkDescription(Node node, ArtNetworkDescription network) {
		for (; node!=null; node=node.getNextSibling()) {
			if (isTag(node,"network")) {
				return readNetworkElement((Element) node, network);
			}
		}
		
		return null;
	}

	private SchedulingConstraints readNetworkElement(Element element, ArtNetworkDescription network) {
		String name=element.getAttribute("name");
		
		if (name!=null && name.equals(network.getName())) {
			Map<String,Map<String,Action>> actorMap=new HashMap<String,Map<String,Action>>();
			SchedulingConstraints stateDep=new SchedulingConstraints();
			
			// Create actor and action look-up
			for (Action action: network.getActionMap().values()) {
				ActorInstance actor=network.getActor(action);
				Map<String,Action> actionMap=actorMap.get(actor.getInstanceName());
				if (actionMap==null) {
					actionMap=new HashMap<String,Action>();
					actorMap.put(actor.getInstanceName(), actionMap);
				}
				actionMap.put(action.getName(), action);
			}
			
			// Read the actors
			for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
				if (isTag(child,"actor")) {
					readActor((Element) child, network, actorMap, stateDep);
				}
				else if (isTag(child,"decoration")) {
					readDecoration((Element) child, network, stateDep);
				}

			}
			
			return (getErrorConsole().hasErrors())? null : stateDep;
		}
		else {
			error("StateDependenceReader: Network names do not match: \""+name+"\" vs \""+network.getName()+"\"");
			return null;
		}
	}
	
	private void readActor(Element element,
			               ArtNetworkDescription network,
			               Map<String,Map<String,Action>> actorMap,
			               SchedulingConstraints stateDep) {
		String instanceName=element.getAttribute("instance-name");
		
		ActorInstance actor=null;
		for (ActorInstance a: network.getActors()) {
			if (a.getInstanceName().equals(instanceName)) {
				actor=a;
			    break;
			}
		}
		
		if (actor!=null) {
			Map<String,Action> actionMap=actorMap.get(instanceName);
			assert(actionMap!=null);
			Map<String,Variable> variableMap=new HashMap<String,Variable>();
			
			for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
				if (isTag(child,"action")) {
					readActionEffect((Element) child, actionMap, variableMap, instanceName, stateDep);
				}
				else if (isTag(child,"affinity")) {
					String cpu=readAffinity((Element) child);
					stateDep.setAffinity(actor, cpu);
				}
				else if (isTag(child,"decoration")) {
					readDecoration((Element) child, actor, stateDep);
				}
			}
		}
		else {
			error("StateDependenceReader: Actor \""+instanceName+"\" not found in network");
		}
	}
	
	private void readActionEffect(Element element, 
			                  	  Map<String,Action> actionMap,
			                      Map<String,Variable> variableMap,
			                      String inActor,
			                      SchedulingConstraints stateDep) {
		String name=element.getAttribute("name");
		Action action=actionMap.get(inActor+"."+name);
		
		if (action!=null) {
			for (Node child=element.getFirstChild(); child!=null; child=child.getNextSibling()) {
				if (isTag(child,"uses")) {
					Variable var=readVariable((Element) child, variableMap, inActor);
					if (var!=null)
						stateDep.getUses(action).add(var);
				}
				else if (isTag(child,"defines")) {
					Variable var=readVariable((Element) child, variableMap, inActor);
					if (var!=null)
						stateDep.getDefinitions(action).add(var);
				}
				else if (isTag(child,"decoration")) {
					readDecoration((Element) child, action, stateDep);
				}
			}
		}
		else {
			error("StateDependenceReader: Action \""+name+"\" not found in actor \""+inActor+"\"");
		}
	}
	
	/**
	 * @param element  A <decoration> element
	 * @param parent   object associated with decoration
	 * @param stateDep container for the object-decoration association
	 */
	private void readDecoration(Element element, Object parent, SchedulingConstraints stateDep) {
		String key=element.getAttribute("key");
		String value=element.getAttribute("value");

		if (key!=null && value!=null) {
			stateDep.getDecorations().decorate(parent, key, value);
		}
		else {
			error("StateDependenceReader: Decoration \"key\" and \"value\" attributes expected");
		}
	}
	
	private Variable readVariable(Element element, Map<String,Variable> variableMap, String inActor) {
		String id=element.getAttribute("id");
		
		if (id!=null) {
			Variable var=variableMap.get(id);
			if (var==null) {
				var=new Variable(inActor,id);
				variableMap.put(id, var);
			}
			return var;
		}
		else {
			error("StateDependenceReader: <"+element.getTagName()+"> has no attribute \"id\"");
			return null;
		}
	}	
	
	private String readAffinity(Element element) {
		String cpu=element.getAttribute("cpu");
		
		if (cpu==null) {
			error("StateDependenceReader: <affinity> has no attribute \"cpu\"");
		}
		
		return cpu;
	}
}
