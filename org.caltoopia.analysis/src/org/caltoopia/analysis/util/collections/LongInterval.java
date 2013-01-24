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

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class LongInterval implements Interval<Long> {

	protected long mLo, mHi;
	
	// Normalized representation of the empty set
	protected static final long sEmptyLo=Long.MAX_VALUE;
	protected static final long sEmptyHi=Long.MIN_VALUE;
	
	protected static final Interval<Long> sEmpty=new LongInterval(sEmptyLo,sEmptyHi);
	protected static final Interval<Long> sUniverse=new LongInterval(Long.MIN_VALUE, Long.MAX_VALUE);
	
	
	public LongInterval(long lo, long hi) {
		if (lo>hi) {
			mLo=sEmptyLo;
			mHi=sEmptyHi;
		}
		else {
			mLo=lo;
			mHi=hi;
		}
	}

	public LongInterval(Interval<Long> interval) {
		if (interval.isEmpty()) {
			mLo=sEmptyLo;
			mHi=sEmptyHi;
		}
		else {
			mLo=interval.getMin();
			mHi=interval.getMax();
		}
	}
	
	public static Interval<Long> create(long min, long max) {
		// Normalize the empty interval
		return (min<=max)? new LongInterval(min,max) : sEmpty;
	}
	
	public static Interval<Long> emptySet() {
		return sEmpty;
	}
	
	public static Interval<Long> universalSet() {
		return sUniverse;
	}
	
	@Override
	public boolean isEmpty() {
		return mHi<mLo;
	}
	
	@Override
	public Long getMin() {
		return mLo;
	}

	@Override
	public Long getMax() {
		return mHi;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Interval) {
			return equals((Interval<?>) obj);		
		}
		else
			return super.equals(obj);
	}
	
	public boolean equals(Interval<?> interval) {
		return isEmpty()==interval.isEmpty()
			   && (isEmpty() 
				   || ((Long) mLo).equals(interval.getMin()) && ((Long) mHi).equals(interval.getMax()));
	}

	@Override
	public int hashCode() {		
		// To make hashCode consistent with AbstractSet<Long> 
		// (while avoiding iteration over each element) note that
		// the hashCode is the sum of the hashCodes of the elements.
		
		if (isEmpty())
			return 0;
		else {
			// It can be computed as the sum of hashCodes from zero to mHi
			// less the hash-code-sum from zero to mLo-1 (if mLo is non-negative).
			int hiSum=(mHi>=0)? sumZeroTo(mHi) : -sumZeroTo(mHi+1);
			int loSum=(mLo<=0)? sumZeroTo(mLo) : -sumZeroTo(mLo-1);
			return hiSum+loSum;
		}
	}

	private int sumZeroTo(long x) {		
		if (x<0) {
			// symmetry around zero: sumToZero(x) = sumToZero(-1-x) = sumToZero(~x)
			x = ~x;
		}

		// The hashCode of a Long is hi32 XOR lo32
		int sum=0;
		int hi32=(int) (x>>32);
		int lo32=(int) x;
		
		// 1. first sum from zero to the closest multiple of 2^32, no greater than x
		if ((hi32 & 1)!=0) {
			// The sum 0 + 1 + 2 + ... + 2^32 - 1 is congruent 2^31 (modulo 2^32).
			// XOR:ing each term by some K (hi32) doesn't change the collection
			// of terms -it just permutes their order.
			// This means that we get a total contribution of 2^31 for an
			// odd number of such sums (zero for an even number of sums, again modulo 2^32).
			sum += 0x80000000;
		}
		
		// 2. Then sum from that multiple of 2^32 to x
		
		// This is achieved by repeatedly summing sequences of 2^k terms,
		// such that we get all combinations of the k LSBs (XOR just permutes)
		// and the remaining MSBs are constant (and can be XOR:ed by the MSB:s of hi32)
		for (int k=31; k>=0; --k) {
			int bitN=1<<k;
			
			if ((lo32 & bitN)!=0) {
				int msbMask=~(bitN-1);              // MSBs that are constant for the 2^k terms
				int msb=(hi32 ^ lo32 ^ bitN) & msbMask;   // value of those bits <msb-bits>0<lsb-bits>
				int sumAllBits=(bitN-1)*(bitN>>>1); // Sum of LSB bit patterns: 0+1+2+3+...+2^k-1
				
				sum += bitN*msb + sumAllBits;      // 2^k times the constant MSBs + sum of all LSBs
			}
		}
		
		return sum + (hi32 ^ lo32);  // finally add the last term
	}

	@Override
	public boolean isSingleton() {
		return mHi==mLo;
	}
	
	@Override
	public boolean isSingleton(Long x) {
		return mLo==x && mHi==x;
	}

	@Override
	public boolean contains(Long x) {
		return mLo<=x && x<=mHi;
	}
	
	@Override
	public boolean containsAll(Interval<Long> interval) {
		// All Intervals contain the emptyInterval
		if (interval.isEmpty())
			return true;
		// emptyInterval contains only itself
		// infinite intervals can't be contained within this (finite) interval
		else if (isEmpty())
			return false;
		else
			return mLo<=interval.getMin() && interval.getMax()<=mHi;  
	}
	
	@Override
	public boolean overlaps(Interval<Long> interval) {
		// emptyInterval overlaps no interval (not even itself)
		// otherwise, it is tested that this interval does not only
		// contain larger (smaller) elements than the given interval
		return !isEmpty() && !interval.isEmpty() 
			   && mLo<=interval.getMax() && interval.getMin()<=mHi; 
	}	

	@Override
	public Interval<Long> intersection(Interval<Long> interval) {
		// Intersection with empty set --> empty set
		if (isEmpty())
			return this;
		else if (interval.isEmpty())
			return interval;
		
		long a=Math.max(mLo, interval.getMin());
		long b=Math.min(mHi, interval.getMax());
		return create(a,b);
	}

	@Override
	public Interval<Long> unionEnclosure(Interval<Long> interval) {
		// Union with empty set --> identity
		if (isEmpty())
			return interval;
		else if (interval.isEmpty())
			return this;
		
		long a=Math.min(mLo, interval.getMin());
		long b=Math.max(mHi, interval.getMax());
		return create(a,b);
	}

	@Override
	public Set<Long> asSet() {
		return new IntervalAsSet();
	}

	@Override
	public String toString() {
		return isEmpty()? "[]" : "["+mLo+","+mHi+"]";
	}

	/**
	 * Set<Long> adaptor
	 */
	private class IntervalAsSet extends AbstractSet<Long> {
		
		@Override
		public boolean contains(Object obj) {
			if (obj instanceof Long)
				return LongInterval.this.contains((Long) obj);
			else
				return false;
		}

		@Override
		public boolean containsAll(Collection<?> obj) {
			if (obj instanceof IntervalAsSet)
				return LongInterval.this.containsAll(((IntervalAsSet) obj).asInterval());
			else
				return super.containsAll(obj);
		}

		LongInterval asInterval() {
			return LongInterval.this;
		}
		
		@Override
		public boolean isEmpty() {
			return LongInterval.this.isEmpty();
		}

		@Override
		public int size() {
			if (LongInterval.this.isEmpty())
				return 0;
			else {
				long s=LongInterval.this.mHi+1-LongInterval.this.mLo;
				if (s>Integer.MAX_VALUE || s<0)
					return Integer.MAX_VALUE;   // negative s means >2^63
				else
					return (int) s;
			}
		}

		@Override
		public Iterator<Long> iterator() {
			return new Iterator<Long>() {

				long nxt=LongInterval.this.mLo;
				boolean more=!LongInterval.this.isEmpty();

				@Override
				public boolean hasNext() {
					return more;
				}

				@Override
				public Long next() {
					more=(nxt<LongInterval.this.mHi);
					return nxt++;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof IntervalAsSet)
				return LongInterval.this.equals(((IntervalAsSet) obj).asInterval());
			else
				return super.equals(obj);
		}

		@Override
		public int hashCode() {
			return LongInterval.this.hashCode();
		}

		@Override
		public String toString() {
			return LongInterval.this.toString();
		}
	}
}
