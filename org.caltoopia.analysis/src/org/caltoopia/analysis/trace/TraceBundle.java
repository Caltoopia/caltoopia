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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TraceBundle<E extends TimeStamped & Comparable<E>> implements Trace<E> {

	private List<SimpleTrace<E>> mCpuTraces;
	
	public TraceBundle() {
		mCpuTraces=new ArrayList<SimpleTrace<E>>();
	}
	
	public TraceBundle(Collection<SimpleTrace<E>> traces) {
		mCpuTraces=new ArrayList<SimpleTrace<E>>(traces);
	}
	
	public void add(SimpleTrace<E> trace) {
		mCpuTraces.add(trace);
	}
	
	@Override
	public Iterator<E> iterator() {
		return new BundleIterator();
	}

	@Override
	public int size() {
		int sum=0;
		for (Trace<E> trace: mCpuTraces) {
			sum+=trace.size();
		}
		return sum;
	}

	
	@Override
	public boolean isEmpty() {
		for (Trace<E> trace: mCpuTraces) {
			if (!trace.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public E first() {
		E firstEvent=null;
		
		for (Trace<E> trace: mCpuTraces) {
			if (!trace.isEmpty()) {
				E event=trace.first();
				if (firstEvent==null || event.getTimeStamp() < firstEvent.getTimeStamp()) {
					firstEvent=event;
				}
			}
		}
		return firstEvent;
	}

	@Override
	public E last() {
		E lastEvent=null;
		
		for (Trace<E> trace: mCpuTraces) {
			if (!trace.isEmpty()) {
				E event=trace.last();
				if (lastEvent==null || event.getTimeStamp() > lastEvent.getTimeStamp()) {
					lastEvent=event;
				}
			}
		}
		return lastEvent;
	}

	@Override
	public Trace<E> headSubTrace(long beforeTimeStamp) {
		List<SimpleTrace<E>> headTraces=new ArrayList<SimpleTrace<E>>();
		for (SimpleTrace<E> trace: mCpuTraces) {
			headTraces.add(trace.headSubTrace(beforeTimeStamp));
		}
		return new TraceBundle<E>(headTraces);
	}

	@Override
	public Trace<E> tailSubTrace(long startingWithTimeStamp) {
		List<SimpleTrace<E>> tailTraces=new ArrayList<SimpleTrace<E>>();
		for (SimpleTrace<E> trace: mCpuTraces) {
			tailTraces.add(trace.tailSubTrace(startingWithTimeStamp));
		}
		return new TraceBundle<E>(tailTraces);
	}

	/**
	 * @return the number of CPUs in the trace 
	 */
	public int numberOfCPUs() {
		return mCpuTraces.size();
	}

	/**
	 * @param onCpu  cpu index 0,1,2,3,...,numberOfCPUs()-1
	 * @return the sub-set of trace events that execute on a particular CPU
	 */
	public Trace<E> cpuSubTrace(int onCpu) {
		return mCpuTraces.get(onCpu);
	}	
	
	class BundleIterator implements Iterator<E> {

		int mPosition[] = new int[numberOfCPUs()];
		
		@Override
		public boolean hasNext() {
			for (int i=0; i<mPosition.length; ++i)
				if (mPosition[i]<mCpuTraces.get(i).size())
					return true;
			return false;
		}

		@Override
		public E next() {
			int minIndex=-1;
			long minTimeStamp=0;
			
			// Find a minimal timestamp among the events "up next"
			for (int i=0; i<mPosition.length; ++i) {
				if (mPosition[i]<mCpuTraces.get(i).size()) {
					long t=mCpuTraces.get(i).get(mPosition[i]).getTimeStamp();
					if (minIndex==-1 || t<minTimeStamp) {
						minIndex=i;
						minTimeStamp=t;
					}
				}
			}
			
			if (minIndex!=-1)
				return mCpuTraces.get(minIndex).get(mPosition[minIndex]++);
			else
				throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}	
	}
}


