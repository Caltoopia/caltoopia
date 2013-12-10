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

import java.util.HashMap;
import java.util.Map;

public class DecorationMap<Obj,Value> {

	private Map<String,Map<Obj,Value>> mDecorations=new HashMap<String,Map<Obj,Value>>();
	
	/**
	 * Associate (object,property,value) 
	 * 
	 * @param object   object associated with decoration (e.g Actor, Action)
	 * @param property Key string (e.g. "execution time")
	 * @param value    Associated value (e.g. "1234")
	 */
	public void decorate(Obj object, String property, Value value) {
		getProperty(property).put(object,value);
	}
	
	/**
	 * Get value of (object,property,value) association 
	 * 
	 * @param object   object associated with decoration (e.g Actor, Action)
	 * @param property Key string (e.g. "execution time")
	 * @return Associated value (e.g. "1234") or null if there is no such association
	 */
	public Value getDecoration(Obj obj, String property) {
		Map<Obj,Value> m=mDecorations.get(property);
		return (m!=null)? m.get(obj) : null;
	}
	
	/**
	 * @param property Key string (e.g. "execution time")
	 * @return Map of (object,value) pairs pertaining to the given property
	 */
	public Map<Obj,Value> getProperty(String property) {
		Map<Obj,Value> m=mDecorations.get(property);
		if (m==null) {
			// Create the map if it doesn't already exist
			m=new HashMap<Obj,Value>();
			mDecorations.put(property, m);
		}
		return m;
	}
	
	/**
	 * Sets an entire map of properties
	 * @param property    Key string (e.g. "execution time")
	 * @param propertyMap Map of (object,value) pairs pertaining to the given property
	 */
	public void setProperty(String property, Map<Obj,Value> propertyMap) {
		mDecorations.put(property, propertyMap);
	}
	
	/**
	 * @param property    Key string (e.g. "execution time")
	 * @return true if this DecorationMap contains the given property
	 */
	public boolean hasProperty(String property) {
		return mDecorations.containsKey(property);
	}
}
