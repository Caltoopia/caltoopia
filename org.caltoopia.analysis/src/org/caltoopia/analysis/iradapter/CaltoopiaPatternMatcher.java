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

import java.util.HashMap;
import java.util.Map;

import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/**
 * This is a box in which we put an assortment of rather messy pattern matching functions
 * 
 * Each of the methods return a caltoopia.ir object on match and null otherwise.
 */
public class CaltoopiaPatternMatcher {

	public static BinaryExpression matchBinaryExpression(EObject expr) {
		return new IrSwitch<BinaryExpression>() {
			@Override
			public BinaryExpression caseBinaryExpression(BinaryExpression expr) {
				return expr;
			}
		}.doSwitch(expr);
	}

	public static VariableExpression matchVariableExpression(EObject expr) {
		return new IrSwitch<VariableExpression>() {
			@Override
			public VariableExpression caseVariableExpression(VariableExpression expr) {
				return expr;
			}
		}.doSwitch(expr);
	}

	public static IntegerLiteral matchIntegerLiteral(EObject expr) {
		return new IrSwitch<IntegerLiteral>() {
			@Override
			public IntegerLiteral caseIntegerLiteral(IntegerLiteral expr) {
				return expr;
			}
		}.doSwitch(expr);
	}

	public enum Relop {
		Equal,
		NotEqual,
		LessThan,
		LessThanEqual,
		GreaterThan,
		GreaterThanEqual;

		public Relop complement() {
			switch (this) {
			case Equal:            return NotEqual;
			case NotEqual:         return Equal;
			case LessThan:         return GreaterThanEqual;
			case LessThanEqual:    return GreaterThan;
			case GreaterThan:      return LessThanEqual;
			default:
			case GreaterThanEqual: return LessThan;
			}
		}
		
		public Relop reverse() {
			switch (this) {
			case Equal:            return Equal;
			case NotEqual:         return NotEqual;
			case LessThan:         return GreaterThan;
			case LessThanEqual:    return GreaterThanEqual;
			case GreaterThan:      return LessThan;
			default:
			case GreaterThanEqual: return LessThanEqual;
			}
		}
	}
	
	private static Map<String,Relop> sRelops=createRelops();
	
	private static Map<String,Relop> createRelops() {
		Map<String,Relop> result=new HashMap<String,Relop>();
		result.put("=",Relop.Equal);
		result.put("!=",Relop.NotEqual);
		result.put("<",Relop.LessThan);
		result.put("<=",Relop.LessThanEqual);
		result.put(">",Relop.GreaterThan);
		result.put(">=",Relop.GreaterThanEqual);
		return result;
	}
	
	public static Relop matchRelop(String operatorName) {
		return sRelops.get(operatorName);
	}
}
