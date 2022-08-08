package com.cbs.config;

import org.json.simple.JSONObject;

import com.cbs.utils.json.JSONUtil;

public class TestConfiguration {

   
    public JSONObject envConf = new JSONObject();
    public JSONObject apiEndpointConfig = new JSONObject();

    public TestConfiguration(){
       
        envConf = JSONUtil.getJSONObjectFromFile(System.getProperty("environmentConfig.file","src/main/resources/configurations/environment.json")); 
        apiEndpointConfig = JSONUtil.getJSONObjectFromFile(System.getProperty("endpointConfig.file","src/main/resources/configurations/apiendpoints.json"));   
           
    }

    public Environment getEnvironment(){
        Index index = (Index) JSONUtil.getClassObject(envConf.toJSONString(),Index.class);
        return index.getEnvironment();
    }

    
    public ApiEndPoints getApiEndPoint(){
        Index index = (Index) JSONUtil.getClassObject(apiEndpointConfig.toJSONString(),Index.class);
        return index.getApiEndPoints();
    }

}
