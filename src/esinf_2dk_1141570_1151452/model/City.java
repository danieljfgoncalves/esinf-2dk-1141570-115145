package esinf_2dk_1141570_1151452.model;

import java.util.Objects;
import javafx.util.Pair;

/**
 * Represents a City
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class City {
    //INSTANCE ATTRIBUTES

    /**
     * City's name
     */
    private String name;

    /**
     * City's points
     */
    private int points;

    /**
     * City's coordinates
     */
    private Pair<Double, Double> coordinates;

    /**
     * City's mayor
     */
    private User mayor;

    //CLASS ATTRIBUTES
    //CONSTANTS
    /**
     * City's name by default
     */
    private static final String DEFAULT_NAME = "undefined";

    /**
     * City's points by default
     */
    private static final int DEFAULT_POINTS = 0;

    /**
     * City's pair by default
     */
    private static final Pair<Double, Double> DEFAULT_COORDINATES = new Pair(new Double(0), new Double(0));

    //CONSTRUCTORS
    /**
     * builds up an instance of City with parameters by default
     */
    public City() {
        this.coordinates = DEFAULT_COORDINATES;
        this.mayor = new User();
        this.name = DEFAULT_NAME;
        this.points = DEFAULT_POINTS;
    }

    /**
     * builds up an instance of City from another instance of City
     *
     * @param otherCity City to copy
     */
    public City(City otherCity) {
        this.coordinates = otherCity.coordinates;
        this.mayor = otherCity.mayor;
        this.name = otherCity.name;
        this.points = otherCity.points;
    }

    /**
     * builds up an instance of City with parameters coordinates, mayor, name,
     * points
     *
     * @param coordinates City's coordinates
     * @param name City's name
     * @param points City's points
     */
    public City(Pair coordinates, String name, int points) {
        this.coordinates = coordinates;
        this.mayor = new User();
        this.name = name;
        this.points = points;
    }

    //GETTERS AND SETTERS
    /**
     * Obtain the City's name
     *
     * @return the City's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifies the City's name
     *
     * @param name the City's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtain the City's points
     *
     * @return the City's points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Modifies the City's points
     *
     * @param points the City's points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Obtain the City's coordinates
     *
     * @return the City's coordinates
     */
    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

    /**
     * Modifies the City's coordinates
     *
     * @param coordinates the City's coordinates to set
     */
    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Obtain the City's mayor
     *
     * @return the City's mayor
     */
    public User getMayor() {
        return this.mayor;
    }

    /**
     * Modifies the City's mayor
     *
     * @param mayor the City's mayor to set
     */
    public void setMayor(User mayor) {
        this.mayor = mayor;
    }
    
    //METHODS
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.points;
        hash = 11 * hash + Objects.hashCode(this.coordinates);
        hash = 11 * hash + Objects.hashCode(this.mayor);
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

        final City otherCity = (City) otherObject;

        return this.getName().equals(otherCity.getName()) || this.getCoordinates().equals(otherCity.getCoordinates());
    }

    @Override
    public String toString() {
        return String.format("%nCity's name: %s %nCity's points: %d %nCity's Mayor: %s %nCity's coordinates: (%.2f, .2f)",
                this.getName(),
                this.getPoints(),
                this.getMayor(),
                this.getCoordinates().getKey(),
                this.getCoordinates().getValue());
    }

}
