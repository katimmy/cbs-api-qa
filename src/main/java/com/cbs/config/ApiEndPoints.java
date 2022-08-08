package com.cbs.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"apiendpoints"
})

public class ApiEndPoints {

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();


@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

/**
     * Get endpoint from apiendpoint.json by apiName
     * @param stack
     * @param application
     * @return
     */
    public String getEndPoint(String apiendpoints, String apiName) {
        Map<String, Object> apiEndpointConfig = (LinkedHashMap) getAdditionalProperties().get(apiendpoints);
        return (String) apiEndpointConfig.get(apiName);
    }

}