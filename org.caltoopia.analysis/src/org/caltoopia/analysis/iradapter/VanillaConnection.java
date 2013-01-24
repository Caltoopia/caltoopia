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

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.PortInstance;


/**
 * Represents a connection between two PortInstances, the edges of a Network
 * "vanilla" = straight-forward implementation as a producer/consumer pair of ports 
 */
public class VanillaConnection implements Connection {

	private PortInstance mProducerPort;
	private PortInstance mConsumerPort;
	
	public VanillaConnection(PortInstance producerPort, PortInstance consumerPort) {
		assert(producerPort.getDirection()==PortInstance.Direction.OUT
			   && consumerPort.getDirection()==PortInstance.Direction.IN);
		mProducerPort=producerPort;
		mConsumerPort=consumerPort;
	}

	public PortInstance getProducerPort() {
		return mProducerPort;
	}
	
	public PortInstance getConsumerPort() {
		return mConsumerPort;
	}
	
	/*
	 * Implementation of GenericEdge
	 */
	
	public ActorInstance getFirst() {
		return mProducerPort.getActor();
	}

	public ActorInstance getSecond() {
		return mConsumerPort.getActor();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj!=null && obj instanceof VanillaConnection) {
			VanillaConnection c=(VanillaConnection) obj;
			return mProducerPort.equals(c.mProducerPort) && mConsumerPort.equals(c.mConsumerPort);
		}
		else
			return false;
	}

	@Override
	public int hashCode() {
		return 999007*mProducerPort.hashCode() + mConsumerPort.hashCode();
	}	
	
	@Override
	public String toString() {
		return "("+mProducerPort+","+mConsumerPort+")";
	}
}
