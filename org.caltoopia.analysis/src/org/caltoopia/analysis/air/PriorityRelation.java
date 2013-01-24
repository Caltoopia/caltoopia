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

import java.util.Set;

/**
 * Models the priority relation among actions or transitions
 */
public interface PriorityRelation {
	
	/**
	 * @param x 
	 * @param y 
	 * @return true iff x is an ancestor of (has -strictly- higher priority than) y
	 *         false otherwise (y ancestor of x, x=y or x,y unordered) 
	 */
	boolean isAncestorOf(Transition x, Transition y);
	
	/**
	 * @param x
	 * @param y
	 * @return true iff x and y are unorderd
	 *         false otherwise (x ancestor of y, vice versa or x=y) 
	 */
	boolean areUnordered(Transition x, Transition y);
	
	/**
	 * @param x  
	 * @return the set of ancestors of x (elements with strictly higher priority)
	 */
	Set<? extends Transition> getAncestors(Transition x);
	
	/**
	 * @param x
	 * @return the set of descendants of x (elements with strictly lower priority)
	 */
	Set<? extends Transition> getDescendants(Transition x);
	
	/**
	 * @param x
	 * @return the set of elements that are not ordered relative x (i.e. elements that
	 *         are distinct from x and neither have strictly higher nor strictly lower priority).
	 */
	Set<? extends Transition> getUnordered(Transition x);
}
