package com.cbs.config;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        
        "environment",
        "apiendpoints"
})

public class Index {

    @JsonProperty("stack")
    private Environment environment;

    @JsonProperty("apiendpoints")
    private ApiEndPoints apiendpoints;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    @JsonProperty("stack")
    public Environment getEnvironment() {
        return environment;
    }

    @JsonProperty("stack")
    public void setenvironment(Environment environment) {
        this.environment = environment;
    }


    @JsonProperty("apiendpoints")
    public ApiEndPoints getApiEndPoints() {
        return apiendpoints;
    }

    @JsonProperty("apiendpoints")
    public void setapiEndpoints(ApiEndPoints apiendpoints) {
        this.apiendpoints = apiendpoints;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    
}
