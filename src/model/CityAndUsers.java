package model;

import javafx.util.Pair;

/**
 * Represents a AVL Binary Search Tree for Cities (Ordered by number of users in
 * each city).
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class CityAndUsers extends Pair<City, Integer> implements Comparable<CityAndUsers> {

    /**
     * Constructs a key & value pair with city and number of current users
     * checked in.
     *
     * @param city selected city
     * @param numUsers number of users checked in
     */
    public CityAndUsers(City city, int numUsers) {
        super(city, numUsers);
    }

    /**
     * Obtain city.
     *
     * @return city
     */
    public City getCity() {

        return getKey();
    }

    /**
     * Obtain city.
     *
     * @return
     */
    public Integer getNumUsers() {

        return getValue();
    }

    @Override
    public int compareTo(CityAndUsers o) {

        int compareNumUsers = this.getValue().compareTo(o.getValue());
        int compareCityNames = this.getKey().getName().compareToIgnoreCase(o.getKey().getName());

        return compareNumUsers == 0 ? compareCityNames : compareNumUsers;
    }

}
