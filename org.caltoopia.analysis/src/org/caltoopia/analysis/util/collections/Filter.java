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

import java.util.Iterator;

public interface Filter {

	/**
	 * @param x  an element
	 * @return true iff the element satisfies the conditions of the filter
	 */
	boolean contains(Object x);
	
	/**
	 * Iteration over the subset of the elements, given by unfilteredIterator, that 
	 * satisfy the conditions of the filter (isIncluded(x) returns true).
	 *
	 * @param <T> element type
	 */
	class FilteredIterator<T> implements Iterator<T> {
		private Filter mFilter;
		private Iterator<? extends T> mUnfilteredIterator;
		private T mLookAhead;
		
		public FilteredIterator(Filter filter, Iterator<? extends T> unfilteredIterator) {
			mFilter=filter;
			mUnfilteredIterator=unfilteredIterator;
			mLookAhead=lookAhead();
		}
		
		private T lookAhead() {
			while (mUnfilteredIterator.hasNext()) {
				T next=mUnfilteredIterator.next();
				if (mFilter.contains(next))
					return next;
			}
			return null;
		}
		
		public boolean hasNext() {
			return mLookAhead!=null;
		}

		public T next() {
			T result=mLookAhead;
			mLookAhead=lookAhead();
			return result;
		}

		public void remove() {
			// last "unfiltered" next() is not the last "filtered" next
			throw new UnsupportedOperationException();
		}
	}
}
