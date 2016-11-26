package graphs.map;

/**
 * Represents a Map Graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 * 
 * @param <V> the vertex
 * @param <E> the edge
 */
public interface MapGraphInterface <V,E> {
  
  /**
   * Obtains the number of vertices of the graph 
   * 
   * @return the number of vertices of the graph 
   */
  int numVertices();
  
  /**
   * Obtains all the vertices of the graph as an iterable collection  
   * 
   * @return all the vertices of the graph as an iterable collection  
   */
  Iterable<V> vertices();
  
  /**
   * Obtains the number of the edges of the graph 
   * 
   * @return the number of the edges of the graph 
   */
  int numEdges();

  /**
   * Obtains the information of all the edges of the graph as an iterable collection
   * 
   * @return the information of all the edges of the graph as an iterable collection
   */
  Iterable<Edge<V,E>> edges();
   
  /** 
   * Obtains the edge from vOrig to vDest, or null if vertices are not adjacent
   * 
   * @param vOrig the origin vertex
   * @param vDest the destination vertex
   * 
   * @return the edge or null if vertices are not adjacent or don't exist 
   */
   Edge<V,E> getEdge(V vOrig, V vDest);

  /**
   * Obtains the vertices of edge as an array of length two 
   * If the graph is directed, the first vertex is the origin, and
   * the second is the destination.  If the graph is undirected, the
   * order is arbitrary.
   * 
   * @param edge
   * 
   * @return array of two vertices or null if edge doesn't exist 
   */
   V[] endVertices(Edge<V,E> edge); 
  
  /**
   * Obtains the vertex that is opposite vertex on edge, or null if vertex or edge doesn't exist.
   * 
   * @param vert the vertex
   * @param edge the edge
   * 
   * @return the vertex that is opposite vertex on edge, or null if vertex or edge doesn't exist.
   */
   V opposite(V vert, Edge<V,E> edge);
   
  /**
   * Obtains the number of edges leaving vertex,
   * for an undirected graph, this is the same result returned by inDegree
   * 
   * @param vert the vertex
   * 
   * @return the number of edges leaving vertex, -1 if vertex doesn't exist  
   */
  int outDegree(V vert) ;
  
  /**
   * Obtains the number of edges for which vertex is the destination, 
   * for an undirected graph, this is the same result returned by outDegree
   * 
   * @param vert the destination vertex
   * 
   * @return the number of the edges leaving vertex, -1 if vertex doesn't exist  
   */
  int inDegree(V vert) ;
  
  /**
  * Obtains an iterable collection of edges for which vertex is the origin 
  * for an undirected graph, this is the same result returned by incomingEdges
  * 
  * @param vert the origin vertex
  * 
  * @return the iterable collection of edges, null if vertex doesn't exist
  */
  Iterable<Edge<V,E>> outgoingEdges (V vert);
  
 /**
  * Obtains an iterable collection of edges for which vertex is the destination,
  * for an undirected graph this is the same result as returned by outgoingEdges
  * 
  * @param vert the destination vertex
  * 
  * @return iterable collection of edges reaching vertex, null if vertex doesn't exist
  */        
  Iterable<Edge<V,E>> incomingEdges(V vert);
  
  /** 
   * Inserts a new vertex with some specific comparable type
   * 
   * @param newVert the new vertex contents to insert
   * 
   * @return true if insertion succeeds, false otherwise
   */
   boolean insertVertex(V newVert);

  /**
   * Adds a new edge between vertices vOrig and vDest, with some 
   * specific comparable type. If vertices vOrig, vDest don't exist in the graph they  
   * are inserted  
   * 
   * @param vOrig the origin vertex
   * @param vDest the destination vertex
   * @param edge the edge
   * @param eWeight the edge weight
   * 
   * @return true if succeeds(if it adds), or false if an edge already exists between the two vertices.
   */
   boolean insertEdge(V vOrig, V vDest, E edge, double eWeight);
   
   
  /**
  * Removes a vertex and all its incident edges from the graph 
  * 
  * @param vert the vertex to remove
  * 
  * @return true if vertex is removed, false otherwise
  */
  boolean removeVertex(V vert);

 /**
  * Removes the edge between two vertices 
  *  
  * @param vOrig the origin vertex
  * @param vDest the destination vertex
  * 
  * @return true if edge is removed, false otherwise
  */  
  boolean removeEdge(V vOrig, V vDest);
  
}