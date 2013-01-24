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
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A set, which is a union of disjoint intervals. 
 * Example: 0 <= x <= 10, x!=1, x!=6 is represented as: [0,0] u [2,5] u [7,10].
 * 
 * The data structure can be accessed in any of two views:
 * a) as a collection of intervals (a union of three intervals, in our example)
 * b) as a set of elements (in our example the set {0,2,3,4,5,7,8,9,10} consisting of nine elements)
 * 
 * Modifying any of the two views (adding/removing elements) reflects on the backing data structure 
 * such that:
 * a) Intervals are kept disjunct
 * b) Adjacent intervals (e.g. [1,10] and [11,15]) are merged into one interval ([1,15] in this case)
 * 
 * The collection of intervals is a bit unusual (in terms of expected behaviors of Collection classes)
 * since for a given interval, [a,b], in the collection, any sub-interval is also in the collection. 
 * This means that [a',b'] (a sub-interval of some [a,b] in the collection) is contained in the collection,
 * although iteration over all intervals would not include [a',b'] (but instead [a,b]).
 */
public class UnionOfDisjointIntervals {

	private TreeMap<Long,Long> mIntervals;
	
	public UnionOfDisjointIntervals() {
		mIntervals=new TreeMap<Long,Long>();
	}

	protected UnionOfDisjointIntervals(Map<Long,Long> intervals) {
		mIntervals=new TreeMap<Long,Long>(intervals);
	}
	
	/**
	 * @return this union viewed as a collection of intervals
	 */
	public Collection<Interval<Long>> asCollectionOfIntervals() {
		return new IntervalCollectionView();
	}
	
	/**
	 * @return this union viewed as a set of elements (of type T)
	 */
	public Set<Long> asSet() {
		return new SetView();
	}
	
	/**
	 * @param start
	 * @param end
	 * @return true iff [start,end] belongs (is enclosed by) to this Union
	 */
	public boolean containsAll(long start, long end) {
		Map.Entry<Long, Long> entry=getEntry((Long) start);
		
		return entry!=null && end<=entry.getValue();
	}
	
	
	/**
	 * @param x
	 * @return the entry (interval), to which x belongs (null if none)
	 */
	private Map.Entry<Long,Long> getEntry(long x) {
		Map.Entry<Long,Long> entry=mIntervals.floorEntry(x);
		
		return (entry!=null && x<=entry.getValue())? entry : null;
	}

	
	/**
	 * Adds the interval [lo,hi] to the union of disjoint intervals, while merging any
	 * overlapping/adjacent intervals (to maintain disjoint intervals).
	 * 
	 * @param lo  start of interval
	 * @param hi  end of interval (inclusive)
	 * @return true iff the data structure was updated
	 */
	public boolean add(long lo, long hi) {
		if (lo>hi)
			return false;
		
		Map.Entry<Long,Long> entry=mIntervals.floorEntry(lo);
		boolean wasMerged=false;
		
		if (entry!=null) {
			long endPoint=entry.getValue();
			
			if (endPoint==Long.MAX_VALUE || lo<=endPoint+1) {
				// Overlap or adjacent, use existing starting point of interval 
				lo=entry.getKey();
				// use existing end-point of interval, if larger
				hi=Math.max(hi, endPoint);
			}
			// otherwise: entry denotes non-overlapping, non-adjacent interval, 
			// such that endPoint < lo-1
		}

		// Iterate over intervals with greater starting points than 'lo'
		// and merge overlapping/adjacent intervals
		Map<Long,Long> tailMap=mIntervals.tailMap(lo,/*inclusive*/ false);
		Iterator<Map.Entry<Long,Long>> pEntry=tailMap.entrySet().iterator();
		while (pEntry.hasNext()) {
			entry=pEntry.next();
			
			if (entry.getKey()-1 > hi) {
				// non-overlapping, non-adjacent interval
				break;
			}
			else {
				// overlapping or adjacent, use existing end-point of interval, if larger
				hi=Math.max(hi, entry.getValue());
				
				// remove the existing interval
				pEntry.remove();
				wasMerged=true;
			}
		}

		// Finally insert the interval [lo,hi]
		Long old=mIntervals.put(lo, hi);
		return wasMerged || old==null || hi!=old;
	}
	
	/**
	 * Removes the interval [lo,hi] from the union of disjoint intervals
	 * 
	 * @param lo  start of interval
	 * @param hi  end of interval (inclusive)
	 * @return true iff the data structure was updated
	 */
	public boolean remove(long lo, long hi) {
		if (lo>hi)
			return false;
		
		Map.Entry<Long,Long> entry=mIntervals.floorEntry(lo);
		boolean wasModified=false;
		
		// Deal with floorEntry, the one with greatest starting point <= lo
		if (entry!=null) {
			long endPoint=entry.getValue();
			
			if (lo<=endPoint) {
				// Overlap
				long startingPoint=entry.getKey();
				if (lo==startingPoint) {
					// Remove entry/starting point
					mIntervals.remove(startingPoint);
				}
				else {
					// Remove overlapping part of entry
					mIntervals.put(startingPoint, lo-1);
				}
				
				// Do we split the entry?
				if (hi<endPoint) {
					mIntervals.put(hi+1, endPoint);
				}
				wasModified=true;
			}
		}
		
		// Iterate over intervals with greater starting points than 'lo'
		Map<Long,Long> tailMap=mIntervals.tailMap(lo,/*inclusive*/false);
		Iterator<Map.Entry<Long,Long>> pEntry=tailMap.entrySet().iterator();

		while (pEntry.hasNext()) {
			entry=pEntry.next();
			long startingPoint=entry.getKey();
			
			if (startingPoint>hi) {
				// Done
				break;
			}
			else {
				// Completely or partially contained interval: remove
				long endPoint=entry.getValue();
			
				pEntry.remove();
				wasModified=true;
				
				if (endPoint>hi) {
					// Partial overlap
					mIntervals.put(hi+1, endPoint);
					// Here, we have destroyed the iterator (better exit!)
					break; 
				}
			}
		}
		return wasModified;
	}	
	
	
	/**
	 * @param u  another UnionOfDisjointIntervals
	 * @return the union of this object and 'u'
	 * 
	 * Note that a new object is created, neither this UnionOfDisjointIntervals nor 'u' is modified 
	 */
	public UnionOfDisjointIntervals union(UnionOfDisjointIntervals u) {
		UnionOfDisjointIntervals result=new UnionOfDisjointIntervals(mIntervals);
		result.addAll(u.mIntervals);
		return result;
	}
	
	
	private void addAll(Map<Long,Long> intervals) {
		for (Map.Entry<Long, Long> entry: intervals.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * @param u  another UnionOfDisjointIntervals
	 * @return the intersection between this object and 'u'
	 * 
	 * Note that a new object is created, neither this UnionOfDisjointIntervals nor 'u' is modified 
	 */
	public UnionOfDisjointIntervals intersection(UnionOfDisjointIntervals u) {
		UnionOfDisjointIntervals result=new UnionOfDisjointIntervals();
		result.addIntersection(mIntervals, u.mIntervals);
		return result;
	}

	private void addIntersection(NavigableMap<Long,Long> intervals1, NavigableMap<Long,Long> intervals2) {
		
		if (intervals1.isEmpty() || intervals2.isEmpty()) {
			return;
		}
		
		Map.Entry<Long,Long> entry1=intervals1.firstEntry();
		Map.Entry<Long,Long> entry2=intervals2.firstEntry();
		long first=Math.max(entry1.getKey(), entry2.getKey());
		
		while (true) {
			// Skip forward until entries overlap
			do {	
				if (entry1.getValue() < first) {
					intervals1=intervals1.tailMap(first,true);
					if (intervals1.isEmpty())
						return;
					entry1=intervals1.firstEntry();
					first=Math.max(entry1.getKey(), first);
				}

				if (entry2.getValue() < first) {
					intervals2=intervals2.tailMap(first,true);
					if (intervals2.isEmpty())
						return;
					entry2=intervals2.firstEntry();
					first=Math.max(first, entry2.getKey());
				}
			} while (entry1.getValue() < first);

			// Add common interval
			long last=Math.min(entry1.getValue(), entry2.getValue());
			add(first,last);
			if (last==Long.MAX_VALUE)
				return;
			first=last+1;
		}
	}

	
	@Override
	public String toString() {
		return asCollectionOfIntervals().toString();
	}

	/**
	 * This class implements the view of the Union as a collection of Intervals
	 * By adding/removing Intervals the backing data structure is updated.
	 */
	private class IntervalCollectionView extends AbstractCollection<Interval<Long>> {

		@Override
		public int size() {
			return UnionOfDisjointIntervals.this.mIntervals.size();
		}

		@Override
		public boolean contains(Object obj) {
			if (obj!=null && obj instanceof Interval<?>) {
				Interval<?> interval=((Interval<?>) obj);
				
				// Check if the start/end-points of the interval is in the map
				if (interval.isEmpty()) {
					// The empty set is contained in any set
					return true;
				}
				else {
					Object start=interval.getMin();
					if (start instanceof Long) {
						Object end=interval.getMax();
						return UnionOfDisjointIntervals.this.containsAll((Long) start, (Long) end);
					}
				}
			}
	
			return false;
		}

		@Override
		public boolean add(Interval<Long> interval) {
			if (interval.isEmpty()) {
				return false;
			}
			else {
				long start=interval.getMin();
				long end=interval.getMax();
				
				return UnionOfDisjointIntervals.this.add(start,end);
			}
		}

		@Override
		public boolean remove(Object obj) {
			if (obj!=null && obj instanceof Interval<?>) {
				Interval<?> interval=((Interval<?>) obj);
				
				// Check if the start/end-points of the interval is in the map
				if (!interval.isEmpty()) {
					Object start=interval.getMin();
					Object end=interval.getMax();
				return UnionOfDisjointIntervals.this.remove((Long) start, (Long) end);
				}
			}
			// Otherwise:
			return false;
		}
		
		
		@Override
		public void clear() {
			UnionOfDisjointIntervals.this.mIntervals.clear();
		}

		
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO: not strait-forward to implement!
			// An interval not contained in c doesn't mean that it can be removed entirely  
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Interval<Long>> iterator() {
			return new Iterator<Interval<Long>>() {

				Iterator<Map.Entry<Long,Long>> pEntry=
						UnionOfDisjointIntervals.this.mIntervals.entrySet().iterator();
				
				@Override
				public boolean hasNext() {
					return pEntry.hasNext();
				}

				@Override
				public Interval<Long> next() {
					Map.Entry<Long,Long> entry=pEntry.next();
					return new LongInterval(entry.getKey(), entry.getValue());
				}

				@Override
				public void remove() {
					pEntry.remove();
				}
			};
		}
	}
	
	/**
	 * This class implements the view of the Union as a set of Long elements
	 * By adding/removing elements, the backing data structure (the map of Intervals) is updated.
	 */
	private class SetView extends AbstractSet<Long> {

		@Override
		public boolean isEmpty() {
			return UnionOfDisjointIntervals.this.mIntervals.isEmpty();		
		}

		@Override
		public int size() {
			int sum=0;
			for (Map.Entry<Long,Long> entry: UnionOfDisjointIntervals.this.mIntervals.entrySet()) {
				long n=entry.getValue() - entry.getKey() + 1;
				
				sum += n;
				if (n<=0 || n>Integer.MAX_VALUE || sum<=0) {
					// Overflow
					return Integer.MAX_VALUE;
				}
			}
			return sum;
		}

		@Override
		public boolean add(Long x) {
			return UnionOfDisjointIntervals.this.add(x,x);
		}

		@Override
		public boolean contains(Object obj) {
			return (obj!=null && obj instanceof Long
					&& UnionOfDisjointIntervals.this.getEntry((Long) obj)!=null);
		}

		@Override
		public boolean remove(Object obj) {
			if (obj!=null && obj instanceof Long) {
				long x=(Long) obj;
				return UnionOfDisjointIntervals.this.remove(x,x);
			}
			else
				return false;
		}

		@Override
		public void clear() {
			UnionOfDisjointIntervals.this.mIntervals.clear();
		}

		@Override
		public Iterator<Long> iterator() {
			return new Iterator<Long>() {

				Map.Entry<Long,Long> mCurrInterval=UnionOfDisjointIntervals.this.mIntervals.firstEntry();
				long mNext=isEmpty()? 0 : mCurrInterval.getKey();
				long mMaxOfUnion=isEmpty()? Long.MIN_VALUE : UnionOfDisjointIntervals.this.mIntervals.lastEntry().getValue();
				
				@Override
				public boolean hasNext() {
					return mNext<=mMaxOfUnion;
				}

				@Override
				public Long next() {
					if (mNext>mCurrInterval.getValue()) {
						// Past end of interval? get the next one!
						mCurrInterval=UnionOfDisjointIntervals.this.mIntervals.ceilingEntry(mNext);
						mNext=mCurrInterval.getKey();  // First element of that interval
					}
					return mNext++; // advance
				}
				
				@Override
				public void remove() {
					long last;
					if (mNext==mCurrInterval.getKey()) {
						// Last element that was returned by next() is not in mCurrInterval!
						// It is the last element of the previous interval.
						last=UnionOfDisjointIntervals.this.mIntervals.lowerEntry(mNext).getValue();
					}
					else
						last=mNext-1;
					
					UnionOfDisjointIntervals.this.remove(last,last);
				}
			};
		}
	}
}
