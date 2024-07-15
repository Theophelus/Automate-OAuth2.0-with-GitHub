package org.anele.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteToConfigFileUtils {
    //define properties file
    private static Properties properties = null;

    public WriteToConfigFileUtils() {
        properties = new Properties();
    }

    //load current data in config.properties file
    static synchronized void loadProps() {
        try (FileInputStream file = new FileInputStream("src/main/resources/config.properties")) {
            //load the file
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Error while trying to load the config file: " + e);
        }
    }
    private static synchronized void storeProperties(String value) {
        // Load existing properties from the file
        loadProps();
        //write to config.properties files
        try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/config.properties")) {
            //set key value pair
            properties.setProperty("auth.code", value);
            //write to file
            properties.store(outputStream, null);
        } catch (IOException e) {
            System.out.println("Issue occurred while trying to write to config file: " + e.getMessage());
        }
    }
    //set value into config.properties file
//    public static void setCode(String value) {
//        storeProperties(value);
//    }
}
