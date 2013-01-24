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

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A collection that is formed as the union of
 * the elements in a collection of "containers".
 * Supports removal by elements if the underlying 
 * containers do (add elements via underlying containers).
 * 
 * @param <T> Element type
 */
public class UnionCollection<T> extends AbstractCollection<T> {

	private Iterable<? extends Collection<? extends T>> mContainers;
	
	public UnionCollection(Collection<? extends T> container1, Collection<? extends T> container2) {
		List<Collection<? extends T>> containers=new ArrayList<Collection<? extends T>>(2);
		containers.add(container1);
		containers.add(container2);
		mContainers=containers;
	}
	
	public UnionCollection(Collection<? extends T> container1, 
			               Collection<? extends T> container2,
			               Collection<? extends T> container3) {
		List<Collection<? extends T>> containers=new ArrayList<Collection<? extends T>>(3);
		containers.add(container1);
		containers.add(container2);
		containers.add(container3);
		mContainers=containers;
	}
	
	public UnionCollection(Iterable<? extends Collection<? extends T>> containers) {
		mContainers=containers;
	}

	@Override
	public boolean contains(Object element) {
		for (Collection<? extends T> block: mContainers) {
			if (block.contains(element))
				return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object element) {
		for (Collection<? extends T> block: mContainers) {
			if (block.remove(element))
				return true;
		}
		return false;
	}
	
	@Override
	public int size() {
		int count=0;
		for (Collection<? extends T> block: mContainers) {
			count += block.size();
		}
		return count;
	}
	
	@Override
	public boolean isEmpty() {
		for (Collection<? extends T> block: mContainers) {
			if (!block.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleIterator();
	}
	

	private class DoubleIterator implements Iterator<T> {
		private Iterator<? extends Collection<? extends T>> mBlock=mContainers.iterator();
		private Iterator<? extends T> mElement;
		
		@Override
		public boolean hasNext() {
			if (mElement!=null && mElement.hasNext())
				return true;
			else {
				mElement=advance();
				return mElement!=null;
			}
		}

		public T next() {
			if (mElement.hasNext()==false)
				mElement=advance();
			return mElement.next();
		}

		private Iterator<? extends T> advance() {
			while (mBlock.hasNext()) {
				Iterator<? extends T> b=mBlock.next().iterator();
				if (b.hasNext())
					return b;
			}
			return null;
		}
		
		public void remove() {
			mElement.remove();
		}
	}
}
	
