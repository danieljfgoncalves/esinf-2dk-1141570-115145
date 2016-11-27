package model;

import graphs.map.Edge;
import graphs.map.MapGraph;
import java.util.HashSet;

/**
 * Represents a map mapGraph of friendships.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class FriendshipMap {

    /**
     * Graph that contains all friendship.
     */
    private MapGraph<User, Integer> mapGraph;

    /**
     * Constructs a empty friendship map mapGraph.
     *
     * @param direction
     */
    public FriendshipMap(boolean direction) {
        this.mapGraph = new MapGraph(false);
    }

    /**
     * Constructs a friendship map mapGraph receiving the mapGraph
     *
     * @param graph mapGraph with friendships
     */
    public FriendshipMap(MapGraph<User, Integer> graph) {
        this.mapGraph = graph;
    }

    /**
     * Copy constructor of a friendship map mapGraph
     *
     * @param friendshipMap friendship map to copy
     */
    public FriendshipMap(FriendshipMap friendshipMap) {
        this.mapGraph = friendshipMap.mapGraph;
    }

    /**
     * Obtains the Graph that contains all friendship.
     *
     * @return the mapGraph
     */
    public MapGraph<User, Integer> getGraph() {
        return mapGraph;
    }

    /**
     * Sets the Graph that contains all friendship.
     *
     * @param graph the mapGraph to set
     */
    public void setGraph(MapGraph<User, Integer> mapGraph) {
        this.mapGraph = mapGraph;
    }

    /**
     * Obtains all the friends from a user passed as parameter
     *
     * @param user the user to find all friends
     *
     * @return all the friends from a user passed as parameter
     */
    public Iterable<User> getFriends(User user) {

        HashSet<User> friends = new HashSet<>();
        Iterable<Edge<User, Integer>> outEdges = this.mapGraph.outgoingEdges(user);
        for (Edge<User, Integer> outEdge : outEdges) {
            friends.add(outEdge.getVDest());
        }
        return friends;
    }

}
