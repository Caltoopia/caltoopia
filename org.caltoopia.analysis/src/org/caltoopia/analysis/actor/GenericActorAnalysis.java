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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.NullPointerException;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.ActorSchedule;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PriorityRelation;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

/**
 * Extension of ActorAnalysis for a Generic Dataflow Actor Analysis
 * Implemented using an ActorAnalysis delegate, 
 */
public class GenericActorAnalysis{

	public enum ActorInstanceType {
		SINGLE_RATE_STATIC("Single-rate Static"),
		MULTI_RATE_STATIC("Multi-rate Static"),
		CYCLO_STATIC("Cyclo-static"),
		QUASI_STATIC("Quasi-static"),
		DYNAMIC("Dynamic"),
		UNCLASSIFIED("Unclassified");
		
		private final String name;
		ActorInstanceType(String n){
			name = n;
		}
		
		public String getName(){
			return name;
		}
	}
	
	protected class AnnotatedActionProperty{
		public String actionName;
		public Integer executionTime = new Integer(0);
		public Map<String, Integer> portRates = new HashMap<String, Integer>();
		AnnotatedActionProperty(String n){
			actionName=n;
		}
	};

	
	private ActorInstance mActor;
	private ActorAnalysis actorAnalysis;
	private ActorInstanceType mActorType = ActorInstanceType.UNCLASSIFIED;
	protected Map<PortInstance,PortAnalysis> mPortMap=new HashMap<PortInstance,PortAnalysis>();
	private Map<Action, ActionAnalysis> mActionMap=new HashMap<Action,ActionAnalysis>();
	protected List<AnnotatedActionProperty> annotatedActionProperties = new ArrayList<AnnotatedActionProperty>();
    private Integer executionTime = null;
    private Integer stateSize = null;
    private Integer id = new Integer(0);
    private boolean typeAnnotated = false;
    
	public GenericActorAnalysis(ActorInstance actor, ActorAnalysis delegate) {
		mActor=actor;
		actorAnalysis=delegate;
		mActorType = ActorInstanceType.UNCLASSIFIED;
				
		stateSize = new Integer(0);
		executionTime = new Integer(0);
		for(PortInstance portInstance : mActor.getPorts()){
			PortAnalysis portAnalysis = new PortAnalysis(portInstance);
			mPortMap.put(portInstance, portAnalysis);
		}
		
		if(actorAnalysis!=null){
			for(Action action : actor.getImplementation().getActions()){
				ActionAnalysis actionAnalysis = new ActionAnalysis(action, actor);
				mActionMap.put(action, actionAnalysis);
			}				
		}
		
		//Parse annotations
		if(mActor.hasAnnotation("ActorProperty")){
			//Set execution time
			String time = mActor.getAnnotationArgumentValue("ActorProperty","WCET");
			if(time!=null)
				executionTime = new Integer(time);
		
			//Set state size
			String size = mActor.getAnnotationArgumentValue("ActorProperty","StateSize");
			if(size!=null)
				stateSize = new Integer(size);

			
			//Set actor instance type
			String type = mActor.getAnnotationArgumentValue("ActorProperty","Type");
			if(type!=null){
				typeAnnotated = true;
				if(type.equalsIgnoreCase("HSDF")){
					mActorType = ActorInstanceType.SINGLE_RATE_STATIC;					
				}
				else if(type.equalsIgnoreCase("SDF")){
					mActorType = ActorInstanceType.MULTI_RATE_STATIC;
				}
				else if(type.equalsIgnoreCase("CSDF")){
					mActorType = ActorInstanceType.CYCLO_STATIC;
				}
				else
					typeAnnotated = false;
			}
			
			//Set action execution times
			try{
				String actionProperties = mActor.getAnnotationArgumentValue("ActorProperty","ActionProperty");
				if(typeAnnotated && actionProperties==null)
					throw new NullPointerException("ActionProperty tag must exist " +
							"if actor type is annotated with HSDF/SDF/CSDF.");
				
				if(typeAnnotated && actionProperties!=null){
					for(String actionProperty: actionProperties.split(";")){
						String actionPropertyPair[] = actionProperty.split(":");
							if(actionPropertyPair.length != 2)
								throw new Exception("ActionProperty tag syntax error. " +
										"Use Eg. ActionProperty=\"action1:WCET=2, p1=1, p2=3;action2:WCET=1, p1=0, p2=4\"");
							AnnotatedActionProperty aap = new AnnotatedActionProperty(actionPropertyPair[0]);
							for(String property: actionPropertyPair[1].split(",")){
								String propertyPair[] = property.split("=");
								if(propertyPair[0].equalsIgnoreCase("WCET"))
									aap.executionTime = new Integer(propertyPair[1]);									
								else
									aap.portRates.put(propertyPair[0], new Integer(propertyPair[1]));
								
							}
							annotatedActionProperties.add(aap);
					}
					//Check if all ports have a rate in all ActionProperties
					for(AnnotatedActionProperty aap: annotatedActionProperties){
						for(PortInstance p: getActor().getPorts()){
						if(aap.portRates.get(p.getName())==null)
							throw new Exception("Type annotated actor '"+p.getActor().getInstanceName()+
									"' does not have rate for port '"+p.getName()+"'.");
						}
					}
				}
			}
			catch (Exception e){
				 System.out.println(e.getMessage());
				 System.exit(1);
			}				
						
		}		

		if(!typeAnnotated && actorAnalysis!=null){
			if(isSingleRateStaticActor())
				mActorType=ActorInstanceType.SINGLE_RATE_STATIC;
			else if(isMultiRateStaticActor())
				mActorType=ActorInstanceType.MULTI_RATE_STATIC;
			else if(isCycloStaticActor())
				mActorType=ActorInstanceType.CYCLO_STATIC;
			else if(isQuasiStaticActor())
				mActorType=ActorInstanceType.QUASI_STATIC;
			else if(isDynamicActor())
				mActorType=ActorInstanceType.DYNAMIC;	
		}
	}
	
	public boolean isTypeAnnotated(){
		return typeAnnotated;
	}
	
	public void setTypeAnnotated(boolean t){
		typeAnnotated = t;
	}
	
	public ActorInstance getActor(){
		return mActor;
	}
	
	public Integer getExecutionTime(){
		return executionTime;
	}
	
	protected void setExecutionTime(Integer t){
		executionTime = t;
		
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer t){
		id = t;		
	}
	
	public Integer getStateSize(){
		return stateSize;
	}
	
	protected void setStateSize(Integer s){
		stateSize = s;
		
	}
	
	public PortAnalysis getPortAnalysis(PortInstance p){
		assert(mPortMap.get(p)!=null);
		return mPortMap.get(p);
	}
	
	public PortAnalysis getPortAnalysis(String name){
		PortInstance portInstance=null;
		for(PortInstance p: mActor.getPorts()){
			if(p.getName().equals(name)){
				portInstance = p;
				break;
			}
		}
		if(portInstance !=null){
			assert(mPortMap.get(portInstance)!=null);
			return mPortMap.get(portInstance);
		}
		return null;
	}
	
	public ActionAnalysis getActionAnalysis(Action a){
		assert(mActionMap.get(a)!=null);
		return mActionMap.get(a);
	}
	
	public boolean executesIndefinitely() {
		return actorAnalysis.executesIndefinitely();
	}
	
	
	public Collection<State> getPotentiallyTerminalStates() {
		return actorAnalysis.getPotentiallyTerminalStates();
	}

	
	public boolean isTimingIndependent() {
		return actorAnalysis.isTimingIndependent();
	}

	
	public Collection<State> getPotentiallyTimingDependentStates() {
		return actorAnalysis.getPotentiallyTimingDependentStates();
	}

	
	public boolean hasDeterminateRates() {
		return actorAnalysis.hasDeterminateRates();
	}

	
	public Collection<State> getPotentiallyNonDeterminateStates() {
		return actorAnalysis.getPotentiallyNonDeterminateStates();
	}

	
	public boolean hasStaticSchedule() {
		return actorAnalysis.hasStaticSchedule();
	}

	
	public StaticActorSchedule getStaticSchedule() {
		return actorAnalysis.getStaticSchedule();
	}

	
	public Collection<State> getPotentiallyInputDependentStates() {
		return actorAnalysis.getPotentiallyInputDependentStates();
	}

	public ActorSchedule getSchedule() {
		return actorAnalysis.getSchedule();
	}
	
	public ActorInstanceType getActorInstanceType(){
		return mActorType;
	}
	
	public String getActorInstanceTypeAsString(){		
		return this.getActorInstanceType().getName();
	}
	
	public void setActorInstanceType(ActorInstanceType t){
		mActorType = t;
	}
	
	public boolean isSingleRateActor() {
		for (Action action: mActor.getImplementation().getActions()) {
			PortSignature portSignature = action.getPortSignature();
			for(PortInstance portInstance : portSignature.getPorts()){
				if(portSignature.getPortRate(portInstance) > 1)
					return false;
			}
		}
		return true;//executesIndefinitely();
	}
	
	public List<Integer> getAnnotatedActionExecutionTime(){
		List<Integer> executionTime = new ArrayList<Integer>();
		for(AnnotatedActionProperty aap: annotatedActionProperties){
			executionTime.add(aap.executionTime);
		}
		return executionTime;
	}
	
	
	public List<Integer> getAnnotatedPortRate(PortInstance p){
		List<Integer> portRate = new ArrayList<Integer>();
		for(AnnotatedActionProperty aap: annotatedActionProperties){
			portRate.add(aap.portRates.get(p.getName()));
		}
		return portRate;
	}
	
	public boolean areAllActionsGuarded(){
		for (Action action: mActor.getImplementation().getActions()) {
			ActionAnalysis actionAnalysis = new ActionAnalysis(action, mActor);
			if(!action.hasGuard() && !actionAnalysis.isCatchAll()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if guard matches the following format.
	 * Guards are of the form <M "op" C [ and/or M "op" C]> 
	 * where M is a port variable, "op" is "=, !=, <=, >=, <, >" 
	 * and C is a constant.
	 */
	public boolean areAllActionGuardsSimple(){
		for(Action action: getActor().getImplementation().getActions()){
			if(action.hasGuard()){
				Guard guard = action.getGuard();				
				UnionOfDisjointIntervals modeSet = guard.matchModeControlGuard();
				if(modeSet==null)
					return false;				
			}			
		}
		return true;
	}
	
	/**
	 * Checks if the guards of those non-deterministic actions 
	 * matches the following format.
	 * Guards are of the form <M "op" C [ (and/or) M "op" C]> 
	 * where M is a port variable, "op" is "=, !=, <=, >=, <, >" 
	 * and C is a constant.
	 */
	public boolean areNonDeterministicStatesSimplyGuarded(){
		ActorSchedule schedule = mActor.getImplementation().getSchedule(); 
		for(State state: schedule.getStates()){			
			if (state.getTransitions().size() > 1){
				for(Transition transition: state.getTransitions()){
					Action action = transition.getAction();
					if(!action.hasGuard())
						return false;
					Guard guard = action.getGuard();
					Map<InputLookAhead, UnionOfDisjointIntervals> modeSet 
								= guard.matchScenarioAwareGuard();
					if (modeSet == null)
						return false;					
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if guards cover entire condition space.
	 * TODO: currently, it is assumed this is manually checked.
	 * Extension to automatic check is a future work.
	 * @return
	 */
	public boolean areAllGuardsMutuallyExclusive(){
		return true;
	}
	
	private boolean isSingleRateStaticActor() {
		if(!hasStaticSchedule())
			return false;
		
		if(!isSingleRateActor())
			return false;
		for(PortInstance portInstance : mActor.getPorts()){
			List<Action> actions = new ArrayList<Action>(getStaticSchedule().getRepeatedPhase().getFlatSequence());
			assert(actions!=null);
			if(!getPortAnalysis(portInstance).isActiveInAllActions(actions))
				return false;
		}
		return true;
	}
	
	/**
	 * An actor is a multi-rate static actor (SDF) if 
	 * 1.all ports are active in all actions in the periodic phase
	 * 2.all actions in the periodic phase have the same port signature
	 * @return true if it is an SDF actor and false otherwise.
	 */
	private boolean isMultiRateStaticActor() {
		if(!hasStaticSchedule())
			return false;
		
		//check if all ports are active in the periodic phase
		List<Action> actions = new ArrayList<Action>(getStaticSchedule().getRepeatedPhase().getFlatSequence());
		for(PortInstance portInstance : mActor.getPorts()){
			if(!getPortAnalysis(portInstance).isActiveInAllActions(actions))
				return false;
		}
		
		//Check if all actions in the periodic phase have the same port signatures
		PortSignature refPortSignature = null;
		for(Action action: getStaticSchedule().getRepeatedPhase().getFlatSequence()){
			if(refPortSignature==null)
				refPortSignature = action.getPortSignature();
			else{
				if(refPortSignature.isSubsetOf(action.getPortSignature()) &&
						action.getPortSignature().isSubsetOf(refPortSignature))
						continue;
				else
					return false;
			}		
		}
		return executesIndefinitely();
	}
	
	/**
	 * An actor is cyclo-static if it has 
	 * a static schedule (transient + periodic).
	 * @return
	 */
	private boolean isCycloStaticActor() {
		return (hasStaticSchedule() && executesIndefinitely());
	}
	
	/**
	 * An actor is a quasi-static actor if
	 * 1) it has a tree-structured FSM. 
	 * 2) all feedback transitions return back to the initial state.
	 * 3) a state does not terminate.
	 * @return
	 */
	private boolean isQuasiStaticActor() {
		ActorSchedule schedule = mActor.getImplementation().getSchedule(); 
		State initialState = schedule.getInitialState();
		for(Transition transition: initialState.getTransitions()){
			State dstState = transition.getTargetState();
			List<State> visitedStates = new ArrayList<State>();
			visitedStates.add(dstState);
			if(!isQuasiStaticBranch(initialState, dstState, visitedStates))
				return false;
		}
		return true;
	}
	
	private boolean isQuasiStaticBranch(State initialState, State state, List<State> visitedStates){
		//Trivial case is a one-state FSM.
		if(state==initialState)
			return true;
		
		//Check if state does not terminate.
		//This should actually check if the guards of the actions 
		//of a state fully cover the test space.
		if(getPotentiallyTerminalStates().contains(state))
			return false;
		
		//Check for tree-like topology.
		for(Transition transition: state.getTransitions()){
			State dstState = transition.getTargetState();
			if(dstState==initialState){
				continue;
			}				
			else if(visitedStates.contains(dstState)){
				return false;
			}
			else{
				List<State> newBranch = new ArrayList<State>(visitedStates);
				newBranch.add(dstState);
				if(!isQuasiStaticBranch(initialState, dstState, newBranch))
					return false;
			}			
		}
		return true;
	}
	
	/**
	 * A dynamic actor has input-dependent behavior. 
	 * It is assumed now that any actor which has implementation is dynamic.
	 * But this can be a broad definition.
	 * @return
	 * TODO: define a dynamic actor.
	 */
	private boolean isDynamicActor() {		
		return mActor.hasImplementation();
	}
}
