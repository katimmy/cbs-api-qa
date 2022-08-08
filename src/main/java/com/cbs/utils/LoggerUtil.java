package com.cbs.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class LoggerUtil {

    private Logger logger;
    private boolean isTraceEnabled = false;

    public LoggerUtil(Class clazz){
        logger = LoggerFactory.getLogger(clazz.getSimpleName());
    }

    /**
     * Log info
     * @param message
     */
    public void info(String message){
        logger.info(message);
        
    }

    /**
     * Logger for list
     * @param message
     */
    public void info(List<String> message){
        logger.info(message.toString());
    }

    /**
     * Logger for list
     * @param message
     */
    public void info(String message, List<String> values){
        logger.info(message + values.toString());
    }

    /**
     * Log debug
     * @param message
     */
    public void debug(String message){
        logger.debug(message);
        
    }

    /**
     * Log.warning
     * @param message
     */
    public void warn(String message){
        logger.warn(message);
       
    }

    /**
     * Log error
     * @param message
     */
    public void error(String message){
        logger.error(message);
        
    }

    /**
     * Log error
     * @param logMessage
     */
    public void fail(String logMessage, String extentMessage){
        logger.error(logMessage);
        
    }

    /**
     * Log.warning
     * @param message
     */
    public void trace(String message){
        if (isTraceEnabled) {
            logger.trace(message);
        }
    }

}
