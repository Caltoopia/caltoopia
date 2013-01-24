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

package org.caltoopia.analysis.util.graph;

import java.util.Set;

/**
 * A basic (unlabeled) DiGraph (directed graph) 
 *
 * @param <V> type of vertices
 */
public interface DiGraph<V> {
	/**
	 * @return the set of vertices
	 * 
	 * The returned set is a view, backed by the graph. By modifying this set 
	 * (adding/removing vertices) the graph is modified accordingly.
	 */
	Set<V> getVertices();
	
	/**
	 * @return the set of edges
	 * 
	 * The returned set is a view, backed by the graph. By modifying this set
	 * (adding/removing edges) the graph is modified accordingly
	 */
	Set<Edge<V>> getEdges();
	
	/**
	 * Adds the edge (s,t) to the graph (and the vertices s, t if needed).
	 * 
	 * @param s a vertex
	 * @param t a vertex
	 * @return true if the edge was added to the graph
	 * 
	 * Note: same effect as getEdges().add(DiEdge.create(s,t))
	 */
	boolean addEdge(V s, V t);

	/**
	 * Removes the edge (s,t) from the graph.
	 * 
	 * @param s a vertex
	 * @param t a vertex
	 * @return true if the edge was removed from the graph (present before this operation).
	 * 
	 * Note: same effect as getEdges().remove(DiEdge.create(s,t))
	 */
	boolean removeEdge(Object s, Object t);
	
	/**
	 * @param s a vertex
	 * @param t a vertex
	 * @return true if the graph contains the edge (s,t)
	 * 
	 * Note: same result as getEdges().contains(DiEdge.create(s,t))
	 */
	boolean containsEdge(Object s, Object t);

	/**
	 * @param vertex
	 * @return the predecessors of the given vertex:
	 *         vertices u, such that there is an edge (u,vertex).
	 *         
	 * The returned set is a view, backed by the graph. By modifying this set (adding/removing vertices) 
	 * the graph is modified accordingly (adds/removes edges etc.).
	 */
	Set<V> getPredecessors(V vertex);

	/**
	 * @param vertex
	 * @return the successors of the given vertex:
	 *         vertices w, such that there is an edge (vertex,w).
	 *         
	 * The returned set is a view, backed by the graph. By modifying this set (adding/removing vertices) 
	 * the graph is modified accordingly (adds/removes edges etc.).
	 */
	Set<V> getSuccessors(V vertex);
	
	/**
	 * @param vertex
	 * @return the set of edges (u, vertex) that are "incident to" the given vertex.
	 * 
	 * The returned set is a view, backed by the graph. By modifying this set (adding/removing edges) 
	 * the graph is modified accordingly.
	 */
	Set<Edge<V>> getIncidentTo(V vertex);

	/**
	 * @param vertex
	 * @return the set of edges (vertex,w) that are "incident from" the given vertex.
	 *
	 * The returned set is a view, backed by the graph. By modifying this set (adding/removing edges) 
	 * the graph is modified accordingly.
	 */
	Set<Edge<V>> getIncidentFrom(V vertex);	
}
