package model;

import bsts.AVL;
import utils.FileManager;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javafx.util.Pair;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a Social Network
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class SocialNetworkTest {

    /**
     * Social Network object to test with 10 cities & 10 users.
     */
    SocialNetwork sn10;
//
//    /**
//     * Social Network object to test with 10 cities & 100 users.
//     */
//    SocialNetwork sn100;
//
//    /**
//     * Social Network object to test with 10 cities & 300 users.
//     */
//    SocialNetwork sn300;

    public SocialNetworkTest() {

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));

        FileManager.loadCitiesGraph(sn10, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_TEN));
        FileManager.loadFriendshipGraph(sn10);

        // Commented so testes of 3rd part can performe faster
//
//        sn100 = FileManager.loadSocialNetwork(
//                FileManager.defaultCitiesFile(FileManager.DEFAULT_ONE_HUNDRED),
//                FileManager.defaultUsersFile(FileManager.DEFAULT_ONE_HUNDRED));
//
//        FileManager.loadCitiesGraph(sn100, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_ONE_HUNDRED));
//        FileManager.loadFriendshipGraph(sn100);
//
//        sn300 = FileManager.loadSocialNetwork(
//                FileManager.defaultCitiesFile(FileManager.DEFAULT_THREE_HUNDRED),
//                FileManager.defaultUsersFile(FileManager.DEFAULT_THREE_HUNDRED));
//
//        FileManager.loadCitiesGraph(sn300, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_THREE_HUNDRED));
//        FileManager.loadFriendshipGraph(sn300);
    }

    /**
     * Test of getUsersList method, of class SocialNetwork.
     */
    @Test
    public void testGetUsersList() {
        System.out.println("getUsersList");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick6", "mail_6_@sapo.pt"));
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));

        Set<User> result = sn10.getUsersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsersList method, of class SocialNetwork.
     */
    @Test
    public void testSetUsersList() {
        System.out.println("setUsersList");
        Set<User> usersList = new HashSet<>();
        usersList.add(new User("test1", "test@email.com"));
        sn10.setUsersList(usersList);
        assertEquals(usersList, sn10.getUsersList());
    }

    /**
     * Test of getCitiesList method, of class SocialNetwork.
     */
    @Test
    public void testGetCitiesList() {
        System.out.println("getCitiesList");
        Set<City> expResult = new HashSet<>();
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        expResult.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        Set<City> result = sn10.getCitiesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCitiesList method, of class SocialNetwork.
     */
    @Test
    public void testSetCitiesList() {
        System.out.println("setCitiesList");
        Set<City> citiesList = new HashSet<>();

        sn10.setCitiesList(citiesList);
        assertEquals(citiesList, sn10.getCitiesList());
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_User01() {
        System.out.println("addUser");
        User user = new User("test", "test@email.com");
        boolean result = sn10.addUser(user);
        assertTrue(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_User02() {
        System.out.println("addUser");
        User user = new User("nick0", "mail_0_@sapo.pt");
        boolean result = sn10.addUser(user);
        assertFalse(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_User03() {
        System.out.println("addUser");
        User user = new User("test", "test@email.com");
        sn10.addUser(user);
        boolean result = sn10.getUsersList().contains(user);
        assertTrue(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_User04() {
        System.out.println("addUser");

        User user = new User("test", "test@email.com");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick6", "mail_6_@sapo.pt"));
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));
        expResult.add(user);

        sn10.addUser(user);
        Set<User> result = sn10.getUsersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_Nickname_Email01() {
        System.out.println("addUser");
        String nickname = "test1";
        String email = "test1@email.com";
        boolean result = sn10.addUser(nickname, email);
        assertTrue(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_Nickname_Email02() {
        System.out.println("addUser");
        String nickname = "nick1";
        String email = "mail_1_@sapo.pt";
        boolean result = sn10.addUser(nickname, email);
        assertFalse(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_Nickname_Email03() {
        System.out.println("addUser");
        String nickname = "test1";
        String email = "test1@email.com";
        sn10.addUser(nickname, email);

        boolean result = sn10.getUsersList().contains(new User(nickname, email));
        assertTrue(result);
    }

    /**
     * Test of addUser method, of class SocialNetwork.
     */
    @Test
    public void testAddUser_Nickname_Email04() {
        System.out.println("addUser");
        String nickname = "test1";
        String email = "test_1_@sapo.pt";

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick6", "mail_6_@sapo.pt"));
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));
        expResult.add(new User(nickname, email));

        sn10.addUser(nickname, email);
        Set<User> result = sn10.getUsersList();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of removeUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testRemoveUser_User01() {
//        System.out.println("removeUser");
//        User user = sn100.getUsersList().iterator().next();
//
//        boolean result = sn100.removeUser(user);
//        assertTrue(result);
//    }
//    /**
//     * Test of removeUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testRemoveUser_User02() {
//        System.out.println("removeUser");
//        User user = new User();
//
//        boolean result = sn300.removeUser(user);
//        assertFalse(result);
//    }
    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_User03() {
        System.out.println("removeUser");
        User user = new User("nick0", "mail_0_@sapo.pt");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick6", "mail_6_@sapo.pt"));
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));

        sn10.removeUser(user);
        Set<User> result = sn10.getUsersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_User04() {
        System.out.println("removeUser");
        User user = new User("nick10", "mail_10_@sapo.pt");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick0", "mail_0_@sapo.pt"));
        expResult.add(new User("nick1", "mail_1_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick6", "mail_6_@sapo.pt"));
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));

        sn10.removeUser(user);
        Set<User> result = sn10.getUsersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_User05() {
        System.out.println("removeUser");

        User friend = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick4")) {
                friend = user1;
            }
        }

        Set<User> friends = new HashSet<>();
        friends.add(new User("nick7", "mail_7_@sapo.pt"));
        friends.add(new User("nick3", "mail_3_@sapo.pt"));
        friends.add(friend);

        User user = new User("nick0", "mail_0_@sapo.pt");
        user.setFriends(friends);
        sn10.removeUser(user);

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick9", "mail_9_@sapo.pt"));

        Set<User> result = friend.getFriends();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of removeUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testRemoveUser_String01() {
//        System.out.println("removeUser");
//        String nickname = sn100.getUsersList().iterator().next().getNickname();
//
//        boolean result = sn100.removeUser(nickname);
//        assertTrue(result);
//    }
//    /**
//     * Test of removeUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testRemoveUser_String02() {
//        System.out.println("removeUser");
//        String nickname = "test";
//
//        boolean result = sn100.removeUser(nickname);
//        assertFalse(result);
//    }
    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_String03() {
        System.out.println("removeUser");

        User friend = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick7")) {
                friend = user1;
            }
        }

        sn10.removeUser("nick0");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick2", "mail_2_@sapo.pt"));

        Set<User> result = friend.getFriends();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of hasUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testHasUser_User01() {
//        System.out.println("hasUser");
//        User user = new User("nick46", "mail_46_@sapo.pt");
//        boolean result = sn300.hasUser(user);
//        assertTrue(result);
//    }
//    /**
//     * Test of hasUser method, of class SocialNetwork.
//     */
//    @Test
//    public void testHasUser_User02() {
//        System.out.println("hasUser");
//        User user = new User("nick466", "mail_466_@sapo.pt");
//        boolean result = sn300.hasUser(user);
//        assertFalse(result);
//    }
    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_City01() {

        System.out.println("addCity");
        City cityTest = new City(new Pair(41.200000, -8.000000), "cityTest", 30);
        Set<City> expResult = new HashSet<>();

        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        expResult.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));
        expResult.add(cityTest);

        sn10.addCity(cityTest);
        Set<City> result = sn10.getCitiesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_City02() {

        System.out.println("addCity");
        City cityTest = new City(new Pair(41.200000, -8.000000), "cityTest", 30);

        boolean result = sn10.addCity(cityTest);
        assertTrue(result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_City03() {

        System.out.println("addCity");
        City cityTest = new City(new Pair(41.243345, -8.674084), "city0", 28);
        Set<City> expResult = new HashSet<>();

        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        expResult.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        sn10.addCity(cityTest);
        Set<City> result = sn10.getCitiesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_City04() {

        System.out.println("addCity");
        City cityTest = new City(new Pair(41.243345, -8.674084), "city0", 28);

        boolean result = sn10.addCity(cityTest);
        assertFalse(result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_3args01() {

        System.out.println("addCity_3args");
        String cityName = "cityTest";
        Pair coordenates = new Pair(41.200000, -8.000000);
        int points = 30;
        City cityTest = new City(coordenates, cityName, points);
        Set<City> expResult = new HashSet<>();

        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        expResult.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));
        expResult.add(cityTest);

        sn10.addCity(coordenates, cityName, points);
        Set<City> result = sn10.getCitiesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_3args02() {

        System.out.println("addCity_3args");
        String cityName = "cityTest";
        Pair coordenates = new Pair(41.200000, -8.000000);
        int points = 30;

        boolean result = sn10.addCity(coordenates, cityName, points);
        assertTrue(result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_3args03() {

        System.out.println("addCity_3args");
        String cityName = "city0";
        Pair coordenates = new Pair(41.243345, -8.674084);
        int points = 28;
        City cityTest = new City(coordenates, cityName, points);
        Set<City> expResult = new HashSet<>();

        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        expResult.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        expResult.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        sn10.addCity(coordenates, cityName, points);
        Set<City> result = sn10.getCitiesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
    @Test
    public void testAddCity_3args04() {

        System.out.println("addCity_3args");
        String cityName = "city0";
        Pair coordenates = new Pair(41.243345, -8.674084);
        int points = 28;

        boolean result = sn10.addCity(coordenates, cityName, points);
        assertFalse(result);
    }

    /**
     * Test of updateMayors method, of class SocialNetwork.
     */
    @Test
    public void testUpdateMayors() {
        System.out.println("updateMayors");

        Set<City> cities1 = new HashSet<>();
        cities1.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        cities1.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        cities1.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        cities1.add(new City(new Pair(41.118700, -8.589700), "city3", 42));

        Set<City> cities2 = new HashSet<>();
        City city0 = (new City(new Pair(41.243345, -8.674084), "city0", 28));
        city0.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        cities2.add(city0);
        City city1 = (new City(new Pair(41.237364, -8.846746), "city1", 72));
        city1.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        cities2.add(city1);
        City city2 = (new City(new Pair(40.519841, -8.085113), "city2", 81));
        city2.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        cities2.add(city2);
        City city3 = (new City(new Pair(41.118700, -8.589700), "city3", 42));
        cities2.add(city3);
        city3.setMayor(new User("nick8", "mail_8_@sapo.pt"));

        SocialNetwork sn = new SocialNetwork(sn10.getUsersList(), cities1);
        sn.updateMayors();

        List<User> result = new LinkedList<>();
        for (City city : sn.getCitiesList()) {
            result.add(city.getMayor());
        }
        List<User> expResult = new LinkedList<>();
        for (City city : cities2) {
            expResult.add(city.getMayor());
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of listMayors method, of class SocialNetwork.
     */
    @Test
    public void testListMayors01() {
        System.out.println("listMayors");

        Map expResult = new LinkedHashMap();

        City city1 = (new City(new Pair(41.237364, -8.846746), "city1", 72));
        city1.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city1, city1.getMayor());

        City city5 = (new City(new Pair(41.337408, -8.291943), "city5", 74));
        city5.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city5, city5.getMayor());

        City city6 = (new City(new Pair(41.314965, -8.423371), "city6", 80));
        city6.setMayor(new User("nick0", "mail_0_@sapo.pt"));
        expResult.put(city6, city6.getMayor());

        City city3 = (new City(new Pair(41.118700, -8.589700), "city3", 42));
        city3.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city3, city3.getMayor());

        City city4 = (new City(new Pair(41.467407, -8.964340), "city4", 64));
        city4.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city4, city4.getMayor());

        City city0 = (new City(new Pair(41.243345, -8.674084), "city0", 28));
        city0.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city0, city0.getMayor());

        City city2 = (new City(new Pair(40.519841, -8.085113), "city2", 81));
        city2.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city2, city2.getMayor());

        City city7 = (new City(new Pair(40.822244, -8.794953), "city7", 11));
        city7.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city7, city7.getMayor());

        City city9 = (new City(new Pair(40.851360, -8.136585), "city9", 65));
        city9.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city9, city9.getMayor());

        City city8 = (new City(new Pair(40.781886, -8.697502), "city8", 7));
        city8.setMayor(new User("nick3", "mail_3_@sapo.pt"));
        expResult.put(city8, city8.getMayor());

        Map result = sn10.listMayors();

        // To compare Keys.
        assertArrayEquals(expResult.keySet().toArray(), result.keySet().toArray());
    }

    /**
     * Test of listMayors method, of class SocialNetwork.
     */
    @Test
    public void testListMayors02() {
        System.out.println("listMayors");

        Map expResult = new LinkedHashMap();

        City city1 = (new City(new Pair(41.237364, -8.846746), "city1", 72));
        city1.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city1, city1.getMayor());

        City city5 = (new City(new Pair(41.337408, -8.291943), "city5", 74));
        city5.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city5, city5.getMayor());

        City city6 = (new City(new Pair(41.314965, -8.423371), "city6", 80));
        city6.setMayor(new User("nick0", "mail_0_@sapo.pt"));
        expResult.put(city6, city6.getMayor());

        City city3 = (new City(new Pair(41.118700, -8.589700), "city3", 42));
        city3.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city3, city3.getMayor());

        City city4 = (new City(new Pair(41.467407, -8.964340), "city4", 64));
        city4.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city4, city4.getMayor());

        City city0 = (new City(new Pair(41.243345, -8.674084), "city0", 28));
        city0.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city0, city0.getMayor());

        City city2 = (new City(new Pair(40.519841, -8.085113), "city2", 81));
        city2.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city2, city2.getMayor());

        City city7 = (new City(new Pair(40.822244, -8.794953), "city7", 11));
        city7.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city7, city7.getMayor());

        City city9 = (new City(new Pair(40.851360, -8.136585), "city9", 65));
        city9.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city9, city9.getMayor());

        City city8 = (new City(new Pair(40.781886, -8.697502), "city8", 7));
        city8.setMayor(new User("nick3", "mail_3_@sapo.pt"));
        expResult.put(city8, city8.getMayor());

        Map result = sn10.listMayors();

        // To compare Values.
        assertArrayEquals(expResult.values().toArray(), result.values().toArray());
    }

    /**
     * Test of listMayors method, of class SocialNetwork.
     */
    @Test
    public void testListMayors03() {
        System.out.println("listMayors");

        Map expResult = new LinkedHashMap();

        City city1 = (new City(new Pair(41.237364, -8.846746), "city1", 72));
        city1.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city1, city1.getMayor());

        City city5 = (new City(new Pair(41.337408, -8.291943), "city5", 74));
        city5.setMayor(new User("nick6", "mail_6_@sapo.pt"));
        expResult.put(city5, city5.getMayor());

        City city6 = (new City(new Pair(41.314965, -8.423371), "city6", 80));
        city6.setMayor(new User("nick0", "mail_0_@sapo.pt"));
        expResult.put(city6, city6.getMayor());

        City city3 = (new City(new Pair(41.118700, -8.589700), "city3", 42));
        city3.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city3, city3.getMayor());

        City city4 = (new City(new Pair(41.467407, -8.964340), "city4", 64));
        city4.setMayor(new User("nick8", "mail_8_@sapo.pt"));
        expResult.put(city4, city4.getMayor());

        City city0 = (new City(new Pair(41.243345, -8.674084), "city0", 28));
        city0.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city0, city0.getMayor());

        City city2 = (new City(new Pair(40.519841, -8.085113), "city2", 81));
        city2.setMayor(new User("nick2", "mail_2_@sapo.pt"));
        expResult.put(city2, city2.getMayor());

        City city7 = (new City(new Pair(40.822244, -8.794953), "city7", 11));
        city7.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city7, city7.getMayor());

        City city9 = (new City(new Pair(40.851360, -8.136585), "city9", 65));
        city9.setMayor(new User("nick5", "mail_5_@sapo.pt"));
        expResult.put(city9, city9.getMayor());

        City city8 = (new City(new Pair(40.781886, -8.697502), "city8", 7));
        city8.setMayor(new User("nick3", "mail_3_@sapo.pt"));
        expResult.put(city8, city8.getMayor());

        Map result = new LinkedHashMap(sn10.listMayors());

        // To compare Values.
        assertThat(result, is(expResult));
    }

    /**
     * Test of getInfluentialUsers method, of class SocialNetwork.
     */
    @Test
    public void testGetInfluentialUsers01() {
        System.out.println("getInfluentialUsers");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick3", "mail_3_@sapo.pt"));
        expResult.add(new User("nick5", "mail_5_@sapo.pt"));
        expResult.add(new User("nick8", "mail_8_@sapo.pt"));

        Set<User> result = sn10.getInfluentialUsers();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getInfluentialUsers method, of class SocialNetwork.
//     */
//    @Test
//    public void testGetInfluentialUsers02() {
//        System.out.println("getInfluentialUsers");
//
//        Set<User> expResult = new HashSet<>();
//        expResult.add(new User("nick99", "mail_99_@sapo.pt"));
//        Set<User> result = sn100.getInfluentialUsers();
//        assertEquals(expResult, result);
//    }
//    /**
//     * Test of getInfluentialUsers method, of class SocialNetwork.
//     */
//    @Test
//    public void testGetInfluentialUsers03() {
//        System.out.println("getInfluentialUsers");
//
//        Set<User> expResult = new HashSet<>();
//        expResult.add(new User("nick267", "mail_267_@sapo.pt"));
//        Set<User> result = sn300.getInfluentialUsers();
//        assertEquals(expResult, result);
//    }
    /**
     * Test of getNearbyFriendsInAgivenDistance method, of class SocialNetwork.
     */
    @Test
    public void testGetNearbyFriendsInAgivenDistance01() {
        System.out.println("getNearbyFriendsInAgivenDistance");
        User user = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                user = user1;
            }
        }
        Double distance = 70.0;
        HashSet<User> expResult = new HashSet();
        expResult.add(new User("nick7", "mail_7_@sapo.pt"));

        HashSet<User> result = sn10.getNearbyFriendsInAgivenDistance(user, distance);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getNearbyFriendsInAgivenDistance method, of class SocialNetwork.
     */
    @Test
    public void testGetNearbyFriendsInAgivenDistance02() {
        System.out.println("getNearbyFriendsInAgivenDistance");
        User user = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                user = user1;
            }
        }
        Double distance = 70.0;
        HashSet<User> expResult = new HashSet();
        expResult.add(new User("nick4", "mail_4_@sapo.pt"));

        HashSet<User> result = sn10.getNearbyFriendsInAgivenDistance(user, distance);
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of getShortestPath method, of class SocialNetwork.
     */
    @Test
    public void testGetShortestPath01() {
        System.out.println("getShortestPath");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick7")) {
                userB = user1;
            }
        }
        LinkedList<City> shortestPathCities = new LinkedList();
        Double expResult = 66.0;
        Double result = sn10.getShortestPath(userA, userB, shortestPathCities);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getShortestPath method, of class SocialNetwork.
     */
    @Test
    public void testGetShortestPath02() {
        System.out.println("getShortestPath");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick7")) {
                userB = user1;
            }
        }
        LinkedList<City> shortestPathCities = new LinkedList();
        Double expResult = 76.0;
        Double result = sn10.getShortestPath(userA, userB, shortestPathCities);
        assertFalse(expResult.intValue() == result.intValue());
    }

    /**
     * Test of getShortestPath method, of class SocialNetwork.
     */
    @Test
    public void testGetShortestPath03() {
        System.out.println("getShortestPath");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick7")) {
                userB = user1;
            }
        }

        LinkedList<City> expResult = new LinkedList();
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        LinkedList<City> result = new LinkedList();
        sn10.getShortestPath(userA, userB, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortestPath method, of class SocialNetwork.
     */
    @Test
    public void testGetShortestPath04() {
        System.out.println("getShortestPath");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick7")) {
                userB = user1;
            }
        }

        LinkedList<City> expResult = new LinkedList();

        LinkedList<City> result = new LinkedList();
        sn10.getShortestPath(userA, userB, result);
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of shrtPathPassingCitiesWithMostFriends method, of class
     * SocialNetwork.
     */
    @Test
    public void testShrtPathPassingCitiesWithMostFriends01() {
        System.out.println("shrtPathPassingCitiesWithMostFriends");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick9")) {
                userB = user1;
            }
        }
        LinkedList<City> expResult = new LinkedList<>();
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));

        LinkedList<City> result = sn10.shrtPathPassingCitiesWithMostFriends(userA, userB);
        assertEquals(expResult, result);
    }

    /**
     * Test of shrtPathPassingCitiesWithMostFriends method, of class
     * SocialNetwork.
     */
    @Test
    public void testShrtPathPassingCitiesWithMostFriends() {
        System.out.println("shrtPathPassingCitiesWithMostFriends");
        User userA = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick0")) {
                userA = user1;
            }
        }
        User userB = null;
        for (User user1 : sn10.getUsersList()) {
            if (user1.getNickname().equals("nick9")) {
                userB = user1;
            }
        }
        LinkedList<City> expResult = new LinkedList<>();
        expResult.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        expResult.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        expResult.add(new City(new Pair(40.851360, -8.136585), "city9", 65));
        expResult.add(new City(new Pair(40.781886, -8.697502), "city8", 7));

        LinkedList<City> result = sn10.shrtPathPassingCitiesWithMostFriends(userA, userB);
        assertFalse(expResult.equals(result));
    }

    // ****  3rd PART  **** //
    // ****     4.     **** // 
    /**
     * Test of createTree method, of class MayorAVL.
     */
    @Test
    public void testSetMayorsSearchTree01() {

        System.out.println("setMayorsSearchTree");
        // Converted to TreeSet so it would preserve the order but exclude repetition (Can be the same mayor of more than one city)
        Set<User> expResult = new TreeSet<>(sn10.listMayors().values());
        sn10.setMayorsSearchTree();
        List<User> result = (List<User>) sn10.getMayorsAVL().inOrder();

        // Test if Mayors are order by descending order & if AVL Tree is ordered & has the same size
        // listMayors already returns a collection order by mayors score
        assertArrayEquals(expResult.toArray(), result.toArray());

    }

    /**
     * Test of createTree method, of class MayorAVL.
     */
    @Test
    public void testSetMayorsSearchTree02() {

        System.out.println("setMayorsSearchTree");
        MayorAVL expResult = new MayorAVL();
        sn10.setMayorsSearchTree();

        MayorAVL result = sn10.getMayorsAVL();

        // Verify if tree is not equal to empty list.
        assertFalse(expResult.equals(result));

    }

    /**
     * Test of setCitiesSearchTree method, of class SocialNetwork.
     */
    @Test
    public void testSetCitiesSearchTree01() {
        System.out.println("setCitiesSearchTree");
        sn10.setCitiesSearchTree();

        // Create order list with expected result
        List<CityAndUsers> expResult = new LinkedList<>();
        expResult.add(new CityAndUsers(new City(new Pair(41.243345, -8.674084), "city0", 28), 0));
        expResult.add(new CityAndUsers(new City(new Pair(41.237364, -8.846746), "city1", 72), 0));
        expResult.add(new CityAndUsers(new City(new Pair(40.822244, -8.794953), "city7", 11), 0));
        expResult.add(new CityAndUsers(new City(new Pair(40.519841, -8.085113), "city2", 81), 1));
        expResult.add(new CityAndUsers(new City(new Pair(41.118700, -8.589700), "city3", 42), 1));
        expResult.add(new CityAndUsers(new City(new Pair(41.467407, -8.964340), "city4", 64), 1));
        expResult.add(new CityAndUsers(new City(new Pair(41.337408, -8.291943), "city5", 74), 1));
        expResult.add(new CityAndUsers(new City(new Pair(41.314965, -8.423371), "city6", 80), 1));
        expResult.add(new CityAndUsers(new City(new Pair(40.851360, -8.136585), "city9", 65), 2));
        expResult.add(new CityAndUsers(new City(new Pair(40.781886, -8.697502), "city8", 7), 3));

        List<CityAndUsers> result = (List<CityAndUsers>) sn10.getCitiesAVL().inOrder();

        // Test if cities are ordered by ascendent order of users checked in & if AVL Tree is ordered & has the same size
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of setCitiesSearchTree method, of class SocialNetwork.
     */
    @Test
    public void testSetCitiesSearchTree02() {
        System.out.println("setCitiesSearchTree");
        sn10.setCitiesSearchTree();

        AVL<CityAndUsers> expResult = new AVL<>();
        
        AVL<CityAndUsers> result = sn10.getCitiesAVL();

        // Verify if tree is not equal to empty list.
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of equals method, of class SocialNetwork.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object otherObject = new SocialNetwork();
        SocialNetwork sn = new SocialNetwork();
        boolean result = sn.equals(otherObject);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class SocialNetwork.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object otherObject = new SocialNetwork();
        boolean result = sn10.equals(otherObject);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class SocialNetwork.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");

        Set<User> users = new HashSet<>();
        users.add(new User("nick0", "mail_0_@sapo.pt"));
        users.add(new User("nick1", "mail_1_@sapo.pt"));
        users.add(new User("nick2", "mail_2_@sapo.pt"));
        users.add(new User("nick3", "mail_3_@sapo.pt"));
        users.add(new User("nick4", "mail_4_@sapo.pt"));
        users.add(new User("nick5", "mail_5_@sapo.pt"));
        users.add(new User("nick6", "mail_6_@sapo.pt"));
        users.add(new User("nick7", "mail_7_@sapo.pt"));
        users.add(new User("nick8", "mail_8_@sapo.pt"));
        users.add(new User("nick9", "mail_9_@sapo.pt"));

        Set<City> cities = new HashSet<>();
        cities.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        cities.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        cities.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        cities.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        cities.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        cities.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        cities.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        cities.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        cities.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        cities.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        SocialNetwork otherSN = new SocialNetwork(users, cities);
        boolean result = sn10.equals(otherSN);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class SocialNetwork.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");

        Set<City> cities = new HashSet<>();
        cities.add(new City(new Pair(41.243345, -8.674084), "city0", 28));
        cities.add(new City(new Pair(41.237364, -8.846746), "city1", 72));
        cities.add(new City(new Pair(40.519841, -8.085113), "city2", 81));
        cities.add(new City(new Pair(41.118700, -8.589700), "city3", 42));
        cities.add(new City(new Pair(41.467407, -8.964340), "city4", 64));
        cities.add(new City(new Pair(41.337408, -8.291943), "city5", 74));
        cities.add(new City(new Pair(41.314965, -8.423371), "city6", 80));
        cities.add(new City(new Pair(40.822244, -8.794953), "city7", 11));
        cities.add(new City(new Pair(40.781886, -8.697502), "city8", 7));
        cities.add(new City(new Pair(40.851360, -8.136585), "city9", 65));

        Object otherObject = new SocialNetwork(new HashSet(), cities);
        boolean result = sn10.equals(otherObject);
        assertFalse(result);
    }
}
