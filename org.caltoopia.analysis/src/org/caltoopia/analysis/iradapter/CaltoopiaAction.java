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

package org.caltoopia.analysis.iradapter;

import java.util.HashMap;
import java.util.Map;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.PortSignature;

public class CaltoopiaAction implements Action {
	private org.caltoopia.ir.Action mAction;
	private PortSignature mPortSignature;
	private Guard mGuard;
	private Map<String, Map<String,String>> annotations = new HashMap<String, Map<String, String>>();
	
	public CaltoopiaAction(org.caltoopia.ir.Action action, PortSignature portSignature, Guard guard) {
		mAction=action;
		mPortSignature=portSignature;
		mGuard=guard;
		for (org.caltoopia.ir.Annotation a : action.getAnnotations()) {
			Map<String, String> annotationArguments = new HashMap<String,String>();
			for (org.caltoopia.ir.AnnotationArgument arg : a.getArguments()) {
				annotationArguments.put(arg.getId(), arg.getValue());
			}
			annotations.put(a.getName(), annotationArguments);
		}
	}

	@Override
	public String getName() {
		return mAction.getId(); 
	}

	@Override
	public PortSignature getPortSignature() {
		return mPortSignature;
	}
	
	public boolean hasGuard() {
		return mGuard!=null;
	}
	
	public Guard getGuard() {
		return mGuard;
	}	
	
	public boolean hasAnnotation(String annotation){
		return annotations.containsKey(annotation);
	}
	
	public Map<String,String> getAnnotationArguments(String annotation){
		return annotations.get(annotation);
	}
	
	public String getAnnotationArgumentValue(String annotation, String argId){
		Map<String,String> annotationArguments = getAnnotationArguments(annotation);
		if(annotationArguments!=null)
			return annotationArguments.get(argId);
		return null;
	}	

}
