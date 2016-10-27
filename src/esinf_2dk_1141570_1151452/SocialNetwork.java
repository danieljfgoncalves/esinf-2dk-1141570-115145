package esinf_2dk_1141570_1151452;

import java.util.HashSet;
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
        this.usersList = new HashSet<>();
        this.citiesList = new HashSet<>();
    }

    /**
     * builds up an instance of SocialNetwork from another instance of
     * SocialNetwork
     *
     * @param otherSN
     */
    public SocialNetwork(SocialNetwork otherSN) {
        this.citiesList = otherSN.citiesList;
        this.usersList = otherSN.usersList;
    }

    /**
     * builds up an instance of SocialNetwork with parameters usersList,
     * citiesList
     *
     * @param usersList
     * @param citiesList
     */
    public SocialNetwork(Set usersList, Set citiesList) {
        this.usersList = usersList;
        this.citiesList = citiesList;
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
