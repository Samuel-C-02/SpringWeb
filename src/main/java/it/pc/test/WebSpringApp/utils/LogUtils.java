package it.pc.test.WebSpringApp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    public static final Logger log = LoggerFactory.getLogger(LogUtils.class);

    public static void logException(Exception e) {
        log.error("Exception Caught: ", e);
    }

}
