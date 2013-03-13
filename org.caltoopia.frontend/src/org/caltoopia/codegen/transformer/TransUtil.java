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

package org.caltoopia.codegen.transformer;

import java.util.List;

import org.caltoopia.codegen.transformer.IrTransformer.IrAnnotationTypes;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Node;
import org.eclipse.emf.ecore.EObject;

public class TransUtil {
	static public Annotation createAnnotation(EObject obj, String name) {
		List<Annotation> annotations = null;
		//Most obj is a node
		if(obj instanceof Node) {
			annotations = ((Node)obj).getAnnotations();
		} else {
			return null;
		}
		for(Annotation a : annotations) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		//No analyze annotation yet but we should have one
		Annotation a = IrFactory.eINSTANCE.createAnnotation();
		a.setName(name);
		annotations.add(a);
		return a;
	}

	static public Annotation getAnnotation(EObject obj, String name) {
		List<Annotation> annotations = null;
		//Most obj is a node
		if(obj instanceof Node) {
			annotations = ((Node)obj).getAnnotations();
		} else {
			return null;
		}
		for(Annotation a : annotations) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	static public String getAnnotationArg(EObject obj, String name, String key) {
		Annotation a = getAnnotation(obj, name);
		if(a!=null) {
			for(AnnotationArgument aa : a.getArguments()) {
				if(aa.getId().equals(key)) {
					return aa.getValue();
				}
			}
		}
		if(key.equals("VarAccess"))
			return VarAccess.unknown.name();
		else if(key.equals("VarType"))
			return VarType.unknown.name();
		else
			return "unknown";
	}

	public static void setAnnotation(EObject obj, String name, String key, String value) {
		setAnnotation(createAnnotation(obj, name),key,value);
	}
	
	public static void setAnnotation(Annotation a, String key, String value) {
		for(AnnotationArgument aa : a.getArguments()) {
			if(aa.getId().equals(key)) {
				aa.setValue(value);
				return;
			}
		}
		AnnotationArgument aa = IrFactory.eINSTANCE.createAnnotationArgument();
		aa.setId(key);
		aa.setValue(value);
		a.getArguments().add(aa);
	}

	static public void AnnotatePass(Node node, IrAnnotationTypes t, String result) {
		setAnnotation(node,"AnnotationPasses",t.name(),result);
	}

	static public String getPath(AbstractActor actor) {
		for(Annotation ann : actor.getAnnotations()) {
			if(ann.getName().equals("Project")) {
				for(AnnotationArgument aa : ann.getArguments()) {
					if(aa.getId().equals("name")) {
						return aa.getValue();
					}
				}
			}
		}
		return null;
	}
}