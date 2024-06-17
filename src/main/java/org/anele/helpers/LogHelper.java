package org.anele.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    //define a Logger object
    public static Logger log;

    //create a generic Logger constructor for class tracking
    public LogHelper(Class<?> _class) {
        log = LogManager.getLogger(_class);
    }

    //log level methods
    public static void info(String message) {
        log.info(message);
    }

    public  void error(String message, String e) {
        log.error(message, e);
    }
    public void severe(String message) {
        log.fatal(message);
    }

    public static void endTest() {

        log.info("********************* " + "E*N*D" + " ***********************");
        log.info("*************************************************************");
    }
}
