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

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import org.caltoopia.analysis.util.collections.SetWrapper;
import org.caltoopia.analysis.util.graph.Edge;

/**
 * A common base ("generic") for both directed and undirected graphs 
 *
 * Implement the following methods pertaining to the kind of edges of the graph:
 * addEdge(u,v), removeEdge(u,v), containsEdge(u,v), getEdgeLabel(u,v), setEdgeLabel(u,v,l), createEdge(u,v)
 * 
 * @param <V> Vertex type
 * @param <E> Edge type
 * @param <VL> Vertex label
 * @param <EL> Edge label
 */
public abstract class AbstractGenericGraph<V,E extends Edge<V>,VL,EL> {

	protected NeighborhoodMap<V,VL,EL> mNeighbors;
	
	public AbstractGenericGraph(NeighborhoodMap<V,VL,EL> neighbors) {
		mNeighbors=neighbors;
	}
	
	public Set<V> getVertices() {
		return new AllVertexSet();
	}

	public Set<E> getEdges() {
		return new AllEdgeSet();
	}

	public abstract boolean addEdge(V s, V t);

	public abstract boolean removeEdge(Object s, Object t);

	public abstract boolean containsEdge(Object s, Object t);

	public VL getVertexLabel(V vertex) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(vertex);
		
		return (neighborhood!=null)? neighborhood.getVertexLabel() : null;
	}

	public void setVertexLabel(V vertex, VL vertexLabel) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(vertex);
		if (neighborhood==null) {
			// Create a neighborhood (add vertex)
			mNeighbors.add(vertex);
			neighborhood=mNeighbors.get(vertex);
		}
		neighborhood.setVertexLabel(vertexLabel);
	}

	public abstract EL getEdgeLabel(Object s, Object t);

	public EL getEdgeLabel(E edge) {
		return getEdgeLabel(edge.getFirst(), edge.getSecond());
	}

	public abstract void setEdgeLabel(V s, V t, EL edgeLabel);

	public void setEdgeLabel(E edge, EL edgeLabel) {
		setEdgeLabel(edge.getFirst(), edge.getSecond(), edgeLabel);	
	}
	
	/**
	 * @param vertex
	 * @return the set of incident edges with "forward" orientation w.r.t. the vertex
	 *         for directed graphs this is the set of edges "incident from" the vertex, 
	 *         for undirected graphs the orientation is arbitrary (half of the incident edges) 
	 */
	protected Set<E> getForwardEdges(V vertex) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(vertex);
		
		if (neighborhood!=null) {
			Set<V> successors=neighborhood.getForwardLinks();
			return new IncidentFromEdgeSet(vertex, successors);
		}
		else {
			return null;  // vertex not in the graph
		}
	}
	
	/**
	 * @param u  a vertex
	 * @param v  a vertex
	 * @return an edge
	 */
	protected abstract E createEdge(V u, V v);
		
	/**
	 * @return the number of edges in the graph
	 * 
	 * This implementation simply sums the number of successors of all vertices
	 * (override if the edge-count is maintained etc.)
	 */
	protected int getNumberOfEdges() {
		int count=0;
		
		for (V vertex: mNeighbors.keySet()) {
			count += mNeighbors.get(vertex).getForwardLinks().size();
		}
		return count; 
	}
	
	/**
	 * @param u
	 * @param v
	 * @return true iff the edge (u,v) is in the graph and has the given orientation 
	 */
	protected boolean containsOrientedEdge(Object u, Object v) {
		Neighborhood<V,VL,EL> u_neighborhood=mNeighbors.get(u);
		return u_neighborhood!=null && u_neighborhood.getForwardLinks().contains(v);
	}
	
	/**
	 * Adds the oriented edge (u,v). Note that the presence of a possible edge with
	 * reverse orientation, (v,u), is not checked.
	 * @param u
	 * @param v
	 * @return true iff the edge (u,v) was added 
	 */
	protected boolean addOrientedEdge(V u, V v) {
		mNeighbors.add(u);
		if (mNeighbors.get(u).getForwardLinks().add(v)) {
			// Also add the corresponding reverse link
			mNeighbors.add(v);
			mNeighbors.get(v).getReverseLinks().add(u);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Removes the oriented edge (u,v) -but not one with the reverse orientation (v,u), u!=v.
	 * @param u
	 * @param v
	 * @return returns true if the edge was removed.
	 */
	protected boolean removeOrientedEdge(Object u, Object v) {
		Neighborhood<V,VL,EL> u_neighborhood=mNeighbors.get(u);
		if (u_neighborhood!=null && u_neighborhood.getForwardLinks().remove(v)) {
			// Also remove the corresponding reverse link
			mNeighbors.get(v).getReverseLinks().remove(u);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Allows for consistent removal of the edge (vertex,successor) when iterating over successors
	 * 
	 * @param pSucc      the iterator (pSucc.remove() removes successor from the vertex' set of successors) 
	 * @param vertex     a vertex
	 * @param successor  the successor, which was most recently returned by the iterator 
	 */
	protected void removeSuccessor(Iterator<V> pSucc, V vertex, V successor) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(successor);
		pSucc.remove();
		neighborhood.getReverseLinks().remove(vertex);
	}

	/**
	 * Allows for consistent removal of the edge (predecessor,vertex) when iterating over predecessors
	 * 
	 * @param pPred        the iterator (pPred.remove() removes predecessor from the vertex' set of predecessors) 
	 * @param predecessor  the predecessor, which was most recently returned by the iterator
	 * @param vertex       a vertex 
	 */
	protected void removePredecessor(Iterator<V> pPred, V predecessor, V vertex) {
		Neighborhood<V,VL,EL> neighborhood=mNeighbors.get(predecessor);
		pPred.remove();
		neighborhood.getForwardLinks().remove(vertex);
	}
	
	/**
	 * AllVertexSet is the set of all vertices
	 * Unlike the keySet of the Map, it supports the add() method
	 *
	 */
	protected class AllVertexSet extends SetWrapper<V> {

		public AllVertexSet() {
			super(AbstractGenericGraph.this.mNeighbors.keySet());
		}

		@Override
		public boolean add(V vertex) {
			return AbstractGenericGraph.this.mNeighbors.add(vertex);
		}

		@Override
		public boolean remove(Object obj) {
			Neighborhood<V,VL,EL> old=AbstractGenericGraph.this.mNeighbors.remove(obj);
			return old!=null;
		}
	}
	
	/**
	 * Iteration over vertices with the opportunity to catch a remove() operation, so that it updates the graph
	 * in a consistent manner.
	 */
	protected abstract class LinkIterator implements Iterator<V> {
		
		private Iterator<V> mBackingIterator;
		protected V mLast;
		
		public LinkIterator(Iterator<V> backingIterator) {
			mBackingIterator=backingIterator;
		}
		
		@Override
		public boolean hasNext() {
			return mBackingIterator.hasNext();
		}

		@Override
		public V next() {
			mLast=mBackingIterator.next();
			return mLast;
		}
	}
	
	/**
	 * Represents a set of vertices that are adjacent to a given, common vertex, following edges with forward orientation.
	 * 
	 * Adding and removing vertices to this set updates the graph in a consistent manner
	 * ("forward" and "reverse" links etc.)
	 *
	 */
	protected class SuccessorSet extends AbstractSet<V> {
		private V mCommonVertex;
		private Set<V> mBackingSet;
		
		public SuccessorSet(V commonVertex, Set<V> backingSet) {
			mCommonVertex=commonVertex;
			mBackingSet=backingSet;
		}

		@Override
		public boolean add(V vertex) {
			return AbstractGenericGraph.this.addEdge(mCommonVertex, vertex);
		}
		
		@Override
		public boolean remove(Object vertex) {
			return AbstractGenericGraph.this.removeEdge(mCommonVertex, vertex);
		}
		
		@Override
		public boolean contains(Object vertex) {
			return mBackingSet.contains(vertex);
		}
		
		@Override
		public int size() {
			return mBackingSet.size();
		}

		@Override
		public Iterator<V> iterator() {
			return new LinkIterator(mBackingSet.iterator()) {
				@Override
				public void remove() {
					AbstractGenericGraph.this.removeSuccessor(this,mCommonVertex,mLast);
				}
			};
		}
	}
	
	/**
	 * Represents a set of vertices that are adjacent to a given, common vertex, following edges with forward orientation.
	 * 
	 * Adding and removing vertices to this set updates the graph in a consistent manner
	 * ("forward" and "reverse" links etc.)
	 *
	 */
	protected class PredecessorSet extends AbstractSet<V> {
		private V mCommonVertex;
		private Set<V> mBackingSet;
		
		public PredecessorSet(V commonVertex, Set<V> backingSet) {
			mCommonVertex=commonVertex;
			mBackingSet=backingSet;
		}

		@Override
		public boolean add(V vertex) {
			return AbstractGenericGraph.this.addEdge(vertex, mCommonVertex);
		}
		
		@Override
		public boolean remove(Object vertex) {
			return AbstractGenericGraph.this.removeEdge(vertex,mCommonVertex);
		}
		
		@Override
		public boolean contains(Object vertex) {
			return mBackingSet.contains(vertex);
		}

		@Override
		public int size() {
			return mBackingSet.size();
		}

		@Override
		public Iterator<V> iterator() {
			return new LinkIterator(mBackingSet.iterator()){
				@Override
				public void remove() {
					AbstractGenericGraph.this.removePredecessor(this,mLast,mCommonVertex);
				}
			};
		}
	}
	
	/**
	 * Adaptor for sets of incident edges, given adjacent vertices
	 *
	 * Relies on the backing set of vertices for consistent updates (add/remove)
	 */
	protected abstract class AbstractIncidentEdgeSet extends AbstractSet<E> {
		
		protected V mCommonVertex;
		protected Set<V> mAdjacentVertices;
		
		public AbstractIncidentEdgeSet(V commonVertex, Set<V> adjacentVertices) {
			mCommonVertex=commonVertex;
			mAdjacentVertices=adjacentVertices;
		}
				
		protected abstract E createEdge(V otherVertex);
		
		protected abstract Object getOtherEndPoint(Object obj);
		
		@Override
		public abstract boolean add(E edge);

		@Override
		public boolean contains(Object edge) {
			Object other=getOtherEndPoint(edge);
			return (other!=null && mAdjacentVertices.contains(other));
		}

		@Override
		public boolean remove(Object edge) {
			Object other=getOtherEndPoint(edge);
			return (other!=null && mAdjacentVertices.remove(other));
		}

		@Override
		public int size() {
			return mAdjacentVertices.size();
		}
		
		@Override
		public Iterator<E> iterator() {
			return new Iterator<E>() {

				Iterator<V> mBackingIterator=mAdjacentVertices.iterator();
				V mLast;

				@Override
				public boolean hasNext() {
					return mBackingIterator.hasNext();
				}

				@Override
				public E next() {
					mLast=mBackingIterator.next();
					return createEdge(mLast);
				}

				@Override
				public void remove() {
					mBackingIterator.remove();
				}
			};
		}		
	}
	
	/**
	 * Represents a set of edges, which is "incident from" a given vertex:
	 * the out-going edges (v,w) from a vertex, v.
	 */
	protected class IncidentFromEdgeSet extends AbstractIncidentEdgeSet {

		public IncidentFromEdgeSet(V commonVertex, Set<V> adjacentVertices) {
			super(commonVertex, adjacentVertices);
		}

		@Override
		protected E createEdge(V other) {
			return AbstractGenericGraph.this.createEdge(mCommonVertex, other);
		}

		@Override
		protected Object getOtherEndPoint(Object obj) {
			if (obj instanceof Edge<?>) {
				Edge<?> edge=(Edge<?>) obj;
				if (edge.getFirst()==mCommonVertex)
					return edge.getSecond();
			}
			return null;
		}

		@Override
		public boolean add(E edge) {
			if (edge.getFirst()==mCommonVertex) {
				return AbstractGenericGraph.this.addEdge(mCommonVertex, edge.getSecond());
			}
			else
				return false;  // Cannot add edge to this set of incident edges
		}
	}

	/**
	 * Represents a set of edges, which is "incident to" a given vertex:
	 * the edges (u,v) going in to a vertex, v.
	 */
	protected class IncidentToEdgeSet extends AbstractIncidentEdgeSet {

		public IncidentToEdgeSet(V commonVertex, Set<V> adjacentVertices) {
			super(commonVertex, adjacentVertices);
		}

		@Override
		protected E createEdge(V other) {
			return AbstractGenericGraph.this.createEdge(mCommonVertex, other);
		}

		@Override
		protected Object getOtherEndPoint(Object obj) {
			if (obj instanceof Edge<?>) {
				Edge<?> edge=(Edge<?>) obj;
				if (edge.getFirst()==mCommonVertex)
					return edge.getSecond();
			}
			return null;
		}

		@Override
		public boolean add(E edge) {
			if (edge.getFirst()==mCommonVertex) {
				return AbstractGenericGraph.this.addEdge(mCommonVertex, edge.getSecond());
			}
			else
				return false;  // Cannot add edge to this set of incident edges
		}
	}

	/**
	 * Represents the set of all edges in the graph, with consistent updates (add/remove).
	 */
	protected class AllEdgeSet extends AbstractSet<E> {

		@Override
		public boolean add(E edge) {
			return AbstractGenericGraph.this.addEdge(edge.getFirst(), edge.getSecond());
		}

		@Override
		public boolean contains(Object obj) {
			if (obj instanceof Edge<?>) {
				Edge<?> edge=(Edge<?>) obj;
				return AbstractGenericGraph.this.containsEdge(edge.getFirst(), edge.getSecond());
			}
			else
				return false;
		}


		@Override
		public boolean remove(Object obj) {
			if (obj instanceof Edge<?>) {
				Edge<?> edge=(Edge<?>) obj;
				return AbstractGenericGraph.this.removeEdge(edge.getFirst(), edge.getSecond());
			}
			else
				return false;
		}

		@Override
		public int size() {
			return AbstractGenericGraph.this.getNumberOfEdges();
		}
		
		@Override
		public Iterator<E> iterator() {
			return new Iterator<E>() {
				Iterator<V> mVertices=AbstractGenericGraph.this.getVertices().iterator();
				Iterator<E> mEdges;
				
				@Override
				public boolean hasNext() {
					if (mEdges!=null && mEdges.hasNext()) {
						return true;
					}
					
					while (mVertices.hasNext()) {
						V vertex=mVertices.next();
						
						mEdges=AbstractGenericGraph.this.getForwardEdges(vertex).iterator();
						if (mEdges.hasNext()) {
							return true;
						}
					}
					return false;
				}

				@Override
				public E next() {
					return mEdges.next();
				}

				@Override
				public void remove() {
					mEdges.remove();
				}
			};
		}
	}
}
