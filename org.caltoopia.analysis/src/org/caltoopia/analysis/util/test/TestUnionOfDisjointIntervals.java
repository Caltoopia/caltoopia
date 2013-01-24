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

package org.caltoopia.analysis.util.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.caltoopia.analysis.util.collections.Interval;
import org.caltoopia.analysis.util.collections.LongInterval;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

public class TestUnionOfDisjointIntervals {

	private int mTests, mFailed;

	void checkThat(boolean condition) {
		++mTests;
		if (!condition) {
			++mFailed;
			throw new RuntimeException("checkThat() failed");
		}
	}
	
	<T> void equals(T x, T y) {
		++mTests;
		if (x!=null && !x.equals(y)
			|| x==null && y!=null) {
			++mFailed;
			throw new RuntimeException("equals("+x+", "+y+") failed");
		}
	}
	
	void report(String title) {
		String status=(mFailed==0)? "PASSED" : "FAILED";
		System.out.println("*** "+title+" "+status+" ***");
		System.out.println((mTests-mFailed)+" successful tests in "+title);
		System.out.println((mFailed)+" failed tests in "+title);
		System.out.println(mTests+" failed tests in "+title);
	}
	
	void test() {
		UnionOfDisjointIntervals union=new UnionOfDisjointIntervals();
		Collection<Interval<Long>> asIntervals=union.asCollectionOfIntervals();
		Set<Long> asSet=union.asSet();
		
		/*
		 * Step 1: empty set
		 */
		checkThat(asIntervals.isEmpty());
		checkThat(asSet.isEmpty());
		equals(asIntervals.size(),0);
		equals(asSet.size(),0);
		
		/*
		 * Step 2: a single value --> {42}
		 */
		checkThat(asSet.add(42L));
		checkThat(!asIntervals.isEmpty());
		checkThat(!asSet.isEmpty());
		checkThat(!asSet.add(42L));
		equals(asIntervals.size(),1);
		equals(asSet.size(),1);

		/*
		 * Step 3: add an interval --> {32,...,40,42}
		 */
		checkThat(asIntervals.add(LongInterval.create(32, 40)));
		equals(asIntervals.size(),2);
		equals(asSet.size(),10);
		checkThat(!asIntervals.add(LongInterval.create(33, 35)));
		
		/*
		 * Step 4: merge intervals --> {32,...,42}
		 */
		checkThat(asSet.add(41L));
		equals(asIntervals.size(),1);
		equals(asSet.size(),11);
		
		/*
		 * Step 5: remove an element/split interval --> {32,...,35,37,...,42}
		 */
		checkThat(asSet.remove(36L));
		equals(asIntervals.size(),2);
		equals(asSet.size(),10);
		
		/*
		 * Step 6: remove first element --> {32,...,35,38,...,42}
		 */
		checkThat(asSet.remove(37L));
		equals(asIntervals.size(),2);
		equals(asSet.size(),9);

		/*
		 * Step 7: remove last element --> {32,...,34,38,...,42}
		 */
		checkThat(asSet.remove(35L));
		equals(asIntervals.size(),2);
		equals(asSet.size(),8);

		/*
		 * Step 8: try remove element not in set--> {32,...,34,38,...,42}
		 */
		checkThat(!asSet.remove(37L));
		equals(asIntervals.size(),2);
		equals(asSet.size(),8);

		/*
		 * Step 9: partially remove first interval --> {34,38,...,42}
		 */
		checkThat(asIntervals.remove(LongInterval.create(32, 33)));
		equals(asIntervals.size(),2);
		equals(asSet.size(),6);
		checkThat(!asIntervals.remove(LongInterval.create(100, 200)));
		/*
		 * Step 10: remove/split interval --> {34,38,39,42}
		 */
		checkThat(asIntervals.remove(LongInterval.create(40, 41)));
		equals(asIntervals.size(),3);
		equals(asSet.size(),4);
		checkThat(asSet.contains(34L));
		checkThat(asSet.contains(38L));
		checkThat(asSet.contains(39L));
		checkThat(asSet.contains(42L));
		checkThat(!asSet.contains(35L));
		checkThat(asIntervals.contains(LongInterval.create(34, 34)));
		checkThat(asIntervals.contains(LongInterval.create(38, 39)));
		checkThat(asIntervals.contains(LongInterval.create(42, 42)));
		checkThat(!asIntervals.contains(LongInterval.create(35, 36)));
		
		/*
		 * Step 11: add/merge --> {34,36,..,44}
		 */
		checkThat(asIntervals.add(LongInterval.create(36, 44)));
		equals(asIntervals.size(),2);
		equals(asSet.size(),10);
		
		/*
		 * Step 12: Iteration over intervals
		 */
		Iterator<Interval<Long>> pInterval=asIntervals.iterator();
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(34, 34));
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(36, 44));
		checkThat(!pInterval.hasNext());
		
		/*
		 * Step 13: Iteration over set
		 */
		Iterator<Long> pLong=asSet.iterator();
		Long shouldBe[]={34L,36L,37L,38L,39L,40L,41L,42L,43L,44L};
		for (Long l: shouldBe) {
			checkThat(pLong.hasNext());
			equals(pLong.next(), l);
		}
		checkThat(!pLong.hasNext());
		
		/*
		 * Step 14: Removal via iterators and consistency of backing data structure
		 */
		pLong=asSet.iterator();
		pLong.next(); pLong.next(); pLong.next();
		checkThat(pLong.hasNext());
		equals(pLong.next(), 38L);
		pLong.remove();
		/*
		 * {34,36,37,39,...,44}
		 */
		equals(asIntervals.size(),3);
		equals(asSet.size(),9);
		checkThat(!asSet.contains(38L));
		checkThat(!asIntervals.contains(LongInterval.create(36, 44)));
		checkThat(asIntervals.contains(LongInterval.create(36, 37)));
		checkThat(asIntervals.contains(LongInterval.create(39, 44)));
		
		checkThat(pLong.hasNext());
		equals(pLong.next(), 39L);
		checkThat(pLong.hasNext());
		equals(pLong.next(), 40L);
		checkThat(pLong.hasNext());
		equals(pLong.next(), 41L);
		pLong.remove();
		
		/*
		 * {34,36,37,39,40,42,...,44}
		 */
		equals(asIntervals.size(),4);
		equals(asSet.size(),8);
		checkThat(!asSet.contains(41L));
		checkThat(!asIntervals.contains(LongInterval.create(39, 44)));
		checkThat(asIntervals.contains(LongInterval.create(39, 40)));
		checkThat(asIntervals.contains(LongInterval.create(42, 44)));
		
		checkThat(pLong.hasNext());
		equals(pLong.next(), 42L);
		pLong.remove();
		checkThat(pLong.hasNext());
		equals(pLong.next(), 43L);
		checkThat(pLong.hasNext());
		equals(pLong.next(), 44L);
		checkThat(!pLong.hasNext());
		pLong.remove();
		
		/*
		 * {34,36,37,39,40,43}
		 */
		equals(asIntervals.size(),4);
		equals(asSet.size(),6);

		/*
		 * Step 15: Removal via interval iterator
		 */
		pInterval=asIntervals.iterator();
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(34, 34));
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(36, 37));
		checkThat(asIntervals.contains(LongInterval.create(36, 37)));
		checkThat(asSet.contains(36L));
		checkThat(asSet.contains(37L));
		pInterval.remove();
		/*
		 * {34,39,40,43}
		 */

		checkThat(!asIntervals.contains(LongInterval.create(36, 37)));
		checkThat(!asSet.contains(36L));
		checkThat(!asSet.contains(37L));
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(39, 40));
		checkThat(pInterval.hasNext());
		equals(pInterval.next(), LongInterval.create(43, 43));
		checkThat(!pInterval.hasNext());
		
		/*
		 * Step 16: Set and equals
		 */
		Set<Long> s=new HashSet<Long>();
		s.add(34L); s.add(39L); s.add(40L); s.add(43L);
		equals(asSet.hashCode(),s.hashCode());
		equals(asSet,s);
		
		report("TestUnionOfDisjointIntervals");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestUnionOfDisjointIntervals().test();
	}

}
