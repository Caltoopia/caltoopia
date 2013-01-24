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

package org.caltoopia.analysis.trace;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A single-core trace, organized as a single sequence of trace events, sorted by time stamp.
 *
 * @param <E> Trace-event type
 */
public class SimpleTrace<E extends TimeStamped> implements Trace<E> {

	private List<E> mEvents;
	
	/**
	 * @param onCpu
	 * @param events a list of events ordered after time stamp
	 */
	public SimpleTrace(List<E> events) {
		mEvents=events;
	}
	
	@Override
	public int size() {
		return mEvents.size();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public Iterator<E> iterator() {
		return mEvents.iterator();
	}

	@Override
	public E first() {
		if (mEvents.isEmpty())
			throw new NoSuchElementException();
		return mEvents.get(0);
	}

	@Override
	public E last() {
		if (mEvents.isEmpty())
			throw new NoSuchElementException();
		return mEvents.get(mEvents.size()-1);
	}

	@Override
	public SimpleTrace<E> headSubTrace(long beforeTimeStamp) {
		int endIndex=find(beforeTimeStamp);
		List<E> head=mEvents.subList(0, endIndex);
		
		return new SimpleTrace<E>(head);
	}

	@Override
	public SimpleTrace<E> tailSubTrace(long startingWithTimeStamp) {
		int fromIndex=find(startingWithTimeStamp);
		List<E> tail=mEvents.subList(fromIndex, mEvents.size());
		
		return new SimpleTrace<E>(tail);
	}
	
	public E get(int i) {
		return mEvents.get(i);
	}
	
	/**
	 * @param timestamp
	 * @return index of first event with a timestamp that is at least as high as the given parameter
	 *         Index is one past end of list (i.e. size) if there is no event with sufficiently high timestamp. 
	 */
	private int find(long timestamp) {
		int l=0;
		int r=mEvents.size();
		
		while (l<r-1) {
			int m=(l+r)/2;
			if (timestamp<mEvents.get(m).getTimeStamp()) {
				// timestamp in first half
				r=m;
			}
			else {
				// timestamp in second half
				l=m;
			}
		}

		// When there are no events l=r=0
		// If there are events, we know that timestamp of l:th event <= the given timestamp.
		// If timestamps are equal we find the first index
		// Otherwise r=l+1 is the index of the first event with higher timestamp (possibly one past end). 
		if (!mEvents.isEmpty() && timestamp==mEvents.get(l).getTimeStamp()) {
			while (l>=0 && timestamp==mEvents.get(l).getTimeStamp()) {
				l--;
			}
			r=l-1;
		}
		return r;
	}
}