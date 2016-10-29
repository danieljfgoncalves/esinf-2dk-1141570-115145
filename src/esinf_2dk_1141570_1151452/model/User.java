package esinf_2dk_1141570_1151452.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.util.Pair;

/**
 * Represents a User.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class User {

    /**
     * User's nickname.
     */
    private String nickname;

    /**
     * User's email.
     */
    private String email;

    /**
     * Cities user visited in chronologic order (last visited = current
     * location)
     */
    private LinkedList<City> visitedCities;

    /**
     * List of the user's friends.
     */
    private Set<User> friends;

    /**
     * Default user's nickname.
     */
    static final String DEFAULT_NICKNAME = "undefined";

    /**
     * Default user's email.
     */
    static final String DEFAULT_EMAIL = "undefined";

    /**
     * Empty constructor of a user object.
     */
    public User() {

        this.nickname = DEFAULT_NICKNAME;
        this.email = DEFAULT_EMAIL;
        this.visitedCities = new LinkedList<City>();
        this.friends = new HashSet<User>();
    }

    /**
     * Constructor of a user object with nickname and email passed as
     * parameters.
     *
     * @param nickname user's nickname
     * @param email user's email
     */
    public User(String nickname, String email) {

        this.nickname = nickname;
        this.email = email;
        this.visitedCities = new LinkedList<City>();
        this.friends = new HashSet<User>();
    }

    /**
     * Copy Constructor of a user object.
     *
     * @param otherUser user object to copy from
     */
    public User(User otherUser) {

        this.nickname = otherUser.nickname;
        this.email = otherUser.email;
        this.visitedCities = new LinkedList<City>(otherUser.visitedCities);
        this.friends = new HashSet<User>(otherUser.friends);
    }

    /**
     * Returns the user's nickname.
     *
     * @return user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the user's nickname.
     *
     * @param nickname user's nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns the user's email.
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's visited cities list.
     *
     * @return user's visited cities list
     */
    public LinkedList<City> getVisitedCities() {
        return new LinkedList<>(this.visitedCities);
    }

    /**
     * Sets the user's visited cities list.
     *
     * @param visitedCities user's visited cities list
     */
    public void setVisitedCities(List<City> visitedCities) {
        this.visitedCities = new LinkedList<>(visitedCities);
    }

    /**
     * Returns the user's friends list.
     *
     * @return user's friends list
     */
    public Set<User> getFriends() {
        return new HashSet<>(friends);
    }

    /**
     * Sets the user's friends list.
     *
     * @param friends user's friends list
     */
    public void setFriends(Set<User> friends) {
        this.friends = new HashSet<>(friends);
    }

    //METHODS
    /**
     * Adds a friendship connection.
     *
     * @param friend the friend to connect
     * @return true if the friendship connection is successfully added, false
     * otherwise
     */
    public boolean addFriendship(User friend) {

        return this.friends.add(friend)
                && friend.friends.add(this);
    }

    /**
     * Removes friend(user) from the usersList
     *
     * @param friend the friend(user) to remove
     * @return true if the friend(user) is successfully removed, false otherwise
     */
    public boolean removeFriendship(User friend) {
        return this.friends.remove(friend) && friend.friends.remove(this);
    }

    /**
     * Checks the user in a new city and set up the user as Mayor if he has more
     * points than the current Mayor.
     *
     * @param city the new city where the user checks in
     * @return true if the user checks in a new city, false otherwise
     */
    public boolean checkInAnewCity(City city) {
        if (this.visitedCities.isEmpty() || !(this.visitedCities.getLast().equals(city))) {
            this.visitedCities.add(city);

            this.isMayor(city);

            return true;
        }
        return false;
    }

    /**
     * Updates the Mayor in the given city if the user has more points than the
     * current mayor.
     *
     * @param city the city to check
     * @return true if the user is the new mayor, false otherwise
     */
    public boolean isMayor(City city) {

        if ((this.pointsInAgivenCity(city)) >= (city.getMayor().pointsInAgivenCity(city))) {
            city.setMayor(this);
            return true;
        }
        return false;
    }

    /**
     * Obtains the points of the user in a given city.
     *
     * @param givenCity the given city to check how much points of the user has
     * in it
     * @return the points of the user has in a given city
     */
    public int pointsInAgivenCity(City givenCity) {
        int points = 0;
        for (City visitedCity : visitedCities) {
            if (visitedCity.equals(givenCity)) {
                points += visitedCity.getPoints();
            }
        }
        return points;
    }

    /**
     * Obtains the total points of the user for his visited cities.
     *
     *
     * @return the total points of the user.
     */
    public int totalScore() {

        StringBuilder output = new StringBuilder(
                String.format("%s has visted the following cities (chronologic order): %n",
                        this.nickname));

        int i = 0, score = 0;
        Iterator it = this.visitedCities.iterator();
        while (it.hasNext()) {

            City city = (City) it.next();
            score += city.getPoints();
            output.append(String.format("%d. - %s%n", ++i, city.getName()));
        }

        // From our interpretation from the assignement we only need to print 
        // the visited cities by order of check in.
        System.out.println(output);

        return score;
    }

    /**
     * Obtain the friends of the user in a given location(coordinates).
     *
     * @param coordinates the coordinates from the given location
     * @return the friends of the user in a given location(coordinates).
     */
    public HashSet<User> userFriendsInAGivenLocation(Pair<Double, Double> coordinates) {
        HashSet<User> friendsByCoordinates = new HashSet<>();
        for (User friend : this.friends) {
            if (friend.getVisitedCities().getLast().getCoordinates().equals(coordinates)) {
                friendsByCoordinates.add(friend);
            }
        }
        return friendsByCoordinates;
    }

    /**
     * Obtain the friends of the user in a given location(city).
     *
     * @param city the city where the user is
     * @return the friends of the user in a given location(coordinates).
     */
    public HashSet<User> userFriendsInAGivenLocation(City city) {
        HashSet<User> friendsByCity = new HashSet<>();
        for (User friend : this.friends) {
            if (friend.getVisitedCities().getLast().equals(city)) {
                friendsByCity.add(friend);
            }
        }
        return friendsByCity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.nickname);
        hash = 17 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;

        return this.nickname.equalsIgnoreCase(other.nickname)
                || this.email.equalsIgnoreCase(other.email);
        // We do OR in this case, because we can't have users with same email or nickname.
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer(
                String.format("User {%nNickname=%s%nEmail=%s%n",
                        this.nickname, this.email));

        buffer.append("Visited Cities:%n");
        for (City city : visitedCities) {
            buffer.append(String.format("%t%s%n", city.getName()));
        }

        buffer.append("Friends:%n");
        for (User friend : friends) {
            buffer.append(String.format("%t%s%n", friend.getNickname()));
        }
        buffer.append("}");

        return buffer.toString();
    }

}
