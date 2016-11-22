package model;

import model.User;
import model.City;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a City
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class CityTest {

    /**
     * City object for testing.
     */
    City testCity;

    public CityTest() {

        String name = "test";
        Double lat = 30.000001;
        Double lon = -30.000001;
        int points = 10;
        testCity = new City(new Pair(lat, lon), name, points);

    }

    /**
     * Test of getName method, of class City.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "test";
        String result = testCity.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class City.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "test2";
        testCity.setName(name);
        assertEquals(name, testCity.getName());
    }

    /**
     * Test of getPoints method, of class City.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        int expResult = 10;
        int result = testCity.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPoints method, of class City.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        int points = 20;
        testCity.setPoints(points);
        assertEquals(points, testCity.getPoints());
    }

    /**
     * Test of getCoordinates (Latitude) method, of class City.
     */
    @Test
    public void testGetCoordinates01() {
        System.out.println("getCoordinates");
        double expResult = 30.000001;
        Pair<Double, Double> result = testCity.getCoordinates();
        assertEquals(expResult, result.getKey(), 0.000001);
    }

    /**
     * Test of getCoordinates (Longitude) method, of class City.
     */
    @Test
    public void testGetCoordinates02() {
        System.out.println("getCoordinates");
        double expResult = -30.000001;
        Pair<Double, Double> result = testCity.getCoordinates();
        assertEquals(expResult, result.getValue(), 0.000001);
    }

    /**
     * Test of setCoordinates method, of class City.
     */
    @Test
    public void testSetCoordinates() {
        System.out.println("setCoordinates");
        Pair<Double, Double> coordinates = new Pair(10.000001, -20.000001);
        testCity.setCoordinates(coordinates);
        assertEquals(coordinates, testCity.getCoordinates());
    }

    /**
     * Test of getMayor method, of class City.
     */
    @Test
    public void testGetMayor() {
        System.out.println("getMayor");
        User expResult = new User();
        User result = testCity.getMayor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMayor method, of class City.
     */
    @Test
    public void testSetMayor() {
        System.out.println("setMayor");
        User mayor = new User("test", "test@email.com");
        testCity.setMayor(mayor);
        assertEquals(testCity.getMayor(), mayor);
    }

    /**
     * Test of equals method, of class City.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object otherObject = new City();
        City city = new City();
        boolean result = city.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class City.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object otherObject = new City();
        boolean result = testCity.equals(otherObject);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class City.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");
        City otherCity = new City(null, "test", 0);
        boolean result = testCity.equals(otherCity);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class City.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");
        City otherCity = new City(new Pair(30.000001, -30.000001), null, 0);
        boolean result = testCity.equals(otherCity);
        assertTrue(result);
    }
}
