package graphs.map;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Represents a vertex of a map graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 * 
 * @param <V> the vertex
 * @param <E> the edge
 */
public class Vertex <V, E> {
    
    /**
     * Vertex key number.
     */
    private int key ;
    
    /**
     * Vertex information.
     */
    private V  element ; 

    /**
     *  Vertex adjacent vertices.
     */
    private Map< V, Edge<V,E> > outVerts;
     
    /**
     * Builds up an instance of Vertex with parameters by default
     */
    public Vertex () { 
        this.key = -1;
        this.element = null; 
        this.outVerts = new TreeMap<>();
    } 
    
     /**
     * Builds up an instance of Vertex with parameters key and information
     *
     * @param key Vertex key
     * @param vInf Vertex information
     */
    public Vertex (int key, V vInf) {
        this.key = key;
        this.element = vInf;
        this.outVerts = new TreeMap<>();
    } 
  
   /**
     * Obtain the Vertex key
     *
     * @return the Vertex key
     */
    public int getKey() {
        return this.key;
    }
    
    /**
     * Modifies the Vertex key
     *
     * @param key the Vertex key to set
     */
    public void setKey(int key) {
        this.key = key; 
    }	
    
    /**
     * Obtain the Vertex element(Vertex information)
     *
     * @return the Vertex element (Vertex information)
     */
    public V getElement() { 
        return this.element;
    }
    
    /**
     * Modifies the Vertex element (Vertex information)
     *
     * @param vInf the Vertex information to set
     */
    public void setElement(V vInf) {
        this.element = vInf;
    }		

    /**
     * Obtain all the adjacent vertices of the Vertex
     *
     * @return the adjacent vertices of the Vertex
     */
    public Iterable<V> getAllAdjVerts() {
        return this.outVerts.keySet(); 
    }
    
    /**
     * Obtain all the out edges of the Vertex
     *
     * @return all the out edges of the Vertex
     */
    public Iterable<Edge<V,E>> getAllOutEdges() { 
        return this.outVerts.values();
    }
    
    /**
     * Obtain the edge of the adjacent vertex
     *
     * @param vAdj the adjacent vertex
     * 
     * @return the adjacent vertex edge
     */
     public Edge<V,E> getEdge(V vAdj){
         return this.outVerts.get(vAdj);
     }
    
    /**
     * Obtain the adjacent vertex of the edge
     *
     * @param edge the edge
     * 
     * @return the adjacent vertices
     */
    public V getAdjVert(Edge<V,E> edge){ 
        
        for (V vert : this.outVerts.keySet())
            if (edge.equals(this.outVerts.get(vert)))
                return vert; 
        
        return null;
    }
    
    /**
     * Adds an adjacent vertex
     * 
     * @param vAdj the adjacent vertex to add
     * @param edge the edge to add
     */
    public void addAdjVert(V vAdj, Edge<V,E> edge){
        this.outVerts.put(vAdj, edge);
    }
    
    /**
     * Removes the adjacent vertex
     * 
     * @param vAdj removes the adjacent vertex
     */
    public void remAdjVert(V vAdj){
        this.outVerts.remove(vAdj);
    } 
    
    /**
     * Obtains the number of adjacent vertices
     * 
     * @return the number of adjacent vertices
     */
    public int numAdjVerts() {
        return this.outVerts.size();
    }
         
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj){
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }
        Vertex<V,E> otherVertex = (Vertex<V,E>) otherObj;
        
        return (this.key == otherVertex.key && 
                this.element != null &&
                otherVertex.element != null   && 
                this.element.equals(otherVertex.element));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.key;
        hash = 97 * hash + Objects.hashCode(this.element);
        hash = 97 * hash + Objects.hashCode(this.outVerts);
        return hash;
    }
     
    
    @Override
    public Vertex<V,E> clone() {
        
        Vertex<V,E> newVertex = new Vertex<>();
        
        newVertex.key = this.key;
        newVertex.element = this.element;
        
        Map<V, Edge<V,E>> newMap = new TreeMap<>();  
        
        for (V vert : outVerts.keySet())
            newVertex.outVerts.put(vert, outVerts.get(vert));
            
        return newVertex;
    }
    
    @Override
    public String toString() {
        String st="";
        if (this.element != null)
           st= this.element + " (" + this.key + "): \n";
           if (!this.outVerts.isEmpty())
              for (V vert : this.outVerts.keySet()) 
                 st += this.outVerts.get(vert);
        
        return st; 
    }   

}
