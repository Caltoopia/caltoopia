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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class CartesianProduct<T> {
	/**
	 * performs a Cartesian product of a list of sets of objects.
	 * @param objects
	 * @return if the input list of sets is empty, it returns empty set. 
	 * if the input list has only one set, it returns the same set. If the 
	 * list has more than one list, it returns the Cartesian product.
	 */
	public static <T> Set<Set<T>> cartesianProduct(List<Set<T>> objects) {
	    if (objects.size() == 0)
	        //throw new IllegalArgumentException("cartesianProduct: empty set.");
	    	return new HashSet<Set<T>>();
	    else if (objects.size() == 1){
	    	Set<Set<T>> setOfSets = new HashSet<Set<T>>();
	    	for(T t:objects.remove(0)){
	    		Set<T> set = new HashSet<T>();
	    		set.add(t);
	    		setOfSets.add(set);
	    	}
	    	return setOfSets;
	    }
	    return _cartesianProduct(0,objects);
	}

	/**
	 * Recursive implementation of Cartesian product of a list of sets
	 * @param index: recursion depth 
	 * @param sets: a list of sets
	 * @return
	 */
	private static <T> Set<Set<T>> _cartesianProduct(int index, List<Set<T>> sets) {
	    Set<Set<T>> ret = new HashSet<Set<T>>();
	    if (index == sets.size()) {
	        ret.add(new HashSet<T>());
	    } else {
	        for (T object : sets.get(index)) {
	        	Set<Set<T>> cartProduct = _cartesianProduct(index+1, sets);
	            for (Set<T> set : cartProduct) {
	                set.add(object);
	                ret.add(set);
	            }
	        }
	    }
	    return ret;
	}
	
	/**
	 * Iterative implementation of Cartesian product of a list of sets
	 * @param sets:  a list of sets
	 * @return Cartesian product
	 */
	private static <T> Set<Set<T>> _cartesianProduct(List<Set<T>> sets) {
	    Set<Set<T>> ret = new HashSet<Set<T>>();
	    ret.add(new HashSet<T>());
	    int index = sets.size()-1;
	    while(index >= 0){
	    	Set<Set<T>> tempret = new HashSet<Set<T>>();
	    	for(T object : sets.get(index)){
	    		for (Set<T> set : ret) {
	    			Set<T> sett = new HashSet<T>(set);
	                sett.add(object);
	                tempret.add(sett);
	            }
	    	}
	    	ret.clear();
	    	ret.addAll(tempret);
	    	index--;
	    }
	    return ret;
	}
}
