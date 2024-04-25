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
    static void loadProps() {
        try (FileInputStream file = new FileInputStream("src/main/resources/config.properties")) {
            //load the file
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Error while trying to load the config file: " + e);
        }
    }






}
