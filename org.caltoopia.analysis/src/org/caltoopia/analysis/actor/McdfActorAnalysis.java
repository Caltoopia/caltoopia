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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.caltoopia.analysis.actor.PortAnalysis.PortType;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.PortInstance.Direction;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

/**
 * Extension of ActorAnalysis for mode-controlled dataflow (MCDF)
 * Implemented using an ActorAnalysis delegate, 
 */
public class McdfActorAnalysis extends GenericActorAnalysis {

	public enum McdfActorInstanceType {
		MC("mc"),
		SWITCH("switch"),
		SELECT("select"),
		TUNNEL("tunnel"),
		MODAL("modal"),
		AMODAL("amodal"),
		UNCLASSIFIED("unclassified");
		
		private final String name;
		McdfActorInstanceType(String n){
			name=n;
		}
		
		public String getName() {
	        return name;
	    }
	}
	
	
	private McdfActorInstanceType mMcdfActorType = McdfActorInstanceType.UNCLASSIFIED;
	private Integer mMode = null;  
	public static Map<McdfActorInstanceType, String> actorTypes = new HashMap<McdfActorInstanceType, String>();
	
	public McdfActorAnalysis(ActorInstance actor, ActorAnalysis delegate)
	{
		super(actor, delegate);
		
		//Set actor instance type if it is already annotated by its super GenericActorAnalysis 
		String type = getActor().getAnnotationArgumentValue("ActorProperty","Type");
		if(type!=null && !isTypeAnnotated()){
			setTypeAnnotated(true);
			if(type.equalsIgnoreCase("select")){
				mMcdfActorType = McdfActorInstanceType.SELECT;					
			}
			else if(type.equalsIgnoreCase("switch")){
				mMcdfActorType = McdfActorInstanceType.SWITCH;	
			}
			else if(type.equalsIgnoreCase("tunnel")){
				mMcdfActorType = McdfActorInstanceType.TUNNEL;
			}
			else if(type.equalsIgnoreCase("mc")){
				mMcdfActorType = McdfActorInstanceType.MC;
			}
			else if(type.equalsIgnoreCase("amodal")){
				mMcdfActorType = McdfActorInstanceType.AMODAL;
			}
			else if(type.equalsIgnoreCase("modal")){
				mMcdfActorType = McdfActorInstanceType.MODAL;
			}
			else
				setTypeAnnotated(false);
		}
		
		//Set action execution times
		try{
			if(isTypeAnnotated() && isDataDependent()){
				String controlPort = getActor().getAnnotationArgumentValue("ActorProperty","ControlPort");
				if(controlPort==null)
					throw new NullPointerException("ControlPort tag must exist " +
							"if actor type is annotated with Select/Switch/Tunnel.");
				//Find the control port
				getPortAnalysis(controlPort).setPortType(PortType.CONTROL);
				
				String modes = getActor().getAnnotationArgumentValue("ActorProperty","Mode");
				if(isTypeAnnotated() && modes==null)
					throw new NullPointerException("Mode tag must exist " +
							"if actor type is annotated with Select/Switch/Tunnel.");
				for(String property: modes.split(",")){
					String propertyPair[] = property.split("=");
					if(getMcdfActorInstanceType()==McdfActorInstanceType.SELECT){
						if(getPortAnalysis(propertyPair[0]).getPortInstance().getDirection()!=Direction.IN)
							throw new NullPointerException("Mode tag error. Port '"+propertyPair[0]+
									"':Only input ports of a select actor can have modes");
							getPortAnalysis(propertyPair[0]).setMode(new Integer(propertyPair[1]));
					}
					else if(getMcdfActorInstanceType()==McdfActorInstanceType.SWITCH){
						if(getPortAnalysis(propertyPair[0]).getPortInstance().getDirection()!=Direction.OUT)
							throw new NullPointerException("Mode tag error. Port '"+propertyPair[0]+
									"':Only output ports of a switch actor can have modes");
							getPortAnalysis(propertyPair[0]).setMode(new Integer(propertyPair[1]));
					}
					else if(getMcdfActorInstanceType()==McdfActorInstanceType.TUNNEL){
						if(getPortAnalysis(propertyPair[0]).getPortType()==PortType.CONTROL)
						throw new NullPointerException("Mode tag error. Port '"+propertyPair[0]+
								"':Control port of a tunnel actor cannot have a mode");
						getPortAnalysis(propertyPair[0]).setMode(new Integer(propertyPair[1]));
					}				
				}
			}
		}
		catch (Exception e){
			 System.out.println(e.getMessage());
			 System.exit(1);
		}				
		if(!isTypeAnnotated() && isModalActor()){
			if(isSwitchActor())
				setMcdfActorInstanceType(McdfActorInstanceType.SWITCH);
			else if(isSelectActor())
				setMcdfActorInstanceType(McdfActorInstanceType.SELECT);
			else if(isTunnelActor())
				setMcdfActorInstanceType(McdfActorInstanceType.TUNNEL);
		}
		
		
		//Set port type
		for(PortInstance portInstance: getActor().getPorts()){
			if(getPortAnalysis(portInstance).isControlPort())
				getPortAnalysis(portInstance).setPortType(PortType.CONTROL);
			else
				getPortAnalysis(portInstance).setPortType(PortType.DATA);
		}
		
		//Set port mode
		if(!isTypeAnnotated() && isDataDependent()){
			for(Action action: getActor().getImplementation().getActions()){
				PortSignature portSignature = action.getPortSignature();
				if(action.hasGuard()){
					Guard guard = action.getGuard();				
					UnionOfDisjointIntervals modeSet = guard.matchModeControlGuard();
					// TODO: extend this to deal with sets of modes, now only singleton are used
					Long m=(modeSet!=null && modeSet.asSet().size()==1)? modeSet.asSet().iterator().next() : null;
					if(m!=null){
						for(PortInstance portInstance: getActor().getPorts()){
							if(portSignature.getPortRate(portInstance)==1){
								if(getMcdfActorInstanceType() == McdfActorInstanceType.SWITCH &&
									portInstance.getDirection()==Direction.OUT){
									getPortAnalysis(portInstance).setMode(new Integer(m.intValue()));
								}
								else if(getMcdfActorInstanceType() == McdfActorInstanceType.SELECT &&
									portInstance.getDirection()==Direction.IN && 
									getPortAnalysis(portInstance).getPortType()!=PortType.CONTROL){
									getPortAnalysis(portInstance).setMode(new Integer(m.intValue()));
								}
								else if(getMcdfActorInstanceType() == McdfActorInstanceType.TUNNEL &&
										getPortAnalysis(portInstance).getPortType()!=PortType.CONTROL){
									getPortAnalysis(portInstance).setMode(new Integer(m.intValue()));
								}
							}
						}						
					}
				}			
			}
		}		
	}
	
	public McdfActorInstanceType getMcdfActorInstanceType(){
		return mMcdfActorType;
	}
	
	public String getMcdfActorInstanceTypeAsString(){	
			if(this.getMcdfActorInstanceType()==McdfActorInstanceType.MODAL)
				return "mode"+getMode().toString();
			return getMcdfActorInstanceType().getName();
	}
	
	public void setMcdfActorInstanceType(McdfActorInstanceType t){
		assert(getMcdfActorInstanceType()!=McdfActorInstanceType.UNCLASSIFIED);
		mMcdfActorType = t;
	}

	public Integer getMode(){
		return mMode;
	}
	
	public void setMode(Integer m){
		mMode = m;
	}
	
	private boolean isModalActor(){
		if(!isSingleRateActor()){
			return false;
		}
		if(getActorInstanceType()==ActorInstanceType.SINGLE_RATE_STATIC){
			return false;
		}
		if(getControlPorts().size()!=1){
			return false;
		}
		PortAnalysis portAnalysis = new PortAnalysis(getControlPorts().get(0));
		if(!portAnalysis.isActiveInAllActions()){
			return false;
		}
		if(!areAllActionsGuarded()){
			return false;
		}
		if(!areAllGuardsMutuallyExclusive()){
			return false;
		}
		return true;
	}	

	
	private List<PortInstance> getControlPorts(){
		List<PortInstance> controlPorts=new ArrayList<PortInstance>();
		for(PortInstance portInstance : getActor().getInputPorts()){
			PortAnalysis portAnalysis = new PortAnalysis(portInstance);
			if(portAnalysis.isControlPort())
				controlPorts.add(portInstance);
		}
		return controlPorts;
	}
	
	
	public boolean isDataDependent(){
		McdfActorInstanceType actorType=getMcdfActorInstanceType();
		if(actorType==McdfActorInstanceType.SELECT || 
				actorType==McdfActorInstanceType.SWITCH ||
				actorType==McdfActorInstanceType.TUNNEL)
			return true;
		return false;
	}
	
	private boolean isSwitchActor(){
		if(!isModalActor()){
			return false;
		}
		if(getActor().getImplementation().getInputPorts().size()!=2){
			return false;
		}
		if(getActor().getImplementation().getOutputPorts().size() < 1){
			return false;
		}
		
		PortInstance controlPort = getControlPorts().get(0);
		for(PortInstance dataPort: getActor().getInputPorts()){
			if(!dataPort.equals(controlPort)){
				PortAnalysis portAnalysis = new PortAnalysis(dataPort);
				if(!portAnalysis.isActiveInAllActions()){
					return false;
				}					
			}
		}
		
		//Exactly one output port is active per action
		for (Action action: getActor().getImplementation().getActions()) {
			ActionAnalysis actionAnalysis = new ActionAnalysis(action, getActor());
			if(!actionAnalysis.isCatchAll()){
				if(actionAnalysis.getNumberOfActiveOutputPorts() > 1){
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isSelectActor(){
		if(!isModalActor()){
			return false;
		}
		if(getActor().getImplementation().getOutputPorts().size()!=1){
			return false;
		}
		if(getActor().getImplementation().getInputPorts().size() < 1){
			return false;
		}
		
		for(PortInstance dataPort: getActor().getImplementation().getOutputPorts()){
			PortAnalysis portAnalysis = new PortAnalysis(dataPort);
			if(!portAnalysis.isActiveInAllActions()){
				return false;
			}					
		}
		
		//Exactly one input port is active per action
		for (Action action: getActor().getImplementation().getActions()) {
			ActionAnalysis actionAnalysis = getActionAnalysis(action);
			if(!actionAnalysis.isCatchAll()){
				if(actionAnalysis.getNumberOfActiveInputPorts() > 2){
					return false;
				}
			}
		}
		return true;
	}
	

	private boolean isTunnelActor(){
		if(!isModalActor()){
			return false;
		}
		if(getActor().getImplementation().getOutputPorts().size()!=1){
			return false;
		}
		if(getActor().getImplementation().getInputPorts().size() !=2){
			return false;
		}
		
		//Exactly two ports are active per action
		for (Action action: getActor().getImplementation().getActions()) {
			ActionAnalysis actionAnalysis = getActionAnalysis(action);
			if(!actionAnalysis.isCatchAll()){
				if(actionAnalysis.getNumberOfActiveInputPorts()+
						actionAnalysis.getNumberOfActiveOutputPorts() > 2){
					return false;
				}
			}
		}
		
		if(getActor().getImplementation().getActions().size()!=3 &&
				getActor().getImplementation().getActions().size()!=4){
			return false;
		}
		return true;
	}	
}
