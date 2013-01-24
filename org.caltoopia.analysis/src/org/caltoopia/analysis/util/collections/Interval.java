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

package org.caltoopia.analysis.util.collections;

import java.util.Set;

/**
 * An (open or closed) interval
 *
 * @param <T> the domain of the interval
 */
public interface Interval<T> {

	/**
	 * @return true iff the interval is empty (contains no elements)
	 */
	public boolean isEmpty();

	/**
	 * @return the minimum element in the closed interval, i.e. 'a' in [a,b] 
	 * @see isEmpty() the interval does not necessarily have a minimum element
	 */
	public T getMin();
	
	/**
	 * @return the maximum element in the closed interval, i.e. 'b' in [a,b]
	 * @see hasMax() the interval does not necessarily have a maximum element
	 */
	public T getMax();
		
	/**
	 * @return true iff the interval consists of a single element
	 */
	public boolean isSingleton();
	
	/**
	 * @param x  an element
	 * @return true iff the interval consists of a single element, x, that is [x,x] or {x}.
	 */
	public boolean isSingleton(T x);

	/**
	 * @return this interval interpreted as a (non-modifiable) Set
	 * @see isFinite() the interval is not necessarily finite, so the size() of the
	 *         set may not well-defined, also iteration over the elements may not be practical.
	 */
	public Set<T> asSet();

	/**
	 * @param x  an element
	 * @return true if x belongs to the interval
	 */
	public boolean contains(T x);
	
	/**
	 * @param interval  an interval
	 * @return true if this interval contains (is a superset of) the given interval
	 *         An interval is considered containing itself (being a non-proper superset is sufficient).
	 */
	public boolean containsAll(Interval<T> interval);
	
	/**
	 * @param interval  an interval
	 * @return true if this interval overlaps the given interval (the intersection is non-empty)
	 */
	public boolean overlaps(Interval<T> interval);
	
	/**
	 * @param interval  an interval
	 * @return the intersection of the given interval and this interval
	 */
	public Interval<T> intersection(Interval<T> interval);
	
	/**
	 * @param interval  an interval
	 * @return the interval enclosure (convex hull) of the union of the given
	 *         interval and this interval (the union is not necessarily an interval). 
	 */
	public Interval<T> unionEnclosure(Interval<T> interval);	
}
