package esinf_2dk_1141570_1151452.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
            s.append(String.format("%s%n", user));
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
