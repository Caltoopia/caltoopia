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
import org.caltoopia.frontend.cal.AstConstructor;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.util.VoidSwitch;
import org.caltoopia.types.TypeConverter;

public class AstDeclVertex implements VertexData {
	
	private EObject var;
	
	final List<EObject> inputDefs = new ArrayList<EObject>();
	
	private class DependencySwitch extends VoidSwitch  {				
		// Add all occurrences of user defined elements, 
		// such as variables, types, functions and procedures
		
		@Override
		public Void caseAstExpressionSymbolReference(AstExpressionSymbolReference c)  {
			inputDefs.add(c.getSymbol());
			return super.caseAstExpressionSymbolReference(c);
		}	
									
		@Override
		public Void caseAstTypeName(AstTypeName typeName) { 		
			if (!typeName.getConstructor().isEmpty()) {
				// This means that we have found 
				// a type definition 				
				for (AstConstructor ctor : typeName.getConstructor()) {
					for (AstVariable m : ((AstConstructor) ctor).getMembers()) {
						doSwitch(m.getType());
					}
				}
			} 					
			return null;
		}

	   @Override
	   public Void caseAstType(AstType type) {
		   if (TypeConverter.isUserDefined(type)) {
			   inputDefs.add(type.getName());
		   }		   		 
		   return super.caseAstType(type);		   
	   }
		
	}
	
	public AstDeclVertex(EObject var) {
		this.var = var;
	}

	public EObject getData() {
		return var;
	}
		
	public String getName() {
		if (var instanceof AstVariable) {
			return ((AstVariable) var).getName();
		} else if (var instanceof AstFunction){
			return ((AstFunction) var).getName();
		} else if (var instanceof AstProcedure) {
			return ((AstProcedure) var).getName();
		} else if (var instanceof AstTypeName) {
			return ((AstTypeName) var).getName();
		} else {
			throw new RuntimeException("Invalid data in dependecy graph.");
		}
	}

	public List<VertexData> getConnectedData() {
		DependencySwitch s = new DependencySwitch();
		
		if (var instanceof AstConstructor){
			AstConstructor ctor = (AstConstructor) var;
			for (AstVariable member : ctor.getMembers()) {
				s.doSwitch(member);
			}	
		} else if (var instanceof AstFunction){
			AstFunction function = (AstFunction) var;
			s.doSwitch(function.getType());
			
			// Iterate to include dependencies on type parameters
			for (AstVariable parameter : function.getParameters()) {
				s.doSwitch(parameter.getType());
			}				
		} else if (var instanceof AstProcedure) {
			AstProcedure procedure = (AstProcedure) var;
			for (AstVariable parameter : procedure.getParameters()) {
				s.doSwitch(parameter);
			}
		} else if (var instanceof AstTypeName) {
			AstTypeName typedef = (AstTypeName) var;
			for (AstConstructor ctor : typedef.getConstructor()) {
				for (AstVariable m : ((AstConstructor) ctor).getMembers()) {
					s.doSwitch(m);
				}			
			}
		} else if (var instanceof AstVariable) {
			AstVariable variable = (AstVariable) var;
			s.doSwitch(variable.getType());
			if (variable.getValue() != null) {
				s.doSwitch(variable.getValue());
			}
		} else {
			throw new RuntimeException("Invalid data in dependecy graph.");
		}
									
		List<VertexData> result = new ArrayList<VertexData>();
		for (EObject o : inputDefs) {
			result.add(new AstDeclVertex(o));
		}
		return result;
	}
}
