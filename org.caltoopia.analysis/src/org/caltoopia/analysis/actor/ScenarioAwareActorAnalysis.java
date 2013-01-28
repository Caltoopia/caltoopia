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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.actor.GenericActorAnalysis.AnnotatedActionProperty;
import org.caltoopia.analysis.actor.McdfActorAnalysis.McdfActorInstanceType;
import org.caltoopia.analysis.actor.PortAnalysis.PortType;
import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.ActorSchedule;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.air.PortInstance.Direction;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ast2ir.Stream;

public class ScenarioAwareActorAnalysis extends GenericActorAnalysis {

	/**
	 * The ScenarioAwareActorInstanceType class captures the different
	 * scenario-aware actor types. 	 
	 */
	public enum ScenarioAwareActorInstanceType {
		// scenario-aware detector actor
		SA_DETECTOR("sa_detector"),
		
		// scenario-aware static actor can be either SDF or CSDF actor.
		SA_STATIC("sa_static"),
		
		// scenario-aware dynamic actor 
		SA_DYNAMIC("sa_dynamic"),
		
		// unclassified scenario-aware actor
		UNCLASSIFIED("unclassified");
		
		private final String name;
		ScenarioAwareActorInstanceType(String n){
			name=n;
		}
		
		public String getName() {
	        return name;
	    }
	}
	
	/**
	 * The Scenario class represents a mode of operation of an actor.
	 * A static actor has only one scenario. A dynamic actor has more than
	 * one scenario. A scenario is defined by the intervals of the values of 
	 * control tokens from input look-ahead ports.
	 */
	public class Scenario{
		/**
		 * The FSM transition of the actor that generates the scenario.
		 * For static actors, transition is remains null.
		 */
		private Transition transition = null;
		
		//The intervals of the values of control tokens of input look-aheads
		private Map<InputLookAhead, UnionOfDisjointIntervals> inputLookAheadIntervals;
		
		//The worst-case execution time of the scenario. It is set from action annotations.
		private Integer executionTime = new Integer(0);
		
		//The port rates of the actor at this scenario
		private Map<PortInstance, Integer> portRates = new HashMap<PortInstance, Integer>();
		

		public Scenario(){
			inputLookAheadIntervals = new HashMap<InputLookAhead, UnionOfDisjointIntervals>();			
		}
		
		public Transition getTransition(){
			return transition;
		}
		
		public void setTransition(Transition t){
			transition = t;
		}
		
		public Map<InputLookAhead, UnionOfDisjointIntervals> getInputLookAheadIntervals(){
			return inputLookAheadIntervals;
		}
		
		public void setInputLookAheadIntervals(Map<InputLookAhead, UnionOfDisjointIntervals> i){
			inputLookAheadIntervals = i;
		}
		
		public void addInputLookAheadInterval(InputLookAhead ila, UnionOfDisjointIntervals i){
			inputLookAheadIntervals.put(ila, i);
		}
		
		public Integer getExecutionTime(){
			return executionTime;
		}
		
		public void setExecutionTime(Integer t){
			executionTime = t;
		}
		
		public void setPortRate(PortInstance p, Integer rate){
			portRates.put(p, rate);
		}
		
		public Integer getPortRate(PortInstance p){
			return portRates.get(p);
		}
		
		public Map<PortInstance, Integer> getPortRates(){
			return portRates;
		}
		
		public void setPortRates(Map<PortInstance, Integer> rates){
			portRates = rates;
		}
		/**
		 * checks if two scenarios are equivalent. 
		 * Two scenarios are said to be equivalent if they have the same 
		 * port-rates and worst-case execution times.
		 * @param scenario
		 * @return true if scenario is equivalent to this scenario.
		 */
		public boolean isEquivalent(Scenario scenario){
			if(this==scenario)
				return true;
			
			if(portRates.size() != scenario.portRates.size())
				return false;
			
			if(getExecutionTime().intValue()!=scenario.getExecutionTime().intValue())
				return false;
			
			for(PortInstance p: portRates.keySet()){
				if(!scenario.portRates.containsKey(p))
					return false;
				
				if(getPortRate(p).intValue()!=scenario.getPortRate(p).intValue())
					return false;
			}
			
			return true;
		}
		
		/**
		 * returns the interval of control token values for a given 
		 * input look-ahead port. 
		 * @param portInstance
		 * @return the interval of control tokens values if the scenario 
		 * peeks from port 'portInstance'. Otherwise, it returns null.
		 */
		public UnionOfDisjointIntervals getInterval(PortInstance portInstance){
			for(InputLookAhead ila: inputLookAheadIntervals.keySet()){
				if(ila.getPort()==portInstance)
					return inputLookAheadIntervals.get(ila);
			}
			return null;
		}
		
		/**
		 * prints scenario to the given stream.
		 * @param stream
		 */
		public void print(Stream stream){
			if(transition!=null)
				stream.println("\t\tAction: "+transition.getAction().getName());			
			for (Map.Entry<InputLookAhead, UnionOfDisjointIntervals> entry : 
				inputLookAheadIntervals.entrySet()) {
				InputLookAhead ila = entry.getKey();
				UnionOfDisjointIntervals uds = entry.getValue();
				stream.println("\t\tInputLookAhead: "+ila.getPort().getName());
				stream.println("\t\tIntervals: "+uds.toString());
			}
			for (Map.Entry<PortInstance, Integer> entry: portRates.entrySet()){
				if(entry.getValue() > 0)
					stream.println("\t\t\tPort: "+entry.getKey().getName()+", Rate: "+entry.getValue());
			}
		}
	};
	

	//The scenario-aware actor type of this actor instance
	private ScenarioAwareActorInstanceType mSAActorType = ScenarioAwareActorInstanceType.UNCLASSIFIED;
	
	//The list of scenarios of this actor instance
	private List<Scenario> scenarios = new ArrayList<Scenario>();
	
	//flags if this actor is the source actor of the network
	private Boolean source = new Boolean(false);
	
	/**
	 * constructs a ScenarioAwareActorAnalysis object
	 * It sets the actor instance type either from annotation or
	 * from analysis. It also sets the port types of the actor (control or data).
	 * @param actorInstance
	 * @param actorAnalysisDelegate
	 */
	public ScenarioAwareActorAnalysis(ActorInstance actorInstance,
			ActorAnalysis actorAnalysisDelegate) {
		
		super(actorInstance, actorAnalysisDelegate);
		
		//set the actor type if it is annotated  
		String type = getActor().getAnnotationArgumentValue("ActorProperty","Type");
		
		if(type!=null && !isTypeAnnotated()){
			
			setTypeAnnotated(true);
			
			if(type.equalsIgnoreCase("sa_static")){
				mSAActorType = ScenarioAwareActorInstanceType.SA_STATIC;					
			}
			else if(type.equalsIgnoreCase("sa_dynamic")){
				mSAActorType = ScenarioAwareActorInstanceType.SA_DYNAMIC;					
			}
			else if(type.equalsIgnoreCase("sa_detector")){
				mSAActorType = ScenarioAwareActorInstanceType.SA_DETECTOR;					
			}
			else
				setTypeAnnotated(false);
		}
		
		//check if this actor is a source actor
		String src = getActor().getAnnotationArgumentValue("ActorProperty","Source");
		if(src!=null){
			if (src.trim().equalsIgnoreCase("true"))
				source = Boolean.TRUE;
		}
		
		//if actor is not type-annotated, classify the actor by analysis
		if(!isTypeAnnotated()){
			if(isScenarioAwareStaticActor())
				setScenarioAwareActorInstanceType(ScenarioAwareActorInstanceType.SA_STATIC);
			else if(isScenarioAwareDynamicActor())
				setScenarioAwareActorInstanceType(ScenarioAwareActorInstanceType.SA_DYNAMIC);
		}
		
		//set the types of the ports of the actor
		for(PortInstance portInstance: getActor().getPorts()){
			if(getPortAnalysis(portInstance).isPartialControlPort()){
				getPortAnalysis(portInstance).setPortType(PortType.CONTROL);
			}
			else
				getPortAnalysis(portInstance).setPortType(PortType.DATA);
		}				
	}
	
	/**
	 * checks if this actor is a source actor
	 * @return true if actor is a source actor or false, otherwise.
	 */
	public boolean isSource(){
		return source.booleanValue();
	}
	
	/**
	 * gets the possible scenarios of the actor
	 * @return
	 */
	public List<Scenario> getScenarios(){
		return scenarios;
	}
	
	/**
	 * adds a scenario to the list of possible scenarios of the actor
	 * @param sp
	 */
	public void addScenario(Scenario scenario){
		scenarios.add(scenario);
	}
	
	/**
	 * gets the scenario-aware actor type of this actor instance
	 * @return 
	 */
	public ScenarioAwareActorInstanceType getScenarioAwareActorInstanceType(){
		return mSAActorType;
	}
	
	/**
	 * prints the scenarios of the actor to the given stream
	 * @param stream
	 */
	public void printScenarios(Stream stream){
		stream.println("Scenarios: " + getActor().getInstanceName());
		stream.println("\t Annotated: "+ Boolean.toString(isTypeAnnotated()));
		for(Scenario scenario: scenarios){
			scenario.print(stream);
		}
	}
	
	/**
	 * get the scenario-aware actor type of this actor instance as string
	 * @return
	 */
	public String getScenarioAwareActorInstanceTypeAsString(){
			return getScenarioAwareActorInstanceType().getName();
	}
	
	/**
	 * sets the scenario-aware actor type of this actor instance
	 * @param t
	 */
	public void setScenarioAwareActorInstanceType(ScenarioAwareActorInstanceType t){
		assert(getScenarioAwareActorInstanceType()!=ScenarioAwareActorInstanceType.UNCLASSIFIED);
		mSAActorType = t;
	}
	
	/**
	 * gets partial-control ports of this actor.
	 * A partial-control port is a port that is an input look-ahead port to at least 
	 * one of the actions of the actor.
	 * @return a list of partial-control ports of the actor. The list can be empty.
	 */
	public List<PortInstance> getPartialControlPorts(){
		List<PortInstance> controlPorts=new ArrayList<PortInstance>();
		for(PortInstance portInstance : getActor().getInputPorts()){
			PortAnalysis portAnalysis = new PortAnalysis(portInstance);
			if(portAnalysis.isPartialControlPort())
				controlPorts.add(portInstance);
		}
		return controlPorts;
	}
	
	/**
	 * A scenario-aware static actor has input-independent port-signature.
	 * This means, actions can have guards (state-dependent or input-dependent)
	 * as long as the input and output token rates are (cyclo-)statically defined.
	 * @return it returns true if actor is SDF or CSDF actor, based on the generic
	 * actor analysis technique.
	 */
	private boolean isScenarioAwareStaticActor(){		
		return getActorInstanceType()==ActorInstanceType.SINGLE_RATE_STATIC ||
				getActorInstanceType()==ActorInstanceType.MULTI_RATE_STATIC ||
				getActorInstanceType()==ActorInstanceType.CYCLO_STATIC;
	}
	
	/**
	 * A scenario-aware dynamic actor has input-dependent port-signature. 
	 * 1) All non-deterministic transitions are 'scenario-aware guarded'.
	 * 2) For definition of scenario-aware guard, check matchScenarioAwareGuard() 
	 *    of class org.caltoopia.analysis.air.Guard.
	 * 3) Guards of non-deterministic transitions cover entire condition space 
	 * 	  and are mutually exclusive.
	 * 4) It has at least one partial-control port. 
	 * @return
	 */
	private boolean isScenarioAwareDynamicActor(){
		if(isScenarioAwareStaticActor()){
			System.out.println(this.getActor().getInstanceName()+" is not SA_Dynamic : b/c it's SA_Static.");
			return false;
		}

		//Check if those actions on non-deterministic states have scenario-aware guards
		if(!areNonDeterministicStatesSimplyGuarded()){
			System.out.println(this.getActor().getInstanceName()+
					" is not SA_Dynamic : b/c not all non-deterministic transitions are scenario-aware guarded.");
			return false;
		}			
		return true;
	}
	
	/**
	 * populates the possible scenarios of the actor.
	 */
	public void constructScenarios(){
		if(getScenarioAwareActorInstanceType() == ScenarioAwareActorInstanceType.SA_STATIC)
			constructStaticScenarios();
		else if(getScenarioAwareActorInstanceType() == ScenarioAwareActorInstanceType.SA_DYNAMIC ||
				getScenarioAwareActorInstanceType() == ScenarioAwareActorInstanceType.SA_DETECTOR)
			constructDynamicScenarios();			
	}
	
	/**
	 * searches a scenario that is generated by a given transition
	 * @param transition
	 * @return a scenario generated by the transition. It returns null
	 * if there is no such scenario in this actor.
	 */
	Scenario getScenario(Transition transition){
		for(Scenario scenario: scenarios){
			//Exclude the SA_Static actors
			if(scenario.transition!=null){ 
				if(scenario.transition.equals(transition))
					return scenario;
			}
		}
		return null;
	}
	
	//TODO: The port-rate of a cyclo-static actor needs another look. The assumption here
	// is that all actions in the periodic phase are executed in one firing of the actor.
	// If this is not the case, multiple scenarios should be generated by defining a detector
	// actor for the cyclo-static actor that controls its execution.
	/**
	 * constructs a scenario for a scenario-aware static actor.
	 * A static actor has only one scenario. The port-rates are
	 * set from the port-rates of the first action in the periodic phase for 
	 * a SDF actor and from the sum of all actions in the periodic phase for 
	 * cyclo-static actors. The
	 * The execution time is set from the execution time of the super class,
	 * which is set by annotation in the GenericActorAnalysis class.
	 */
	public void constructStaticScenarios(){				
		Scenario Scenario = new Scenario();
		Scenario.executionTime = getExecutionTime();			
		for(PortInstance portInstance : getActor().getPorts()){
			int rate = 0;
			for(Action action: getStaticSchedule().getRepeatedPhase().getFlatSequence()){
				rate += getActionAnalysis(action).getPortRate(portInstance).intValue();
				if(getActorInstanceType()!=ActorInstanceType.CYCLO_STATIC)
					break;
			}
			Scenario.setPortRate(portInstance, new Integer(rate));
		}
		scenarios.add(Scenario);
	}
	
	/**
	 * constructs the scenarios of a scenario-aware dynamic actor.
	 * since the actor is already SA_DYNAMIC (without annotation),
	 * all of its non-deterministic transitions must be guarded.
	 * For each non-deterministic transition, a new scenario is defined and 
	 * all actions until the next non-deterministic transition or scenario
	 * aware guarded action belongs to this scenario.
	 */
	public void constructDynamicScenarios(){
		ActorSchedule schedule = getActor().getImplementation().getSchedule(); 
		
		//queue of transitions to be tested
		List<Transition> transitionsQueue = new ArrayList<Transition>();
		
		//list of FSM states already visited
		List<State> visitedStates = new ArrayList<State>();
		
		//list of FSM transitions already visited
		List<Transition> visitedTransitions = new ArrayList<Transition>();
		
		//list of scenario-aware guarded transitions
		List<Transition> guardedTransitions = new ArrayList<Transition>();
		
		//populate the transitions queue for the initial state
		State initialState = schedule.getInitialState();
		visitedStates.add(initialState);
		for(Transition t: initialState.getTransitions()){
			transitionsQueue.add(t);
		}
		
		//first find all possible scenarios from scenario-aware guarded transitions
		//without looking follow-up deterministic transitions.
		try{
			while(!transitionsQueue.isEmpty()){	
				Transition transition = transitionsQueue.remove(0);
				if(!visitedTransitions.contains(transition)){
					visitedTransitions.add(transition);
					if(transition.getAction().hasGuard()){  
						if(transition.getAction().getGuard().matchScenarioAwareGuard()!=null){
							Scenario scenario = new Scenario();
							Guard guard = transition.getAction().getGuard();			
							scenario.setInputLookAheadIntervals(guard.matchScenarioAwareGuard());
							scenario.setTransition(transition);
								
							//Set execution time
							if(transition.getAction().getAnnotationArguments("ActionProperty")!=null){
								String wcet = transition.getAction().getAnnotationArguments("ActionProperty").get("WCET");
								if (wcet!=null)
									scenario.executionTime = new Integer(wcet);
							}
							
							//Set port rates
							PortSignature  ps = transition.getAction().getPortSignature();
							for(PortInstance p: getActor().getPorts()){
								scenario.setPortRate(p, new Integer(ps.getPortRate(p)));
							}
							
							//Add to the list of scenario properties
							scenarios.add(scenario);
							guardedTransitions.add(transition);		
						}
					}
							//Update for the next state
							State dstState = transition.getTargetState();
							if(!visitedStates.contains(dstState)){
								visitedStates.add(dstState);
								for(Transition t: dstState.getTransitions()){
									transitionsQueue.add(t);
								}
							}
									
				}
			}
		
			//next, for each scenario, add all deterministic transitions that follow
			for(Transition transition: guardedTransitions){
				Scenario Scenario = getScenario(transition);
				if(Scenario==null)
					throw new NullPointerException("constructDynamicScenarioProperties: " +
							"Scenario does not exist for transtion '"+transition.toString()+"'");
				//For every transition that starts a Scenario
				State dstState = transition.getTargetState();
				//A state can have an unguarded transition only if it is deterministic
				while (dstState.getTransitions().size()==1){
					Transition t = dstState.getTransitions().iterator().next();
					if(!guardedTransitions.contains(t)){
						PortSignature pst = t.getAction().getPortSignature();
						for(PortInstance p: getActor().getPorts()){
							int r = Scenario.getPortRate(p).intValue() + pst.getPortRate(p);
							Scenario.setPortRate(p, new Integer(r));
						}
						dstState = t.getTargetState();
					}
					else
						break;
				}
			}
		}
		catch(Exception e){
			System.out.println("Error while constructing dynamic actor scenarios: " + e.getMessage());
			System.exit(1);
		}			
	}	
}
