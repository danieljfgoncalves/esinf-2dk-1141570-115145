package esinf_2dk_1141570_1151452.utils;

import esinf_2dk_1141570_1151452.model.City;
import esinf_2dk_1141570_1151452.model.SocialNetwork;
import esinf_2dk_1141570_1151452.model.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests File Manager
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class FileManagerTest {

    /**
     * Social Network object to test with 10 cities & 10 users.
     */
    SocialNetwork sn10;

    /**
     * Path for test saved cities file.
     */
    String citiesFilePath = "cities_test.txt";

    /**
     * Path for test saved users file.
     */
    String usersFilePath = "users_test.txt";

    public FileManagerTest() {

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));

    }

    @After
    public void tearDown() throws IOException {

        Files.deleteIfExists((new File(citiesFilePath)).toPath());
        Files.deleteIfExists((new File(usersFilePath)).toPath());
    }

    /**
     * Test of defaultCitiesFile method, of class FileManager.
     */
    @Test
    public void testDefaultCitiesFile() {
        System.out.println("defaultCitiesFile");
        String expResult = "test-files/files10/cities10.txt";
        String result = FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN);
        assertEquals(expResult, result);
    }

    /**
     * Test of defaultUsersFile method, of class FileManager.
     */
    @Test
    public void testDefaultUsersFile() {
        System.out.println("defaultUsersFile");
        String num = "";
        String expResult = "test-files/files100/users100.txt";
        String result = FileManager.defaultUsersFile(FileManager.DEFAULT_ONE_HUNDRED);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadSocialNetwork method, of class FileManager.
     */
    @Test
    public void testLoadSocialNetwork() {
        System.out.println("loadSocialNetwork");
        String citiesFilePath = "test-files/files10/cities10.txt";
        String usersFilePath = "test-files/files10/users10.txt";

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
        SocialNetwork expResult = new SocialNetwork(users, cities);

        SocialNetwork result = FileManager.loadSocialNetwork(citiesFilePath, usersFilePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveSocialNetwork method, of class FileManager.
     */
    @Test
    public void testSaveSocialNetwork01() {
        System.out.println("saveSocialNetwork");

        boolean result = FileManager.saveSocialNetwork(sn10, citiesFilePath, usersFilePath);
        assertTrue(result);
    }

    /**
     * Test of saveSocialNetwork method, of class FileManager.
     */
    @Test
    public void testSaveSocialNetwork02() {
        System.out.println("saveSocialNetwork");

        boolean result = FileManager.saveSocialNetwork(null, citiesFilePath, usersFilePath);
        assertFalse(result);
    }

    /**
     * Test of saveSocialNetwork method, of class FileManager.
     */
    @Test
    public void testSaveSocialNetwork03() throws IOException {
        System.out.println("saveSocialNetwork");

        FileManager.saveSocialNetwork(sn10, citiesFilePath, usersFilePath);
        
        File savedCities = new File(citiesFilePath);
        File dataSetCities = new File(FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN));

        List lines1 = Files.readAllLines(savedCities.toPath());
        Collections.sort(lines1);
        
        List lines2 = Files.readAllLines(dataSetCities.toPath());
        
        assertEquals(lines1, lines2);
    }

}
//        File savedUsers = new File(usersFilePath);
//        File dataSetUsers = new File(FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));
