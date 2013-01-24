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

import java.util.Map;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.Guard;
import org.caltoopia.analysis.air.PortSignature;

/**
 * "vanilla" = straight-forward implementation of an Action.
 */
public class VanillaAction implements Action {
	private String mName;
	private PortSignature mPortSignature;
	private Guard guard;
	
	public VanillaAction(String name, PortSignature portSignature) {
		mName=name;
		mPortSignature=portSignature;
		guard=null;
	}
	
	@Override
	public String getName() {
		return mName;
	}
	
	@Override
	public PortSignature getPortSignature() {
		return mPortSignature;
	}		
	
	public boolean hasGuard(){
		return guard!=null;
	}
	
	public Guard getGuard(){
		return guard;
	}
	
	public boolean hasAnnotation(String annotation){
		return false;
	}
	
	public Map<String,String> getAnnotationArguments(String annotation){
		return null;
	}
	
	public String getAnnotationArgumentValue(String annotation, String argId){
		return null;
	}	

}
