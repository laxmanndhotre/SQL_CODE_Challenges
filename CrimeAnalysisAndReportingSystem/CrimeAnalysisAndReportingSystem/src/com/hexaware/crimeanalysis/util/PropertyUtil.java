package com.hexaware.crimeanalysis.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString(String key) {
        Properties properties = new Properties();

        try {
            // Adjusting the path to point to db.properties file
            FileInputStream input = new FileInputStream("db.properties"); 
            properties.load(input);

            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            System.err.println("Property file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading property file.");
            e.printStackTrace();
        }

        return null; // Return null if the property is not found or an error occurs
    }

    public static void main(String[] args) {
        // Test reading properties
        String hostname = getPropertyString("hostname");
        String dbname = getPropertyString("dbname");
        String username = getPropertyString("username");
        String password = getPropertyString("password");
        String port = getPropertyString("port");

        // Print out the values for verification
        System.out.println("Hostname: " + hostname);
        System.out.println("DB Name: " + dbname);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Port: " + port);
    }
}
