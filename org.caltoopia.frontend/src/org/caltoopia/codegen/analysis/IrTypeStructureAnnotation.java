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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.analysis.IrAnnotations.IrAnnotationTypes;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class IrTypeStructureAnnotation extends IrReplaceSwitch {

	private PrintStream serr = null; 
	AbstractActor currentActor=null;

	public enum TypeMember {
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
	 * At the moment this function inline everything when possible
	 */
	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration decl) {
		System.out.println("[IrTypeStructureAnnotation] Setting type structure for type declaration '" + decl.getName() + "'");
		List<String> typeUsage = Arrays.asList(IrAnnotations.getAnnotationArg(decl, 
								IrAnnotations.TYPE_ANNOTATION, "TypeUsage").split(", *"));
		boolean anyByRef=false;
		
		for(Variable m: ((TypeRecord)decl.getType()).getMembers()) {
			List<String> memberUsage = Arrays.asList(IrAnnotations.getAnnotationArg(m, 
					IrAnnotations.TYPE_ANNOTATION, "TypeUsage").split(", *"));
			if(!UtilIR.isListOrRecord(m.getType())) {
				IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
						"TypeStructure",TypeMember.builtin.name());
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
						switch(TypeMember.valueOf(IrAnnotations.getAnnotationArg(td, IrAnnotations.TYPE_ANNOTATION, "TypeStructure"))) {
						case builtin:
						case inlineFull:
						case byListFull:
							IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
									"TypeStructure",TypeMember.byListFull.name());
							break;
						case byRef:
						case byListSome:
						case inlineSome:
							IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
									"TypeStructure",TypeMember.byListSome.name());
							anyByRef=true;
							break;
						}
					} else {
						IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
								"TypeStructure",TypeMember.byListFull.name());
					}
				} else {
					//List of unknown size
					IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
							"TypeStructure",TypeMember.byRef.name());
					anyByRef=true;
				}
			} else if(UtilIR.isRecord(m.getType())) {
				TypeDeclaration td = UtilIR.getTypeDeclaration(m.getType());
				switch(TypeMember.valueOf(IrAnnotations.getAnnotationArg(td, IrAnnotations.TYPE_ANNOTATION, "TypeStructure"))) {
				case builtin:
				case inlineFull:
				case byListFull:
					IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
							"TypeStructure",TypeMember.inlineFull.name());
					break;
				case byRef:
				case byListSome:
				case inlineSome:
					IrAnnotations.setAnnotation(IrAnnotations.getAnalysAnnotations(m,IrAnnotations.TYPE_ANNOTATION), 
							"TypeStructure",TypeMember.inlineSome.name());
					anyByRef=true;
					break;
				}
			} else {
				anyByRef=true;
			}
		}

		IrAnnotations.setAnnotation(
				IrAnnotations.getAnalysAnnotations(decl,IrAnnotations.TYPE_ANNOTATION), 
				"TypeStructure",anyByRef?TypeMember.inlineSome.name():TypeMember.inlineFull.name());
		return decl;
	}

	
	@Override
	public AbstractActor caseNetwork(Network network) {
		for(Declaration d: network.getDeclarations()) {
			doSwitch(d);
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

		//Annotate that the Type Structure pass has executed
		IrAnnotations.AnnotatePass(network, IrAnnotationTypes.TypeStructure, "0");
		//Store in ActorDirectory $Transformed section
		ActorDirectory.addTransformedActor(network, null, path);

		return network;
	}

}