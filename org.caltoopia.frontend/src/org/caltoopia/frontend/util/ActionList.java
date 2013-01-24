/*
 * Copyright (c) 2009, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package org.caltoopia.frontend.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import org.jgrapht.DirectedGraph;

import org.caltoopia.ir.Action;

/**
 * A list of action is like an ordered map, except keys are tags (list of
 * strings).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ActionList implements Iterable<Action> {

	final private List<Action> actionList;

	final private Map<List<String>, List<Action>> tagMap;

	final private List<Action> untaggedList, taggedList;

	/**
	 * Creates an empty action list.
	 */
	public ActionList() {
		actionList = new ArrayList<Action>();
		tagMap = new HashMap<List<String>, List<Action>>();
		untaggedList = new ArrayList<Action>();
		taggedList = new ArrayList<Action>();
	}

	/**
	 * Adds the given action to this action list.
	 * 
	 * @param action
	 *            an action
	 */
	public void add(Action action) {
		actionList.add(action);

		List<String> tag = action.getTag();
		if (tag.isEmpty()) {
			untaggedList.add(action);
		} else {
			taggedList.add(action);
			// a tag has the form a.b.c
			// we add the action to the tagMap for entries:
			// [a]; [a, b]; [a, b, c]

			int tagLength = 1;
			List<String> currentTag = new ArrayList<String>(tagLength);
			for (String id : tag) {
				currentTag.add(id);

				List<Action> actions = tagMap.get(currentTag);
				if (actions == null) {
					// create a new list of actions associated with the given
					// tag
					actions = new ArrayList<Action>();
					tagMap.put(currentTag, actions);
				}

				actions.add(action);

				// creates a new list and copies the tag in it
				tagLength++;
				currentTag = new ArrayList(currentTag);
			}
		}
	}

	/**
	 * Returns the list of actions (tagged or untagged)
	 * 
	 * @return the list of actions (tagged or untagged)
	 */
	public List<Action> getAllActions() {
		return actionList;
	}

	/**
	 * Returns the list of actions that match the given tag.
	 * 
	 * @param tag
	 *            a tag
	 * @return the list of actions that match the given tag
	 */
	public List<Action> getTaggedActions(List<String> tag) {
		return tagMap.get(tag);
	}

	/**
	 * Returns the list of untagged actions.
	 * 
	 * @return the list of untagged actions
	 */
	public List<Action> getUntaggedActions() {
		return untaggedList;
	}
	
	/**
	 * Returns the list of tagged actions.
	 * 
	 * @return the list of tagged actions
	 */
	public List<Action> getTaggedActions() {
		return taggedList;
	}

	/**
	 * Returns true if this action list is empty.
	 * 
	 * @return true if this action list is empty
	 */
	public boolean isEmpty() {
		return actionList.isEmpty();
	}


	public Iterator<Action> iterator() {
		return actionList.iterator();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Entry<List<String>, List<Action>> entry : tagMap.entrySet()) {
			builder.append(entry.getKey().toString());
			builder.append(": ");
			builder.append(entry.getValue().toString());
			builder.append('\n');
		}

		return builder.toString();
	}

}
