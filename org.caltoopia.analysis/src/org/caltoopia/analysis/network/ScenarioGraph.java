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
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis.ControlTokensPerAction;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.ast2ir.Stream;

/**
 *The ScenarioGraph class encapsulates a Synchronous Dataflow (SDF) 
 *graph representation of a scenario.
 */
public class ScenarioGraph{
	//The assigned name of the graph
	private String name;
		
	//list of actors and their corresponding scenario properties that are active in the scenario 
	private Map<ActorInstance, ScenarioAwareActorAnalysis.Scenario> actorsMap = 
			new HashMap<ActorInstance, ScenarioAwareActorAnalysis.Scenario>();
	
	//TODO: connections are not yet implemented
	//list of connections active in the scenario and their corresponding initial tokens
	private Map<Connection, Integer> connectionsMap = new HashMap<Connection, Integer>();

	//The set of control-control-token tuples that define this scenario
	private Set<Set<ControlTokensPerAction>> controlTokens = new HashSet<Set<ControlTokensPerAction>>();
	
	//constructor
	public ScenarioGraph(String n){
		name = n;
	}
	
	//set name
	public void setName(String n){
		name = n;
	}
	
	//set actors map
	public void setActorsMap(Map<ActorInstance, ScenarioAwareActorAnalysis.Scenario> am){
		actorsMap = am;
	}
	
	//add actor 
	public void addActor(ActorInstance a, ScenarioAwareActorAnalysis.Scenario s){
		actorsMap.put(a,s);
	}
	
	//set actors map
	public void setConnectionsMap(Map<ActorInstance, ScenarioAwareActorAnalysis.Scenario> am){
		actorsMap = am;
	}
	
	//add actor 
	public void addConnection(Connection c, Integer t){
		connectionsMap.put(c,t);
	}
	
	//set control tokens
	public void setControlTokens(Set<Set<ControlTokensPerAction>> c){
		controlTokens = c;
	}
	
	//add control token
	public void addControlToken(Set<ControlTokensPerAction> c){
		controlTokens.add(c);
	}
	
	//get name of the graph
	public String getName(){
		return name;
	}
	
	//get the list of actors active in the scenario graph
	public Map<ActorInstance, ScenarioAwareActorAnalysis.Scenario> getActors(){
		return actorsMap;
	}
	
	//get the list of actors active in the scenario graph
	public Map<ActorInstance, ScenarioAwareActorAnalysis.Scenario> getConnections(){
		return actorsMap;
	}
		
	//get control tokens
	public Set<Set<ControlTokensPerAction>> getControlTokens(){
		return controlTokens;
	}	
	
	public void print(Stream stream){
		stream.println("\tScenarioGraph: "+name+ ", actors: "+ actorsMap.size());
		if(controlTokens!=null){
			stream.println("ControlTokens: ");
			for(Set<ControlTokensPerAction> sta: controlTokens){
				stream.println("ControlTokensTuple: ");
				for(ControlTokensPerAction ta: sta){
					ta.print(stream);
				}
			}
		}
		
		for(Map.Entry<ActorInstance, ScenarioAwareActorAnalysis.Scenario> e:
			actorsMap.entrySet()){
			if(e.getValue().getTransition()!=null)
				stream.println("\t\t"+e.getKey().getInstanceName()+" "+e.getValue().getTransition().getAction().getName());
			else
				stream.println("\t\t"+e.getKey().getInstanceName());
		}
	}
}

