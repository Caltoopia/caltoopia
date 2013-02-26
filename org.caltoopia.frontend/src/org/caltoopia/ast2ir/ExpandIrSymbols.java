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
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class ExpandIrSymbols {

	public static void addAndResolveDeclarations(Network network, Set<Namespace> namespaces) {
		
	    Set<IrDeclVertex> added = new HashSet<IrDeclVertex>();

	    final Map<Declaration, Declaration> imported = new HashMap<Declaration, Declaration>();
	    
	    /*
	     * When elaborating the network declarations are moved from their
	     * namespace scope into a globally sorted list in the top network.
	     * Hence, we also change all scope references to the top network.
	     */
	    final Network theNetwork = network;
	    class scopeReplace extends IrReplaceSwitch {
	    	
	    	private void setNamespace(Node node, Scope scope) {
	    		if(scope instanceof Namespace) {
	    			Annotation a = IrFactory.eINSTANCE.createAnnotation();
	    			a.setName("NAMESPACE");
	    			for(String s:((Namespace) scope).getName()) {
		    			AnnotationArgument aa = IrFactory.eINSTANCE.createAnnotationArgument();
		    			aa.setId(s);
		    			aa.setValue(s);
		    			a.getArguments().add(aa);
	    			}
	    			node.getAnnotations().add(a);
	    		}
	    	}
	    	
	    	@Override
			public Declaration caseTypeDeclaration(TypeDeclaration type) {
	    		TypeDeclaration decl = (TypeDeclaration) super.caseTypeDeclaration(type);
	    		setNamespace(decl,decl.getScope());
	    		decl.setScope(theNetwork);
	    		return decl;
	    	}

	    	@Override
			public TypeConstructor caseTypeConstructor(TypeConstructor type) {
	    		type = super.caseTypeConstructor(type);
	    		setNamespace(type,type.getScope());
	    		type.setScope(theNetwork);
	    		return type;
	    	}

	    	@Override
			public Type caseTypeRecord(TypeRecord type) {
	    		TypeRecord typeRet = (TypeRecord) super.caseTypeRecord(type);
	    		for (Variable member : ((TypeRecord)typeRet).getMembers()) {	
		    		setNamespace(member,member.getScope());
	    			member.setScope(theNetwork);			
	    		}
	    		return typeRet;
	    	}

	    	@Override
			public Declaration caseVariable(Variable var) {
	    		Variable decl = (Variable) super.caseVariable(var);
	    		setNamespace(decl,decl.getScope());
	    		decl.setScope(theNetwork);
	    		return decl;
	    	}

	    	@Override
			public Declaration caseVariableExternal(VariableExternal var) {
	    		Declaration decl = (Declaration) super.caseVariableExternal(var);
	    		setNamespace(decl,decl.getScope());
	    		decl.setScope(theNetwork);
	    		return decl;
	    	}

	    	@Override
			public Declaration caseVariableImport(VariableImport decl) {
	    		setNamespace(decl,decl.getScope());
	    		decl.setScope(theNetwork);
	    		return decl;
	    	}

	    	@Override
			public Declaration caseForwardDeclaration(ForwardDeclaration var) {
	    		Declaration decl = (Declaration) super.caseForwardDeclaration(var);
	    		setNamespace(decl,decl.getScope());
	    		decl.setScope(theNetwork);
	    		return decl;
	    	}

	    	@Override
	    	public EObject caseLambdaExpression(LambdaExpression lambda) {
	    		setNamespace(lambda,lambda.getOuter());
	    		lambda.setContext(theNetwork);
	    		lambda.setOuter(theNetwork);

	    		//Visit the types
	    		doSwitch(lambda.getType());
	    		for (int i = 0; i < lambda.getParameters().size(); i++) {
	    			doSwitch(lambda.getParameters().get(i).getType());
	    		}

	    		//No super since don't want to dive into body
	    		return lambda;
	    	}

	    	@Override
	    	public EObject caseListExpression(ListExpression expr) {
	    		setNamespace(expr,expr.getContext());
	    		expr.setContext(theNetwork);
	    		for(Expression e: expr.getExpressions()) {
	    			doSwitch(e);
	    		}
	    		//TODO should we dive in and find types?
	    		return expr;
	    	}

	    	@Override
	    	public Expression caseTypeConstructorCall(TypeConstructorCall call) {
	    		setNamespace(call,call.getContext());
	    		call.setContext(theNetwork);

	    		//Visit the parameter types
	    		/*
	    		for (int i = 0; i < call.getParameters().size(); i++) {
	    			doSwitch(call.getParameters().get(i));
	    		}
	    		*/
	    		return call;
	    	}

	    }
	    
	    /*
	     * Any TypeDeclaration import we pull in from ActorDirectory might
	     * have members that also points to type declaration imports.
	     * This class fix that by replacing with the real type declarations.
	     * FIXME This happens only the second time we build, and is likely due to 
	     * an aliasing of the object we have in cache from actor directory
	     * have been mangled with something that inserts the import.
	     * We should fix that!!!
	     */
	    class innerTypeDeclarationImportReplace extends IrReplaceSwitch {
	    	@Override	
			public TypeUser caseTypeUser(TypeUser t) {
				if(t.getDeclaration() instanceof TypeDeclarationImport) {
			    	try {
						Declaration innerDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) t.getDeclaration());
						t.setDeclaration(innerDecl);
			    	} catch (DirectoryException e) {
						//Ignore it
					}
				}
				return t;
			}
		}

	    for (Declaration decl : network.getDeclarations()) {
			
			try {
				if (decl instanceof TypeDeclarationImport) {  
					Declaration newDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) decl);
					//Change scope to network due to this is the new home of the declaration
					new scopeReplace().doSwitch(newDecl);
					imported.put(decl, newDecl);
					decl = newDecl;			
				} else if (decl instanceof VariableImport) {	
					Declaration newDecl = ActorDirectory.findVariable((VariableImport) decl);
					//Change scope to network due to this is the new home of the declaration
					new scopeReplace().doSwitch(newDecl);
					imported.put(decl, newDecl);
					decl = newDecl;
				}
				new scopeReplace().doSwitch(decl);
				new innerTypeDeclarationImportReplace().doSwitch(decl);
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
						//Change scope to network due to this is the new home of the declaration
						new scopeReplace().doSwitch(newDecl);
						imported.put(decl, newDecl);
						decl = newDecl;	
					} else if  (decl instanceof VariableImport) {	
						Declaration newDecl = ActorDirectory.findVariable((VariableImport) decl);
						//Change scope to network due to this is the new home of the declaration
						new scopeReplace().doSwitch(newDecl);
						imported.put(decl, newDecl);
						decl = newDecl;
					} 
					if (!added.contains(decl)) {
						//Change scope to network due to this is the new home of the declaration
						new scopeReplace().doSwitch(decl);
						new innerTypeDeclarationImportReplace().doSwitch(decl);
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
