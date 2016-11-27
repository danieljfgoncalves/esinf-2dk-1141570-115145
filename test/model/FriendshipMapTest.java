package model;

import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.FileManager;

/**
 * Tests FriendshipMap class
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt TurmaDN
 */
public class FriendshipMapTest {

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

    public FriendshipMapTest() {

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));

        FileManager.loadCitiesGraph(sn10, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_TEN));
        FileManager.loadFriendshipGraph(sn10);

        sn100 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_ONE_HUNDRED),
                FileManager.defaultUsersFile(FileManager.DEFAULT_ONE_HUNDRED));

        FileManager.loadCitiesGraph(sn100, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_ONE_HUNDRED));
        FileManager.loadFriendshipGraph(sn100);

        sn300 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_THREE_HUNDRED),
                FileManager.defaultUsersFile(FileManager.DEFAULT_THREE_HUNDRED));

        FileManager.loadCitiesGraph(sn300, FileManager.defaultCityConnectionsFile(FileManager.DEFAULT_THREE_HUNDRED));
        FileManager.loadFriendshipGraph(sn300);

    }

    /**
     * Test of getRelationshipDistance method, of class FriendshipMap.
     */
    @Test
    public void testGetRelationshipDistance01() {
        System.out.println("getRelationshipDistance");
        User user0 = new User("nick0", "mail_0_@sapo.pt");
        User user9 = new User("nick9", "mail_9_@sapo.pt");
        FriendshipMap instance = sn10.getFriendshipMap();
        int expResult = 1; // user0--user4--user9 = 1
        int result = instance.getRelationshipDistance(user0, user9);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRelationshipDistance method, of class FriendshipMap.
     */
    @Test
    public void testGetRelationshipDistance02() {
        System.out.println("getRelationshipDistance");
        User user0 = new User("nick0", "mail_0_@sapo.pt");
        User user9 = new User("nick9", "mail_9_@sapo.pt");
        FriendshipMap instance = sn10.getFriendshipMap();
        int expResult = 3; // user0--user4--user9 = 1
        int result = instance.getRelationshipDistance(user0, user9);
        assertFalse(expResult == result);
    }

    /**
     * Test of findRelatedUser method, of class FriendshipMap.
     */
    @Test
    public void testFindRelatedUser01() {
        System.out.println("findRelatedUser");
        User source = new User("nick0", "mail_0_@sapo.pt");
        int distance = 1; //user0 in 10 users just dont have connection with user1 in a distance = 1 x--x--x
        FriendshipMap instance = sn10.getFriendshipMap();

        HashSet<User> relatedUsers = new HashSet();
        relatedUsers.add(new User("nick2", "mail_2_@sapo.pt"));
        relatedUsers.add(new User("nick3", "mail_3_@sapo.pt"));
        relatedUsers.add(new User("nick4", "mail_4_@sapo.pt"));
        relatedUsers.add(new User("nick5", "mail_5_@sapo.pt"));
        relatedUsers.add(new User("nick6", "mail_6_@sapo.pt"));
        relatedUsers.add(new User("nick7", "mail_7_@sapo.pt"));
        relatedUsers.add(new User("nick8", "mail_8_@sapo.pt"));
        relatedUsers.add(new User("nick9", "mail_9_@sapo.pt"));

        Iterable<User> expResult = new HashSet<User>(relatedUsers);

        Iterable<User> result = instance.findRelatedUser(source, distance);
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of findRelatedUser method, of class FriendshipMap.
     */
    @Test
    public void testFindRelatedUser02() {
        System.out.println("findRelatedUser");
        User source = new User("nick0", "mail_0_@sapo.pt");
        int distance = 1;
        FriendshipMap instance = sn10.getFriendshipMap();

        HashSet<User> relatedUsers = new HashSet();
        relatedUsers.add(new User("nick2", "mail_2_@sapo.pt"));
        relatedUsers.add(new User("nick3", "mail_3_@sapo.pt"));
        relatedUsers.add(new User("nick4", "mail_4_@sapo.pt"));
        relatedUsers.add(new User("nick5", "mail_5_@sapo.pt"));
        relatedUsers.add(new User("nick6", "mail_6_@sapo.pt"));
        relatedUsers.add(new User("nick7", "mail_7_@sapo.pt"));
        relatedUsers.add(new User("nick8", "mail_8_@sapo.pt"));

        Iterable<User> expResult = new HashSet<User>(relatedUsers);

        Iterable<User> result = instance.findRelatedUser(source, distance);
        assertFalse(result.equals(expResult));
    }

    /**
     * Test of mostInfluentialNetworkUsers method, of class FriendshipMap.
     */
    //@Test TODO: Daniel
    public void testMostInfluentialNetworkUsers01() {
        System.out.println("mostInfluentialNetworkUsers");
        FriendshipMap instance = sn10.getFriendshipMap();

        HashSet<User> influentialUsers = new HashSet();
        influentialUsers.add(new User("nick3", "mail_3_@sapo.pt"));
        influentialUsers.add(new User("nick5", "mail_5_@sapo.pt"));
        influentialUsers.add(new User("nick7", "mail_7_@sapo.pt"));
        influentialUsers.add(new User("nick8", "mail_8_@sapo.pt"));
        influentialUsers.add(new User("nick9", "mail_9_@sapo.pt"));

        Iterable<User> expResult = new HashSet<>(influentialUsers);
        Iterable<User> result = instance.mostInfluentialNetworkUsers();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of mostInfluentialNetworkUsers method, of class FriendshipMap.
     */
    //@Test TODO: Daniel
    public void testMostInfluentialNetworkUsers02() {
        System.out.println("mostInfluentialNetworkUsers");
        FriendshipMap instance = sn10.getFriendshipMap();

        HashSet<User> influentialUsers = new HashSet();
        influentialUsers.add(new User("nick3", "mail_3_@sapo.pt"));

        Iterable<User> expResult = new HashSet<>(influentialUsers);
        Iterable<User> result = instance.mostInfluentialNetworkUsers();
        assertFalse(result.equals(expResult));
    }

}
