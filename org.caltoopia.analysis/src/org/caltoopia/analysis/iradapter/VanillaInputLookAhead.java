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

import org.caltoopia.analysis.air.InputLookAhead;
import org.caltoopia.analysis.air.PortInstance;

/**
 * A (port,index) pair that represents input look-ahead. 
 * Used in guards of Dynamic Dataflow actors (a.k.a pinPeek).
 */
public class VanillaInputLookAhead implements InputLookAhead {
	private PortInstance mPort;
	private int mIndex;
	
	public VanillaInputLookAhead(PortInstance port, int index) {
		mPort=port;
		mIndex=index;
	}
	
	/**
	 * @return the (input) port that receives the input stream, 
	 *         in which we look ahead.
	 */
	public PortInstance getPort() {
		return mPort;
	}
	
	/**
	 * @return the look-ahead index: 0 means next token, 1 means second next etc.
	 */
	public int getIndex() {
		return mIndex;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof InputLookAhead) {
			InputLookAhead ila=(InputLookAhead) obj;
			return mPort.equals(ila.getPort()) && mIndex==ila.getIndex();
		}
		else
			return false;
	}
	
	@Override 
	public int hashCode() {
		return (999007*mIndex) ^ mPort.hashCode();
	}
	
	
	@Override
	public String toString() {
		return mPort.getName() + "@" + mIndex;
	}
}
