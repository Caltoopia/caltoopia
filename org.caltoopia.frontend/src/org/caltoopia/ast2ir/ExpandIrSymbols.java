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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;

public class ExpandIrSymbols {

	public static void addAndResolveDeclarations(Network network, Set<Namespace> namespaces) {
		
	    Set<IrDeclVertex> added = new HashSet<IrDeclVertex>();

	    final Map<Declaration, Declaration> imported = new HashMap<Declaration, Declaration>();
		
		for (Declaration decl : network.getDeclarations()) {
			
			try {
				if (decl instanceof TypeDeclarationImport) {  
					Declaration newDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) decl);
					imported.put(decl, newDecl);
					decl = newDecl;			
				} else if (decl instanceof VariableImport) {	
					Declaration newDecl = ActorDirectory.findVariable((VariableImport) decl);
					imported.put(decl, newDecl);
					decl = newDecl;
				} 
				added.add(new IrDeclVertex(decl));
			} catch (DirectoryException x) {
				System.err.println("[ExpandIrSymbols] Internal error #1");
				return;
			}
		}
		
		for (Namespace ns : namespaces) {		
			for (Declaration decl : ns.getDeclarations()) {
				
				try {
					if (decl instanceof TypeDeclarationImport) {  
						Declaration newDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) decl);
						imported.put(decl, newDecl);
						decl = newDecl;	
					} else if  (decl instanceof VariableImport) {	
						Declaration newDecl = ActorDirectory.findVariable((VariableImport) decl);
						imported.put(decl, newDecl);
						decl = newDecl;
					} 
					if (!added.contains(decl)) {
						network.getDeclarations().add(decl);
						added.add(new IrDeclVertex(decl));
					}
				} catch (DirectoryException x) {
					System.err.println("[ExpandIrSymbols] Internal error #2");
					return;					
				}
			}
		}

		// Redirect all declarations that reference to TypedefImport/VariableImport 
		// to point at the real thing.
		for (VertexData data : added) {
			new IrReplaceSwitch() {
				@Override	
				public Expression caseVariableExpression(VariableExpression e) {
					if (imported.containsKey(e.getVariable())) {
						e.setVariable(imported.get(e.getVariable()));
					}
						
					return e;					
				}
				
				@Override	
				public TypeUser caseTypeUser(TypeUser t) {
					if (imported.containsKey(t.getDeclaration())) {
						t.setDeclaration(imported.get(t.getDeclaration()));
					}
						
					return t;					
				}
				
				@Override
				public Expression caseTypeConstructorCall(TypeConstructorCall expr) {
					if (imported.containsKey(expr.getTypedef() )) {
						expr.setTypedef(imported.get(expr.getTypedef()));
					}
					
					return expr;
				}
				
			}.doSwitch((EObject) data.getData());			
		}			
		
		// Do all the definitions, sort first
		List<VertexData> graphData = new ArrayList<VertexData>(added);		
		graphData = new Graph(graphData).sortByDependency();
		
		List<Declaration> sortedDeclarations = new ArrayList<Declaration>();
		for (VertexData data : graphData) {
			sortedDeclarations.add((Declaration) data.getData());
		}	
		
		int nrOfDeclarations = network.getDeclarations().size();
		for (int i = 0; i < nrOfDeclarations; i++) {
			network.getDeclarations().remove(0);
		}

		for (Declaration decl : sortedDeclarations) {
			network.getDeclarations().add(decl);
		}

		// Finally, redirect all expressions that references TypedefImport/VariableImport 
		// to point at the real thing.
		new IrReplaceSwitch() {
			@Override	
			public Expression caseVariableExpression(VariableExpression e) {
				if (imported.containsKey(e.getVariable())) {
					e.setVariable(imported.get(e.getVariable()));
				}				
				return e;					
			}
				
		}.doSwitch(network);			
		
	}
}
