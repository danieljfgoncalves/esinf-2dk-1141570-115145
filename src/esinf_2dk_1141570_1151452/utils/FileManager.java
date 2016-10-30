package esinf_2dk_1141570_1151452.utils;

import esinf_2dk_1141570_1151452.model.City;
import esinf_2dk_1141570_1151452.model.SocialNetwork;
import esinf_2dk_1141570_1151452.model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import javafx.util.Pair;

/**
 * Represents a class to manage read & write of the text files used for data
 * persistency.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class FileManager {

    /**
     * Text file reader.
     */
    static BufferedReader input;

    /**
     * Text file writer.
     */
    static BufferedWriter output;

    /**
     * Constant to represent default files with 10 elements.
     */
    public static final String DEFAULT_TEN = "10";

    /**
     * Constant to represent default files with 15 elements.
     */
    public static final String DEFAULT_FIFTEEN = "15";

    /**
     * Constant to represent default files with 20 elements.
     */
    public static final String DEFAULT_TWENTY = "20";

    /**
     * Constant to represent default files with 30 elements.
     */
    public static final String DEFAULT_THRITY = "30";

    /**
     * Constant to represent default files with 100 elements.
     */
    public static final String DEFAULT_ONE_HUNDRED = "100";

    /**
     * Constant to represent default files with 300 elements.
     */
    public static final String DEFAULT_THREE_HUNDRED = "300";

    /**
     * Obtain a default cities file path.
     *
     * @param num Number of cities to load (Use Class Constants - ex.
     * FileManager.TEN)
     * @return file path.
     */
    public static String defaultCitiesFile(String num) {

        return "test-files"
                + File.separatorChar
                + String.format("files%s", num)
                + File.separatorChar
                + String.format("cities%s.txt", num);
    }

    /**
     * Obtain a default users file path.
     *
     * @param num Number of users to load (Use Class Constants - ex.
     * FileManager.TEN)
     * @return file path.
     */
    public static String defaultUsersFile(String num) {

        return "test-files"
                + File.separatorChar
                + String.format("files%s", num)
                + File.separatorChar
                + String.format("users%s.txt", num);
    }

    // Read Files
    /**
     * Loads cities from a text file to a social network object.
     *
     * @param socialNetwork The Social Network where to load the cities.
     * @param filepath the file's location
     *
     * @return true if all cities are loaded correctly.
     */
    private static boolean loadCities(SocialNetwork socialNetwork, String filepath) {

        boolean loaded = true;

        try {

            input = new BufferedReader(new FileReader(filepath));

            String line;
            while ((line = input.readLine()) != null) {

                // [0]-Name;[1]-Points;[2]-Latitude;[3]-Longitude
                String[] attributes = line.split(",");

                try {

                    City newCity = new City(
                            new Pair(
                                    Double.parseDouble(attributes[2]),
                                    Double.parseDouble(attributes[3])),
                            attributes[0],
                            Integer.parseInt(attributes[1]));

                    socialNetwork.addCity(newCity);

                } catch (Exception ex) {
                    System.out.println("Object not created correctly.\n Error: " + ex.getMessage());
                    loaded = false;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.\n Error: " + ex.getMessage());
            loaded = false;
        } catch (IOException ex) {
            System.out.println("Something went wrong\nError: " + ex.getMessage());
            loaded = false;
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return loaded;
    }

    /**
     * Loads users from a text file to a social network object.
     *
     * @param socialNetwork The Social Network where to load the cities.
     * @param filepath the file's location
     *
     * @return true if all users are loaded correctly.
     */
    private static boolean loadUsers(SocialNetwork socialNetwork, String filepath) {

        boolean loaded = true;

        LinkedList<User> newUsers = new LinkedList<User>();
        LinkedList<String> usersFriends = new LinkedList<String>();
        try {
            input = new BufferedReader(new FileReader(filepath));

            int i = 0;
            String line;
            while ((line = input.readLine()) != null) {

                if (i % 2 == 0) {

                    // [0]-Name;[1]-Points;[2-n]-Visited Cities.
                    String[] attributes = line.split(",");

                    try {

                        User newUser = new User(attributes[0], attributes[1]);
                        for (int j = 2; j < attributes.length; j++) {

                            boolean found = false;
                            City visitedCity = null;

                            Iterator<City> citySearch = socialNetwork.getCitiesList().iterator();
                            while (citySearch.hasNext() && !found) {

                                visitedCity = citySearch.next();

                                if (visitedCity.getName().equalsIgnoreCase(attributes[j])) {
                                    found = true;
                                }
                            }
                            if (found) {
                                newUser.checkInAnewCity(visitedCity);
                            }
                        }
                        newUsers.add(newUser);

                    } catch (Exception ex) {
                        System.out.println("Object not created correctly.\n Error: " + ex.getMessage());
                        loaded = false;
                    }
                } else {
                    usersFriends.add(line);
                }
                i++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.\n Error: " + ex.getMessage());
            loaded = false;
        } catch (IOException ex) {
            System.out.println("Something went wrong\nError: " + ex.getMessage());
            loaded = false;
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        Iterator usersIt = newUsers.iterator();
        Iterator friendsIt = usersFriends.iterator();
        while (usersIt.hasNext()) {

            User user = (User) usersIt.next();
            String[] friends = ((String) friendsIt.next()).split(",");
            for (String friendName : friends) {

                User friend = null;
                boolean found = false;

                Iterator searchIt = newUsers.iterator();
                while (searchIt.hasNext() && !found) {
                    friend = (User) searchIt.next();
                    if (friend.getNickname().equals(friendName)) {
                        found = !found;
                    }
                }
                user.addFriendship(friend);
            }
            socialNetwork.addUser(user);
        }
        
        socialNetwork.updateMayors();

        return loaded;
    }

    /**
     * Loads users & cities from a text file to a social network object.
     *
     * @param citiesFilePath cities file path.
     * @param usersFilePath users file path.
     *
     * @return a social network with users & cities loaded.
     */
    public static SocialNetwork loadSocialNetwork(String citiesFilePath, String usersFilePath) {

        SocialNetwork sn = new SocialNetwork();

        boolean loadedCities = loadCities(sn, citiesFilePath);
        boolean loadedUsers = loadUsers(sn, usersFilePath);

        if (!(loadedCities && loadedUsers)) {

            System.out.println("Some cities/users may not be loaded correctly.");
        }

        return sn;
    }

    // Write Files
    /**
     * Saves cities to a text file from a social network object.
     *
     * @param socialNetwork The Social Network where the cities to save are.
     * @param filepath the output's location
     *
     * @return true if all cities are saved correctly.
     */
    private static boolean saveCities(SocialNetwork socialNetwork, String filepath) {

        boolean saved = true;

        try {
            output = new BufferedWriter(new FileWriter(filepath));

            Iterator citiesIt = socialNetwork.getCitiesList().iterator();
            while (citiesIt.hasNext()) {

                City city = (City) citiesIt.next();
                String line = String.format(Locale.CANADA, "%s,%d,%.6f,%.6f%n",
                        city.getName(),
                        city.getPoints(),
                        city.getCoordinates().getKey().doubleValue(),
                        city.getCoordinates().getValue().doubleValue());

                output.write(line);
            }

        } catch (IOException ex) {
            System.out.println("Something went wrong\nError: " + ex.getMessage());
            saved = false;
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return saved;
    }

    /**
     * Saves users to a text file from a social network object.
     *
     * @param socialNetwork The Social Network where the users to save are.
     * @param filepath the output's location
     *
     * @return true if all users are saved correctly.
     */
    private static boolean saveUsers(SocialNetwork socialNetwork, String filepath) {

        boolean saved = true;

        try {
            output = new BufferedWriter(new FileWriter(filepath));

            Iterator usersIt = socialNetwork.getUsersList().iterator();
            while (usersIt.hasNext()) {

                User user = (User) usersIt.next();
                StringBuilder line = new StringBuilder(user.getNickname() + ',' + user.getEmail());
                for (City city : user.getVisitedCities()) {
                    line.append(',' + city.getName());
                }
                line.append("%n");
                boolean comma = false;
                for (User friend : user.getFriends()) {

                    line.append(comma ? "," : "" + friend.getNickname());
                    comma = true;
                }
                line.append("%n");

                output.write(line.toString());
            }

        } catch (IOException ex) {
            System.out.println("Something went wrong\nError: " + ex.getMessage());
            saved = false;
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return saved;
    }

    /**
     * Saves users & cities to a text file from a social network object.
     *
     * @param socialNetwork social network to save.
     * @param citiesFilePath file path to save cities.
     * @param usersFilePath file path to save users.
     *
     * @return a social network with users & cities loaded.
     */
    public static boolean saveSocialNetwork(SocialNetwork socialNetwork, String citiesFilePath, String usersFilePath) {

        if (socialNetwork == null) {
            return false;
        }
        
        boolean savedCities = saveCities(socialNetwork, citiesFilePath);
        boolean savedUsers = saveUsers(socialNetwork, usersFilePath);

        if (!(savedCities && savedUsers)) {

            System.out.println("Some cities/users may not be saved correctly.");
        }

        return (savedCities && savedUsers);
    }
}
