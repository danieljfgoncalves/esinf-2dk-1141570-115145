package model;

import graphs.map.Edge;
import graphs.map.MapGraph;
import graphs.map.MapGraphAlgorithms;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

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

    // **** 2nd PART **** //
    // **** 3 f)     ****//
    /**
     * Return the most influential network users.
     *
     * @return the most influential network users
     */
    public Iterable<User> mostInfluentialNetworkUsers() {

        HashSet influentialUsers = new HashSet();
        // Verify if graph has at least one connection
        if (this.mapGraph.numEdges() < 1) {
            return influentialUsers;
        }

        // Discover centrality of each user
        HashMap<User, Integer> centrality = new HashMap<>();
        Iterable<User> users = this.mapGraph.vertices();

        for (User source : users) {

            int maxLength = 0;
            for (User dest : users) {

                if (!source.equals(dest)) {

                    LinkedList<User> relation = new LinkedList();
                    MapGraphAlgorithms.shortestPath(mapGraph, source, dest, relation);

                    maxLength = (maxLength > relation.size() - 1) ? maxLength : relation.size() - 1;
                }
            }

            centrality.put(source, maxLength);
        }

        // Order by centrality (Ascending order)
        TreeMap<User, Integer> sortedCent = new TreeMap(new Comparator<User>() {

            @Override
            public int compare(User u1, User u2) {

                int cent1 = centrality.get(u1);
                int cent2 = centrality.get(u2);

                // Tiebreak is the user's name (needed to guarantee no key is equal).
                int compareName = u1.compareTo(u2);

                return cent1 == cent2 ? compareName : cent1 > cent2 ? 1 : -1;
            }
        }); // Ascending order
        sortedCent.putAll(centrality); // Order by centrality

        // Add to list the one(s) with least centrality (> radius)
        Iterator<User> userIt = sortedCent.keySet().iterator();
        Iterator<Integer> centIt = sortedCent.values().iterator();

        influentialUsers.add(userIt.next());
        Integer max = centIt.next();
        while (centIt.hasNext() && max == centIt.next()) {

            influentialUsers.add(userIt.next());
        }

        return influentialUsers;
    }
}
