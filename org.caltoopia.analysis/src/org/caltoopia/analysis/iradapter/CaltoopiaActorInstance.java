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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.util.collections.UnionCollection;

/**
 * Represents an ActorInstance, a node of a dataflow graph 
 * basically an adapter for org.caltoopia.ir.Network objects   
 */
public class CaltoopiaActorInstance implements org.caltoopia.analysis.air.ActorInstance {

	private org.caltoopia.ir.ActorInstance mActorInstance; 
    private org.caltoopia.ir.AbstractActor mInstantiatedActorClass;
    private List<org.caltoopia.analysis.air.PortInstance> mInputPorts;
    private List<org.caltoopia.analysis.air.PortInstance> mOutputPorts;
    private Map<String, Map<String,String>> annotations = new HashMap<String, Map<String, String>>();

	public CaltoopiaActorInstance(org.caltoopia.ir.ActorInstance actorInstance, 
			                      org.caltoopia.ir.AbstractActor instantiatedActorClass) {
		mActorInstance=actorInstance;
		mInstantiatedActorClass=instantiatedActorClass;
		// Create the ports
		mInputPorts=createPorts(actorInstance.getInputs(),PortInstance.Direction.IN);
		mOutputPorts=createPorts(actorInstance.getOutputs(),PortInstance.Direction.OUT);	

		for (org.caltoopia.ir.Annotation a : instantiatedActorClass.getAnnotations()) {
			Map<String, String> annotationArguments = new HashMap<String,String>();
			for (org.caltoopia.ir.AnnotationArgument arg : a.getArguments()) {
				annotationArguments.put(arg.getId(), arg.getValue());
			}
			annotations.put(a.getName(), annotationArguments);
		}
	}
	
	@Override
	public String getInstanceName() {
		return mActorInstance.getName();
	}

	
	@Override
	public Collection<PortInstance> getPorts() {
		return new UnionCollection<PortInstance>(mInputPorts,mOutputPorts);
	}

	@Override
	public List<PortInstance> getInputPorts() {
		return mInputPorts;
	}

	@Override
	public List<PortInstance> getOutputPorts() {
		return mOutputPorts;
	}

	@Override
	public PortInstance getPort(String name) {
		for (PortInstance p: getPorts()) {
			if (p.getName().equals(name))
				return p;
		}
		
		return null;
	}

	
	@Override
	public boolean hasImplementation() {
		return false;
	}

	@Override
	public ActorImplementation getImplementation() {
		return null;
	}

	@Override
	public String toString() {
		return getInstanceName();
	}	
	
	private List<org.caltoopia.analysis.air.PortInstance> createPorts(List<org.caltoopia.ir.PortInstance> ports, 
			                                                          PortInstance.Direction direction) {
		List<org.caltoopia.analysis.air.PortInstance> result=new ArrayList<org.caltoopia.analysis.air.PortInstance>();
		for (org.caltoopia.ir.PortInstance port: ports) {
			PortInstance newPort=new VanillaPortInstance(this, port.getName(), direction);
			result.add(newPort);
		}
		return result;
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
