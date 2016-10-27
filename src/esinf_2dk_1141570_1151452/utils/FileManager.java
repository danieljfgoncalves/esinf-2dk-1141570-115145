package esinf_2dk_1141570_1151452.utils;

import esinf_2dk_1141570_1151452.model.City;
import esinf_2dk_1141570_1151452.model.SocialNetwork;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.util.Pair;

/**
 * Represents a class to manage read & write of the text files used for data
 * persistency.
 *
 * @author Daniel Goncalves <1151452@isep.ipp.pt>
 */
public class FileManager {

    /**
     * Text file reader.
     */
    static BufferedReader input;

    /**
     * Constant to represent files with 10 elements.
     */
    static final String TEN = "10";

    /**
     * Constant to represent files with 15 elements.
     */
    static final String FIFTEEN = "15";

    /**
     * Constant to represent files with 20 elements.
     */
    static final String TWENTY = "20";

    /**
     * Constant to represent files with 30 elements.
     */
    static final String THRITY = "30";

    /**
     * Constant to represent files with 100 elements.
     */
    static final String ONE_HUNDRED = "100";

    /**
     * Constant to represent files with 300 elements.
     */
    static final String THREE_HUNDRED = "300";

    /**
     * Loads cities from a text file to a social network object.
     *
     * @param socialNetwork The Social Network where to load the cities.
     * @param num Number of cities to load (Use Class Constants - ex.
     * FileManager.TEN)
     *
     * @return true if all cities are loaded correctly.
     */
    static boolean loadCities(SocialNetwork socialNetwork, String num) {

        boolean loaded = true;

        String filepath = "test-files"
                + File.separatorChar
                + String.format("files%s", num)
                + File.separatorChar
                + String.format("cities%s.txt", num);

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
                    
                    // TODO: Implement when addCity funcionality is developed.
                    System.out.println(newCity);
                } catch (Exception ex) {
                    System.out.println("Object not created correctly.");
                    loaded = false;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
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
}
