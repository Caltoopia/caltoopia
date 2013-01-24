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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.StateVariable;
import org.caltoopia.analysis.util.collections.Interval;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.ExpressionPrinter;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.Variable;


public class CaltoopiaGuard implements Guard {

	List<org.caltoopia.ir.Guard> mGuards;
	Set<StateVariable> mStateVariables=new LinkedHashSet<StateVariable>();
	Set<InputLookAhead> mLookAheads=new LinkedHashSet<InputLookAhead>();
	
	public CaltoopiaGuard(List<org.caltoopia.ir.Guard> guards,
				         Map<String,StateVariable> stateVariableMap,
				         ActorInstance actor) {
		StateVariableFinder stateVarFinder=new StateVariableFinder(stateVariableMap);
		
		mGuards=guards;
		for (org.caltoopia.ir.Guard g: mGuards) {		
			stateVarFinder.doSwitch(g);
			
			for(PortPeek portPeek: g.getPeeks()) {
				// FIXME: what about this kind of look-ahead?  IN[a,b,c] repeat 10
				// a) repeat means that the variables are arrays/Lists
				// b) what's the relation between getPosition() and the FIFO index
				//    when there are multiple variables in the input pattern?
				String name=portPeek.getPort().getName();
				PortInstance port=actor.getPort(name);
				int index=portPeek.getPosition();
				InputLookAhead lookAhead=new VanillaInputLookAhead(port,index);
			
				mLookAheads.add(lookAhead);
			}
		}
	}
	
	
	@Override
	public boolean dependsOnInput() {
		return !mLookAheads.isEmpty();
	}


	@Override
	public Collection<InputLookAhead> getInputLookAheads() {
		return mLookAheads;
	}


	@Override
	public boolean dependsOnState() {
		return !mStateVariables.isEmpty();
	}


	@Override
	public Collection<? extends StateVariable> getStateVariables() {
		return mStateVariables;
	}


	@Override
	public UnionOfDisjointIntervals matchModeControlGuard() {
		if (mLookAheads.size()==1 && !dependsOnState()) {
			UnionOfDisjointIntervals result=matchModeControlGuard(mGuards.get(0).getBody());
			
			if (result==null || result.asSet().isEmpty()) {
				return null;
			}
			
			// Match each sub-guard in a possible conjunction of several guards
			for (int i=1; i<mGuards.size(); ++i) {
				UnionOfDisjointIntervals s=matchModeControlGuard(mGuards.get(i).getBody());
				if (s==null)
					return null; // doesn't match pattern
				result = result.intersection(s);
				if (result.asSet().isEmpty()) {
					return null;
				}
			}

			return result;
		}
		else
			return null;
	}
	
	public Map<InputLookAhead, UnionOfDisjointIntervals> matchScenarioAwareGuard(){
		if(dependsOnState() || mLookAheads.size() == 0)
			return null;
		
		Map<InputLookAhead, UnionOfDisjointIntervals> result = 
				new HashMap<InputLookAhead, UnionOfDisjointIntervals>();
		//For each input lookahead this guard depends on, find the interval
		for(InputLookAhead ila: mLookAheads){
			UnionOfDisjointIntervals s = getScenarioAwareGuardIntervals(ila);
			if (s == null)
				return null;
			else
				result.put(ila, s);				
		}
		return result;
	}
	
	@Override
	public UnionOfDisjointIntervals getScenarioAwareGuardIntervals(InputLookAhead ila) {

			UnionOfDisjointIntervals result = null;			
		
			// Match each sub-guard in a possible conjunction of several guards
			boolean matches;
			for (int i=0; i<mGuards.size(); ++i) {
				matches = false;
				//Check if this sub-guard peeks from the given InputLookAhead 'ila' 
				for(PortPeek pp: mGuards.get(i).getPeeks()){
					if (pp.getPort().getName().equals(ila.getPort().getName())){
						matches = true;
						break;
					}						
				}
				if(matches){
					UnionOfDisjointIntervals s=matchModeControlGuard(mGuards.get(i).getBody());
					if (s==null)
						return null; // doesn't match pattern
					if(result == null)
						result = s;
					else 
						result = result.intersection(s);
					if (result.asSet().isEmpty()) {
						return null;
					}
				}
			}

			return result;
	}
	
	private UnionOfDisjointIntervals matchModeControlGuard(org.caltoopia.ir.Expression g) {
		BinaryExpression binExpr=CaltoopiaPatternMatcher.matchBinaryExpression(g);
		
		if (binExpr!=null) {
			/*
			 * a)  VariableExpression relop LiteralExpression
			 * a') LiteralExpression  relop VariableExpression
			 * b)  p and q, p or q
			 */
			CaltoopiaPatternMatcher.Relop relop=CaltoopiaPatternMatcher.matchRelop(binExpr.getOperator());
			if (relop!=null) {
				VariableExpression var=CaltoopiaPatternMatcher.matchVariableExpression(binExpr.getOperand1());
				IntegerLiteral konst;
				
				if (var!=null) {
					konst=CaltoopiaPatternMatcher.matchIntegerLiteral(binExpr.getOperand2());
				}
				else {
					var=CaltoopiaPatternMatcher.matchVariableExpression(binExpr.getOperand2());
					konst=CaltoopiaPatternMatcher.matchIntegerLiteral(binExpr.getOperand1());
					
					// Normalize by reversing relop
					relop=relop.reverse();
				}
				
				boolean check = false;
				if(var!=null){ 
					if (var.getIndex().isEmpty())
						check = true;
					else if (var.getIndex().size()==1 ){
						IntegerLiteral index = CaltoopiaPatternMatcher.matchIntegerLiteral(var.getIndex().get(0));
						check = index.getValue()==0;
					}
				}
					
				if (var!=null && check && konst!=null) {
					// Since we have only one look-ahead and
					// there'd no state dependence, var must indeed
					// be the variable that is bound to the input
					long x=konst.getValue();
					UnionOfDisjointIntervals result=new UnionOfDisjointIntervals(); 
					
					switch (relop) {
					case Equal:
						result.add(x, x);
						break;
					case NotEqual:
						// This relop needs TWO disjoint intervals
						if (x>Long.MIN_VALUE) {
							result.add(Long.MIN_VALUE,x-1);
						}
						if (x<Long.MAX_VALUE) {
							result.add(x+1,Long.MAX_VALUE);
						}
						break;
					case LessThan:
						if (x>Long.MIN_VALUE) {
							result.add(Long.MIN_VALUE,x-1);
						}
						break;
					case LessThanEqual:
						result.add(Long.MIN_VALUE,x);
						break;
					case GreaterThan:
						if (x<Long.MAX_VALUE) {
							result.add(x+1,Long.MAX_VALUE);
						}
						break;
					case GreaterThanEqual:
						result.add(x,Long.MAX_VALUE);
						break;
					default:
						assert(false);
						return null;
					}
					return result;
				}
			}
			else if (binExpr.getOperator().equals("and") || binExpr.getOperator().equals("or")) {
				UnionOfDisjointIntervals result1=matchModeControlGuard(binExpr.getOperand1());
				
				if (result1!=null) {
					UnionOfDisjointIntervals result2=matchModeControlGuard(binExpr.getOperand2());
					
					if (result2!=null) {
						return (binExpr.getOperator().equals("and"))?
								result1.intersection(result2)
								: result1.union(result2);
					}
				}
			}
		}
	
		// TODO: also match unary operator "not"
		
		// Otherwise: not a match
		return null;
	}
	
	public String toString() {
		ExpressionPrinter printer=new ExpressionPrinter() {
			@Override
			public String caseGuard(org.caltoopia.ir.Guard guard) {
				return doSwitch(guard.getBody());
			}					
		};
		String result="";
		String delimiter="";

		// Print the guard expressions with "... and ..." inbetween
		for (org.caltoopia.ir.Guard g: mGuards) {
			result += delimiter + printer.doSwitch(g);
			delimiter=" and ";
		}
		return result;
	}
	
	
	private class StateVariableFinder extends IrReplaceSwitch {

		private Map<String,StateVariable> mVariableMap;
		
		public StateVariableFinder(Map<String,StateVariable> variableMap) {
			mVariableMap=variableMap;
		}

		@Override
		public Expression caseVariableExpression(VariableExpression var) {
			String name=var.getVariable().getName();
			StateVariable stateVar=mVariableMap.get(name);
			if (stateVar!=null) {
				mStateVariables.add(stateVar);
			}
			
			return var;  // don't replace IR!
		}
	}
}
