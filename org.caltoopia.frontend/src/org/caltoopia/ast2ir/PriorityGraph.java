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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.caltoopia.frontend.cal.AstInequality;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.util.ActionList;
import org.caltoopia.ir.Action;

public class PriorityGraph {

	private List<AstPriority> priorities;
	
	private Set<Vertex> vertices = new HashSet<Vertex>();
	
	// private Set<Edge> edges = new HashSet<Edge>();
		
	public class Vertex {
		
		private Action action;
		
		private Set<Edge> incoming = new HashSet<Edge>();
		
		private Set<Edge> outgoing = new HashSet<Edge>();
		
		private boolean marked = false;
		
		private int nrOfTimesVisited = 0;
		
		public Vertex(Action action) {
			this.action = action;
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
		
		public Action getAction() {
			return action;
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
	
	public class Edge {
		
		private Vertex source, target;
		
		public Edge(Vertex source, Vertex target) {
			this.source = source;
			source.addOutgoingEdge(this);
			this.target = target;
			target.addIncomingEdge(this);
			//edges.add(this);
		}
		
		public Vertex getSource() {
			return source;
		}

		public Vertex getTarget() {
			return target;
		}
	
	}
	
	public PriorityGraph(ActionList actions, List<AstPriority> priorities) {
		this.priorities = priorities;
		
		// Add the vertices
		for (AstPriority priority : priorities) {
			for (AstInequality inequality : priority.getInequalities()) {
				// the grammar requires there be at least two tags
				Iterator<AstTag> it = inequality.getTags().iterator();
				List<String> previousTag = new ArrayList<String>(it.next().getIdentifiers());
				while (it.hasNext()) {
					List<String> tag = new ArrayList<String>(it.next().getIdentifiers());
					List<Action> sources = actions.getTaggedActions(previousTag);
					List<Action> targets = actions.getTaggedActions(tag);
					for (Action source : sources) {
						Vertex sourceVertex = findVertex(source);
						if (sourceVertex == null) {
							sourceVertex = addVertex(source);
						}
						for (Action target : targets) {
							Vertex targetVertex = findVertex(target);
							if (targetVertex == null) {
								targetVertex = addVertex(target);
							}
							
							new Edge(sourceVertex, targetVertex);
						}
					}

					previousTag = tag;
				}
			}
		}
		
		// Finally add the actions that are used in the FSM, 
		// but not in any priority relation
		
		List<Action> la = new ArrayList<Action>(actions.getTaggedActions());
		la.removeAll(this.getActions());
		for (Action a : la) {
			addVertex(a);
		}
	}
		
	public PriorityGraph() {		
	}
	
	// public void addEdge(Edge edge) {
	// 	edges.add(edge);
	// }
	
	public void addEdge(Action source, Action target) {
		Vertex sourceVertex = findVertex(source);
		Vertex targetVertex = findVertex(target);
		new Edge(sourceVertex, targetVertex);
	}
	
	// public Set<Edge> getEdges() {
	// 	return this.edges;
	// }

	public Set<Vertex> getVertices() {
		return this.vertices;
	}
	
	private Vertex findVertex(Action a) {
		for (Vertex v : vertices) {
			if (v.getAction() == a) {
				return v;
			}
		}
		return null;
	}
	
	public Vertex addVertex(Action a) {
		if (findVertex(a) != null) {
			return findVertex(a);
		}
		Vertex v = new Vertex(a);
		vertices.add(v);
		return v; 
	}	
	
	private void addVertex(Vertex v) {
		vertices.add(v);		
	}
		
	/*   
	 *  Returns a projection of the graph containing only  
	 *  actions also found in the argument set 'a'.
	 */
	public PriorityGraph getSubgraph(Set<Action> a)  {
		PriorityGraph subgraph = new PriorityGraph();
				
		for (Vertex v : vertices) {
			Vertex v1 = subgraph.addVertex(v.getAction());
			if (!a.contains(v.getAction())) {
				v1.mark();
			}
		}
		
		for (Vertex v : vertices) {
			Vertex source = subgraph.findVertex(v.getAction());
			for (Edge e : v.getOutgoingEdges()) {
				Vertex target = subgraph.findVertex(e.getTarget().getAction());
				new Edge(source, target);
			}
		}
						
		for (Vertex v : subgraph.getVertices()) {
			// System.out.println("delete vertex: '" + v.getAction().getId() + "'");
			if (v.isMarked()) {
				for (Edge incoming : v.getIncomingEdges()) {
					for (Edge outgoing : v.getOutgoingEdges()) {
						Vertex source = incoming.getSource();
						// System.out.println("  " + source.getAction().getId());
						Vertex target = outgoing.getTarget();
						// System.out.println("  " + target.getAction().getId());												
						new Edge(source, target);
					}					
				}
				for (Edge incoming : v.getIncomingEdges()) {
					incoming.getSource().removeOutgoingEdge(incoming);
				}
				for (Edge outgoing : v.getOutgoingEdges()) {
					outgoing.getTarget().removeIncomingEdge(outgoing);
				}
			}
 		}

		PriorityGraph result = new PriorityGraph();
		for (Vertex v : subgraph.getVertices()) {
			if (!v.isMarked()) {
				result.addVertex(v);
			}
		}
		return result;
	}
			
	public List<AstPriority> getPriority() {
		return this.priorities;
	}
	
	public List<Action> getActions() {
		Set<Vertex> vertices = this.getVertices();
		List<Action> actions = new ArrayList<Action>();	
		for (Vertex v : vertices) {
			actions.add(v.getAction());
		}
		return actions;
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
	
	public List<Action> getOneTopologicalOrder() {
		List<Vertex> reverseOrder = new ArrayList<Vertex>();
		List<Action> result = new ArrayList<Action>();
		// Reverse post order sorting
		
		for (Vertex root : getRoots()) {
			DFS(root, reverseOrder);
		}
		for (int i = reverseOrder.size(); i > 0; i--) {
			result.add(reverseOrder.get(i-1).getAction());
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
	
	public void print(PrintStream  out) {
		out.println("diagraph G {");
		for (Vertex v : vertices) {
			out.println("  " + v.getAction().getId() + ";");
		}

		for (Vertex v : vertices) {
			for (Edge e : v.getOutgoingEdges()) {
				out.println("  " + v.getAction().getId() + " -> " + e.getTarget().getAction().getId() + ";");				
			}
		}

		out.println("}");
	}
}
