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

package org.caltoopia.analysis.iradapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorSchedule;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.StateVariable;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;

public class CaltoopiaActorImplemenation extends CaltoopiaActorInstance 
                                             implements ActorImplementation {

	private Collection<StateVariable> mStateVariables;
	private Collection<Action> mActions;
	private CaltoopiaActionPriorityRelation mPriorityRelation;
	private ActorSchedule mFsm;
	
	public CaltoopiaActorImplemenation(org.caltoopia.ir.ActorInstance actorInstance,
			                               org.caltoopia.ir.Actor instantiatedActorClass) {
		super(actorInstance, instantiatedActorClass);
		
		// Create state variables
		Map<String,StateVariable> stateVarMap=createStateVariables(instantiatedActorClass);
		mStateVariables=new ArrayList<StateVariable>(stateVarMap.values());
		
		// Create actions
		Map<String,CaltoopiaAction> actionMap=createActions(instantiatedActorClass, stateVarMap);
		mActions=new ArrayList<Action>(actionMap.values());
		
		// Create priority relation (between actions)
		org.caltoopia.ir.Schedule schedule=instantiatedActorClass.getSchedule();
		mPriorityRelation=new CaltoopiaActionPriorityRelation(schedule,actionMap);
		
		// Create schedule/FSM
		mFsm=createFsm(schedule, actionMap);		
	}
	
	@Override
	public Collection<StateVariable> getStateVariables() {
		return mStateVariables;
	}

	@Override
	public Collection<Action> getActions() {
		return mActions;
	}

	@Override
	public ActorSchedule getSchedule() {
		return mFsm;
	}

	@Override
	public boolean hasImplementation() {
		return true;
	}

	@Override
	public ActorImplementation getImplementation() {
		return this;
	}
	
	private Map<String,StateVariable> createStateVariables(org.caltoopia.ir.Actor actorClass) {
		Map<String,StateVariable> variables=new LinkedHashMap<String,StateVariable>();

		for (org.caltoopia.ir.Declaration decl: actorClass.getDeclarations()) {
			if (UtilIR.isNormalVariable(decl)) {
				StateVariable stateVar=new CaltoopiaStateVariable((org.caltoopia.ir.Variable) decl);
				variables.put(stateVar.getName(), stateVar);
			}
		}

		return variables;
	} 
	
	private Map<String,CaltoopiaAction> createActions(org.caltoopia.ir.Actor actorClass,
			                                          Map<String,StateVariable> stateVarMap) {
		Map<String,CaltoopiaAction> actionMap=new LinkedHashMap<String,CaltoopiaAction>();

		for (org.caltoopia.ir.Action action: actorClass.getActions()) {
			String name=action.getId();
			PortSignature portSignature=createPortSignature(action);
			Guard guard=createGuard(action.getGuards(),stateVarMap);
			CaltoopiaAction newAction=new CaltoopiaAction(action,portSignature,guard);

			actionMap.put(name, newAction);
		}

		return actionMap;
	}
	
	private PortSignature createPortSignature(org.caltoopia.ir.Action caltoopiaAction) {
		Map<PortInstance,Integer> portRates=new LinkedHashMap<PortInstance,Integer>();

		for (PortRead portRead: caltoopiaAction.getInputs()) {
			PortInstance portInstance=getPort(portRead.getPort().getName());
			assert(portInstance!=null && portInstance.getDirection()==PortInstance.Direction.IN);
			int rate=portRead.getVariables().size();
			if (portRead.getRepeat()!=null)
				rate=rate*sneakyConstantFolding(portRead.getRepeat());
			assert(rate>0);

			portRates.put(portInstance, rate);
		}

		for (PortWrite portWrite: caltoopiaAction.getOutputs()) {
			PortInstance portInstance=getPort(portWrite.getPort().getName());
			assert(portInstance!=null && portInstance.getDirection()==PortInstance.Direction.OUT);
			int rate=portWrite.getExpressions().size();
			if (portWrite.getRepeat()!=null)
				rate=rate*sneakyConstantFolding(portWrite.getRepeat());
			assert(rate>0);

			portRates.put(portInstance, rate);
		}

		return new VanillaPortSignature(portRates);
	}
	
	private Integer sneakyConstantFolding(Expression expr) {
		if (expr instanceof IntegerLiteral) {
			return (int) ((IntegerLiteral) expr).getValue();
		}
		else if (expr instanceof BinaryExpression) {
			BinaryExpression binOp=(BinaryExpression) expr;
			Integer e1=sneakyConstantFolding(binOp.getOperand1());
			Integer e2=sneakyConstantFolding(binOp.getOperand2());

			if (e1!=null && e2!=null && binOp.getOperator().equals("*")) {
				return e1*e2;
			}
			else {
				System.out.println(expr);
			}
		}
		else {
			System.out.println(expr);
		}

		// Not an integer constant
		return null;
	}
	
	
	private Guard createGuard(List<org.caltoopia.ir.Guard> guards,
			Map<String,StateVariable> stateVariableMap) {
		if (guards.isEmpty()) {
			return null; // No guard
		}
		else {
			return new CaltoopiaGuard(guards,stateVariableMap,this);
		}
	}
	
	/**
	 * Creates the "original" FSM of the actor
	 */
	private ActorSchedule createFsm(org.caltoopia.ir.Schedule schedule, 
			                        Map<String,CaltoopiaAction> actionMap) {
		// Create mapping from state identifiers to states and to transitions
		Map<String,State> stateMap=new LinkedHashMap<String,State>();
		Map<State,Set<Transition>> transitionMap=new HashMap<State,Set<Transition>>();
		
		// Just create (initially empty) sets of transitions and priority relations to start with
		for (org.caltoopia.ir.State oldState: schedule.getStates()) {
			String stateName=oldState.getName();
			Set<Transition> transitions=new LinkedHashSet<Transition>();
			PerStatePriorityRelation priorityRelation=new PerStatePriorityRelation(transitions,mPriorityRelation);
			State newState=new VanillaFsmState(stateName,transitions,priorityRelation);
			
			stateMap.put(stateName, newState);
			transitionMap.put(newState, transitions);
		}

		// Create the transitions
		for (org.caltoopia.ir.State oldState: schedule.getStates()) {
			State fromState=stateMap.get(oldState.getName());
			assert(fromState!=null);
			Map<org.caltoopia.ir.Action,String> action2TargetMap = (Map<org.caltoopia.ir.Action,String>) oldState.getAction2TargetMap();
			Collection<Transition> transitions=transitionMap.get(fromState);
			

			// Add each transition from this state
			for (Map.Entry<org.caltoopia.ir.Action,String> entry: action2TargetMap.entrySet()) {
				String name=entry.getKey().getId();
				CaltoopiaAction action=actionMap.get(name);
				State nextState=stateMap.get(entry.getValue());
				assert(action!=null && nextState!=null);

				Transition t=new CaltoopiaTransition(fromState,nextState,action);
				transitions.add(t);
			}

			// Add possible "free-runners" (actions w/o tag) to each state
			for (org.caltoopia.ir.Action freeRunner: schedule.getFreeRunners()) {
				String name=freeRunner.getId();
				CaltoopiaAction action=actionMap.get(name);
				assert(action!=null);

				Transition t=new CaltoopiaTransition(fromState,fromState,action);
				transitions.add(t);
			}
		}		
		
		State initialState=stateMap.get(schedule.getInitialState().getName());
		assert(initialState!=null);

		return new VanillaActorSchedule(stateMap.values(), initialState);
	}
}
