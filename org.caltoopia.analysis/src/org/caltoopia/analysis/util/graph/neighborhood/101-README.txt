util.graph.neighborhood
=======================
The package implements undirected and directed graphs based on adjacency sets (LinkedHashSets containing adjacent vertices).
The idea is to combine the good properties of adjacency list and adjacency matrix representation: fast tests of the presence 
of neighbors (and incident edges) and fast enumeration of neighbors (and incident edges). Additionally, the order of enumeration 
is stable (since the "linked" version of HashSet is used). The down-side of this representation is the larger memory footprint than 
using adjacency lists (but -unlike adjacency matrices- the size is still linear in the number of vertices and edges).
 
1 Structure

1.1 Main API

NeighborhoodMapGraph    Implementation of LabeledGraph (and thus also Graph) using LinkedHashSets for the adjacency relation.
NeighborhoodMapDiGraph  Implementation of LabeledDiGraph (and thus also DiGraph) using LinkedHashSets for the adjacency relation.

Both implementations share a common base class, called AbstractGenericGraph, and leverages on a NeighborhoodMap plug-in.

1.2 Adjacency
NeighborhoodMap         The plug-in that manages the adjacency relation; it is essentially a Map from vertices to Neighborhood 
                        objects. A Neighborhood provides the set of adjacent vertices and maintains vertex- and edge labels
                        (if any). There are several implementations of the NeighborhoodMap interface with different internal
                        representation of adjacency sets and labels:                         
                        + UnlabeledNeighborhoodMap
                        + VertexLabeledNeighborhoodMap
                        + LabeledNeighborhoodMap (both vertex- and edge labeled)
                        + AbstractNeigborhoodMap (common base class)
                        
Neighborhood            The piece of information that is associated with a vertex: the adjacent vertices and possible labels.
                        There are several implementations of the Neighborhood interface, one for each implementation of 
                        NeighborhoodMap.

2. How to...

2.1 How to create a NeigborhoodMapGraph (NeighborhoodMapDiGraph)

A NeigborhoodMapGraph (NeighborhoodMapDiGraph) is parameterized with respect to the type of vertex, vertex label and edge label.
An appropriate NeighborhoodMap -with the same parameterization- is needed in order to construct a NeigborhoodMapGraph 
(NeighborhoodMapDiGraph). Examples:

  NeighborhoodMap<V,VL,EL> nmap=new LabeledNeighborhoodMap<V,VL,EL>();
  LabeledGraph<V,VL,EL> g1=new NeighborhoodGraph(nmap); 
  
creates a vertex- and edge labeled graph, g1. The following creates an unlabeled directed graph, g2.

  DiGraph<V> g2=new NeighborhoodDiGraph(new UnlabeledNeighborhoodMap<V>());
  
The util.graph.Graphs utility class also provides methods that constructs different "flavors" of graphs.


3. Implementation

3.1 Orientation of edges

The NeighborhoodMap stores sets of adjacent vertices. In this implementation of Graph and DiGraph, the Edges are actually not
stored anywhere. Instead, they are constructed on demand, namely when iterating over sets of edges (all edges or edges incident 
to a vertex). Since the internal representation, that of the NeighborhoodMap, is the same for both undirected and directed graphs,
it is thus true to say that the internal representation of all edges (including undirected ones) has some (possibly arbitrary) 
orientation.

The implementation of undirected and directed graphs differ in how edges with opposite orientation are handled. In an undirected
graph (u,v) and (v,u) denote the same edge whereas, in a directed graph, they would be distinct. The explicit Edge objects,
which are constructed when iterating over edge sets, differ w.r.t. identity (how equals and hashCode are implemented). 
 
In a Neighborhood object, the orientation is specified by maintaining separate "forward" and "reverse" sets of adjacent vertices.
For a directed graph, this readily corresponds to the set of successors and predecessors, respectively, whereas in an undirected
graph the orientation may be arbitrary. However, the implementation is based a unique and well-defined orientation of undirected
edges: v is in the "forward" set of u <==> u is in the "reverse" set of v <==> v is *not* in the "reverse" set of u <==> ...
As long as the NeighborhoodMapGraph is the only client that modifies the NeighborhoodMap, a consistent adjacency relation is
guaranteed. It does so by checking for the presence of an edge with reverse orientation when adding and removing edges.
