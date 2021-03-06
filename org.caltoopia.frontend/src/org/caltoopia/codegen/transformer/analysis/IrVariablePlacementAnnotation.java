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

import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.IrXmlPrinter;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.TransUtil.AnnotationsFilter;
import org.caltoopia.codegen.transformer.TransUtil.HowLiteral;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation.TypeMember;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;

public class IrVariablePlacementAnnotation extends IrReplaceSwitch {

	private PrintStream serr = null; 
	private CompilationSession session;

    /*
     * Annotates variable placement, i.e. heap, stack, fifo etc, 
     * this is not followed in the printer now when we have a runtime handling
     * of arrays and user types (i.e. all print the same no change at static level)
     * 
     * FIXME remove this code and any users and reimplement correct behavior when 
     * we want to do optimization on placement due to running on same processor etc.
     *  
     * Quality: 1, should not use the result.
     * 
     * node: top network
     * session: contains metadata about the build like directory paths etc
     * errPrint: if error printout should be printed
     */
	public IrVariablePlacementAnnotation(Node node, CompilationSession session, boolean errPrint) {
		if(!errPrint) {
			serr = new PrintStream(new OutputStream(){
			    public void write(int b) {
			        //NO-OP
			    }
			});
		} else {
			serr = System.err;
		}
		this.session = session;
		this.doSwitch(node);
	}

	/*
	 * Classification of placement
	 * NB! these does not make sense anymore
	 */
	public enum VarPlacement {
		unknown,
		code, //This variable declaration is a function or procedure etc
		constant,
		actor, //Placed in constructor
        auto,
        autoDeepHeap,
        autoListHeap,
        autoListDeepHeap,
		heap,
		ref, //used when passing as parameter as ref
        autoListFifo,
        autoListDeepFifo,
		fifo,
        fifoDeepFifo,
	}
	
	@Override
	public Declaration caseVariable(Variable var) {
		//Get annotations
		Annotation varAnnotation = TransUtil.getAnnotation(var,IrTransformer.VARIABLE_ANNOTATION);
		Map<String,String> annotations = new HashMap<String,String>();
		for(AnnotationArgument aa:varAnnotation.getArguments()) {
			annotations.put(aa.getId(), aa.getValue());
		}
		boolean litSize = true;
		Type type = var.getType();
		while(type instanceof TypeList) {
		    HowLiteral h = TransUtil.isLiteralExpression(((TypeList)type).getSize());
		    if(!h.builtin) {
		        litSize = false;
		    }
			type = ((TypeList)type).getType();
		}
		TypeDeclaration td=null;
		if(UtilIR.isRecord(type)) {
			td = UtilIR.getTypeDeclaration(type);
			Annotation typeAnnotation = TransUtil.getAnnotation(td,IrTransformer.TYPE_ANNOTATION);
			if(!typeAnnotation.getArguments().isEmpty()) {
				for(AnnotationArgument aa:typeAnnotation.getArguments()) {
					annotations.put(aa.getId(), aa.getValue());
				}
			} else {
				System.err.println("[IrVariablePlacementAnnotation] A variable with a user type without annotations " + td.getName() + ": " + annotations.toString());
			}
		} else {
			//Since non-user types don't have a TypeDeclaration just put a builtin in the annotations map
			if(UtilIR.isList(var.getType())) {
			    if(litSize) {
			        annotations.put("TypeStructure", IrTypeStructureAnnotation.TypeMember.listBuiltin.name());
			    } else {
                    annotations.put("TypeStructure", IrTypeStructureAnnotation.TypeMember.dynListBuiltin.name());
			    }
			} else {
				annotations.put("TypeStructure", IrTypeStructureAnnotation.TypeMember.scalarBuiltin.name());
			}
		}
		// -- Decide placement --
		VarPlacement placement = VarPlacement.unknown;
		//constants?
		if(Arrays.asList(IrVariableAnnotation.VarType.constVar.name(),
                         IrVariableAnnotation.VarType.actorConstVar.name(),
                         IrVariableAnnotation.VarType.importConstVar.name(),
                         IrVariableAnnotation.VarType.blockConstVar.name(),
                         IrVariableAnnotation.VarType.actorConstParamVar.name()).contains(annotations.get("VarType"))) {
			placement = VarPlacement.constant;
		//actor constructor?
		} else if(Arrays.asList(IrVariableAnnotation.VarType.actorVar.name(), 
				 				IrVariableAnnotation.VarType.actorParamVar.name()).contains(annotations.get("VarType"))) {
			placement = VarPlacement.actor;
		//normal body var?
		} else if(Arrays.asList(
                IrVariableAnnotation.VarType.funcVar.name(), 
                IrVariableAnnotation.VarType.generatorVar.name(), 
                IrVariableAnnotation.VarType.blockVar.name(), 
				IrVariableAnnotation.VarType.procVar.name(), 
				IrVariableAnnotation.VarType.actionVar.name(),
				IrVariableAnnotation.VarType.actionInitInDepVar.name()).contains(annotations.get("VarType"))) {
		    switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
		    case scalarBuiltin:
		    case scalarUserTypeFull:
                placement = VarPlacement.auto;
                break;
            case listBuiltin:
            case listUserTypeFull:
            case dynListBuiltin:
            case dynListUserTypeFull:
                placement = VarPlacement.autoListHeap;
                break;
            case listUserType:
            case dynListUserType:
                placement = VarPlacement.autoListDeepHeap;
                break;
            case scalarUserType:
                placement = VarPlacement.autoDeepHeap;
                break;
            default:
                CodegenError.err("Variable placement", "Forgotten placement decision #1");
		    }
		//func & proc param?
		} else if(Arrays.asList(
				IrVariableAnnotation.VarType.funcInParamVar.name(), 
				IrVariableAnnotation.VarType.procInParamVar.name()).contains(annotations.get("VarType"))) {
            switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
            case scalarBuiltin:
            case scalarUserTypeFull:
                placement = VarPlacement.auto;
                break;
            case listBuiltin:
            case listUserTypeFull:
            case dynListBuiltin:
            case dynListUserTypeFull:
                placement = VarPlacement.autoListHeap;
                break;
            case listUserType:
            case dynListUserType:
                placement = VarPlacement.autoListDeepHeap;
                break;
            case scalarUserType:
                placement = VarPlacement.autoDeepHeap;
                break;
            default:
                CodegenError.err("Variable placement", "Forgotten placement decision #1");
            }
		} else if(annotations.get("VarType").equals(IrVariableAnnotation.VarType.procOutParamVar.name())) {
			placement = VarPlacement.ref;
		//in port var?
		} else if(Arrays.asList(
				IrVariableAnnotation.VarType.peekVar.name(),
				IrVariableAnnotation.VarType.inPortPeekVar.name(),
				IrVariableAnnotation.VarType.inPortVar.name()).contains(annotations.get("VarType"))) {
			if(annotations.containsKey("VarAssign") && 
			   annotations.get("VarAssign").equals(IrVariableAnnotation.VarAssign.assigned.name())){
			    //In port var should never be assign by anything but a port read!!! FIXME is this code used, check this!!!
	            switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
	            case scalarBuiltin:
	            case scalarUserTypeFull:
	                placement = VarPlacement.auto;
	                break;
	            case listBuiltin:
	            case listUserTypeFull:
	            case dynListBuiltin:
	            case dynListUserTypeFull:
	                placement = VarPlacement.autoListHeap;
	                break;
	            case listUserType:
	            case dynListUserType:
	                placement = VarPlacement.autoListDeepHeap;
	                break;
	            case scalarUserType:
	                placement = VarPlacement.autoDeepHeap;
	                break;
	            default:
	                CodegenError.err("Variable placement", "Forgotten placement decision #1");
	            }
			} else {
                switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
                case scalarBuiltin:
                    placement = VarPlacement.auto;
                    break;
                case scalarUserTypeFull:
                    placement = VarPlacement.fifo;
                    break;
                case listBuiltin:
                case dynListBuiltin:
                    placement = VarPlacement.autoListHeap;
                    break;
                case listUserTypeFull:
                case dynListUserTypeFull:
                    placement = VarPlacement.autoListFifo;
                    break;
                case listUserType:
                case dynListUserType:
                    placement = VarPlacement.autoListDeepFifo;
                    break;
                case scalarUserType:
                    placement = VarPlacement.fifoDeepFifo;
                    break;
                default:
                    CodegenError.err("Variable placement", "Forgotten placement decision #2");
                }

			    /* FIXME should we check VarAccess above is the types not enough???
			    if(annotations.get("TypeStructure").equals(IrTypeStructureAnnotation.TypeMember.inlineFull.name())) {
					if(annotations.get("VarAccessIn").equals(IrVariableAnnotation.VarAccess.listUserTypeSingle.name())) {
						placement = VarPlacement.fifoList;
					} else {
						placement = VarPlacement.fifo;
					}
				} else if(Arrays.asList(IrTypeStructureAnnotation.TypeMember.builtin.name(), 
						 IrTypeStructureAnnotation.TypeMember.byListFull.name()).contains(annotations.get("TypeStructure"))) {
					placement = VarPlacement.auto;
				} else {
					placement = VarPlacement.heap;
				}*/
			}
		//in port var that is never read?
		} else if(annotations.get("VarType").equals(IrVariableAnnotation.VarType.syncVar.name())) {
			placement = VarPlacement.fifo;
		//out port var? FIXME need to look into directly placing in FIFO
		} else if(Arrays.asList(
				IrVariableAnnotation.VarType.outPortVar.name(),
				IrVariableAnnotation.VarType.outPortInitInDepVar.name()).contains(annotations.get("VarType"))) {
            switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
            case scalarBuiltin:
                placement = VarPlacement.auto;
                break;
            case scalarUserTypeFull:
                placement = VarPlacement.fifo;
                break;
            case listBuiltin:
            case dynListBuiltin:
            case listUserTypeFull:
            case dynListUserTypeFull:
                placement = VarPlacement.autoListHeap;
                break;
            case listUserType:
            case dynListUserType:
                placement = VarPlacement.autoListDeepHeap;
                break;
            case scalarUserType:
                placement = VarPlacement.autoDeepHeap;
                break;
            default:
                CodegenError.err("Variable placement", "Forgotten placement decision #3");
            }
          //in/out port var? FIXME need to look into how and when to copy from in to out FIFO placement.
        } else if(Arrays.asList(
                IrVariableAnnotation.VarType.inOutPortVar.name(),
                IrVariableAnnotation.VarType.inOutPortPeekVar.name(),
                IrVariableAnnotation.VarType.outPortVar.name(),
                IrVariableAnnotation.VarType.outPortInitInDepVar.name()).contains(annotations.get("VarType"))) {
            switch (TypeMember.valueOf(annotations.get("TypeStructure"))) {
            case scalarBuiltin:
                placement = VarPlacement.auto;
                break;
            case scalarUserTypeFull:
                placement = VarPlacement.fifo;
                break;
            case listBuiltin:
            case dynListBuiltin:
                placement = VarPlacement.autoListHeap;
                break;
            case listUserTypeFull:
            case dynListUserTypeFull:
                placement = VarPlacement.autoListFifo;
                break;
            case listUserType:
            case dynListUserType:
                placement = VarPlacement.autoListDeepFifo;
                break;
            case scalarUserType:
                placement = VarPlacement.fifoDeepFifo;
                break;
            default:
                CodegenError.err("Variable placement", "Forgotten placement decision #2");
            }
		} else if(Arrays.asList(
                IrVariableAnnotation.VarType.func.name(),
                IrVariableAnnotation.VarType.proc.name(),
                IrVariableAnnotation.VarType.importFunc.name(),
                IrVariableAnnotation.VarType.externProc.name(),
                IrVariableAnnotation.VarType.externFunc.name(),
                IrVariableAnnotation.VarType.importProc.name()).contains(annotations.get("VarType"))) {
		    placement = VarPlacement.code;
		}
		
		TransUtil.setAnnotation(varAnnotation,"VarPlacement",placement.name());

		return super.caseVariable(var);
	}
	
	@Override
	public AbstractActor caseNetwork(Network obj) {
		/*DEBUG
		System.out.println("[IrVariablePlacement] Entered caseNetwork");
		for(Declaration d: obj.getDeclarations()){
			if(d instanceof TypeDeclaration) {
				Annotation a = TransUtil.getAnnotation(d, IrTransformer.TYPE_ANNOTATION);
				System.out.println("[IrVariablePlacement] Get type decl " + d.getName() + "having a type annotation " + (a.getArguments().isEmpty()?"that is empty":":"));
				for(AnnotationArgument aa: a.getArguments()){
					System.out.println("[IrVariablePlacement]     " + aa.getId() + " = " + aa.getValue());
				}
			}
		}
		//DEBUG END*/
		AbstractActor ret = super.caseNetwork(obj);
        new IrReplaceSwitch(){
            @Override
            public VariableReference caseVariableReference(VariableReference var) {
                super.caseVariableReference(var);
                TransUtil.copySelectedAnnotations(var, var.getDeclaration(), new AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement"}));
                return var;
            }

            @Override
            public VariableExpression caseVariableExpression(VariableExpression var) {
                super.caseVariableExpression(var);
                TransUtil.copySelectedAnnotations(var, var.getVariable(), new AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement"}));
                return var;
            }
        }.doSwitch(ret);
		/*DEBUG
		System.out.println("[IrVariablePlacement] Done super.caseNetwork");
		for(Declaration d: obj.getDeclarations()){
			if(d instanceof TypeDeclaration) {
				Annotation a = TransUtil.getAnnotation(d, IrTransformer.TYPE_ANNOTATION);
				System.out.println("[IrVariablePlacement] Get type decl " + d.getName() + "having a type annotation " + (a.getArguments().isEmpty()?"that is empty":":"));
				for(AnnotationArgument aa: a.getArguments()){
					System.out.println("[IrVariablePlacement]     " + aa.getId() + " = " + aa.getValue());
				}
			}
		}
		//DEBUG END*/
		String path = TransUtil.getPath(ret);
		TransUtil.AnnotatePass(ret, IrPassTypes.VariablePlacement, "0");
		ActorDirectory.addTransformedActor(ret, null, path);

		for(ActorInstance a : obj.getActors()) {
			AbstractActor actor=null;
			/*DEBUG
			System.out.println("[IrVariablePlacement] Before actor instance " + a.getName());
			for(Declaration d: obj.getDeclarations()){
				if(d instanceof TypeDeclaration) {
					Annotation an = TransUtil.getAnnotation(d, IrTransformer.TYPE_ANNOTATION);
					System.out.println("[IrVariablePlacement] Get type decl " + d.getName() + "having a type annotation " + (an.getArguments().isEmpty()?"that is empty":":"));
					for(AnnotationArgument aa: an.getArguments()){
						System.out.println("[IrVariablePlacement]     " + aa.getId() + " = " + aa.getValue());
					}
				}
			}
			//DEBUG END*/
			try {
				System.out.println("[IrAnnotateVariablePlacement] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
				actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
			} catch (DirectoryException x) {
				//serr.println("[IrAnnotateVariable] Internal error could not get actor of type " + a.getType().toString());
			}
			if(actor!=null && !(actor instanceof ExternalActor)) {
				/*DEBUG
				System.out.println("[IrVariablePlacement] Imported type decl from actor instance " + a.getName());
				for(Declaration di: actor.getDeclarations()){
					if(di instanceof TypeDeclarationImport) {
						TypeDeclaration d;
						try {
							d = ActorDirectory.findTypeDeclaration((TypeDeclarationImport)di);
							Annotation an = TransUtil.getAnnotation(d, IrTransformer.TYPE_ANNOTATION);
							System.out.println("[IrVariablePlacement] Get type decl " + d.getName() + "having a type annotation " + (an.getArguments().isEmpty()?"that is empty":":"));
							for(AnnotationArgument aa: an.getArguments()){
								System.out.println("[IrVariablePlacement]     " + aa.getId() + " = " + aa.getValue());
							}
						} catch (DirectoryException e) {
							System.err.println("[IrVariablePlacement] could not get type declaration!");
						}
					}
				}
				//DEBUG END*/
				actor = (AbstractActor) doSwitch(actor);
		        new IrReplaceSwitch(){
		            @Override
		            public VariableReference caseVariableReference(VariableReference var) {
		                super.caseVariableReference(var);
		                TransUtil.copySelectedAnnotations(var, var.getDeclaration(), new AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement"}));
		                return var;
		            }

		            @Override
		            public VariableExpression caseVariableExpression(VariableExpression var) {
		                super.caseVariableExpression(var);
		                TransUtil.copySelectedAnnotations(var, var.getVariable(), new AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement"}));
		                return var;
		            }
		        }.doSwitch(actor);
				path = TransUtil.getPath(actor);
				TransUtil.AnnotatePass(actor, IrPassTypes.VariablePlacement, "0");
				ActorDirectory.addTransformedActor(actor, a, path);
			}
		}
		return ret;
	}

}
