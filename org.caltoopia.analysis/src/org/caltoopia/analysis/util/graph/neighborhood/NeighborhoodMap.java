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

package org.caltoopia.analysis.util.graph.neighborhood;

import java.util.Set;

/**
 * Represents the adjacency relation of a graph, essentially as a vertex-to-Neighborhood map.
 * 
 * A Neighborhood objects is thus associated with each vertex. In addition to providing the
 * set of adjacent vertices, the Neighborhood manages any (vertex- or edge-) labeling of the graph. 
 *
 * @param <V> Vertex type
 * @param <VL> Vertex label type (Void if none)
 * @param <EL> Edge label type (Void if none)
 */
public interface NeighborhoodMap<V,VL,EL> {

	/**
	 * @param vertex
	 * @return the Neighborhood, which is associated with the given vertex (null if none)
	 */
	Neighborhood<V,VL,EL> get(Object vertex);
	
	/**
	 * Associate a Neighborhood with a vertex, unless it already has one
	 * @param vertex
	 * @return true if a Neighborhood was added (false if it was already there) 
	 */
	boolean add(V vertex);

	/**
	 * Remove a vertex-Neighborhood association (remove vertex from keySet of map)
	 * @param vertex
	 * @return the Neighborhood that was associated with the vertex (null if it was none) 
	 */
	Neighborhood<V,VL,EL> remove(Object vertex);

	/**
	 * @return the set of vertices, which has an associated neighborhood
	 */
	Set<V> keySet();	
}
