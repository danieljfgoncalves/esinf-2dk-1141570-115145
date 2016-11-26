package graphs.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Map Graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 * 
 * @param <V> the vertex
 * @param <E> the edge
 */
public class MapGraph<V,E> implements MapGraphInterface<V,E> {

    
    /**
     * MapGraph's number of vertices
     */
    private int numVert;
    
    /**
     * MapGraph's number of edges
     */
    private int numEdge;
    
    /**
     * MapGraph's direction
     */
    private boolean isDirected;
    
    /**
     * MapGraph's vertices Map (all vertices of the MapGraph)
     */
    private Map<V,Vertex<V,E>> vertices; 
    
    /**
     * Builds up an instance of MapGraph (empty MapGraph either undirected or directed) with parameters by default
     * 
     * @param directed the boolean if true is directed, otherwise undirected
     */
    public MapGraph(boolean directed) { 
        this.numVert = 0; 
        this.numEdge = 0;
        this.isDirected = directed;
        this.vertices = new TreeMap<>();
    }
       
    @Override
    public int numVertices(){
        return this.numVert; 
    }
      
    @Override
    public Iterable<V> vertices() {
        return this.vertices.keySet();
    }
    
    /**
     * Obtains true if this map (vertices) contains a mapping for the specified vertex (vert) (its key), false otherwise
     * 
     * @param vert the vertex to check if it exists in the vertices(TreeMap)
     * @return true if this map (vertices) contains a mapping for the specified vertex (vert) (its key), false otherwise
     */
    public boolean validVertex(V vert) { 
        
        if (this.vertices.get(vert) == null)
            return false; 
        
        return true;
    }
    
    /**
     * Obtains the vertex key
     * 
     * @param vert the vertex to obtain its key
     * @return the vertex key
     */
    public int getKey(V vert) {
        return this.vertices.get(vert).getKey();
    }
    
    /**
     * Obtains all keys from the vertices 
     * 
     * @return all keys from the vertices 
     */
    public V[] allkeyVerts() {
        
        V[] keyVerts = (V[]) new Object[this.numVert];
        
        for (Vertex<V,E> vert : this.vertices.values())
            keyVerts[vert.getKey()]=vert.getElement();
        
        return keyVerts;
    }
    
    @Override
    public int numEdges(){
        return this.numEdge;
    }
    
    @Override
    public Iterable<Edge<V,E>> edges() { 
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Edge<V,E> getEdge(V vOrig, V vDest){
       
        if (!validVertex(vOrig) || !validVertex(vDest)) 
           return null;
        
        Vertex<V,E> vorig = this.vertices.get(vOrig);
        
        return vorig.getEdge(vDest);
    }
    
    @Override
    public V[] endVertices(Edge<V,E> edge){ 
        
        if (edge == null)
           return null;
        
        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest())) 
           return null;
          
        Vertex<V,E> vOrig = this.vertices.get(edge.getVOrig());
        
        if (!edge.equals(vOrig.getEdge(edge.getVDest())))
            return null;
        
        return edge.getEndpoints();
    }
    
    @Override
    public V opposite(V vert, Edge<V,E> edge){
        
        if (!validVertex(vert)) 
           return null;
        
        Vertex<V,E> vertex = this.vertices.get(vert); 
      
        return vertex.getAdjVert(edge);
    }
    
    @Override
    public int outDegree(V vert){
 
        if (!validVertex(vert)) 
           return -1;
        
        Vertex<V,E> vertex = this.vertices.get(vert);  
        
        return vertex.numAdjVerts();
    }
     
    @Override
    public int inDegree(V vert){
         
        if (!validVertex(vert)) 
           return -1;
                
        int degree=0;       
        for (V otherVert : this.vertices.keySet()) 
            if (getEdge(otherVert,vert) != null)
                degree++;
             
        return degree;
    }
        
    @Override
    public Iterable<Edge<V,E>> outgoingEdges(V vert){
 
        if (!validVertex(vert)) 
            return null;
        
        Vertex<V,E> vertex = vertices.get(vert);           
        
        return vertex.getAllOutEdges();
    }
    
    @Override
    public Iterable<Edge<V,E>> incomingEdges(V vert){
 
        throw new UnsupportedOperationException("Not supported yet.");
    }
            
    @Override
    public boolean insertVertex(V vert){
         
        if (validVertex(vert)) 
            return false;
        
        Vertex<V,E> vertex = new Vertex<>(this.numVert,vert);
        this.vertices.put(vert,vertex);
        this.numVert++;
        
        return true;
    }
    
    @Override
    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight){
        
        if (getEdge(vOrig,vDest) != null)
            return false;
        
        if (!validVertex(vOrig))
           insertVertex(vOrig);
        
        if (!validVertex(vDest))
           insertVertex(vDest);
        
        Vertex<V,E> vorig = this.vertices.get(vOrig);
        Vertex<V,E> vdest = this.vertices.get(vDest);
        
        Edge<V,E> newEdge = new Edge<>(eInf,eWeight,vorig,vdest);
        vorig.addAdjVert(vDest,newEdge);
        this.numEdge++;
             
        //if graph is not direct insert other edge in the opposite direction 
        if (!this.isDirected) 
            // if vDest different vOrig
            if (getEdge(vDest,vOrig) == null){
                Edge<V,E> otherEdge = new Edge<>(eInf,eWeight,vdest,vorig);
                vdest.addAdjVert(vOrig,otherEdge);
                this.numEdge++;
            }     
         
        return true ;
    }
    
    @Override
    public boolean removeVertex(V vert){
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean removeEdge(V vOrig, V vDest) {
        
        if (!validVertex(vOrig) || !validVertex(vDest)) 
           return false;
       
        Edge<V,E> edge = getEdge(vOrig,vDest);
        
        if (edge == null)
          return false;
        
        Vertex<V,E> vorig = this.vertices.get(vOrig);
    
        vorig.remAdjVert(vDest);
        this.numEdge--;
        
        //if graph is not direct 
        if (!this.isDirected){ 
            edge = getEdge(vDest,vOrig);
            if (edge != null){
               Vertex<V,E> vdest = this.vertices.get(vDest);
               vdest.remAdjVert(vOrig);
               this.numEdge--;
            }
        }
        return true;
    }
            
    @Override
    public MapGraph<V,E> clone() {
        
        MapGraph<V,E> newObject = new MapGraph<>(this.isDirected);
        
        //insert all vertices
        for (V vert : this.vertices.keySet())   
            newObject.insertVertex(vert);
        
        //insert all edges
        for (V vert1 : this.vertices.keySet()) 
            for (Edge<V,E> e : this.outgoingEdges(vert1))  
                if (e != null){
                   V vert2=this.opposite(vert1,e);
                   newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }
        
        return newObject;
    }

    
    @Override
    public boolean equals(Object otherObj) {

        if (otherObj == null) return false;

        if (this == otherObj) return true;

        if (!(otherObj instanceof MapGraph<?,?>))
            return false;

        MapGraph<V,E> otherGraph = (MapGraph<V,E>) otherObj;
        
        if (this.numVert != otherGraph.numVertices() || this.numEdge != otherGraph.numEdges()) 
            return false;

        //graph must have same vertices
        boolean eqvertex;
        for (V v1 : this.vertices()){
            eqvertex=false;
            for (V v2 : otherGraph.vertices())
                if (v1.equals(v2))
                   eqvertex=true;
            
            if (!eqvertex)
              return false;
        }
        
         //graph must have same edges
        boolean eqedge;
        for (Edge<V,E> e1 : this.edges()){
            eqedge=false;
            for (Edge<V,E> e2 : otherGraph.edges())
                if (e1.equals(e2))
                    eqedge=true;
            
            if (!eqedge)
              return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String s="" ;
        if (this.numVert == 0) {
            s = "\nGraph not defined!!";
        } 
        else {
            s = "Graph: "+ this.numVert + " vertices, " + this.numEdge + " edges\n";
            for (Vertex<V,E> vert : this.vertices.values()) 
                s += vert + "\n" ;
        }
        return s ;
    }
}
