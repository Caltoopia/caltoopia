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


package org.caltoopia.ast2ir;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	
	private Set<Vertex> vertices = new HashSet<Vertex>();
	
	class Vertex {
		
		private VertexData data;
		
		private Set<Edge> incoming = new HashSet<Edge>();
		
		private Set<Edge> outgoing = new HashSet<Edge>();
		
		private boolean marked = false;
		
		private int nrOfTimesVisited = 0;
		
		public Vertex(VertexData data) {
			this.data = data;
		}
		
		public void addIncomingEdge(Edge edge) {
			incoming.add(edge);
		}

		public void removeIncomingEdge(Edge edge) {
			incoming.remove(edge);
		}
		
		public Set<Edge> getIncomingEdges() {
			return incoming;
		}

		public void addOutgoingEdge(Edge edge) {
			outgoing.add(edge);
		}

		public void removeOutgoingEdge(Edge edge) {
			outgoing.remove(edge);
		}
		
		public Set<Edge> getOutgoingEdges() {
			return outgoing;
		}
		
		public VertexData getData() {
			return data;
		}

		
		public void mark() {
			marked = true;
		}
				
		public boolean isMarked() {
			return marked;
		}
		
		public void visit() {
			nrOfTimesVisited++;
		}

		public int getNrOfTimesVisited() {
			return nrOfTimesVisited;
		}

	}
	
	class Edge {
		
		private Vertex source, target;
		
		public Edge(Vertex source, Vertex target) {
			// First check if there already exists 
			// an edge between the two vertices
			
			for (Edge e : source.getOutgoingEdges()) {
				if (e.getTarget() == target)
					return;
			}
			
			this.source = source;
			source.addOutgoingEdge(this);
			this.target = target;
			target.addIncomingEdge(this);			
		}
		
		public Vertex getSource() {
			return source;
		}

		public Vertex getTarget() {
			return target;
		}
	
	}
	
	// CTOR
	public Graph(List<VertexData> listOfData) {
				
		// Add the vertices
		for (VertexData data : listOfData) {			
			addVertex(data);
		}
		
		for (VertexData data : listOfData) {			
			for (VertexData m : data.getConnectedData()) {
				Vertex srcVertex = findVertex(m);
				Vertex trgVertex = findVertex(data);
				if (srcVertex != null && trgVertex != null) {
					new Edge(srcVertex, trgVertex);
				}
			}
		}
	}
		
	public Set<Vertex> getVertices() {
		return this.vertices;
	}
	
	private Vertex findVertex(VertexData t) {
		for (Vertex v : vertices) {
			if (v.getData().getData() == t.getData()) {
				return v;
			}
		}
		return null;
	}
	
	private Vertex addVertex(VertexData t) {
		if (findVertex(t) != null) {
			return findVertex(t);
		}
		Vertex v = new Vertex(t);
		vertices.add(v);
		return v; 
	}
		
	public Set<Vertex> getRoots() {
		Set<Vertex> entryPoints = new HashSet<Vertex>();
		for (Vertex v : vertices) {
			if (v.getIncomingEdges().isEmpty()) {
				entryPoints.add(v);
			}
		}
		return entryPoints;
	}
	
	public List<VertexData> sortByDependency() {
		List<Vertex> reverseOrder = new ArrayList<Vertex>();
		List<VertexData> result = new ArrayList<VertexData>();
		// Reverse post order sorting
		
		for (Vertex root : getRoots()) {
			DFS(root, reverseOrder);
		}
	
		for (int i = reverseOrder.size(); i > 0; i--) {
			result.add(reverseOrder.get(i-1).getData());
		}
			
		return result;
	}
	
	public void DFS(Vertex v, List<Vertex> order) {
		if (!order.contains(v)) {
			for (Edge e : v.getOutgoingEdges()) {
				if (!order.contains(e.getTarget())) {
					DFS(e.getTarget(), order);
				}
			}
			order.add(v);
		}
	}
	public void print() {
		System.out.println("digraph G {");
		for (Vertex v : vertices) {
			System.out.println("  " + v.getData().getName() + ";");
		}

		for (Vertex v : vertices) {
			for (Edge e : v.getOutgoingEdges()) {
				System.out.println("  " + v.getData().getName() + " -> " + e.getTarget().getData().getName() + ";");				
			}
		}

		System.out.println("}");
	}

}
