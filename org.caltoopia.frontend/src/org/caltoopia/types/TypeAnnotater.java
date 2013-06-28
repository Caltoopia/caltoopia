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

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class TypeAnnotater extends IrReplaceSwitch {
	
	@Override
	public VariableExpression caseVariableExpression(VariableExpression varExpr) {
		super.caseVariableExpression(varExpr);

		Declaration decl = varExpr.getVariable();
		if (decl instanceof VariableImport) {
			try {
				decl =  ActorDirectory.findVariable((VariableImport) decl);
			} catch (DirectoryException x) {
				System.err.println("[TypeAnnotater] Internal Error - caseVariableExpression");
			}
		} else if (decl instanceof ForwardDeclaration) {
			decl = ((ForwardDeclaration) decl).getDeclaration();
		}
		if(decl instanceof VariableExternal) {
			//When external just use the type of the variable
			varExpr.setType(((VariableExternal) decl).getType());
		} else {
			Type type = ((Variable) decl).getType();
			if(varExpr.getIndex().isEmpty()) {
				varExpr.setType(type);
			} else {
				Type typeElem = type;
				for(int i=varExpr.getIndex().size();i>0;i--) {
					typeElem = TypeSystem.getElementType(typeElem);
				}
				varExpr.setType(typeElem);
			}			
			if (!varExpr.getMember().isEmpty()) {						
				for (Member member : varExpr.getMember()) {
					if (TypeSystem.isList(type))
						type = TypeSystem.getElementType(type);
					type = TypeSystem.asTypeTuple(type);
					TaggedTuple tt = ((TypeTuple) type).getTaggedTuples().get(0); //Take first, since there can only be one when dot notation is supported.
					
					Variable found = null;
					for (Variable field : tt.getFields()) {					
						if (field.getName().equals(member.getName())) {
							found = field;
						}
					}
					assert(found != null);
					type = found.getType();
					member.setType(type);
					if(member.getIndex().isEmpty()) {
						varExpr.setType(type);
					} else {
						Type typeElem = type;
						for(int i=member.getIndex().size();i>0;i--) {
							typeElem = TypeSystem.getElementType(typeElem);
						}
						varExpr.setType(typeElem);
					}
				}			
			}
		}
		return varExpr;
	}
	
	@Override 
	public VariableReference caseVariableReference(VariableReference varRef) {
		super.caseVariableReference(varRef);
		
		Declaration decl = varRef.getDeclaration();
		if (decl instanceof VariableImport) {
			try {
				decl =  ActorDirectory.findVariable((VariableImport) decl);
			} catch (DirectoryException x) {
				System.err.println("[TypeAnnotater] Internal Error - caseVariableExpression");
			}
		} else if (decl instanceof ForwardDeclaration) {
			decl = ((ForwardDeclaration) decl).getDeclaration();
		}
		
		Type type = ((Variable) decl).getType();
		if(varRef.getIndex().isEmpty()) {
			varRef.setType(type);
		} else {
			Type typeElem = type;
			for(int i=varRef.getIndex().size();i>0;i--) {
				typeElem = TypeSystem.getElementType(typeElem);
			}
			varRef.setType(typeElem);
		}
		if (!varRef.getMember().isEmpty()) {			
			for (Member member : varRef.getMember()) {
				if (TypeSystem.isList(type))
					type = TypeSystem.getElementType(type);

				type = TypeSystem.asTypeTuple(type);
				TaggedTuple tt = ((TypeTuple) type).getTaggedTuples().get(0); //Take first, since there can only be one when dot notation is supported.

				Variable found = null;
				for (Variable field : tt.getFields()) {					
					if (field.getName().equals(member.getName())) {
						found = field;
					}
				}
				assert(found != null);
				type = found.getType();
				member.setType(type);
				if(member.getIndex().isEmpty()) {
					varRef.setType(type);
				} else {
					Type typeElem = type;
					for(int i=member.getIndex().size();i>0;i--) {
						typeElem = TypeSystem.getElementType(typeElem);
					}
					varRef.setType(typeElem);
				}
			}			
		}
		
		return varRef;
	}
		
}
