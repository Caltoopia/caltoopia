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

import java.util.Set;

import org.caltoopia.analysis.util.collections.FilteredSet;


/**
 * Abstract implementation of PriorityRelation. Add implementations of:
 * a) getDomain()        all elements in the domain of the PriorityRelation
 * b) descendants(x)     an action's set of descendants
 * c) getNumAncestors(x) the number of ancestors of an action
 */
public abstract class AbstractPriorityRelation<T> {

	/**
	 * @return the Actions, the domain of the priority relation
	 */
	public abstract Set<T> getDomain();

	/**
	 * @param x  an element (possibly) in the domain (but perhaps x is not in the domain)
	 * @return   the x's set of descendants (actions with strictly lower priority)
	 *           or null if x is not in the domain of this PriorityRelation
	 */
	protected abstract Set<T> descendants(Object x);

	/**
	 * @param x  an element in the domain
	 * @return   the number of ancestors (actions with strictly higher priority)
	 */
	protected abstract int getNumAncestors(T x);
	

	public boolean isAncestorOf(T x, T y) {
		Set<T> descendants=getDescendants(x);
		return descendants!=null && descendants.contains(y);
	}

	
	public boolean areUnordered(T x, T y) {
		Set<T> descendants_x=getDescendants(x);
		Set<T> descendants_y=getDescendants(y);
		return descendants_x!=null && descendants_y!=null && !descendants_x.contains(y) && !descendants_y.contains(x);
	}

	public Set<T> getAncestors(T x) {
		return (getDomain().contains(x))? new AncestorSet(x) : null;
	}
	
	public Set<T> getDescendants(T x) {
		return descendants(x);
	}

	public Set<T> getUnordered(T x) {
		Set<T> descendants=getDescendants(x);
		return (descendants!=null)? new UnorderedElements(x,descendants) : null;
	}

	private class AncestorSet extends FilteredSet<T> {
		T mElement;
		
		AncestorSet(T action) {
			mElement=action;
		}
		
		@Override
		public boolean contains(Object obj) {
			Set<T> descendants=descendants(obj);
			return descendants!=null && descendants.contains(mElement);
		}
		
		@Override 
		public int size() {
			return getNumAncestors(mElement);
		}

		@Override
		protected Set<T> unfilteredElements() {
			return getDomain();
		}
	}
	
	private class UnorderedElements extends FilteredSet<T> {
		T mElement;
		Set<T> mDescendants;
		
		UnorderedElements(T element, Set<T> descendants) {
			mElement=element;
			mDescendants=descendants;
		}
		
		@Override
		public boolean contains(Object obj) {
			Set<T> otherDescendants=descendants(obj);
			return otherDescendants!=null && mElement!=obj && !mDescendants.contains(obj) && !otherDescendants.contains(mElement);
		}

		@Override
		public int size() {
			// All elements less
			// a) this action
			// b) the proper ancestors of this action
			// c) the proper descendants of this action
			return getDomain().size() - 1 - mDescendants.size() - getNumAncestors(mElement);
		}

		@Override
		protected Set<T> unfilteredElements() {
			return getDomain();
		}
	}	
}
