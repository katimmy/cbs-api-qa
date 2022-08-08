package com.cbs.config;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stack"
})
public class Environment {

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
     * Get URL from environment.json by stack and application
     * @param stack
     * @param application
     * @return
     */
    public String getURL(String stack, String application) {
        Map<String, Object> envConfig = (LinkedHashMap) getAdditionalProperties().get(stack);
        return (String) envConfig.get(application);
    }

}
