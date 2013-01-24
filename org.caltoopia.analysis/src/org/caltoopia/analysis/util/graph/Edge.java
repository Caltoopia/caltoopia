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

/**
 * An edge of a graph, a pair of vertices.
 * 
 * In the case of directed graphs the pair (s,t) is ordered:
 * + The first component, s, is known as the source vertex (also: tail) of the edge. 
 * + The second component, t, is known as the terminus vertex (also: head) of the edge.
 *   
 * An ordinary (undirected) edge is an unordered pair {u,v} of vertices. For practical purposes
 * some orientation is assumed, after which we can talk about a "first" and a "second" vertex.
 * However note that {u,v} and {v,u} denote the same edge and the distinct Java objects representing
 * these two orientations of the same edge are equal (same hash code etc.).
 *  
 * @param <V> vertex type
 */
public interface Edge<V> {
	
	/**
	 * @return the "first" vertex of the edge: the source vertex (tail) of a directed edge,
	 *         one end point of an undirected edge (some orientation assumed).
	 */
	V getFirst();
	
	/**
	 * @return the "second" vertex of the edge: the terminus vertex (head) of a directed edge,
	 *         "the other" ("not the first") end point of an undirected edge (some orientation assumed).
	 */
	V getSecond();	
}
