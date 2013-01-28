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
package org.caltoopia.analysis.actor;

import java.util.Map;
import java.util.HashMap;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;

public class ActionAnalysis {
	private Action action;
	private ActorInstance actor;
	private Integer executionTime;
	private Integer stateSize;
	private Map<PortInstance, Integer> portRate = new HashMap<PortInstance,Integer>();

	ActionAnalysis(Action a, ActorInstance ai){
		action = a;
		actor = ai;
		executionTime = new Integer(0);
		stateSize = new Integer(0);
		
		for(PortInstance portInstance: actor.getPorts()){
			portRate.put(portInstance, new Integer(action.getPortSignature().getPortRate(portInstance)));
		}
		
		if(action.hasAnnotation("ActionProperty")){
			//Set execution time
			String time = action.getAnnotationArgumentValue("ActionProperty","WCET");
			if(time!=null)
				executionTime = new Integer(time);
		
			//Set state size
			String size = action.getAnnotationArgumentValue("ActionProperty","StateSize");
			if(size!=null)
				stateSize = new Integer(size);			
		}
	}
	
	public Integer getExecutionTime(){
		return executionTime;
	}
	
	public void SetExecutionTime(Integer i){
		executionTime = i;
	}
	
	public Integer getStateSize(){
		return stateSize;
	}
	
	public void SetStateSize(Integer i){
		stateSize = i;
	}
	
	public Integer getPortRate(PortInstance p){
		return portRate.get(p);
	}
	
	public void setPortRate(PortInstance p, Integer r){
		portRate.put(p, r);
	}
	
	public boolean isCatchAll(){
		if(actor.getImplementation().getSchedule().getStates().size() > 2){
		    return false;
		}
		for(State state: actor.getImplementation().getSchedule().getStates()){
			if(actor.getImplementation().getSchedule().getStates().size() == 2)
				continue;
			for(Transition transition: state.getTransitions()){
				if(transition.getAction().equals(action)){
					if(state.getPriorityRelation().getDescendants(transition).size() > 0)
						return false;
					if(state.getPriorityRelation().getAncestors(transition).size() == 0)
						return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSingleRate(){
		PortSignature portSignature = action.getPortSignature();
		for(PortInstance portInstance : portSignature.getPorts()){
			if(portSignature.getPortRate(portInstance) > 1)
				return false;
		}
		return true;
	}
	
	public boolean isStateModifying(){
		return false;
	}
	
	public boolean isStateReading(){
		return false;
	}
	
	public int getNumberOfActiveOutputPorts(){
		int counter=0;
		PortSignature portSignature = action.getPortSignature();			
		for (PortInstance portInstance: actor.getImplementation().getOutputPorts()) {	
			if(portSignature.getPortRate(portInstance) > 0)
					counter++;
		}
		return counter;
	}
	
	public int getNumberOfActiveInputPorts(){
		int counter=0;
		PortSignature portSignature = action.getPortSignature();			
		for (PortInstance portInstance: actor.getImplementation().getInputPorts()) {	
			if(portSignature.getPortRate(portInstance) > 0)
					counter++;
		}
		return counter;
	}
}
