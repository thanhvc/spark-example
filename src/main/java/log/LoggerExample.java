package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerExample {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggerExample.class);
        logger.error("error message");
        logger.warn("warn message");
        logger.info("info message");
        logger.debug("debug message");
    }
}
