package model;

import graphs.map.MapGraph;
import graphs.matrix.MatrixGraph;
import graphs.matrix.MatrixGraphAlgorithms;
import graphs.matrix.WeightedMatrixGraphAlgorithms;
import java.util.ArrayList;
import java.util.Collections;
import utils.Algorithms;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import javafx.util.Pair;

/**
 * Represents a SocialNetwork
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class SocialNetwork {
    //INSTANCE ATTRIBUTES

    /**
     * SocialNetwork's users list
     */
    private HashSet<User> usersList;

    /**
     * SocialNetwork's cities list
     */
    private HashSet<City> citiesList;

    /**
     * Social Network cities Graph
     */
    private CitiesMatrix citiesMatrix;

    /**
     * Social Network friendships MapGraph
     */
    private FriendshipMap friendshipMap;

    //CONSTRUCTORS
    /**
     * builds up an instance of SocialNetwork with parameters by default
     */
    public SocialNetwork() {
        this.usersList = new HashSet<>();
        this.citiesList = new HashSet<>();
        this.citiesMatrix = new CitiesMatrix();
        this.friendshipMap = new FriendshipMap(false);
    }

    /**
     * builds up an instance of SocialNetwork from another instance of
     * SocialNetwork
     *
     * @param otherSN SocialNetwork to copy
     */
    public SocialNetwork(SocialNetwork otherSN) {
        this.usersList = new HashSet<>(otherSN.usersList);
        this.citiesList = new HashSet<>(otherSN.citiesList);
        this.citiesMatrix = new CitiesMatrix(otherSN.citiesMatrix);
        this.friendshipMap = new FriendshipMap(false);

    }

    /**
     * builds up an instance of SocialNetwork with parameters usersList,
     * citiesList
     *
     * @param usersList SocialNetwork's usersList
     * @param citiesList SocialNetwork's citiesList
     */
    public SocialNetwork(Set usersList, Set citiesList) {
        this.usersList = new HashSet<>(usersList);
        this.citiesList = new HashSet<>(citiesList);
        this.citiesMatrix = new CitiesMatrix();
        this.friendshipMap = new FriendshipMap(false);
    }

    //GETTERS AND SETTERS
    /**
     * Obtain the SocialNetwork's usersList
     *
     * @return the SocialNetwork's usersList
     */
    public Set<User> getUsersList() {
        return new HashSet<>(this.usersList);
    }

    /**
     * Modifies the SocialNetwork's usersList
     *
     * @param usersList the SocialNetwork's usersList to set
     */
    public void setUsersList(Set<User> usersList) {
        this.usersList = new HashSet<>(usersList);
    }

    /**
     * Obtain the SocialNetwork's citiesList
     *
     * @return the SocialNetwork's citiesList
     */
    public Set<City> getCitiesList() {
        return new HashSet<>(this.citiesList);
    }

    /**
     * Modifies the SocialNetwork's citiesList
     *
     * @param citiesList the SocialNetwork's citiesList to set
     */
    public void setCitiesList(Set<City> citiesList) {
        this.citiesList = new HashSet<>(citiesList);
    }

    /**
     * Obtains the Social Network Cities Graph
     *
     * @return the citiesMatrix
     */
    public CitiesMatrix getCitiesGraph() {
        return citiesMatrix;
    }

    /**
     * Sets the Social Network Cities Graph
     *
     * @param matrix the cities matrix to set
     */
    public void setCitiesGraph(MatrixGraph<City, Double> matrix) {
        this.citiesMatrix = new CitiesMatrix(matrix);
    }

    /**
     * Sets the Social Network Cities Graph
     *
     * @param citiesMatrix the cities matrix to set
     */
    public void setCitiesGraph(CitiesMatrix citiesMatrix) {
        this.citiesMatrix = new CitiesMatrix(citiesMatrix);
    }

    /**
     * Obtains the Social Network Friendship MapGraph
     *
     * @return the FriendshipMapGraph
     */
    public FriendshipMap getFriendshipMap() {
        return this.friendshipMap;
    }

    /**
     * Sets the Social Network Friendship MapGraph
     *
     * @param Map the Friendship MapGraph to set
     */
    public void setFriendshipMap(MapGraph Map) {
        this.friendshipMap = new FriendshipMap(Map);
    }

    /**
     * Sets the Social Network Friendship MapGraph
     *
     * @param FriendshipMap the Friendship MapGraph to set
     */
    public void setFriendshipMap(FriendshipMap FriendshipMap) {
        this.friendshipMap = new FriendshipMap(FriendshipMap);
    }

    /**
     * Add an user to the usersList.
     *
     * @param user the user to add
     * @return true if the user is successfully added, false otherwise
     */
    public boolean addUser(User user) {
        return this.usersList.add(user);
    }

    /**
     * Add an user to the usersList.
     *
     * @param nickname the nickname of the user to add
     * @param email the email of the user to add
     * @return true if the user is successfully added, false otherwise
     */
    public boolean addUser(String nickname, String email) {
        User user = new User(nickname, email);
        return this.usersList.add(user);
    }

    /**
     * Remove an user from the usersList.
     *
     * @param user the user to remove
     * @return true if the user is successfully removed, false otherwise
     */
    public boolean removeUser(User user) {

        for (User friend : user.getFriends()) {

            user.removeFriendship(friend);
        }

        boolean removedCorrectly = this.usersList.remove(user);

        updateMayors();

        return removedCorrectly;
    }

    /**
     * Remove an user from the usersList.
     *
     * @param nickname the user's, to remove, nickname
     * @return true if the user is successfully removed, false otherwise
     */
    public boolean removeUser(String nickname) {

        // Find user with received nickname in users list.
        User user = null;
        for (User otherUser : usersList) {
            if (otherUser.getNickname().equals(nickname)) {
                user = otherUser;
            }
        }

        // If user isn't in the list return false.
        if (user == null) {
            return false;
        }

        for (User friend : user.getFriends()) {

            user.removeFriendship(friend);
        }

        boolean removedCorrectly = this.usersList.remove(user);

        updateMayors();

        return removedCorrectly;
    }

    /**
     * Checks if usersList has a user.
     *
     * @param user the user to check
     * @return true if the userList has user, false otherwise
     */
    public boolean hasUser(User user) {

        return this.usersList.contains(user);
    }

    /**
     * Add a city to the citiesList.
     *
     * @param city the city to add
     * @return true if the city is successfully added, false otherwise
     */
    public boolean addCity(City city) {
        return this.citiesList.add(city);
    }

    /**
     * Add a city to the citiesList.
     *
     * @param coordinates city's coordinates to set the new city
     * @param name city's name to set the new city
     * @param points city's points to set the new city
     * @return true if the city is successfully added, false otherwise
     */
    public boolean addCity(Pair<Double, Double> coordinates, String name, int points) {
        City city = new City(coordinates, name, points);
        return this.citiesList.add(city);
    }

    /**
     * Updates mayors of each city in the cities list.
     *
     */
    public void updateMayors() {
        for (City city : citiesList) {
            for (User user : usersList) {

                user.isMayor(city);
            }
        }
    }

    /**
     * Lists Mayors by mayors points in descending order.
     *
     * @return a map with (City, Mayor) ordered by mayors points (descending).
     */
    public Map listMayors() {

        TreeMap<City, User> map = new TreeMap(new Comparator<City>() {

            @Override
            public int compare(City c1, City c2) {

                // Order by mayor points.
                int pts1 = c1.getMayor().totalScore();
                int pts2 = c2.getMayor().totalScore();
                // Tiebreak most influential user.
                int num1 = c1.getMayor().getFriends().size();
                int num2 = c2.getMayor().getFriends().size();
                // If still tied order by city name (this will guarantee that no map.key is equal)
                String name1 = c1.getName();
                String name2 = c2.getName();

                return pts1 == pts2
                        ? (num1 == num2)
                                ? name1.compareToIgnoreCase(name2)
                                // (name descending order, 0 will not happen because city names are unique) 
                                : (num1 > num2) ? -1 : 1
                        : pts1 > pts2 ? -1 : 1;
                // switched for descending order
            }
        });

        for (City city : citiesList) {

            int score = city.getMayor().totalScore();
            map.put(city, city.getMayor());
        }
        return map;
    }

    /**
     * Returns a list of the most influential users.
     *
     * @return a list of the most influential users.
     */
    public Set<User> getInfluentialUsers() {

        Set<User> influentialUsers = new HashSet<User>();

        if (usersList.size() > 0) {

            // Order by merge sort
            LinkedList<User> orderedList = new LinkedList(Algorithms.mergeSort(usersList, new Comparator<User>() {

                @Override
                public int compare(User u1, User u2) { // Compares how many friends. (Descending order)

                    int num1 = u1.getFriends().size();
                    int num2 = u2.getFriends().size();

                    return num1 == num2 ? 0 : num1 < num2 ? 1 : -1;
                }
            }));
            // We could have used Collections.sort() because it already uses merge sort
            // algorithm, but for the purpose of this subject we implemented our own merge sort
            // algorithm.

            Iterator it = orderedList.iterator();
            // First influential user.
            User first = (User) it.next();
            // First influential user number of friends.
            int n = first.getFriends().size();
            // Add to the set.
            influentialUsers.add(first);

            while (it.hasNext()) {
                User nextUser = (User) it.next();

                if (nextUser.getFriends().size() != n) {

                    return influentialUsers;
                }
                influentialUsers.add(nextUser);
            }
        }

        return influentialUsers;
    }

    // **** 2nd PART **** //
    // **** 2 b)     ****//
    /**
     * Get the friends nearby, at a distance lower than given value.
     *
     * @param user user to get friends
     * @param distance distance to scan
     * @return iterable with the closest friends
     */
    public HashSet<User> getNearbyFriendsInAgivenDistance(User user, Double distance) {

        HashSet<User> nearbyFriends = new HashSet<>();
        Iterable<User> friends = new ArrayList<>();

        friends = this.friendshipMap.getFriends(user);

        //get all cities at the distance
        City userCurrentCity = user.getVisitedCities().getLast();
        for (City city : this.citiesMatrix.getCities()) {
            double distanceFromCity
                    = WeightedMatrixGraphAlgorithms.shortestPath(this.citiesMatrix.getGraph(), userCurrentCity, city, new LinkedList<>());
            if (distanceFromCity < distance && distanceFromCity > 0) { // Verify if nearby and if their is a path between.
                // go through friends to see if any is on the nearby city
                for (User friend : friends) {
                    if (friend.getVisitedCities().getLast().equals(city)) {
                        nearbyFriends.add(user);
                    }
                }
            }

        }
        //check the user current city as well
        for (User friend : friends) {
            if (friend.getVisitedCities().getLast().equals(userCurrentCity)) {
                nearbyFriends.add(user);
            }
        }

        return nearbyFriends;
    }

    // **** 2 d)     ****//
    /**
     * Get cities with most friends.
     *
     * @param user user to check
     * @return the list of cities with most friends
     */
    private Set<City> citiesWithMostFriends(User user) {

        HashSet<City> cities = new HashSet<>();

        Iterable<User> friends = this.friendshipMap.getFriends(user);
        if (friends.iterator().hasNext()) { // If user doesn't have friends return empty set.

            HashMap<City, Integer> friendsPerCity = new HashMap<>();

            for (User friend : friends) {

                City location = friend.getVisitedCities().getLast();
                int numFriends = (friendsPerCity.containsKey(location)) ? friendsPerCity.get(location) + 1 : 1;
                friendsPerCity.put(location, numFriends);
            }

            TreeMap<City, Integer> sortedMap = new TreeMap(new Comparator<City>() {

                @Override
                public int compare(City c1, City c2) {

                    int num1 = friendsPerCity.get(c1);
                    int num2 = friendsPerCity.get(c1);

                    // Tiebreak is the city name (needed to guarantee no key is equal).
                    int compareName = c1.getName().compareToIgnoreCase(c2.getName());

                    return num1 == num2 ? compareName : num1 < num2 ? 1 : -1;
                }
            }); // Descending order
            sortedMap.putAll(friendsPerCity); // Order number of friends in a city

            Iterator<City> cityIt = sortedMap.keySet().iterator();
            Iterator<Integer> numIt = sortedMap.values().iterator();

            cities.add(cityIt.next());
            Integer max = numIt.next();
            while (numIt.hasNext() && max == numIt.next()) {

                cities.add(cityIt.next());
            }
        }

        return cities;
    }

    /**
     * Shortest path from user A to B, passing through the cities with most
     * friends of both users.
     *
     * @param userA first user (source)
     * @param userB second user (destination)
     * @return the shortest path
     */
    public LinkedList<City> shrtPathPassingCitiesWithMostFriends(User userA, User userB) {

        HashSet<City> waypoints = new HashSet<>();

        // Add all cities with most friends from both users
        waypoints.addAll(citiesWithMostFriends(userA));
        waypoints.addAll(citiesWithMostFriends(userB));

        // Get all paths available between User A & B (current locations)
        City locationA = userA.getVisitedCities().getLast();
        City locationB = userB.getVisitedCities().getLast();

        LinkedList<LinkedList<City>> paths = new LinkedList<LinkedList<City>>();
        MatrixGraphAlgorithms.allPaths(this.citiesMatrix.getGraph(), locationA, locationB, paths);

        // Check paths that pass through all waypoints
        for (LinkedList<City> path : paths) {

            boolean containsAll = true;
            while (waypoints.iterator().hasNext() && containsAll) {

                containsAll = path.contains(waypoints.iterator().next());
            }
            if (!containsAll) {
                paths.remove(path);
            }
        }

        // Sort Paths by distance (Ascending order)
        Comparator<LinkedList<City>> criteria = new Comparator<LinkedList<City>>() {
            @Override
            public int compare(LinkedList<City> path1, LinkedList<City> path2) {

                double dist1 = 0.0;
                City cityA = path1.iterator().next();
                while (path1.iterator().hasNext()) {

                    City cityB = path1.iterator().next();

                    dist1 += citiesMatrix.getGraph().getEdge(cityA, cityB);

                    cityA = cityB; // To check next edge
                }

                double dist2 = 0.0;
                cityA = path2.iterator().next();
                while (path2.iterator().hasNext()) {

                    City cityB = path2.iterator().next();

                    dist2 += citiesMatrix.getGraph().getEdge(cityA, cityB);

                    cityA = cityB; // To check next edge
                }

                return (dist1 == dist2) ? 0 : (dist1 > dist2) ? 1 : -1;
            }
        };

        // Sort paths by distance
        Collections.sort(paths, criteria);

        // Return first path ( ordered shortest to longest)
        return paths.getFirst();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.usersList);
        hash = 29 * hash + Objects.hashCode(this.citiesList);
        return hash;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        final SocialNetwork otherSocialNetwork = (SocialNetwork) otherObject;

        return this.getCitiesList().equals(otherSocialNetwork.getCitiesList())
                && this.getUsersList().equals(otherSocialNetwork.getUsersList());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("UsersList{");
        for (User user : this.usersList) {
            s.append(String.format("%s%n", user.getNickname()));
        }
        s.append("}");
        s.append("CitiesList{");
        for (City city : this.citiesList) {
            s.append(String.format("%s%n", city.getName()));
        }
        s.append("}");
        return s.toString();
    }

}
