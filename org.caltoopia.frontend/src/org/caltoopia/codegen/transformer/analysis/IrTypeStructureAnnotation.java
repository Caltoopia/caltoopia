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

package org.caltoopia.codegen.transformer.analysis;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil.AnnotationsFilter;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class IrTypeStructureAnnotation extends IrReplaceSwitch {

	private PrintStream serr = null; 
	private boolean find = true;
	
	private Map<String,String> structs = new HashMap<String,String>();
	
	public enum TypeMember {
	    unknown,
		builtin,
		byRef, //Used when either type (or list of non-decided size?)
		byListSome, //Used when list of decided size and inlined  but have deeper members that are not
		byListFull, //Used when list of decided size and inlined and all deeper members also (including lists of builtins)
		inlineSome, //Used when user type that is inlined but have deeper members that are not
		inlineFull //Used when user type is inlined and all deeper members also
		/*
		 * TODO: Add other type of types e.g. with meta-data like list size, or tagged poly-types
		 */
	}

	public IrTypeStructureAnnotation(Node node, CompilationSession session, boolean errPrint) {
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
	
	/*
	 * Any record member that refers to a type declaration which is inlined
	 * in full is inlined unless it is used on an output port and as member
	 * in same action.
	 */
	private boolean recordMemberInline(TypeDeclaration decl, TypeDeclaration td, Variable m, boolean isList) {
		boolean anyByRef=false;
		switch(TypeMember.valueOf(TransUtil.getAnnotationArg(td, IrTransformer.TYPE_ANNOTATION, "TypeStructure"))) {
		case builtin:
		case inlineFull:
		case byListFull:
			if(TransUtil.getAnnotationArg(td, IrTransformer.TYPE_ANNOTATION, "TypeUsage").contains("memberOutPortVar")) {
				TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
						"TypeStructure",isList?TypeMember.byListSome.name():TypeMember.inlineSome.name());
                structs.put(decl.getId() + "__" + m.getName(), isList?TypeMember.byListSome.name():TypeMember.inlineSome.name());
				anyByRef=true;
			} else {
				TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
						"TypeStructure",isList?TypeMember.byListFull.name():TypeMember.inlineFull.name());
                structs.put(decl.getId() + "__" + m.getName(), isList?TypeMember.byListFull.name():TypeMember.inlineFull.name());
			}
			break;
		case byRef:
		case byListSome:
		case inlineSome:
			TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
					"TypeStructure",isList?TypeMember.byListSome.name():TypeMember.inlineSome.name());
            structs.put(decl.getId() + "__" + m.getName(), isList?TypeMember.byListSome.name():TypeMember.inlineSome.name());
			anyByRef=true;
			break;
		}
		return anyByRef;
	}
	
	/*
	 * At the moment this function inline everything but
	 * types in same action that is used as both
	 * member and output port variable.
	 */
	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration decl) {
	    if(find) {
    		System.out.println("[IrTypeStructureAnnotation] Setting type structure for type declaration '" + decl.getName() + "'");
    		boolean anyByRef=false;
    		
    		for(Variable m: ((TypeRecord)decl.getType()).getMembers()) {
    			if(!UtilIR.isListOrRecord(m.getType())) {
    				TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
    						"TypeStructure",TypeMember.builtin.name());
    				structs.put(decl.getId() + "__" + m.getName(), TypeMember.builtin.name());
    			} else if(UtilIR.isList(m.getType())) {
    				Type type = m.getType();
    				boolean hasSize=true;
    				while(type instanceof TypeList) {
    					if(!(((TypeList)type).getSize()!=null && ((TypeList)type).getSize() instanceof IntegerLiteral)) {
    						hasSize=false;
    					}
    					type = ((TypeList)type).getType();
    				}
    				if(hasSize) {
    					if(UtilIR.isRecord(type)) {
    						TypeDeclaration td = UtilIR.getTypeDeclaration(type);
    						anyByRef=recordMemberInline(decl,td,m,true);
    					} else {
    						TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
    								"TypeStructure",TypeMember.byListFull.name());
    	                    structs.put(decl.getId() + "__" + m.getName(), TypeMember.byListFull.name());
    					}
    				} else {
    					//List of unknown size
    					TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
    							"TypeStructure",TypeMember.byRef.name());
                        structs.put(decl.getId() + "__" + m.getName(), TypeMember.byRef.name());
    					anyByRef=true;
    				}
    			} else if(UtilIR.isRecord(m.getType())) {
    				TypeDeclaration td = UtilIR.getTypeDeclaration(m.getType());
    				anyByRef=recordMemberInline(decl,td,m,false);
    			} else {
    				anyByRef=true;
    			}
    		}
    
    		anyByRef = anyByRef || TransUtil.getAnnotationArg(decl, IrTransformer.TYPE_ANNOTATION, "TypeUsage").contains("memberOutPortVar");
    		TransUtil.setAnnotation(decl,IrTransformer.TYPE_ANNOTATION, 
    				"TypeStructure",anyByRef?TypeMember.inlineSome.name():TypeMember.inlineFull.name());
	    }
		return decl;
	}

    @Override
    public VariableReference caseVariableReference(VariableReference var) {
        if(!(var.getDeclaration() instanceof Variable)) return var;
        Type t = ((Variable)var.getDeclaration()).getType();
        if(!find && UtilIR.isListOrRecord(t)) {
            while(t instanceof TypeList) {
                t = ((TypeList) t).getType();
            }
            if(UtilIR.isRecord(t)) {
                TypeDeclaration td = UtilIR.getTypeDeclaration(t);
                for(Member m: var.getMember()) {
                    String typeStructure = structs.get(td.getId() + "__" + m.getName());
                    if(!typeStructure.isEmpty()) {
                        TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, "TypeStructure", typeStructure);
                        break;
                    }
                    t = m.getType();
                    while(t instanceof TypeList) {
                        t = ((TypeList) t).getType();
                    }
                    if(UtilIR.isRecord(t)) {
                        td = UtilIR.getTypeDeclaration(t);
                    } else {
                        break;
                    }
                }
            }
        }
        return var;
    }

    @Override
    public VariableExpression caseVariableExpression(VariableExpression var) {
        Type t = null;
        Declaration decl = UtilIR.getDeclarationTransformed(var.getVariable());
        if(decl instanceof Variable) {
            t = ((Variable) decl).getType();
        } else if(decl instanceof VariableExternal) {
            t = ((VariableExternal) decl).getType();
        }
        if(!find && UtilIR.isListOrRecord(t)) {
            while(t instanceof TypeList) {
                t = ((TypeList) t).getType();
            }
            if(UtilIR.isRecord(t)) {
                TypeDeclaration td = UtilIR.getTypeDeclaration(t);
                for(Member m: var.getMember()) {
                    String typeStructure = structs.get(td.getId() + "__" + m.getName());
                    if(!typeStructure.isEmpty()) {
                        TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, "TypeStructure", typeStructure);
                        break;
                    }
                    t = m.getType();
                    while(t instanceof TypeList) {
                        t = ((TypeList) t).getType();
                    }
                    if(UtilIR.isRecord(t)) {
                        td = UtilIR.getTypeDeclaration(t);
                    } else {
                        break;
                    }
                }
            }
        }
        return var;
    }

	
	@Override
	public AbstractActor caseNetwork(Network network) {
		/*DEBUG
		System.out.println("[IrTypeStructurAnnotation] Entered caseNetwork");
		for(Declaration d: network.getDeclarations()){
			if(d instanceof TypeDeclaration) {
				Annotation a = IrTransformer.getAnalysAnnotations(d, IrTransformer.TYPE_ANNOTATION);
				System.out.println("[IrTypeStructurAnnotation] Get type decl " + d.getName() + "having a type annotation " + (a.getArguments().isEmpty()?"that is empty":":"));
				for(AnnotationArgument aa: a.getArguments()){
					System.out.println("[IrTypeStructurAnnotation]     " + aa.getId() + " = " + aa.getValue());
				}
			}
		}
		*/
	    find = true;
		for(Declaration d: network.getDeclarations()) {
			doSwitch(d);
		}
		
        find = false;
        for(Declaration d: network.getDeclarations()) {
            doSwitch(d);
        }

        String path = TransUtil.getPath(network);

		//Annotate that the Type Structure pass has executed
		TransUtil.AnnotatePass(network, IrPassTypes.TypeStructure, "0");
		//Store in ActorDirectory $Transformed section
		ActorDirectory.addTransformedActor(network, null, path);

        for(ActorInstance a : network.getActors()) {
              AbstractActor actor=null;
              try {
                  System.out.println("[IrTypeStructure] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                  actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
              } catch (DirectoryException x) {
                  //serr.println("[IrTypeStructure] Internal error could not get actor of type " + a.getType().toString());
              }
              if(actor!=null && !(actor instanceof ExternalActor)) {
                  actor = (AbstractActor) doSwitch(actor);
                  path = TransUtil.getPath(actor);
                  TransUtil.AnnotatePass(actor, IrPassTypes.TypeStructure, "0");
                  ActorDirectory.addTransformedActor(actor, a, path);
              }
        }

		return network;
	}

}