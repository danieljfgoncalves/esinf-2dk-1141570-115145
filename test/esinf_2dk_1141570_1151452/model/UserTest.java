/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_2dk_1141570_1151452.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt TurmaDN
 */
public class UserTest {

    /**
     * User object for testing.
     */
    User testUser;

    public UserTest() {

        String nickname = "nickname1";
        String email = "email1";
        testUser = new User(nickname, email);

        LinkedList<City> visitedCities = new LinkedList<>();
        City city1 = new City(new Pair(41.243345, -8.674084), "city0", 28);
        City city2 = new City(new Pair(41.237364, -8.846746), "city1", 72);
        City city3 = new City(new Pair(40.519841, -8.085113), "city2", 81);
        visitedCities.add(city1);
        visitedCities.add(city2);
        visitedCities.add(city3);

        testUser.setVisitedCities(visitedCities);

        Set<User> friends = new HashSet<>();
        User friend1 = new User("nick0", "mail_0_@sapo.pt");
        User friend2 = new User("nick1", "mail_1_@sapo.pt");
        User friend3 = new User("nick2", "mail_2_@sapo.pt");
        friends.add(friend1);
        friends.add(friend2);
        friends.add(friend3);

        testUser.setFriends(friends);

    }

    /**
     * Test of getNickname method, of class User.
     */
    @Test
    public void testGetNickname() {
        System.out.println("getNickname");
        String expResult = "nickname1";
        String result = testUser.getNickname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNickname method, of class User.
     */
    @Test
    public void testSetNickname() {
        System.out.println("setNickname");
        String nickname = "nicknameTest";
        testUser.setNickname(nickname);
        assertEquals(nickname, testUser.getNickname());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "email1";
        String result = testUser.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "emailTest";
        testUser.setEmail(email);
        assertEquals(email, testUser.getEmail());
    }

    /**
     * Test of getVisitedCities method, of class User.
     */
    @Test
    public void testGetVisitedCities() {
        System.out.println("getVisitedCitiesList");

        LinkedList<City> expResult = new LinkedList<>();
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));

        LinkedList<City> result = testUser.getVisitedCities();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisitedCities method, of class User.
     */
    @Test
    public void testSetVisitedCities() {
        System.out.println("setVisitedCitiesList");

        LinkedList<City> expResult = new LinkedList<>();
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));

        testUser.setVisitedCities(expResult);
        LinkedList<City> result = testUser.getVisitedCities();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFriends method, of class User.
     */
    @Test
    public void testGetFriends() {
        System.out.println("getFriends");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));

        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFriends method, of class User.
     */
    @Test
    public void testSetFriends() {
        System.out.println("setFriends");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));

        testUser.setFriends(expResult);
        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);

    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testAddFriendship01() {
        System.out.println("addFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(friendTest);

        testUser.addFriendship(friendTest);
        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testAddFriendship02() {
        System.out.println("addFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");

        boolean result = testUser.addFriendship(friendTest);
        assertTrue(result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testAddFriendship03() {
        System.out.println("addFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick2", "mail_2_@sapo.pt");
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));

        testUser.addFriendship(friendTest);
        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testAddFriendship04() {
        System.out.println("addFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick2", "mail_2_@sapo.pt");

        boolean result = testUser.addFriendship(friendTest);
        assertFalse(result);
    }

    /**
     * Test of removeFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship01() {
        System.out.println("removeFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));

        testUser.addFriendship(friendTest);
        testUser.removeFriendship(friendTest);
        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship02() {
        System.out.println("removeFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");

        testUser.addFriendship(friendTest);

        boolean result = testUser.removeFriendship(friendTest);
        assertTrue(result);
    }

    /**
     * Test of removeFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship03() {
        System.out.println("removeFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));

        testUser.removeFriendship(friendTest);
        Set<User> result = testUser.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship04() {
        System.out.println("removeFriendship");

        Set<User> expResult = new HashSet<>();
        User friendTest = new User("nick3", "mail_3_@sapo.pt");

        boolean result = testUser.removeFriendship(friendTest);
        assertFalse(result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity01() {
        System.out.println("checkInAnewCity");

        LinkedList<City> expResult = new LinkedList<>();
        City cityTest = new City(new Pair(41.118700, -8.589700), "city3", 42);
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(cityTest);

        testUser.checkInAnewCity(cityTest);

        LinkedList<City> result = testUser.getVisitedCities();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity02() {
        System.out.println("checkInAnewCity");

        City cityTest = new City(new Pair(41.118700, -8.589700), "city3", 42);

        boolean result = testUser.checkInAnewCity(cityTest);
        assertTrue(result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity03() {
        System.out.println("checkInAnewCity");

        LinkedList<City> expResult = new LinkedList<>();
        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));

        testUser.checkInAnewCity(cityTest);

        LinkedList<City> result = testUser.getVisitedCities();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity04() {
        System.out.println("checkInAnewCity");

        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);

        boolean result = testUser.checkInAnewCity(cityTest);
        assertFalse(result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity05() {
        System.out.println("checkInAnewCity");

        LinkedList<City> expResult = new LinkedList<>();
        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));

        testUser.setVisitedCities(new LinkedList<>());
        testUser.checkInAnewCity(cityTest);

        LinkedList<City> result = testUser.getVisitedCities();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity06() {
        System.out.println("checkInAnewCity");

        City cityTest = new City(new Pair(41.118700, -8.589700), "city3", 42);
        testUser.setVisitedCities(new LinkedList<>());

        boolean result = testUser.checkInAnewCity(cityTest);
        assertTrue(result);
    }

    /**
     * Test of pointsInAgivenCity method, of class User.
     */
    @Test
    public void testPointsInAgivenCity01() {
        System.out.println("pointsInAgivenCity");

        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);

        int expResult = testUser.getVisitedCities().getLast().getPoints();
        int result = testUser.pointsInAgivenCity(cityTest);
        assertEquals(expResult, result);
    }

    /**
     * Test of pointsInAgivenCity method, of class User.
     */
    @Test
    public void testPointsInAgivenCity02() {
        System.out.println("pointsInAgivenCity");

        City cityTest = new City(new Pair(41.118700, -8.589700), "city3", 42);

        int expResult = 0;
        int result = testUser.pointsInAgivenCity(cityTest);
        assertEquals(expResult, result);
    }

    /**
     * Test of totalScore method, of class User.
     */
    @Test
    public void testTotalScore01() {
        System.out.println("totalScore");

        int expResult = testUser.getVisitedCities().get(0).getPoints()
                + testUser.getVisitedCities().get(1).getPoints()
                + testUser.getVisitedCities().get(2).getPoints();
        int result = testUser.totalScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of totalScore method, of class User.
     */
    @Test
    public void testTotalScore02() {
        System.out.println("totalScore");

        int expResult = 0;
        testUser.setVisitedCities(new LinkedList<>());
        int result = testUser.totalScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of userFriendsInAGivenLocation method, of class User.
     */
    @Test
    public void testUserFriendsInAGivenLocation_Pair() {
        System.out.println("userFriendsInAGivenLocation");
        Pair<Double, Double> coordinates = null;
        User instance = new User();
        HashSet<User> expResult = null;
        HashSet<User> result = instance.userFriendsInAGivenLocation(coordinates);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userFriendsInAGivenLocation method, of class User.
     */
    @Test
    public void testUserFriendsInAGivenLocation_City() {
        System.out.println("userFriendsInAGivenLocation");
        City city = null;
        User instance = new User();
        HashSet<User> expResult = null;
        HashSet<User> result = instance.userFriendsInAGivenLocation(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMayor method, of class User.
     */
    @Test
    public void testIsMayor() {
        System.out.println("isMayor");
        City city = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isMayor(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object otherObject = new User();
        User user = new User();
        boolean result = user.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object otherObject = new User();
        boolean result = testUser.equals(otherObject);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");
        Object otherObject = new User("nickname1", "email1");
        boolean result = testUser.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");
        Object otherObject = new User("nickname2", "email2");
        boolean result = testUser.equals(otherObject);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals05() {
        System.out.println("equals");
        Object otherObject = new User("nickname1", "email2");
        boolean result = testUser.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals06() {
        System.out.println("equals");
        Object otherObject = new User("nickname2", "email1");
        boolean result = testUser.equals(otherObject);
        assertTrue(result);
    }

}
