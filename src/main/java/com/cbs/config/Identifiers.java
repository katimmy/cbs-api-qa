package com.cbs.config;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cbs.web.base.ElementUtils;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "version"
})
public class Identifiers {

    @JsonProperty("version")
    private String version;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    //Get identifier for default version in config.json
    public String getIdentifier(ElementUtils.EIdentifier eIdentifier) {
        Map<String, Object> identifierConfig = (LinkedHashMap) getAdditionalProperties().get(getVersion());
        return (String) identifierConfig.get(eIdentifier.toString());
    }

    // //Get identifier by version
    // public String getIdentifier(ElementUtils.EIdentifier eIdentifier, CommonPage.EVersion eVersion) {
    //     Map<String, Object> identifierConfig = (LinkedHashMap) getAdditionalProperties().get(eVersion.toString());
    //     return (String) identifierConfig.get(eIdentifier.toString());
    // }

}
