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

package org.caltoopia.analysis.air;

import java.util.Collection;
import java.util.Map;

public interface ActorInstance {

	/**
	 * @return a descriptive name of the actor instance, 
	 *         which is unique within the actor network
	 */
	String getInstanceName();
	
	/**
	 * @return the ports of the actor
	 */
	Collection<? extends PortInstance> getPorts();
	
	
	/**
	 * @return the input ports of the actor
	 */
	Collection<? extends PortInstance> getInputPorts();
	
	/**
	 * @return the output ports of the actor
	 */
	Collection<? extends PortInstance> getOutputPorts();
	
	/**
	 * @param name  a port name
	 * @return the port with that name (null if the actor has none)
	 */
	PortInstance getPort(String name);
	
	/**
	 * @return true if the actor has a known implementation (false if it is external/unknown)
	 */
	boolean hasImplementation();

	/**
	 * @return the ActorImplementation, additional implementation details, if known 
	 *         (null if the actor is external/has an unknown implementation)
	 */
	ActorImplementation getImplementation();
	
	/**
	 * @param annotation name
	 * @return true if the annotation exists or false otherwise.
	 */
	boolean hasAnnotation(String annotation);
	
	/**
	 * @param annotation name
	 * @return map of annotation arguments if annotation exists or null otherwise
	 */
	Map<String,String> getAnnotationArguments(String annotation);
	
	/**
	 * @param annotation name
	 * @param argId - argument id
	 * @return the value of the argument id if it exists in the map of the annotation.
	 * 			null if the argument id does not exist in the map of the annotation.
	 */
	String getAnnotationArgumentValue(String annotation, String argId);
}
