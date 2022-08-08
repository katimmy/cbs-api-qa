/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:40:37
 * @modify date 2022-06-03 15:40:37
 * @desc Load all the Api Configurations
 */
package com.cbs.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "classpath:general.properties", "classpath:local.properties",
"classpath:grid.properties", "classpath:apis.properties" })
public interface ApiConfiguration extends Config {

    @Key("stage.env.url")
    String stageapiurl();

    @Key("api.createcounterpart.endpoint")
    String createcounterpartendpoint();

    @Key("api.v1.counterparty.endpoint")
    String counterpartendpoint();    

    @Key("api.auth.username")
    String apiauthusername();    

    
    @Key("api.auth.password")
    String apiauthpsswd();

    @Key("apvaokta.auth.email")
    String oktaapiauthusername();
       
    @Key("pvaokta.auth.password")
    String oktaapiauthpsswd();


    
}
