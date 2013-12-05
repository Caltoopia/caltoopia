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
import org.caltoopia.codegen.CodegenError;
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
		scalarBuiltin, //A scalar builtin type
        listBuiltin, //A list of builtin types
        dynListBuiltin, //A list of builtin types with varying length
        scalarUserTypeFull, //A scalar user type which only contains builtin types
        scalarUserTypeByRef, //A scalar user type which is reached by ref (could be used for very large user types), but also on *variables* referring into FIFO.
        scalarUserType, //A scalar user type which contains lists or user types
        listUserTypeFull, //A list of user type which only contains builtin types
        dynListUserTypeFull, //A list of user type which only contains builtin types with varying length
        listUserType, //A list of user type which contains lists or user types
        dynListUserType, //A list of user type which contains lists or user types with varying length
		/*
		 * TODO: Add other type of types e.g. tagged tuple-types
		 */
	}

    /*
     * Annotates the user types members, is currently not of much use
     * more than debugging but might be needed when introducing tuple types.
     * 
     * The analysis result of this pass is used very little. Only reason
     * it is still here is due to that when the tuple types are introduced
     * we might need this analysis.
     * 
     * Quality: 2, since rarely used.
     * 
     * node: top network
     * session: contains metadata about the build like directory paths etc
     * errPrint: if error printout should be printed
     */
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
		//start at caseNetwork() below
		this.doSwitch(node);
	}
	
	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration decl) {
	    if(find) {
    		System.out.println("[IrTypeStructureAnnotation] Setting type structure for type declaration '" + decl.getName() + "'");
    		boolean notFull=false;
    		
    		for(Variable m: UtilIR.getMembers(decl.getType())) {
    			if(!UtilIR.isListOrSingleTagTuple(m.getType())) {
    				TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
    						"TypeStructure",TypeMember.scalarBuiltin.name());
    				structs.put(decl.getId() + "__" + m.getName(), TypeMember.scalarBuiltin.name());
    			} else if(UtilIR.isList(m.getType())) {
    			    notFull = true;
    			    Type type = m.getType();
    				boolean hasSize=true;
    				while(type instanceof TypeList) {
    					if(!(((TypeList)type).getSize()!=null && ((TypeList)type).getSize() instanceof IntegerLiteral)) {
    						hasSize=false;
    					}
    					type = ((TypeList)type).getType();
    				}
					if(UtilIR.isSingleTagTuple(type)) {
						TypeDeclaration td = UtilIR.getTypeDeclaration(type);
						TypeMember tm = TypeMember.valueOf(TransUtil.getAnnotationArg(td,IrTransformer.TYPE_ANNOTATION, "TypeStructure"));
						switch (tm) {
                        case scalarUserTypeFull:
                            if(hasSize) {
    						    TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                        "TypeStructure",TypeMember.listUserTypeFull.name());
                                structs.put(decl.getId() + "__" + m.getName(), TypeMember.listUserTypeFull.name());
                            } else {
                                TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                        "TypeStructure",TypeMember.dynListUserTypeFull.name());
                                structs.put(decl.getId() + "__" + m.getName(), TypeMember.dynListUserTypeFull.name());
                            }
                            break;
                        case scalarUserType:
                            if(hasSize) {
                                TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                        "TypeStructure",TypeMember.listUserType.name());
                                structs.put(decl.getId() + "__" + m.getName(), TypeMember.listUserType.name());
                            } else {
                                TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                        "TypeStructure",TypeMember.dynListUserType.name());
                                structs.put(decl.getId() + "__" + m.getName(), TypeMember.dynListUserType.name());
                            }
                            break;
                        default:
                            CodegenError.err("Type struct annotation", "Not expecting a member " + decl.getName() +"."+ m.getName()+ "that is record to be " + tm.name());
						}
					} else if(UtilIR.isTuple(type)) {
                        CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (1) " + decl.getName() +"."+ m.getName());
					} else {
                        if(hasSize) {
    						TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
    								"TypeStructure",TypeMember.listBuiltin.name());
    	                    structs.put(decl.getId() + "__" + m.getName(), TypeMember.listBuiltin.name());
                        } else {
                            TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                    "TypeStructure",TypeMember.dynListBuiltin.name());
                            structs.put(decl.getId() + "__" + m.getName(), TypeMember.dynListBuiltin.name());
                        }
					}
    			} else if(UtilIR.isSingleTagTuple(m.getType())) {
                    TypeDeclaration td = UtilIR.getTypeDeclaration(m.getType());
                    TypeMember tm = TypeMember.valueOf(TransUtil.getAnnotationArg(td,IrTransformer.TYPE_ANNOTATION, "TypeStructure"));
                    switch (tm) {
                    case scalarUserTypeFull:
                            TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                    "TypeStructure",TypeMember.scalarUserTypeFull.name());
                            structs.put(decl.getId() + "__" + m.getName(), TypeMember.scalarUserTypeFull.name());
                        break;
                    case scalarUserType:
                            notFull = true;
                            TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, 
                                    "TypeStructure",TypeMember.scalarUserType.name());
                            structs.put(decl.getId() + "__" + m.getName(), TypeMember.scalarUserType.name());
                        break;
                    default:
                        CodegenError.err("Type struct annotation", "Not expecting a member " + decl.getName() +"."+ m.getName()+ "that is record to be " + tm.name());
                    }
    			} else {
                    CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (2) " + decl.getName() +"."+ m.getName());
    			}
    		}
    		TransUtil.setAnnotation(decl,IrTransformer.TYPE_ANNOTATION, 
    				"TypeStructure",notFull?TypeMember.scalarUserType.name():TypeMember.scalarUserTypeFull.name());
	    }
		return decl;
	}

    @Override
    public VariableReference caseVariableReference(VariableReference var) {
        if(!(var.getDeclaration() instanceof Variable)) return var;
        Type t = ((Variable)var.getDeclaration()).getType();
        if(UtilIR.isMultiTagTuple(t)) {
            CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (3) ");
        }
        if(!find && UtilIR.isListOrSingleTagTuple(t)) {
            while(t instanceof TypeList) {
                t = ((TypeList) t).getType();
            }
            if(UtilIR.isSingleTagTuple(t)) {
                TypeDeclaration td = UtilIR.getTypeDeclaration(t);
                for(Member m: var.getMember()) {
                    String typeStructure = structs.get(td.getId() + "__" + m.getName());
                    if(!typeStructure.isEmpty()) {
                        TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, "TypeStructure", typeStructure);
                    }
                    t = m.getType();
                    while(t instanceof TypeList) {
                        t = ((TypeList) t).getType();
                    }
                    if(UtilIR.isSingleTagTuple(t)) {
                        td = UtilIR.getTypeDeclaration(t);
                    } else if(UtilIR.isTuple(t)) {
                        CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (4) ");
                    } else {
                        break;
                    }
                }
            } else if(UtilIR.isTuple(t)) {
                CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (5) ");
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
        if(UtilIR.isMultiTagTuple(t)) {
            CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (6) ");
        }
        if(!find && UtilIR.isListOrSingleTagTuple(t)) {
            while(t instanceof TypeList) {
                t = ((TypeList) t).getType();
            }
            if(UtilIR.isSingleTagTuple(t)) {
                TypeDeclaration td = UtilIR.getTypeDeclaration(t);
                for(Member m: var.getMember()) {
                    String typeStructure = structs.get(td.getId() + "__" + m.getName());
                    if(!typeStructure.isEmpty()) {
                        TransUtil.setAnnotation(m,IrTransformer.TYPE_ANNOTATION, "TypeStructure", typeStructure);
                    }
                    t = m.getType();
                    while(t instanceof TypeList) {
                        t = ((TypeList) t).getType();
                    }
                    if(UtilIR.isSingleTagTuple(t)) {
                        td = UtilIR.getTypeDeclaration(t);
                    } else if(UtilIR.isTuple(t)) {
                        CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (7) ");
                    } else {
                        break;
                    }
                }
            } else if(UtilIR.isTuple(t)) {
                CodegenError.err("Type struct annotation", "Not yet implemented tuple with multiple tags (8) ");
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