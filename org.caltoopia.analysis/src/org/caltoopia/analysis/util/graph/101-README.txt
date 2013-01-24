util.graph
=============
The package implements graphs (as in vertices connected by edges). Undirected and directed graphs, vertex labeled and 
edge labeled graphs, multigraphs are all supported. Graph traversals and decomposition algorithms such as acyclic 
condensation are provided.
 
1 Structure

1.1 Main API

Graph           Base interface of (undirected) graphs; parameterized in the vertex type.
DiGraph         Base interface of directed graphs; parameterized in the vertex type.
MultiGraph      Base interface of an (undirected) multigraph as an edge labeled graph.
MultiDiGraph    Base interface of a directed multigraph as an edge labeled directed graph.
Graphs          An assortment of utilities and graph algorithms. Intended as the analog of java.util.Collections.


1.2 Edges

Edge            Common interface of all edge types: directed and undirected edges as well as edges of multigraphs,
                which may have identity (and thus allowing for any number of parallel edges between two vertices).

1.3 Graph labeling

LabeledGraph        Sub interface of Graph, which allows for vertex and edge labels.
LabeledDiGraph      Sub interface of DiGraph, which allows for vertex and edge labels.
LabeledMultiDiGraph Sub interface of MultiGraph, which allows for vertex labels.


1.4 Implementations

NeighborhoodMapDiGraph  Implementation of LabeledDiGraph (and thus also DiGraph) using LinkedHashSets for the
                        adjacency relation. This is the class used by Graphs.createDiGraph().
MultiDiGraphAdaptor     Implementation of LabeledMultiDiGraph (and thus also MultiDiGraph) using an edge-labeled
                        DiGraph. Edge labels are the sets of parallel edges, which must implement GenericEdge.

                     
1.5 AcyclicCondensation

AcyclicCondensation  Represents decomposition of a directed graph into its strongly connected components (SCCs).
                     The acyclic condensation is itself a directed (acyclic) graph, whose vertices are the SCCs.
                     The Graphs class provides two variants of the decomposition, which use BasicSCC and FancySCC,
                     respectively.
BasicScc             The simplest form of SCCs, just providing the subgraphs of the original, decomposed graph.
FancyScc             A more elaborate form of SCC, which in addition provides a decomposition of each SCC into
                     a spanning tree and elementary circuits.


2. How to...

2.1 How to construct graphs

Basic pattern (undirected graph) is

  Graph<V> g = Graphs.createDiGraph(); 
  
or (directed graph)

  DiGraph<V> g = Graphs.createDiGraph();
  
In both cases V is the type of the vertices. Labeled graphs and multigraphs are constructed similarly. 
The underlying implementation is NeighborhoodMapGraph (NeighborhoodMapDiGraph), which represents adjacent vertices using 
LinkedHashSets. In this way, the good properties of adjacency list and adjacency matrix representation 
are combined: fast tests of the presence of neighbors (and incident edges) and fast enumeration of neighbors 
(and incident edges). Additionally, the order of enumeration is stable (since the "linked" version of 
HashSet is used). The down-side of this representation is the larger memory footprint than using 
adjacency lists (but it is still linear in the number of vertices and edges).


2.2 How to implement custom Graphs (DiGraphs)

This is achieved simply by implementing the Graph (DiGraph) interface. In addition to an implementation from scratch, 
it is possible to either leverage on AbstractGraph (AbstractDiGraph) or NeighborhoodMaps.


2.2.1 Implementing custom Graphs (DiGraphs) using AbstractGraph (AbstractDiGraph)

AbstractGraph (AbstractDiGraph) is provided as a convenient way of doing so. For an unmodifiable graph it is sufficient
to implement four methods: getVertices(), getPredecessors(v), getSuccessors(v) and containsEdge(u,v).

For a modifiable graph, the set returned by getVertices() needs to be modifiable; further the methods addEdge(u,v)
and removeEdge(u,v) are needed. To allow for removal of adjacent vertices (via the sets getPredecessors(v) and 
getSuccessors(v)) and incident edges (getIncident(v), getIncidentFrom(v), getIncidentTo(v)) via iterators two
additional methods, removePredecessor and removeSuccessor are required. Unless these six methods are overridden,
they throw an UnsupportedOperationException.

To also support edge and vertex labeling, the following methods need to be overridden: getVertexLabel(v),
setVertexLabel(v,label), getEdgeLabel(u,v) and setEdgeLabel(u,v,label). If not, the getters return null and the
setters throw an UnsupportedOperationException.


2.2.2 Implementing custom Graphs (DiGraphs) using NeighborhoodMaps

A NeighborhoodMap is the plug-in of a NeighborhoodMapGraph (NeighborhoodMapDiGraph), that deals with the
adjacency relation of the graph: it specifies the pairs of vertices that are connected by edges.

By implementing a NeighborhoodMap that reflects the adjacency in the custom Graph, the NeighborhoodMapGraph 
(NeighborhoodMapDiGraph) does the foot work of implementing the LabeledGraph (LabeledDiGraph) -of course including their
base interfaces (Graph and DiGraph, respectively).

The NeighborhoodMap interface can be implemented using a Map from vertices to Neighborhoods or another means of implementing
the get(), remove() and keySet() methods of a Map. In addition an add() method, which creates a Neighborhood and associates
it with a vertex, is required.

The Neighborhood interface specifies the objects returned by NeighborhoodMap.get(): it specifies the adjacent vertices
(neighbors) of a vertex and provides setters and getters of vertex- and edge labels of labeled graphs.   


2.3 How to work with MultiGraphs (MultiDiGraphs)

Edges of MultiGraphs are assumed to have identity. This is achieved by implementing the Edge interface
and paying particular attention to the equals() and hashCode() methods:
a) For (undirected) MultiGraphs, the two end points forms an unordered pair: (u,v) and (v,u)
   denote the same pair (this is precisely what the Edge class is about). 
   Additional "identity" allows for multiple edges between a single pair of vertices. The "identity" is specified 
   using the equals() method and hashCode() should be consistent (same hash code for equal edges). A possible 
   implementation is that each edge is unique (in which case the methods of Object can be leveraged without overriding them).
b) For (undirected) MultiGraphs, the two end points forms an ordered pair: (u,v) and (v,u)
   denote distinct pairs (if u!=v). 
   Additional "identity" allows for multiple edges between a single pair of vertices. The "identity" is specified 
   using the equals() method and hashCode() should be consistent (same hash code for equal edges). A possible 
   implementation is that each edge is unique (in which case the methods of Object can be leveraged without overriding them).


2.4 Algorithms provided by the Graphs class

a) Creation of a special representation for "trivial" graphs, which consist of a single vertex and no edges.
b) The transpose view of a directed graph (the graph in which each edge gets the opposite orientation)
c) Traversals of the vertices in a graph: preorder, postorder, reverse postorder (rPOSTORDER) and (for directed graphs only) 
   reverse inverse postorder.
d) Construction of the subgraph induced by (1) a subset of vertices or (2) a subset of edges. 
e) Decomposition of a directed graph into its strongly connected components (the acyclic condensation of the graph).
f) Decomposition of an (undirected) graph into its biconnected components.
g) Tree decomposition of a graph (into a spanning tree and a collection of chords/fundamental cycles).
 