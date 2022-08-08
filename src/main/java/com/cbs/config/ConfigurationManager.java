/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:43:56
 * @modify date 2022-06-03 15:43:56
 * @desc [description]
 */


package com.cbs.config;

import org.aeonbits.owner.ConfigCache;


public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }

    public static ApiConfiguration apiConfiguration() {
        return ConfigCache.getOrCreate(ApiConfiguration.class);
    }

    public static ErrorDictionary errorDictionary() {
        return ConfigCache.getOrCreate(ErrorDictionary.class);
    }

}