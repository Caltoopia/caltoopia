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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.actor.ActionAnalysis;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

public class PortAnalysis {
	
	public enum PortType {
		CONTROL,
		DATA,
		UNCLASSIFIED
	}
	
	private PortInstance port;
	private Integer mode = null;
	private PortType type = PortType.UNCLASSIFIED;	
	
	public PortAnalysis(PortInstance p){
		port = p;		
	}
	
	public void setPortType(PortType t){
		type = t;
	}
	
	public PortType getPortType(){
		return type;
	}
	
	public Integer getMode(){
		return mode;
	}
	
	public PortInstance getPortInstance(){
		return port;
	}
	
	public void setPortInstance(PortInstance p){
		port = p;
	}
	
	public boolean isActiveInAllActions(){
		boolean skip;
		ActorAnalyzer actorAnalyzer=new SneakyActorAnalyzer();
		ActorImplementation actorImplementation=port.getActor().getImplementation();
		GenericActorAnalysis actorAnalysis = new GenericActorAnalysis(port.getActor(),actorAnalyzer.analyze(actorImplementation));
		for (Action action: port.getActor().getImplementation().getActions()) {
			//continue if action is in the transient
			skip = false;
			if(actorAnalysis.hasStaticSchedule() && actorAnalysis.getStaticSchedule().getTransientPhase()!=null){
				for(Action initAction: actorAnalysis.getStaticSchedule().getTransientPhase().getFlatSequence()){
					if(action.equals(initAction))
						skip = true;
				}
			}

			ActionAnalysis actionAnalysis = new ActionAnalysis(action, port.getActor());
			if(!skip && !actionAnalysis.isCatchAll()){
				PortSignature portSignature = action.getPortSignature();				
				if(portSignature.getPortRate(port)==0)
					return false;
			}
		}
		return true;
	}
	
	public boolean isActiveInAllActions(List<Action> actions){
		for(Iterator<Action> i = actions.iterator(); i.hasNext();){
			Action action = i.next();
			ActionAnalysis actionAnalysis = new ActionAnalysis(action, port.getActor());
			if(!actionAnalysis.isCatchAll()){
				PortSignature portSignature = action.getPortSignature();				
				if(portSignature.getPortRate(port)==0)
					return false;
			}
		}
		return true;
	}
	
	public void setMode(Integer m){		
		try{
			assert(mode!=null);
			mode = m;
		}
		catch(Exception e){
			System.err.println("Duplicate mode assignement detected for port '"+port.getName()+"'.");
			System.exit(1);
		}		
	}
	

	/**
	 * Checks if a port is a control port. A port is a control port if 
	 * its variable is used in all guards of the actor. Unguarded actions 
	 * are skipped. Approach: InputLookAhead of size one is assumed. Hence,
	 * the port should be in the InputLookAhead.
	 * @param 
	 * @return true if it is a control port and false, otherwise.
	 */
	public boolean isControlPort(){	
		//Port must be an input port
		if(port.getDirection()==PortInstance.Direction.OUT)
			return false;

		if(port.getActor().getImplementation()!=null){
			for (Action action: port.getActor().getImplementation().getActions()) {
				//Every action must have a guard
				if(!action.hasGuard())
					return false;
				
				Guard guard = action.getGuard();
				//One of the InputLookAheads must be from this port
				for(InputLookAhead lookAhead: guard.getInputLookAheads()){
					if(port.equals(lookAhead.getPort())){
						return true;	
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a port is a partial-control port. A port is a partial-control 
	 * port if its variable is used in at least one of the guards of the actor. 
	 * Approach: InputLookAhead of size one is assumed. Hence, the port should 
	 * be in the InputLookAhead. 
	 * @return
	 */
	public boolean isPartialControlPort(){
		//Port must be an input port
		if(port.getDirection()==PortInstance.Direction.OUT)
			return false;
		
		if(port.getActor().getImplementation()!=null){
			for (Action action: port.getActor().getImplementation().getActions()) {
				//Every action must have a guard
				if(action.hasGuard()){
					Guard guard = action.getGuard();
					
					Map<InputLookAhead, UnionOfDisjointIntervals> modeSet 
					= guard.matchScenarioAwareGuard();
					if (modeSet == null)
						return false;	
		
					//One of the InputLookAheads must be from this port
					for(InputLookAhead lookAhead: modeSet.keySet()){
						if(port.equals(lookAhead.getPort())){
							return true;	
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean isModal(){
		return getMode()!=null;
	}
}
