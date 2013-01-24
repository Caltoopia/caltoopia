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

import org.caltoopia.analysis.util.graph.Edge;
import org.caltoopia.analysis.util.graph.LabeledDiGraph;

/**
 * An implementation of the LabeledDiGraph interface, which is based on NeighborhoodMap.
 * 
 * Management of the adjacency relation and possible (vertex-/edge-) labeling is delegated
 * to the NeighborhoodMap. By plugging-in NeighborhoodMaps of different "flavors" we thus
 * get unlabeled, vertex-labeled or edge-labeled directed graphs.
 *
 * The required foot work, which implements the DiGraph and LabeledDiGraph interfaces, is
 * found in the base class AbstractGenericGraph. Much of this is common to both directed and
 * undirected graphs.
 *  
 * @param <V> vertex type
 * @param <VL> vertex label type
 * @param <EL> edge label type
 */
public class NeighborhoodMapDiGraph<V,VL,EL> extends AbstractGenericGraph<V,Edge<V>,VL,EL>
                                             implements LabeledDiGraph<V,VL,EL> {

	public NeighborhoodMapDiGraph(NeighborhoodMap<V,VL,EL> neighbors) {
		super(neighbors);
	}
		
	@Override
	public boolean containsEdge(Object s, Object t) {
		return containsOrientedEdge(s,t);
	}

	@Override
	public boolean addEdge(V s, V t) {
		return addOrientedEdge(s,t);
	}

	@Override
	public boolean removeEdge(Object s, Object t) {
		return removeOrientedEdge(s,t);
	}

	
	@Override
	public Set<V> getPredecessors(V vertex) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(vertex);
		return (neighborhood!=null)? neighborhood.getReverseLinks() : null;
	}

	@Override
	public Set<V> getSuccessors(V vertex) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(vertex);
		return (neighborhood!=null)? neighborhood.getForwardLinks() : null;
	}

	@Override
	public Set<Edge<V>> getIncidentTo(V vertex) {
		Set<V> predecessors=getPredecessors(vertex);
		return (predecessors!=null)? new IncidentToEdgeSet(vertex,predecessors) : null;
	}

	@Override
	public Set<Edge<V>> getIncidentFrom(V vertex) {
		Set<V> successors=getSuccessors(vertex);
		return (successors!=null)? new IncidentFromEdgeSet(vertex,successors) : null;
	}

	@Override
	public EL getEdgeLabel(Object s, Object t) {
		Neighborhood<V,VL,EL> s_neighborhood=mNeighbors.get(s);
		return (s_neighborhood!=null)? s_neighborhood.getForwardLinkLabel(t) : null;
	}

	@Override
	public void setEdgeLabel(V s, V t, EL edgeLabel) {
		addEdge(s,t);
		mNeighbors.get(s).setForwardLinkLabel(t, edgeLabel);
	}

	@Override
	protected Edge<V> createEdge(V u, V v) {
		return new DirectedEdge<V>(u,v);
	}	
}
