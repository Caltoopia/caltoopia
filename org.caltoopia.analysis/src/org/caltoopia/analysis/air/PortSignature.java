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

/**
 * Represents a "port signature": a collection of port rates (consumption/production rates).
 * 
 * A PortSignature is a property of a Transition, an Action, a sequence of actions/transitions etc.
 */
public interface PortSignature {

	/**
	 * @return the ports of this PortSignature (which have rates greater than zero)
	 */
	Collection<? extends PortInstance> getPorts();
	
	/**
	 * @param port
	 * @return the rate that is associated with given port in this PortSignature 
	 *         (zero if port is not among the ports with associated rates)
	 */
	int getPortRate(PortInstance port);
	
	/**
	 * @param s  a port signature
	 * @return returns true iff this PortSignature has no greater rate on any port in 's'
	 *         (including the case when a port is not contained in 's' and thus has zero rate). 
	 */
	boolean isSubsetOf(PortSignature s);
	/**
	 * @param s  a port signature
	 * @return a port with higher rate in this PortSignature than in s (null if no such port)
	 */
	PortInstance findPortWithHigherRate(PortSignature s);

	
	/**
	 * @param ports a collection of ports
	 * @return the projection of this PortSignature onto the given collection of ports,
	 *         which means identical rates for ports in the collection and zero rate for all other ports.
	 */
	PortSignature project(Collection<? extends PortInstance> ports);
	
	/**
	 * @param s   a port signature
	 * @return    the "intersection" of this PortSignature and s, in which a port appears
	 *            if it appears in both signatures with an associated rate that is
	 *            the minimum of the two signatures. 
	 */
    PortSignature intersect(PortSignature s);
    
	/**
	 * @param s   a port signature
	 * @return    the "union" of this PortSignature and s, in which a port appears
	 *            if it appears in either of the signatures with an associated 
	 *            rate that is the maximum of the two signatures. 
	 */
	PortSignature union(PortSignature s);
	
	
	/**
	 * @return true iff this is the empty PortSignature (no port with a non-zero rate) 
	 */
	boolean isEmpty();	
}
