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

package org.caltoopia.ir.util;

import java.util.Iterator;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrSwitch;

public class ExpressionPrinter extends IrSwitch<String> {
	@Override	
	public String caseIntegerLiteral(IntegerLiteral e) {
		return Long.toString(e.getValue());
	}
	
	@Override 
	public String caseFloatLiteral(FloatLiteral e) {		
		return Double.toString(e.getValue());
	}
	
	@Override
	public String caseBooleanLiteral(BooleanLiteral e) {		
		return Boolean.toString(e.isValue());
	}

	@Override
	public String caseStringLiteral(StringLiteral e) {		
		return e.toString();
	}	
			
	@Override
	public String caseBinaryExpression(BinaryExpression e) {	
		String s1 = doSwitch(e.getOperand1());
		String s2 = doSwitch(e.getOperand2());
		String op = e.getOperator().toString();
		return "(" + s1 + " " + op + " " + s2 + ")";
	}

	@Override
	public String caseFunctionCall(FunctionCall e) { 
		String result = doSwitch(e.getFunction()) + "(";
		for (Expression p  : e.getParameters()) {
			result += doSwitch(p);
		}
		result += ")";
		return result;
	}

	@Override
	public String caseUnaryExpression(UnaryExpression e) {
		String result = e.getOperator();
		result += "(" + doSwitch(e.getOperand()) + ")";
		return result;		
	}

	@Override
	public String caseVariableExpression(VariableExpression e)  {
		String result = e.getVariable().getName();
		if (!e.getIndex().isEmpty()) {
			result += "[";
			for (Iterator<Expression> i = e.getIndex().iterator(); i.hasNext();) {
				Expression e1 = i.next();
				result += doSwitch(e1);
				if (i.hasNext()) 
					result += ", ";
			}
			result += "]";
		}
		
		for (Member m : e.getMember()) {
			result += "." + m.getName();
			if (!e.getIndex().isEmpty()) {
				result += "[";
				for (Iterator<Expression> i = m.getIndex().iterator(); i.hasNext();) {
					Expression e1 = i.next();
					result += doSwitch(e1);
					if (i.hasNext()) 
						result += ", ";
				}
				result += "]";
			}
		}
		
		return result;
	}

	
	@Override
	public String doSwitch(EObject o) {
		if (o != null) {
			return super.doSwitch(o);
		} else {			
			return "<null>";
		}
	}
	
	
}
