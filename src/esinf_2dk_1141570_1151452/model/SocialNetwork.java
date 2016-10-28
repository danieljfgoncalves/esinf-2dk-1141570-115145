package esinf_2dk_1141570_1151452.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
