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


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;

public class VanillaPortSignature implements PortSignature {

	private Map<PortInstance,Integer> mPortMap;
	
	public VanillaPortSignature(Map<PortInstance,Integer> portMap) {
		mPortMap=portMap;
	}

	@Override
	public Set<PortInstance> getPorts() {
		return mPortMap.keySet();
	}

	@Override
	public int getPortRate(PortInstance port) {
		Integer rate=mPortMap.get(port);
		if (rate!=null)
			return rate;
		else
			return 0;
	}
	
	/**
	 * @param s  a port signature
	 * @return returns true iff this PortSignature has no greater rate on any port in 's'
	 *         (including the case when a port is not contained in 's' and thus has zero rate). 
	 */
	@Override
	public boolean isSubsetOf(PortSignature s) {
		// No port with higher rate than in s
		return findPortWithHigherRate(s)==null;
	}
	
	@Override
	public PortInstance findPortWithHigherRate(PortSignature s) {
		for (Entry<PortInstance,Integer> entry: mPortMap.entrySet()) {
			PortInstance port=entry.getKey();
			if (s.getPortRate(port) < entry.getValue()) {
				return port;
			}
		}
		return null; // No port with higher rate than in s
	}

	@Override
	public PortSignature project(Collection<? extends PortInstance> ports) {
		HashMap<PortInstance,Integer> portMap=new HashMap<PortInstance,Integer>();
		
		for (PortInstance port: ports) {
			int portRate=getPortRate(port);
			if (portRate!=0) {
				portMap.put(port, portRate);
			}
		}
		
		if (portMap.equals(mPortMap)) {
			return this; // same
		}
		else
			return new VanillaPortSignature(portMap);
	}

	@Override
	public PortSignature intersect(PortSignature s) {
		if (s==this || 
			(s instanceof VanillaPortSignature && mPortMap.equals(((VanillaPortSignature) s).mPortMap)))
			return this;
		else {
			HashMap<PortInstance,Integer> portMap=new HashMap<PortInstance,Integer>();
			boolean thisIsSubSet=true;
			boolean otherIsSubSet=true;
			
			for (Entry<PortInstance,Integer> entry: mPortMap.entrySet()) {
				PortInstance port=entry.getKey();
				int portRate2=s.getPortRate(port);
				if (portRate2!=0) {
					int portRate=entry.getValue();
					if (portRate2<portRate) {
						portRate=portRate2;
						thisIsSubSet=false; // other port rate is lower
					}
					else if (portRate<portRate2)
						otherIsSubSet=false; // other port rate is lower
					
					assert(portRate>0);
					portMap.put(port, portRate);
				}
				else
					thisIsSubSet=false; // other port rate is lower
			}

			if (thisIsSubSet)
				return this;
			else if (otherIsSubSet)
				return s;
			else
				return new VanillaPortSignature(portMap);
		}
	}

	@Override
	public PortSignature union(PortSignature s2) {
		if (s2==this || 
			(s2 instanceof VanillaPortSignature && mPortMap.equals(((VanillaPortSignature) s2).mPortMap)))
			return this;
		else {
			HashMap<PortInstance,Integer> portMap=new HashMap<PortInstance,Integer>();
			boolean thisIsSuperSet=true;
			boolean otherIsSuperSet=true;
			
			// First compare the entries to port rates of s2
			for (Entry<PortInstance,Integer> entry: mPortMap.entrySet()) {
				PortInstance port=entry.getKey();
				int portRate=entry.getValue();
				int portRate2=s2.getPortRate(port);
				if (portRate2>portRate) {
					portRate=portRate2;
					thisIsSuperSet=false; // other port rate is higher
				}
				else if (portRate>portRate2)
					thisIsSuperSet=false; // this port rate is higher
				
				portMap.put(port, portRate);
			}
			
			// Then check for entries in s2, but not s1
			for (PortInstance port: s2.getPorts()) {
				if (getPortRate(port)==0) {
					int portRate=s2.getPortRate(port);
					assert(portRate!=0);
					
					portMap.put(port, portRate);
					thisIsSuperSet=false; // other port rate is higher
				}
			}
			
			if (thisIsSuperSet)
				return this;
			else if (otherIsSuperSet)
				return s2;
			else
				return new VanillaPortSignature(portMap);
		}
	}
	
	@Override
	public boolean isEmpty() {
		return mPortMap.isEmpty();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o!=null && o instanceof VanillaPortSignature) {
			return mPortMap.equals(((VanillaPortSignature) o).mPortMap);
		}
		else if (o!=null && o instanceof PortSignature) {
			PortSignature s=(PortSignature) o;
			
			return this.isSubsetOf(s) && s.isSubsetOf(this);
		}
		else
			return false;
	}
		
	public int hashCode() {
		// Since we want to put PortSignatures in HashSets and HashMaps, it is important that the 
		// hashCode of a possible other implementation of PortSignature works the same.
		//
		// Map.hashCode is defined as the sum of the hashCodes of the Map.Entry objects,
		// which (in turn) have hashCodes that XOR:s the two components of Entry (using 0 for null)
		return mPortMap.hashCode();
	}
	
	@Override
	public String toString() {	
		String inputs="";
		String inputDelimiter="";
		String outputs="";
		String outputDelimiter="";
		
		for (Map.Entry<PortInstance, Integer> entry: mPortMap.entrySet()) {
			PortInstance port=entry.getKey();
			Integer rate=entry.getValue();
			String descr=port.getName();
			if (rate!=1)
				descr+="["+rate+"]";

			if (port.getDirection()==PortInstance.Direction.IN) {
				inputs+=inputDelimiter+descr;
				inputDelimiter=",";
			}
			else {
				outputs+=outputDelimiter+descr;
				outputDelimiter=",";
			}
		}
		return inputs+"==>"+outputs;
	}
}
