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

package org.caltoopia.analysis.actor;

import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortSignature;

public class ConnectionAnalysis {
	
	public enum ConnectionType {
		CONTROL,
		DATA,
		UNCLASSIFIED
	}
	
	private Connection connection;
	private ConnectionType type = ConnectionType.UNCLASSIFIED;
	private Integer initialTokens;
	private Integer tokenSize;
	
	
	public ConnectionAnalysis(Connection c, GenericActorAnalysis srcActorAnalysis){
		
		connection = c;
		
		//Assign initial tokens
		initialTokens = null;
		if(srcActorAnalysis!=null){
			if (srcActorAnalysis.hasStaticSchedule() 
				&& srcActorAnalysis.getStaticSchedule().hasTransientPhase()) {
				// Number of initial tokens on the connection is
				// given by the rate of the initial/transient phase 
				StaticFiringSequence phase0=srcActorAnalysis.getStaticSchedule().getTransientPhase();
				PortSignature portSignature=phase0.getPortSignature();
				if(portSignature!=null){
					initialTokens = new Integer(portSignature.getPortRate(connection.getProducerPort()));
				}
			}
		}
		else
			initialTokens = new Integer(0);
		
		//Assign token size
		tokenSize = new Integer(1);
	}
	
	public void setConnectionType(ConnectionType t){
		type=t;
	}
	
	public ConnectionType getConnectionType(){
		return type;
	}
	
	public Integer getTokenSize(){
		return tokenSize;
	}
	
	public Integer getInitialTokenSize(){
		return initialTokens;
	}	
}
