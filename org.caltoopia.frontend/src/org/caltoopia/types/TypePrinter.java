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

package org.caltoopia.types;

import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.util.ExpressionPrinter;
import org.caltoopia.ir.util.IrSwitch;

public class TypePrinter extends IrSwitch<String> {

	private ExpressionPrinter expressionPrinter = new ExpressionPrinter();

	@Override
	public String caseTypeInt(TypeInt type){
		return "int";
	}

	@Override
	public String caseTypeUint(TypeUint type){
		return "uint";
	}

	@Override
	public String caseTypeFloat(TypeFloat type){
		return "float";
	}

	@Override
	public String caseTypeBool(TypeBool type){
		return "bool";
	}

	@Override
	public String caseTypeString(TypeString type){
		return "string";
	}

	@Override
	public String caseTypeList(TypeList type){
		String result = "list";
		result += "[";
		result += "type:" + doSwitch(type.getType());
		result += ", ";
		result += "size=" + expressionPrinter.doSwitch(type.getSize());
		result += "]";
		return result;
	}

	@Override
	public String caseTypeRecord(TypeRecord type){		
		String result = "record(";
		for (int i = 0; i < type.getMembers().size(); i++) {
			result += type.getMembers().get(i).getName();
			result += " " + doSwitch(type.getMembers().get(i).getType());
			if (i< type.getMembers().size()) result += ", ";
		}
		result += ")";
		return result;
	}
	
	@Override
	public String caseTypeUser(TypeUser type){		
		String result = "user(name:" + type.getDeclaration().getName() + ")";
		return result;
	}
	
	@Override
	public String doSwitch(EObject o) {
		if (o != null) {
			return super.doSwitch(o);
		} else {
			return "Null";
		}
	}
	
}
