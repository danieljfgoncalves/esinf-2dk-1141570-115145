/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.User;
import model.SocialNetwork;
import model.City;
import utils.FileManager;
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
     * Social Network object to test with 10 cities & 10 users.
     */
    SocialNetwork sn10;

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

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));

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

        User friendTest = new User("nick2", "mail_2_@sapo.pt");

        boolean result = testUser.addFriendship(friendTest);
        assertFalse(result);
    }

    /**
     * Test of addFriendship method, of class User.
     */
    @Test
    public void testAddFriendship05() {
        System.out.println("addFriendship");

        User friendTest = new User("nick3", "mail_3_@sapo.pt");

        Set<User> expResult = new HashSet<>();
        expResult.add(testUser);

        testUser.addFriendship(friendTest);
        Set<User> result = friendTest.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship01() {
        System.out.println("removeFriendship");

        User friendTest = new User("nick0", "mail_0_@sapo.pt");

        Set<User> expResult = new HashSet<>();
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
    public void testRemoveFriendship02() {
        System.out.println("removeFriendship");

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
        User friendTest = new User("test", "test@sapo.pt");
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

        User friendTest = new User("test", "test@sapo.pt");

        boolean result = testUser.removeFriendship(friendTest);
        assertFalse(result);
    }

    /**
     * Test of removeFriendship method, of class User.
     */
    @Test
    public void testRemoveFriendship05() {
        System.out.println("removeFriendship");

        Set<User> friendsOfFriend = new HashSet<>();
        friendsOfFriend.add(new User("nick7", "mail_7_@sapo.pt"));
        friendsOfFriend.add(new User("nick4", "mail_4_@sapo.pt"));
        friendsOfFriend.add(testUser);

        User friend = new User("nick0", "mail_0_@sapo.pt");
        friend.setFriends(friendsOfFriend);

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));

        testUser.removeFriendship(friend);
        Set<User> result = friend.getFriends();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkInAnewCity method, of class User.
     */
    @Test
    public void testCheckInAnewCity01() {
        System.out.println("checkInAnewCity");

        City cityTest = new City(new Pair(41.118700, -8.589700), "city3", 42);

        LinkedList<City> expResult = new LinkedList<>();
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

        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);

        LinkedList<City> expResult = new LinkedList<>();
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

        City cityTest = new City(new Pair(40.519841, -8.085113), "city2", 81);

        LinkedList<City> expResult = new LinkedList<>();
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));

        testUser.setVisitedCities(expResult);
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

        User userTest = new User();
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userTest = user1;
            }
        }

        Set<User> expResult = new HashSet<>();
        for (User user1 : userTest.getFriends()) {
            if (!user1.getNickname().equals("nick7")) {
                expResult.add(user1);
            }
        }

        Pair coordenates = new Pair(40.781886, -8.697502);

        Set<User> result = new HashSet<>();
        result = userTest.userFriendsInAGivenLocation(coordenates);

        assertEquals(expResult, result);
    }

    /**
     * Test of userFriendsInAGivenLocation method, of class User.
     */
    @Test
    public void testUserFriendsInAGivenLocation_City() {
        System.out.println("userFriendsInAGivenLocation");

        User userTest = new User();
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userTest = user1;
            }
        }

        Set<User> expResult = new HashSet<>();
        for (User user1 : userTest.getFriends()) {
            if (!user1.getNickname().equals("nick7")) {
                expResult.add(user1);
            }
        }

        City city8 = new City(new Pair(40.781886, -8.697502), "city8", 7);

        Set<User> result = new HashSet<>();
        result = userTest.userFriendsInAGivenLocation(city8);

        assertEquals(expResult, result);
    }

    /**
     * Test of isMayor method, of class User.
     */
    @Test
    public void testIsMayor01() {
        System.out.println("isMayor");

        User userTest = new User();
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick3")) {
                userTest = user1;
            }
        }
        
        City city8 = new City(new Pair(40.781886, -8.697502), "city8", 7);
        city8.setMayor(testUser);

        boolean result = userTest.isMayor(city8);
        assertTrue(result);
    }

    /**
     * Test of isMayor method, of class User.
     */
    @Test
    public void testIsMayor02() {
        System.out.println("isMayor");

        User userTest = new User();
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick3")) {
                userTest = user1;
            }
        }

        City city = new City();
        for (City city1 : sn10.getCitiesList()) {
            if (city1.getName().equals("city0")) {
                city = city1;
            }
        }

        boolean result = userTest.isMayor(city);
        assertFalse(result);
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
