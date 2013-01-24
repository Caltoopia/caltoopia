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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;

/**
 * In addition to the information, which is given by an ACTORS-runtime trace, this description of a network
 * models state dependence.
 */
public class SchedulingConstraints implements CpuAffinity<ActorInstance,Object> {

	private Map<Action,ActionEffect> mEffectMap=new HashMap<Action,ActionEffect>();
	private Map<ActorInstance,Object> mAffinityMap=new LinkedHashMap<ActorInstance,Object>();
	
	/**
	 * @return the collection of "CPU:s" used as "affinity" for some actor instance
	 */
	public Collection<Object> getCpus() {
		return new LinkedHashSet<Object>(mAffinityMap.values());
	}
	
	/**
	 * @param actor
	 * @return the "CPU" with which the given actor has affinity (null if no such CPU, i.e. dynamic allocation)
	 */
	public Object getAffinity(ActorInstance actor) {
		return mAffinityMap.get(actor);
	}
	
	/**
	 * @param actor
	 * @param cpu
	 * Sets the affinity of the given CPU. A null CPU means removing affinity (i.e. dynamic CPU allocation)
	 */
	public void setAffinity(ActorInstance actor, Object cpu) {
		if (cpu==null) {
			mAffinityMap.remove(actor);
		}
		else {
			mAffinityMap.put(actor, cpu);
		}
	}
	
	public CpuAffinity<ArtTraceEvent,Object> getTraceEventAffinity(ArtNetworkDescription network) {
		return new TraceEventAffinity(network);
	}
	
	/**
	 * @return the collection of "variables" with exposed uses in action (=may be used before any definition)
	 */
	public Collection<Object> getUses(Action action) {
		ActionEffect effect=getActionEffect(action);
		return effect.getUses();
	}
	
	/**
	 * @return the collection of "variables" with definition in action (=the ones that may be given a new value)
	 */
	public Collection<Object> getDefinitions(Action action) {
		ActionEffect effect=getActionEffect(action);
		return effect.getDefinitions();
	}
	
	/**
	 * @param action
	 * @return the effect, which is associated with the given action (create a new one if needed)
	 */
	private ActionEffect getActionEffect(Action action) {
		ActionEffect effect=mEffectMap.get(action);
		if (effect==null) {
			effect=new ActionEffect();
			mEffectMap.put(action,effect);
		}
		return effect;
	}
	
	private class TraceEventAffinity implements CpuAffinity<ArtTraceEvent,Object> {

		ArtNetworkDescription mNetwork;
		
		TraceEventAffinity(ArtNetworkDescription network) {
			mNetwork=network;
		}
		
		@Override
		public Collection<Object> getCpus() {
			return SchedulingConstraints.this.getCpus();
		}

		@Override
		public Object getAffinity(ArtTraceEvent event) {
			Action action=mNetwork.getAction(event.getAction());
			ActorInstance actor=mNetwork.getActor(action);
			return SchedulingConstraints.this.getAffinity(actor);
		}

		@Override
		public void setAffinity(ArtTraceEvent task, Object cpu) {
			throw new UnsupportedOperationException();
		}
	}

	private static class ActionEffect {
		private Set<Object> mUses=new LinkedHashSet<Object>();
		private Set<Object> mDefinitions=new LinkedHashSet<Object>();
		
		Collection<Object> getUses() {
			return mUses;
		}

		Collection<Object> getDefinitions() {
			return mDefinitions;
		}
	}	
}
