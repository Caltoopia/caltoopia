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

import org.caltoopia.analysis.util.graph.neighborhood.LabeledNeighborhoodMap;
import org.caltoopia.analysis.util.graph.neighborhood.NeighborhoodMap;
import org.caltoopia.analysis.util.graph.neighborhood.NeighborhoodMapDiGraph;
import org.caltoopia.analysis.util.graph.neighborhood.UnlabeledNeighborhoodMap;
import org.caltoopia.analysis.util.graph.neighborhood.VertexLabeledNeighborhoodMap;

/**
 * Graph utilities (c.f. java.util.Collections)
 */
public class Graphs {
	/**
	 * @param <V> vertex type
	 * @return an empty, unlabeled (no vertex/edge labels) directed graph  
	 */
	public static<V> LabeledDiGraph<V,Void,Void> createDiGraph() {
		NeighborhoodMap<V,Void,Void> neighborhoodMap=new UnlabeledNeighborhoodMap<V>();
		return new NeighborhoodMapDiGraph<V,Void,Void>(neighborhoodMap);
	}

	/**
	 * @param <V> vertex type
	 * @param <VL> vertex label type
	 * @return an empty, vertex-labeled directed graph  
	 */
	public static<V,VL> LabeledDiGraph<V,VL,Void> createVertexLabeledDiGraph() {
		NeighborhoodMap<V,VL,Void> neighborhoodMap=new VertexLabeledNeighborhoodMap<V,VL>();
		return new NeighborhoodMapDiGraph<V,VL,Void>(neighborhoodMap);
	}
	
	/**
	 * @param <V> vertex type
	 * @param <EL> edge label type
	 * @return an empty, edge-labeled directed graph  
	 */
	public static<V,EL> LabeledDiGraph<V,Void,EL> createEdgeLabeledDiGraph() {
		NeighborhoodMap<V,Void,EL> neighborhoodMap=new LabeledNeighborhoodMap<V,Void,EL>();
		return new NeighborhoodMapDiGraph<V,Void,EL>(neighborhoodMap);
	}

	/**
	 * @param <V> vertex type
	 * @param <VL> vertex label type
	 * @param <EL> edge label type
	 * @return an empty, edge-labeled directed graph  
	 */
	public static<V,VL,EL> LabeledDiGraph<V,VL,EL> createVertexAndEdgeLabeledDiGraph() {
		NeighborhoodMap<V,VL,EL> neighborhoodMap=new LabeledNeighborhoodMap<V,VL,EL>();
		return new NeighborhoodMapDiGraph<V,VL,EL>(neighborhoodMap);
	}

//	/**
//	 * @param singleVertex  a vertex
//	 * @return the "trivial" Graph, consisting only of the given vertex and no edges
//	 */
//		public static<V> Graph<V> trivialGraph(V singleVertex) {
//			return new TrivialGraph<V>(singleVertex);
//		}
//	 
//
//	/**
//	 * @param graph  a DiGraph
//	 * @return a LabeledDiGraph (setVertex/EdgeLabel unsupported operations)
//	 */
//	public static<V,VL,EL> LabeledDiGraph<V,VL,EL> asLabeledDiGraph(DiGraph<V> graph) {
//		return new LabeledDiGraphAdaptor<V,VL,EL>(graph);
//	}
//
//	/**
//	 * @param singleVertex  a vertex
//	 * @return the "trivial" DiGraph, consisting only of the given vertex and no edges
//	 */
//	public static<V> DiGraph<V> trivialDiGraph(V singleVertex) {
//		return new TrivialDiGraph<V,Void,Void>(singleVertex);
//	}
//
//
//	/**
//	 * @param singleVertex  a vertex
//	 * @return the "trivial" LabeledDiGraph, consisting only of the given vertex and no edges
//	 */
//	public static<V,VL,EL> LabeledDiGraph<V,VL,EL> trivialLabeledDiGraph(V singleVertex) {
//		return new TrivialDiGraph<V,VL,EL>(singleVertex);
//	}
//
//	/**
//	 * @param vertices  a vertex set
//	 * @param graph     a directed graph
//	 * @return a graph consisting of the given vertices and the edges of the original graph, 
//	 *         which are induced by the vertices.
//	 *         
//	 * Note 1: If the vertex set contains vertices, not in the backing graph, they become
//	 *         isolated (no incident edges) in the resulting graph.
//	 * Note 2: The resulting graph is backed by the original graph, which means that modification
//	 *         of the original graph affects the resulting graph.
//	 * Note 3: Modification or the resulting graph (adding/removing vertices) is supported, but
//	 *         it does not affect the original graph; edges are added/removed as induced by the
//	 *         modified vertex set. Modification of the edge set is not supported. 
//	 */
//	public static<V> DiGraph<V> subGraph(Set<V> vertices, DiGraph<V> graph) {
//		return new SubDiGraph<V,Void,Void>(vertices,graph);
//	}
//
//	/**
//	 * @param vertices  a vertex set
//	 * @param graph     a directed graph
//	 * @return a graph consisting of the given vertices and the edges of the original graph, 
//	 *         which are induced by the vertices.
//	 *         
//	 * Note 1: If the vertex set contains vertices, not in the backing graph, they become
//	 *         isolated (no incident edges) in the resulting graph.
//	 * Note 2: The resulting graph is backed by the original graph, which means that modification
//	 *         of the original graph affects the resulting graph.
//	 * Note 3: Modification or the resulting graph (adding/removing vertices) is supported, but
//	 *         it does not affect the original graph; edges are added/removed as induced by the
//	 *         modified vertex set. Modification of the edge set is not supported. 
//	 * Note 4: Edge and vertex labels can be retrieved, but not altered (since this could require
//	 *         adding edges/vertices to the original graph).
//	 */
//	public static<V,VL,EL> LabeledDiGraph<V,VL,EL> subGraph(Set<V> vertices, LabeledDiGraph<V,VL,EL> graph) {
//		return new SubDiGraph<V,VL,EL>(vertices,graph);
//	}
//
//	/**
//	 * @param vertices  a vertex set
//	 * @param graph     a directed multigraph
//	 * @return a graph consisting of the given vertices and the edges of the original graph, 
//	 *         which are induced by the vertices.
//	 *         
//	 * Note 1: If the vertex set contains vertices, not in the backing graph, they become
//	 *         isolated (no incident edges) in the resulting graph.
//	 * Note 2: The resulting graph is backed by the original graph, which means that modification
//	 *         of the original graph affects the resulting graph.
//	 * Note 3: Modification or the resulting graph (adding/removing vertices) is supported, but
//	 *         it does not affect the original graph; edges are added/removed as induced by the
//	 *         modified vertex set. Modification of the edge set is not supported. 
//	 * Note 4: Edge and vertex labels can be retrieved, but not altered (since this could require
//	 *         adding edges/vertices to the original graph).
//	 */
//	public static<V,E extends GenericEdge<V>> MultiDiGraph<V,E> subGraph(Set<V> vertices, MultiDiGraph<V,E> graph) {
//		LabeledDiGraph<V,Object,Set<E>> g=new SubDiGraph<V,Object,Set<E>>(vertices, graph.asLabeledDiGraph());
//		return new MultiDiGraphAdaptor<V,E,Object>(g);
//	}
//
//	/**
//	 * @param normalGraph  a directed Graph
//	 * @return the transpose graph, in which the source and terminus of each
//	 *         edge trade roles.
//	 *         
//	 * Note 1: The transposed graph is backed by the original graph, which means that
//	 *         modification (add/remove of vertices/edges) also affects the original graph.
//	 *         Further, the transposed graph is modifiable only if the original graph is.
//	 * Note 2: removal of vertices/edges via iterators is an optional operation,
//	 *         which may not be supported (even when supported by the original graph).
//	 */
//	public static<V> DiGraph<V> transpose(DiGraph<V> normalGraph) {
//		return new TransposeDiGraph<V,Void,Void>(normalGraph);
//	}
//
//	/**
//	 * @param normalGraph  a labeled directed Graph
//	 * @return the transpose graph, in which the source and terminus of each
//	 *         edge trade roles.
//	 *         
//	 * Note 1: The transposed graph is backed by the original graph, which means that
//	 *         modification (add/remove of vertices/edges) also affects the original graph.
//	 *         Further, the transposed graph is modifiable only if the original graph is.
//	 * Note 2: removal of vertices/edges via iterators is an optional operation,
//	 *         which may not be supported (even when supported by the original graph).
//	 */
//	public static<V,VL,EL> LabeledDiGraph<V,VL,EL> transpose(LabeledDiGraph<V,VL,EL> normalGraph) {
//		return new TransposeDiGraph<V,VL,EL>(normalGraph);
//	}
//
//	/**
//	 * @param graph
//	 * @return the vertices of graph in "preorder" (ancestors before descendants 
//	 *         when the graph is acyclic, possible cycles broken arbitrarily)
//	 */
//	public static<V> List<V> preOrder(DiGraph<V> graph) {
//		DiGraphTraversal<V> traversal=new DiGraphTraversal<V>(graph) {
//			@Override
//			protected void visit(V vertex) {
//				mResult.add(vertex);
//				super.visit(vertex);
//			}
//		};
//
//		return traversal.visitAll();
//	}
//
//	/**
//	 * @param graph
//	 * @return the vertices of graph in "postorder" (descendants before ancestors 
//	 *         when the graph is acyclic, possible cycles broken arbitrarily)
//	 */
//	public static<V> List<V> postOrder(DiGraph<V> graph) {
//		DiGraphTraversal<V> traversal=new DiGraphTraversal<V>(graph) {
//			@Override
//			protected void visit(V vertex) {
//				super.visit(vertex);
//				mResult.add(vertex);
//			}
//		};
//
//		return traversal.visitAll();
//	}
//
//	/**
//	 * @param graph
//	 * @return an acyclic condensation of the graph
//	 */
//	public static<V> AcyclicCondensation<V,DiGraph<V>> acyclicCondensation(DiGraph<V> graph) {
//		AcyclicCondensation<V,DiGraph<V>> ac=new AcyclicCondensation<V,DiGraph<V>>() {
//			@Override
//			protected DiGraph<V> createScc(V root, List<DiEdge<V>> treeEdges) {
//				return new SubDiGraph<V,Void,Void>(getSccVertices(root,treeEdges), getOriginalGraph());
//			}
//
//
//			@Override
//			protected DiGraph<V> createTrivialScc(V vertex) {
//				return new TrivialDiGraph<V,Void,Void>(vertex);
//			}
//		};
//
//		ac.decompose(graph);
//		return ac;
//	}
}

