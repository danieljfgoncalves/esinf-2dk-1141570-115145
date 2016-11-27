package model;

import graphs.map.Edge;
import graphs.map.MapGraph;
import graphs.map.MapGraphAlgorithms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
    public FriendshipMap(MapGraph graph) {
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
    public MapGraph getGraph() {
        return mapGraph;
    }

    /**
     * Sets the Graph that contains all friendship.
     *
     * @param graph the mapGraph to set
     */
    public void setGraph(MapGraph mapGraph) {
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
    // **** 3 c)     ****//
    /**
     * Obtains the relationship distance between 2 users
     *
     * @param user1 user one
     * @param user2 user two
     *
     * @return the relationship distance between 2 users, if -1 it means no
     * existence of a relationship
     */
    public int getRelationshipDistance(User user1, User user2) {

        LinkedList<User> relation = new LinkedList<>();
        MapGraphAlgorithms.shortestPath(this.mapGraph, user1, user2, relation);

        // if user1--user2--user3  connection between user1--user3 = 1 it gets 3-2=1
        // if user1--user2  connection between user1--user2 = 0 it gets 2-2=0
        // if user1!(--)user2  no connection between user1--user2 = -1
        return (relation.size() < 2) ? -1 : relation.size() - 2;
    }

     // **** 2nd PART **** //
    // **** 3 b)     ****//
    /**
     * Find the users with a relationship distance from another user on the
     * social network less than a certain distance.
     *
     * @param source the source user
     * @param distance relationship distance (-1: no relation, 0: direct
     * relation, n: n(th) relation degree)
     *
     * @return the users in the relation distance range (empty list if no
     * friends)
     */
    public Iterable<User> findRelatedUser(User source, int distance) {
        if (distance == 0) {
            return this.getFriends(source);
        }

        HashSet<User> users = new HashSet<>();

        boolean hasFriends = this.getFriends(source).iterator().hasNext();
        if (hasFriends) { // If source doesn't have friends no relation is possible

            for (User user : this.mapGraph.vertices()) {
                if (!user.equals(source)) {

                    int tmp = getRelationshipDistance(source, user);

                    if (tmp >= 0 && tmp <= distance) {
                        users.add(user);
                    }
                }
            }
        }

        return users;
    }

}
