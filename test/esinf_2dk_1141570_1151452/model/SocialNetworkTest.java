package esinf_2dk_1141570_1151452.model;

import esinf_2dk_1141570_1151452.utils.FileManager;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    /**
     * Social Network object to test with 10 cities & 100 users.
     */
    SocialNetwork sn100;

    /**
     * Social Network object to test with 10 cities & 300 users.
     */
    SocialNetwork sn300;

    public SocialNetworkTest() {

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));

        sn100 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_ONE_HUNDRED),
                FileManager.defaultUsersFile(FileManager.DEFAULT_ONE_HUNDRED));

        sn300 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_THREE_HUNDRED),
                FileManager.defaultUsersFile(FileManager.DEFAULT_THREE_HUNDRED));

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

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_User01() {
        System.out.println("removeUser");
        User user = sn100.getUsersList().iterator().next();

        boolean result = sn100.removeUser(user);
        assertTrue(result);
    }

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_User02() {
        System.out.println("removeUser");
        User user = new User();

        boolean result = sn300.removeUser(user);
        assertFalse(result);
    }

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

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_String01() {
        System.out.println("removeUser");
        String nickname = sn100.getUsersList().iterator().next().getNickname();

        boolean result = sn100.removeUser(nickname);
        assertTrue(result);
    }

    /**
     * Test of removeUser method, of class SocialNetwork.
     */
    @Test
    public void testRemoveUser_String02() {
        System.out.println("removeUser");
        String nickname = "test";

        boolean result = sn100.removeUser(nickname);
        assertFalse(result);
    }

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

    /**
     * Test of hasUser method, of class SocialNetwork.
     */
    @Test
    public void testHasUser_User01() {
        System.out.println("hasUser");
        User user = new User("nick46", "mail_46_@sapo.pt");
        boolean result = sn300.hasUser(user);
        assertTrue(result);
    }

    /**
     * Test of hasUser method, of class SocialNetwork.
     */
    @Test
    public void testHasUser_User02() {
        System.out.println("hasUser");
        User user = new User("nick466", "mail_466_@sapo.pt");
        boolean result = sn300.hasUser(user);
        assertFalse(result);
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
//    @Test (ERIC)
    public void testAddCity_City() {
        System.out.println("addCity");
        City city = null;
        SocialNetwork instance = new SocialNetwork();
        boolean expResult = false;
        boolean result = instance.addCity(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCity method, of class SocialNetwork.
     */
//    @Test  (ERIC)
    public void testAddCity_3args() {
        System.out.println("addCity");
        Pair<Double, Double> coordinates = null;
        String name = "";
        int points = 0;
        SocialNetwork instance = new SocialNetwork();
        boolean expResult = false;
        boolean result = instance.addCity(coordinates, name, points);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

        System.out.println("result:");
        List<User> result = new LinkedList<>();
        for (City city : sn.getCitiesList()) {
            result.add(city.getMayor());
            System.out.println(city.getMayor().getNickname());
        }
        System.out.println("expResult:");
        List<User> expResult = new LinkedList<>();
        for (City city : cities2) {
            expResult.add(city.getMayor());
            System.out.println(city.getMayor().getNickname());
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

    /**
     * Test of getInfluentialUsers method, of class SocialNetwork.
     */
    @Test
    public void testGetInfluentialUsers02() {
        System.out.println("getInfluentialUsers");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick99", "mail_99_@sapo.pt"));
        Set<User> result = sn100.getInfluentialUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInfluentialUsers method, of class SocialNetwork.
     */
    @Test
    public void testGetInfluentialUsers03() {
        System.out.println("getInfluentialUsers");

        Set<User> expResult = new HashSet<>();
        expResult.add(new User("nick267", "mail_267_@sapo.pt"));
        Set<User> result = sn300.getInfluentialUsers();
        assertEquals(expResult, result);
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
