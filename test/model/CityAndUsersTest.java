package model;

import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a City & Users Class
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class CityAndUsersTest {

    /**
     * City test.
     */
    private City testCity;

    /**
     * City with users test
     */
    private CityAndUsers cityAndUsers;

    public CityAndUsersTest() {

        testCity = new City(new Pair(1.0, 1.0), "CityTest", 2);
        cityAndUsers = new CityAndUsers(testCity, 10);
    }

    /**
     * Test of getCity method, of class CityAndUsers.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");

        City expResult = testCity;
        City result = cityAndUsers.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumUsers method, of class CityAndUsers.
     */
    @Test
    public void testGetNumUsers() {
        System.out.println("getNumUsers");

        Integer expResult = 10;
        Integer result = cityAndUsers.getNumUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CityAndUsers.
     */
    @Test
    public void testCompareTo01() {
        System.out.println("compareTo");

        City testCity2 = new City(new Pair(1.0, 1.0), "CityTest2", 2);
        CityAndUsers other = new CityAndUsers(testCity2, 20);

        int expResult = -1;
        int result = cityAndUsers.compareTo(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CityAndUsers.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo");

        City testCity2 = new City(new Pair(1.0, 1.0), "CityTest2", 2);
        CityAndUsers other = new CityAndUsers(testCity2, 5);

        int expResult = 1;
        int result = cityAndUsers.compareTo(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CityAndUsers.
     */
    @Test
    public void testCompareTo03() {
        System.out.println("compareTo");

        City testCity2 = new City(new Pair(1.0, 1.0), "CityTest", 2);
        CityAndUsers other = new CityAndUsers(testCity2, 10);

        int expResult = 0;
        int result = cityAndUsers.compareTo(other);
        assertEquals(expResult, result);
    }
}