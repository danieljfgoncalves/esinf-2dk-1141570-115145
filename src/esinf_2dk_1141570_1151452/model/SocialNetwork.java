package esinf_2dk_1141570_1151452.model;

import esinf_2dk_1141570_1151452.utils.Algorithms;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
    private Set<User> usersList;

    /**
     * SocialNetwork's cities list
     */
    private Set<City> citiesList;

    //CONSTRUCTORS
    /**
     * builds up an instance of SocialNetwork with parameters by default
     */
    public SocialNetwork() {
        this.usersList = new HashSet<User>();
        this.citiesList = new HashSet<City>();
    }

    /**
     * builds up an instance of SocialNetwork from another instance of
     * SocialNetwork
     *
     * @param otherSN SocialNetwork to copy
     */
    public SocialNetwork(SocialNetwork otherSN) {
        this.usersList = new HashSet<User>(otherSN.usersList);
        this.citiesList = new HashSet<City>(otherSN.citiesList);
    }

    /**
     * builds up an instance of SocialNetwork with parameters usersList,
     * citiesList
     *
     * @param usersList SocialNetwork's usersList
     * @param citiesList SocialNetwork's citiesList
     */
    public SocialNetwork(Set usersList, Set citiesList) {
        this.usersList = new HashSet<User>(usersList);
        this.citiesList = new HashSet<City>(citiesList);
    }

    //GETTERS AND SETTERS
    /**
     * Obtain the SocialNetwork's usersList
     *
     * @return the SocialNetwork's usersList
     */
    public Set<User> getUsersList() {
        return this.usersList;
    }

    /**
     * Modifies the SocialNetwork's usersList
     *
     * @param usersList the SocialNetwork's usersList to set
     */
    public void setUsersList(Set<User> usersList) {
        this.usersList = usersList;
    }

    /**
     * Obtain the SocialNetwork's citiesList
     *
     * @return the SocialNetwork's citiesList
     */
    public Set<City> getCitiesList() {
        return this.citiesList;
    }

    /**
     * Modifies the SocialNetwork's citiesList
     *
     * @param citiesList the SocialNetwork's citiesList to set
     */
    public void setCitiesList(Set<City> citiesList) {
        this.citiesList = citiesList;
    }

    //METHODS
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

        boolean removedCorrectly = true;

        for (User friend : user.getFriends()) {

            if (!user.removeFriendship(friend)) {
                removedCorrectly = false;
            }
        }

        updateMayors(user.getVisitedCities()); // Only updates the cities where the user passed.

        return this.usersList.remove(user) && removedCorrectly;
    }

    /**
     * Remove an user from the usersList.
     *
     * @param nickname the user's, to remove, nickname
     * @return true if the user is successfully removed, false otherwise
     */
    public boolean removeUser(String nickname) {

        boolean removedCorrectly = true;

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

            if (!user.removeFriendship(friend)) {
                removedCorrectly = false;
            }
        }

        updateMayors(user.getVisitedCities()); // Only updates the cities where the user passed.

        return this.usersList.remove(user) && removedCorrectly;
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
     * Checks if usersList has a user.
     *
     * @param nickname the nickname of a user to check
     * @return true if the userList has user, false otherwise
     */
    public boolean hasUser(String nickname) {

        User user = new User();
        user.setNickname(nickname);

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
     * @param cities list of citites.
     */
    public void updateMayors(List<City> cities) {
        for (City city : cities) {
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
                int ptsMayor1 = c1.getMayor().pointsInAgivenCity(c1);
                int ptsMayor2 = c2.getMayor().pointsInAgivenCity(c2);
                // Tiebreak most influential user.
                int numFriends1 = c1.getMayor().getFriends().size();
                int numFriends2 = c2.getMayor().getFriends().size();

                return ptsMayor1 == ptsMayor2
                        ? (numFriends1 == numFriends2) ? 0 : (numFriends1 > numFriends2) ? -1 : 1
                        : ptsMayor1 > ptsMayor2 ? -1 : 1;
                // switched for descending order
            }
        });

        for (City city : citiesList) {

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
