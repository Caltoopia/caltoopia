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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.ir.Point2PointConnection;

/**
 * Represents a network of connected ActorInstances, 
 * basically an adapter for org.caltoopia.ir.Network objects   
 */
public class CaltoopiaNetwork implements org.caltoopia.analysis.air.Network {

	private org.caltoopia.ir.Network mNetwork;
	private Map<org.caltoopia.ir.ActorInstance, org.caltoopia.analysis.air.ActorInstance> mActorMapping;
	private Set<org.caltoopia.analysis.air.Connection> mConnections=new LinkedHashSet<org.caltoopia.analysis.air.Connection>();

	
	public CaltoopiaNetwork(org.caltoopia.ir.Network network, 
			                Map<org.caltoopia.ir.ActorInstance, org.caltoopia.analysis.air.ActorInstance> actorMapping) {
		mNetwork=network;
		mActorMapping=actorMapping;
		createConnections();
	}

	@Override
	public String getName() {
		return mNetwork.getType().getName();
	}

	@Override
	public Collection<org.caltoopia.analysis.air.ActorInstance> getActors() {
		return mActorMapping.values();
	}

	@Override
	public Collection<org.caltoopia.analysis.air.Connection> getConnections() {
		return mConnections;
	}	
	
	@Override
	public Set<org.caltoopia.analysis.air.Connection> getIncidentConnections(org.caltoopia.analysis.air.PortInstance port) {
		return new IncidentConnectionSet(mConnections,port);
	}

	/**
	 * @param actor  a caltoopia IR actor instance
	 * @return       the corresponding representation used by the analysis package
	 */
	public org.caltoopia.analysis.air.ActorInstance getActor(org.caltoopia.ir.ActorInstance actor) {
		return mActorMapping.get(actor);
	}
	
	private void createConnections() {
		for (org.caltoopia.ir.Connection caltoopiaConnection: mNetwork.getConnections()) {
			createConnection((Point2PointConnection) caltoopiaConnection);
		}
	}
	
	private void createConnection(Point2PointConnection caltoopiaConnection) {
		org.caltoopia.analysis.air.PortInstance source=getPortInstance(caltoopiaConnection.getSource());
		org.caltoopia.analysis.air.PortInstance target=getPortInstance(caltoopiaConnection.getTarget());
		assert(source!=null && target!=null);
		mConnections.add(new VanillaConnection(source,target));
	}

	private org.caltoopia.analysis.air.PortInstance getPortInstance(org.caltoopia.ir.PortInstance port) {
		org.caltoopia.analysis.air.ActorInstance actor=getActor(port.getActor());
		return actor.getPort(port.getName());
	}
}
