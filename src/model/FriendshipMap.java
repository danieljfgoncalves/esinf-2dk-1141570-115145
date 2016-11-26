package model;

import graphs.map.MapGraph;

/**
 * Represents a map graph of friendships.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class FriendshipMap {
    
    /**
     * Graph that contains all friendship.
     */
    private MapGraph graph;

    /**
     * Constructs a empty friendship map graph.
     * 
     * @param direction
     */
    public FriendshipMap(boolean direction) {
        this.graph = new MapGraph(false);
    }
    
    /**
     * Constructs a friendship map graph receiving the graph 
     * 
     * @param graph graph with friendships
     */
    public FriendshipMap(MapGraph graph) {
        this.graph = graph;
    }
    
    /**
     * Copy constructor of a friendship map graph
     * 
     * @param friendshipMap  friendship map to copy
     */
    public FriendshipMap(FriendshipMap friendshipMap) {
        this.graph = friendshipMap.graph;
    }

    /**
     * Obtains the Graph that contains all friendship.
     * @return the graph
     */
    public MapGraph getGraph() {
        return graph;
    }

    /**
     * Sets the Graph that contains all friendship.
     * @param graph the graph to set
     */
    public void setGraph(MapGraph graph) {
        this.graph = graph;
    }
    
}
