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

package org.caltoopia.codegen.analysis;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.analysis.IrAnnotations.IrAnnotationTypes;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;

public class IrTypeAnnotation extends IrReplaceSwitch {

	private PrintStream serr = null; 
	AbstractActor currentActor=null;
	
	public IrTypeAnnotation(Node node, CompilationSession session, boolean errPrint) {
		if(!errPrint) {
			serr = new PrintStream(new OutputStream(){
			    public void write(int b) {
			        //NO-OP
			    }
			});
		} else {
			serr = System.err;
		}
		this.doSwitch(node);
	}
	
	private Set<Declaration> userTypes = new HashSet<Declaration>();
	private Map<Declaration,Set<String>> typeUsage = new HashMap<Declaration,Set<String>>();
	private String currentVariableTypeAnnotation = null;
	
	@Override
	public Expression caseVariableExpression(VariableExpression var) {
		Declaration decl = UtilIR.getDeclaration(var.getVariable());
		if(decl instanceof Variable) {
			String a = IrAnnotations.getAnnotationArg(decl,IrAnnotations.VARIABLE_ANNOTATION,"VarType");
			Type type = ((Variable) decl).getType();
			while(UtilIR.isList(type)) {
				type = ((TypeList)type).getType();
			}
			if(UtilIR.isRecord(type)) {
				for(Declaration d:userTypes) {
					if(d.getId().equals(UtilIR.getTypeDeclaration(type).getId())) {
						if(typeUsage.containsKey(d)) {
							typeUsage.get(d).add(a);
						} else {
							Set<String> aSet = new HashSet<String>();
							aSet.add(a);
							typeUsage.put(d, aSet);
						}
						break;
					}
				}
			}
			for(Member m:var.getMember()) {
				type = m.getType();
				if(UtilIR.isRecord(type)) {
					for(Declaration d:userTypes) {
						if(d.getId().equals(UtilIR.getTypeDeclaration(type).getId())) {
							if(typeUsage.containsKey(d)) {
								typeUsage.get(d).add(a);
							} else {
								Set<String> aSet = new HashSet<String>();
								aSet.add(a);
								typeUsage.put(d, aSet);
							}
							break;
						}
					}
				}
			}
		}
		Expression expr = super.caseVariableExpression(var);
		return expr;
	}
	
	@Override
	public Declaration caseVariable(Variable decl) {
		Type type = ((Variable) decl).getType();
		while(UtilIR.isList(type)) {
			type = ((TypeList)type).getType();
		}
		if(UtilIR.isRecord(type)) {
			for(Declaration d:userTypes) {
				if(d.getId().equals(UtilIR.getTypeDeclaration(type).getId())) {
					String a = IrAnnotations.getAnnotationArg(decl,IrAnnotations.VARIABLE_ANNOTATION,"VarType");
					if(typeUsage.containsKey(d)) {
						typeUsage.get(d).add(a);
					} else {
						Set<String> aSet = new HashSet<String>();
						aSet.add(a);
						typeUsage.put(d, aSet);
					}
					break;
				}
			}
		}
		return super.caseVariable(decl);
	}

	@Override
	public VariableReference caseVariableReference(VariableReference var) {
		Variable decl = var.getDeclaration();
		if(decl instanceof Variable) {
			String a = IrAnnotations.getAnnotationArg(decl,IrAnnotations.VARIABLE_ANNOTATION,"VarType");
			Type type = ((Variable) decl).getType();
			while(UtilIR.isList(type)) {
				type = ((TypeList)type).getType();
			}
			if(UtilIR.isRecord(type)) {
				for(Declaration d:userTypes) {
					if(d.getId().equals(UtilIR.getTypeDeclaration(type).getId())) {
						if(typeUsage.containsKey(d)) {
							typeUsage.get(d).add(a);
						} else {
							Set<String> aSet = new HashSet<String>();
							aSet.add(a);
							typeUsage.put(d, aSet);
						}
						break;
					}
				}
			}
			for(Member m:var.getMember()) {
				type = m.getType();
				if(UtilIR.isRecord(type)) {
					for(Declaration d:userTypes) {
						if(d.getId().equals(UtilIR.getTypeDeclaration(type).getId())) {
							if(typeUsage.containsKey(d)) {
								typeUsage.get(d).add(a);
							} else {
								Set<String> aSet = new HashSet<String>();
								aSet.add(a);
								typeUsage.put(d, aSet);
							}
							break;
						}
					}
				}
			}
		}
		return super.caseVariableReference(var);
	}

	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration decl) {
		userTypes.add(decl);
		return decl;
	}

	@Override
	public ActorInstance caseActorInstance(ActorInstance actorInstance) {
		AbstractActor actor=null;
		try {
			actor=ActorDirectory.findTransformedActor(actorInstance);
		} catch (DirectoryException e) {
			//This is OK, since it is likely an external actor
		}
		if(actor!=null) {
			currentActor = actor;
			doSwitch(actor);
			currentActor = null;
		}
		return actorInstance;
	}
	
	@Override
	public AbstractActor caseNetwork(Network network) {
	    //DEBUG
		new IrReplaceSwitch() {
			private Map<String,String> found = new HashMap<String,String>();
			@Override	
			public TypeUser caseTypeUser(TypeUser type) {
				found.put(((TypeUser) type).getDeclaration().getId(),(((TypeUser) type).getDeclaration() instanceof TypeDeclarationImport?"I_":"R_") +((TypeUser) type).getDeclaration().getName());
				return type;
			}

			@Override
			public AbstractActor caseNetwork(Network network) {
				super.caseNetwork(network);
				System.out.println("[IrTypeAnnotation] ----- Found type declarations usage direcly after reading network -----");
				for(String f:found.keySet()) {
					System.out.println("[IrTypeAnnotation] Found type declaration " + f + " " + found.get(f));
				}
				return network;
			}
		}.doSwitch(network);
		//End DEBUG
		/* Find user types, and potential usage
		 * Declarations in an elaborated network are
		 * ordered, hence any type declarations comes
		 * before usage of such type declaration.
		 */
		for(Declaration d: network.getDeclarations()) {
			doSwitch(d);
		}
		
		//Find usage of user types
		for(ActorInstance a: network.getActors()) {
			doSwitch(a);
		}
		
	    //DEBUG
		new IrReplaceSwitch() {
			private Map<String,String> found = new HashMap<String,String>();
			@Override	
			public TypeUser caseTypeUser(TypeUser type) {
				found.put(((TypeUser) type).getDeclaration().getId(),(((TypeUser) type).getDeclaration() instanceof TypeDeclarationImport?"I_":"R_") +((TypeUser) type).getDeclaration().getName());
				return type;
			}

			@Override
			public AbstractActor caseNetwork(Network network) {
				super.caseNetwork(network);
				System.out.println("[IrTypeAnnotation] ----- Found type declarations usage direcly after treating actors -----");
				for(String f:found.keySet()) {
					System.out.println("[IrTypeAnnotation] Found type declaration " + f + " " + found.get(f));
				}
				return network;
			}
		}.doSwitch(network);
		//End DEBUG

		//Put annotations on user types
		for(Declaration d: userTypes) {
			if(typeUsage.get(d)!=null) {
				//A bit uncertain relies on that toString() prints the String Set as [elem1, elem2]
				String use = typeUsage.get(d).toString();
				use = use.substring(1, use.length()-1);
				IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(d,IrAnnotations.TYPE_ANNOTATION), 
						"TypeUsage",use);
			}
		}
		
		String path = null;
		for(Annotation ann : network.getAnnotations()) {
			if(ann.getName().equals("Project")) {
				for(AnnotationArgument aa : ann.getArguments()) {
					if(aa.getId().equals("name")) {
						path = aa.getValue();
						break;
					}
				}
				if(path!=null)
					break;
			}
		}
		if(path==null) {
			path="";
		}

		//Annotate that the Type pass has executed
		IrAnnotations.AnnotatePass(network, IrAnnotationTypes.Type, "0");
		//Store in ActorDirectory $Transformed section
	    //DEBUG
		new IrReplaceSwitch() {
			private Map<String,String> found = new HashMap<String,String>();
			@Override	
			public TypeUser caseTypeUser(TypeUser type) {
				found.put(((TypeUser) type).getDeclaration().getId(),(((TypeUser) type).getDeclaration() instanceof TypeDeclarationImport?"I_":"R_") +((TypeUser) type).getDeclaration().getName());
				return type;
			}

			@Override
			public AbstractActor caseNetwork(Network network) {
				super.caseNetwork(network);
				System.out.println("[IrTypeAnnotation] ----- Found type declarations usage direcly after treating network -----");
				for(String f:found.keySet()) {
					System.out.println("[IrTypeAnnotation] Found type declaration " + f + " " + found.get(f));
				}
				return network;
			}
		}.doSwitch(network);
		//End DEBUG
		ActorDirectory.addTransformedActor(network, null, path);

		return network;
	}

}
