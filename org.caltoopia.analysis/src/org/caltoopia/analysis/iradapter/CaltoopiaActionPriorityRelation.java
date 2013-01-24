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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.ast2ir.PriorityGraph;
import org.caltoopia.ir.Schedule;

public class CaltoopiaActionPriorityRelation extends AbstractPriorityRelation<Action> {

	private Map<Action,Entry> mAction2Entry=new LinkedHashMap<Action,Entry>();
	
	private class Entry {
		Set<Action> mDescendants;
		int mNumAncestors;
		
		Entry(Set<Action> descendants) {
			mDescendants=descendants;
		}
	}

	/**
	 * @param schedule   caltoopia ir schedule (contains priority graph and freerunners)
	 * @param id2Action  Mapping from action ids to actions
	 */
	CaltoopiaActionPriorityRelation(Schedule schedule, Map<String,? extends Action> id2Action) {
		PriorityGraph priorityGraph=(PriorityGraph) schedule.getPriorityGraph();
		
		// First create entries and compute the descendants of all tagged actions
		createTaggedActions(priorityGraph.getRoots(), id2Action);
		
		// At this point, only tagged actions are in the action-to-entry map
		Set<Action> taggedActions=new LinkedHashSet<Action>(mAction2Entry.keySet());

		if (!schedule.getFreeRunners().isEmpty()) {
			// create entries for "free runners" with no tags
			createFreeRunners(schedule.getFreeRunners(),taggedActions,id2Action);
		}
		
		// Count the number of ancestors
		countAncestors();
	}

	@Override
	public Set<Action> getDomain() {
		return mAction2Entry.keySet();
	}

	@Override
	protected Set<Action> descendants(Object x) {
		Entry entry=mAction2Entry.get(x);
		return (entry!=null)? entry.mDescendants : null;
	}

	@Override
	protected int getNumAncestors(Action x) {
		Entry entry=mAction2Entry.get(x);
		return (entry!=null)? entry.mNumAncestors : 0;
	}

	
	private void createTaggedActions(Collection<PriorityGraph.Vertex> roots,
			                         Map<String,? extends Action> id2Action) {
		// Determine descendants by traversing them "Postorder"
		// (so that the descendants of a vertex have been processed
		// before processing the vertex).
		for (PriorityGraph.Vertex vertex: roots) {
			String id=vertex.getAction().getId();
			Action action=id2Action.get(id);
			assert(action!=null);
			computeDescendants(vertex, action, id2Action);
		}	
	}
	
	private void createFreeRunners(Collection<org.caltoopia.ir.Action> freeRunners,
			                       Set<Action> taggedActions,
			                       Map<String,? extends Action> id2Action) {			
		for (org.caltoopia.ir.Action freeRunner: freeRunners) {
			String id=freeRunner.getId();
			Action action=id2Action.get(id);
			assert(action!=null);

			// Create an entry for each free-runner
			// It has higher priority than all "tagged" actions
			
			mAction2Entry.put(action, new Entry(taggedActions));
		}
	}

	
	/**
	 * Determines the number of ancestors of each action by counting
	 * the number of times it is mentioned as a descendant
	 */
	private void countAncestors() {
		for (Entry entry: mAction2Entry.values()) {
			for (Action action: entry.mDescendants) {
				Entry e=mAction2Entry.get(action);
				++e.mNumAncestors;
			}

		}
	}
		
	/**
	 * @param vertex    a vertex of the PriorityGraph
	 * @param action    the corresponding action
	 * @param id2Action mapping from "action ids" to actions
	 * @return the set of descendants (actions with lower priority) of the given vertex/action
	 */
	Set<Action> computeDescendants(PriorityGraph.Vertex vertex, 
		                           Action action,
		                           Map<String,? extends Action> id2Action) {
		Set<Action> descendants;
		
		if (mAction2Entry.containsKey(action)) {
			// Action already visited
			Entry entry=mAction2Entry.get(action);
			if (entry==null)
				throw new IllegalArgumentException("priority graph is cyclic");
			else
				descendants=entry.mDescendants;
		}
		else {
			// Associate a null entry with action (to detect a possible cycle)
			mAction2Entry.put(action,null);

			// Now visit all successors and compute the set of reachable actions (descendants)
			descendants=new LinkedHashSet<Action>();
			for (PriorityGraph.Edge edge: vertex.getOutgoingEdges()) {
				PriorityGraph.Vertex succ=edge.getTarget();
				String id=succ.getAction().getId();
				Action succAction=id2Action.get(id);
				descendants.add(succAction);
				descendants.addAll(computeDescendants(succ, succAction, id2Action));
			}

			// Now publish the actual entry (in Postorder)
			mAction2Entry.put(action, new Entry(descendants));
		}

		return descendants;
	}			
}
