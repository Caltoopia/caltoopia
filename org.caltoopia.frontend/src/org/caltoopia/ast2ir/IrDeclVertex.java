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

package org.caltoopia.ast2ir;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class IrDeclVertex implements VertexData {
	
	private Declaration var;
	
	final List<Declaration> inputDecls = new ArrayList<Declaration>();
	
	private class DependencySwitch extends IrReplaceSwitch  {				
		// Add all occurrences of user defined elements, 
		// such as variables, types, functions and procedures
		
		@Override
		public Expression caseFunctionCall(FunctionCall c)  {
			if (c.getFunction() instanceof VariableExpression) {
				inputDecls.add(((VariableExpression) c.getFunction()).getVariable());
			}
			for (Expression param : c.getParameters()) {
				doSwitch(param);
			}
			return c;
		}	
		
		@Override
		public Expression caseVariableExpression(VariableExpression e)  {
			inputDecls.add(e.getVariable());
			return e;
		}

	   @Override
	   public Type caseTypeUser(TypeUser type) {
		   inputDecls.add(type.getDeclaration());
		   return type;		   
	   }
		
	}
	
	public IrDeclVertex(Declaration var) {
		this.var = var;
	}

	public EObject getData() {
		return var;
	}
		
	public String getName() {
		return var.getName() + "(" + var.getId() + ")";
	}

	public List<VertexData> getConnectedData() {
		DependencySwitch s = new DependencySwitch();
		
		if (var instanceof Variable) {
			Variable variable = (Variable) var;
			s.doSwitch(variable.getType());
			if (variable.getInitValue() != null) {
				s.doSwitch(variable.getInitValue());
			}
        } else if (var instanceof ForwardDeclaration) {
            ForwardDeclaration fwdef = (ForwardDeclaration) var;
            s.doSwitch(fwdef.getType());
		} else if (var instanceof TypeDeclaration) {
			TypeDeclaration typedef = (TypeDeclaration) var;
			s.doSwitch(typedef.getType());
		} 
									
		List<VertexData> result = new ArrayList<VertexData>();
		for (Declaration o : inputDecls) {
			result.add(new IrDeclVertex(o));
		}
		return result;
	}
}
