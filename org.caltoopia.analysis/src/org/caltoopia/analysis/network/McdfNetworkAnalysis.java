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
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.actor.ActorAnalyzer;
import org.caltoopia.analysis.actor.ConnectionAnalysis;
import org.caltoopia.analysis.actor.GenericActorAnalysis;
import org.caltoopia.analysis.actor.PortAnalysis;
import org.caltoopia.analysis.actor.SneakyActorAnalyzer;
import org.caltoopia.analysis.actor.ConnectionAnalysis.ConnectionType;
import org.caltoopia.analysis.actor.GenericActorAnalysis.ActorInstanceType;
import org.caltoopia.analysis.actor.McdfActorAnalysis.McdfActorInstanceType;
import org.caltoopia.analysis.actor.PortAnalysis.PortType;
import org.caltoopia.analysis.actor.McdfActorAnalysis;
import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.PortInstance;

public class McdfNetworkAnalysis extends GenericNetworkAnalysis {
	

	private Integer mNumberOfModes = new Integer(0);
	private Map<ActorInstance,McdfActorAnalysis> mMcdfActorMap=new HashMap<ActorInstance,McdfActorAnalysis>();
	
	public McdfNetworkAnalysis(Network n, NetworkAnalysis na) {
		super(n,na);
		ActorAnalyzer actorAnalyzer=new SneakyActorAnalyzer();
		for (ActorInstance actor: network.getActors()) {			
			if (actor.hasImplementation()) {
				ActorImplementation actorImpl=actor.getImplementation();
				McdfActorAnalysis analysis=new McdfActorAnalysis(actor,actorAnalyzer.analyze(actorImpl));
				mMcdfActorMap.put(actor, analysis);				
			}			
		}	

		//Set actor modes
		for (ActorInstance actor: network.getActors()) {
			McdfActorInstanceType actorType= getMcdfActorAnalysis(actor).getMcdfActorInstanceType();
			if(actorType==McdfActorInstanceType.SWITCH || actorType==McdfActorInstanceType.TUNNEL){
				for(PortInstance portInstance: actor.getOutputPorts()){
					assignActorModes(portInstance, getPortAnalysis(portInstance).getMode());					
				}
			}
		}
		
		//Set number of modes
		for (ActorInstance actor: network.getActors()) {
			if(getMcdfActorAnalysis(actor).getMode()!=null)
				if(getMcdfActorAnalysis(actor).getMode().compareTo(mNumberOfModes) > 0 )
					mNumberOfModes=getMcdfActorAnalysis(actor).getMode();
		}
		
		//Set connection types
		for(Connection connection: network.getConnections()){		
			PortAnalysis srcPortAnalysis = getPortAnalysis(connection.getProducerPort());
			PortAnalysis dstPortAnalysis = getPortAnalysis(connection.getConsumerPort());	
			
			//Assign connection type
			if (srcPortAnalysis.getPortType()==PortType.DATA &&
					dstPortAnalysis.getPortType()==PortType.CONTROL){					
						//If source is data dependent and destination is not, then it is a control port.
						McdfActorAnalysis srcActorAnalysis = 
								getMcdfActorAnalysis(connection.getProducerPort().getActor());
						McdfActorAnalysis dstActorAnalysis = 
								getMcdfActorAnalysis(connection.getConsumerPort().getActor());
						if(!srcActorAnalysis.isDataDependent() && 
								dstActorAnalysis.isDataDependent()){
							getConnectionAnalysis(connection).setConnectionType(ConnectionType.CONTROL);
						}
			}
			else
				getConnectionAnalysis(connection).setConnectionType(ConnectionType.DATA);
		}
		
		//Set MC, Modal and Amodal actors
		for (ActorInstance actor: network.getActors()) {
			McdfActorAnalysis actorAnalysis = getMcdfActorAnalysis(actor);
			boolean flag=false;
			if(getGenericActorAnalysis(actor).getActorInstanceType()==ActorInstanceType.SINGLE_RATE_STATIC){
				for(Connection connection: getControlConnections()){
					flag = true;
					if(!connection.getProducerPort().getActor().equals(actor)){
						flag = false;
						break;
					}
				}
				if(flag) {	
					actorAnalysis.setMcdfActorInstanceType(McdfActorInstanceType.MC);
				}
				else{
					if(actorAnalysis.getMode()==null)
						actorAnalysis.setMcdfActorInstanceType(McdfActorInstanceType.AMODAL);
					else
						actorAnalysis.setMcdfActorInstanceType(McdfActorInstanceType.MODAL);
				}
			}			
		}
		
	}
	
	public Integer getNumberOfModes() {
		return mNumberOfModes;
	}
	
	public McdfActorAnalysis getMcdfActorAnalysis(ActorInstance a){
		assert(mMcdfActorMap.get(a)!=null);
		return mMcdfActorMap.get(a);
	}
	
	public PortAnalysis getPortAnalysis(PortInstance p){
		assert(getMcdfActorAnalysis(p.getActor())!=null);
		return getMcdfActorAnalysis(p.getActor()).getPortAnalysis(p);
	}
	
	//Assign modes to actors
	private void assignActorModes(PortInstance portInstance, Integer mode){
		if(mode!=null){
			for(Connection connection: network.getIncidentConnections(portInstance)){
				ActorInstance dstActor = connection.getConsumerPort().getActor();
				if(getGenericActorAnalysis(dstActor).getActorInstanceType()==ActorInstanceType.SINGLE_RATE_STATIC &&
						getMcdfActorAnalysis(dstActor).getMode() == null){				
					getMcdfActorAnalysis(dstActor).setMode(mode);
					for(PortInstance p: dstActor.getOutputPorts()){
						assignActorModes(p, mode);					
					}
				}
				else 
					return;
			}
		}
	}
	
 	public List<Connection> getControlConnections(){
 		List<Connection> controlConnections= new ArrayList<Connection>();
 		for (Connection connection: network.getConnections()) {	
			if(getConnectionAnalysis(connection).getConnectionType()==ConnectionAnalysis.ConnectionType.CONTROL)
				controlConnections.add(connection);				
		}
 		return controlConnections;
 	}
	
 	public List<ActorInstance> getModeControlActors(){
 		List<ActorInstance> mcActors = new ArrayList<ActorInstance>();
 		for (ActorInstance actor: network.getActors()) {
			if(getMcdfActorAnalysis(actor).getMcdfActorInstanceType()==McdfActorInstanceType.MC)
					mcActors.add(actor);
		}
 		return mcActors;
 	}
	
 	public List<ActorInstance> getDataDependentActors(){
 		List<ActorInstance> dataDependentActors = new ArrayList<ActorInstance>();
 		for (ActorInstance actor: network.getActors()) {
 			McdfActorInstanceType actorType=getMcdfActorAnalysis(actor).getMcdfActorInstanceType();
			if(actorType==McdfActorInstanceType.SELECT || 
					actorType==McdfActorInstanceType.SWITCH ||
					actorType==McdfActorInstanceType.TUNNEL){
				dataDependentActors.add(actor);
			}
		}
 		return dataDependentActors;
 	}

	public boolean isModeControlledDataflow(){
		//Check if all actors are classified
		for (ActorInstance actor: network.getActors()) {
			if(getMcdfActorAnalysis(actor).getMcdfActorInstanceType()==McdfActorInstanceType.UNCLASSIFIED){
				return false;
			}
		}
		
		//Check if there are as many control channels as getDataDependentActors actors
		if(getDataDependentActors().size() != getControlConnections().size()){
			return false;
		}
		
		//Check if there is exactly one MC actor
		if(getModeControlActors().size() != 1){
			return false;
		}		
		
		return true;
	}		
}

