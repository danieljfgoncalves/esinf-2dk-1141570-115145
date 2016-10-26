package esinf_2dk_1141570_1151452;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private List<City> visitedCities;

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
    public List<City> getVisitedCities() {
        return new LinkedList<>(this.visitedCities);
    }

    /**
     * Sets the user's visited cities list.
     * 
     * @param visitedCities user's visited cities list
     */
    public void setVisitedCities(List<City> visitedCities) {
        this.visitedCities = visitedCities;
    }

    /**
     * Returns the user's friends list.
     * 
     * @return user's friends list
     */
    public Set<User> getFriends() {
        return friends;
    }

    /**
     * Sets the user's friends list.
     * 
     * @param friends user's friends list
     */
    public void setFriends(Set<User> friends) {
        this.friends = friends;
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

        return this.nickname.equals(other.nickname)
                || this.email.equals(other.email);
        // We do OR in this case, because we can't have users with same email or nickname.
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer(
                String.format("User {%nNickname=%s%nEmail%s%n",
                        this.nickname, this.email));

        // TODO: Uncomment after City class is implemented.
        /*
        buffer.append("Visited Cities:%n");
        for (City city : visitedCities) {
            buffer.append(String.format("%t%s%n", city.getName()));
        }
        */
        
        buffer.append("Friends:%n");
        for (User friend : friends) {
            buffer.append(String.format("%t%s%n", friend.getNickname()));
        }
        buffer.append("}");

        return buffer.toString();
    }

}
