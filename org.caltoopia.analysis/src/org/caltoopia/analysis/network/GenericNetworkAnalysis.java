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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.actor.ActionAnalysis;
import org.caltoopia.analysis.actor.PortAnalysis;
import org.caltoopia.analysis.actor.ConnectionAnalysis;
import org.caltoopia.analysis.actor.GenericActorAnalysis;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.PortInstance;

public class GenericNetworkAnalysis{

	protected Network network;
	protected NetworkAnalysis networkAnalysis;
	
	
	public GenericNetworkAnalysis(Network n, NetworkAnalysis mDelegate){
		network = n;
		networkAnalysis = mDelegate;
	}
	
	public Network getNetwork(){
		return network;
	}
	
	public List<ActorInstance> getSingleRateStaticActors(){
		return networkAnalysis.getSingleRateStaticActors();
	}
	
	public List<ActorInstance> getMultiRateStaticActors(){
		return networkAnalysis.getMultiRateStaticActors();
	}
	
	public List<ActorInstance> getCycloStaticActors(){
		return networkAnalysis.getCycloStaticActors();
	}
	
	public List<ActorInstance> getQuasiStaticActors(){
		return networkAnalysis.getQuasiStaticActors();
	}
	
	
	public List<ActorInstance> getDynamicActors(){
 		return networkAnalysis.getDynamicActors();
	}
	
	public List<ActorInstance> getUnclassifiedActors(){
		return networkAnalysis.getUnclassifiedActors();
	}
	
	public ConnectionAnalysis getConnectionAnalysis(Connection c){
		return networkAnalysis.getConnectionAnalysis(c);
	}
	
	public PortAnalysis getPortAnalysis(PortInstance p){
		return networkAnalysis.getPortAnalysis(p);
	}
	
	public GenericActorAnalysis getGenericActorAnalysis(ActorInstance a){
		return networkAnalysis.getGenericActorAnalysis(a);
	}
	
	public ActorInstance getActorInstance(String n){
		for(ActorInstance actor: network.getActors())
			if (actor.getInstanceName().equals(n))
				return actor;
		return null;
	}
	
	public boolean isSingleRateStaticDaflowGraph(){
		return (getSingleRateStaticActors().size()==network.getActors().size());
	}
	
	public boolean isMultiRateStaticDaflowGraph(){
		int numberOfStaticActors=0;
		numberOfStaticActors+=getSingleRateStaticActors().size();
		numberOfStaticActors+=getMultiRateStaticActors().size();
		return (numberOfStaticActors==network.getActors().size());
	}
	
	public boolean isCycloStaticDaflowGraph(){
		int numberOfCycloStaticActors=0;
		numberOfCycloStaticActors+=getSingleRateStaticActors().size();
		numberOfCycloStaticActors+=getMultiRateStaticActors().size();
		numberOfCycloStaticActors+=getCycloStaticActors().size();
		return (numberOfCycloStaticActors==network.getActors().size());
	}
	
	public Map<Action,Integer> getRate(PortInstance p){
		Map<Action, Integer> portRate = new HashMap<Action,Integer>();
		GenericActorAnalysis actorAnalysis = networkAnalysis.getGenericActorAnalysis(p.getActor());
		if(isSingleRateStaticDaflowGraph()||isMultiRateStaticDaflowGraph()){
			Action action = actorAnalysis.getStaticSchedule().getRepeatedPhase().getFlatSequence().get(0);
			portRate.put(action,actorAnalysis.getActionAnalysis(action).getPortRate(p));
		}
		else if(isCycloStaticDaflowGraph()){
			for(Action action: actorAnalysis.getStaticSchedule().getRepeatedPhase().getFlatSequence()){
				portRate.put(action, actorAnalysis.getActionAnalysis(action).getPortRate(p));
			}
		}
		return portRate;
	}
	
	public List<Integer> getAnnotatedRate(PortInstance p){
		List<Integer> portRate = new ArrayList<Integer>();
		GenericActorAnalysis actorAnalysis = networkAnalysis.getGenericActorAnalysis(p.getActor());
		if(isSingleRateStaticDaflowGraph()||isMultiRateStaticDaflowGraph())
				portRate.add(actorAnalysis.getAnnotatedPortRate(p).get(0));		
		else if(isCycloStaticDaflowGraph()){
				for(Integer rate: actorAnalysis.getAnnotatedPortRate(p)){
					portRate.add(rate);
				}
		}
		return portRate;		
	}
	
	/**
	 * Returns the execution times of actions as a list. Call this method if
	 * the actor is type annotated. Otherwise, use 'getExecutionTime'.
	 * @param ActorInstance a
	 * @return the execution times of actions as a list
	 */
	public List<Integer> getAnnotatedExecutionTime(ActorInstance a){
		List<Integer> executionTimes = new ArrayList<Integer>();
		GenericActorAnalysis actorAnalysis = networkAnalysis.getGenericActorAnalysis(a);
		if(isSingleRateStaticDaflowGraph()||isMultiRateStaticDaflowGraph()){
			if(actorAnalysis.getExecutionTime().intValue() > 0)
				//Take the actor annotation (if the actor has WCET annotation)
				executionTimes.add(actorAnalysis.getExecutionTime());
			else
				//Take the action annotation. It is guaranteed at this point that
				//the actor has annotated action properties since it is type annotated
				executionTimes.add(actorAnalysis.getAnnotatedActionExecutionTime().get(0));		
		}
		else if(isCycloStaticDaflowGraph()){
			for(Integer time: actorAnalysis.getAnnotatedActionExecutionTime()){
				executionTimes.add(time);
			}
		}
		return executionTimes;		
	}
	
	/**
	 * 1) Case 1: If the type of the ActorInstance of the PortInstance is annotated, 
	 * all rates of the port must come from annotations.
	 * 2) Case 2: If the actor is not type annotated but has implementation,  
	 * all rates of the port must come from analysis. 
	 * 3) Case 3: If the actor has no implementation and not type-annotated, the 
	 * port has no rate.
	 * @param PortInstance p
	 * @return The rates of port p in as a comma-separated string
	 */
	public String getRateAsString(PortInstance p){
		String rate = "";
		List<Integer> portRate = null;
		if(getGenericActorAnalysis(p.getActor()).isTypeAnnotated())
			portRate = getAnnotatedRate(p);
		else if(p.getActor().hasImplementation())
			portRate= new ArrayList<Integer>(getRate(p).values());
		
		if(portRate!=null){
			for(Iterator<Integer> it= portRate.iterator(); it.hasNext();){
				Integer value = it.next();
				rate += value.toString();
				if(it.hasNext())
					rate += ",";
			}
		}
		return rate;
	}
	
	public Map<Action,Integer> getExecutionTime(ActorInstance actor){
		Map<Action, Integer> executionTime = new HashMap<Action,Integer>();
		GenericActorAnalysis actorAnalysis = networkAnalysis.getGenericActorAnalysis(actor);
		if(isSingleRateStaticDaflowGraph()||isMultiRateStaticDaflowGraph()){
			executionTime.put(null, actorAnalysis.getExecutionTime());
		}
		else if(isCycloStaticDaflowGraph()){
			for(Action action: actorAnalysis.getStaticSchedule().getRepeatedPhase().getFlatSequence()){
				executionTime.put(action, actorAnalysis.getActionAnalysis(action).getExecutionTime());
			}
		}
		return executionTime;
	}
	
	public String getExecutionTimeAsString(ActorInstance actor){
		String executionTime = getGenericActorAnalysis(actor).getExecutionTime().toString();
		List<Integer> executionTimes = null;
		if(getGenericActorAnalysis(actor).isTypeAnnotated())
			executionTimes = getAnnotatedExecutionTime(actor);
		else if(actor.hasImplementation())
			executionTimes = new ArrayList<Integer>(getExecutionTime(actor).values());
		
		if(executionTimes!=null){
			for(Iterator<Integer> it= executionTimes.iterator(); it.hasNext();){
				Integer value = it.next();
				executionTime += value.toString();
				if(it.hasNext())
					executionTime += ",";
			}
		}
		return executionTime;
	}
	
	public String getExecutionTimeAsString(ActorInstance actor, Action action){
		GenericActorAnalysis gaa = getGenericActorAnalysis(actor);
		String execTime = gaa.getExecutionTime().toString();
		if(action!=null){
			ActionAnalysis aa = gaa.getActionAnalysis(action);
			if(aa!=null)
				return aa.getExecutionTime().toString();
			
		}
		return execTime;
	}
}
