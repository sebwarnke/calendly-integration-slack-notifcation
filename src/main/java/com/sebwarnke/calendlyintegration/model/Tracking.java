
package com.sebwarnke.calendlyintegration.model;

import java.io.Serializable;
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
    "utm_campaign",
    "utm_source",
    "utm_medium",
    "utm_content",
    "utm_term",
    "salesforce_uuid"
})
public class Tracking implements Serializable {

    @JsonProperty("utm_campaign")
    public Object utmCampaign;
    @JsonProperty("utm_source")
    public Object utmSource;
    @JsonProperty("utm_medium")
    public Object utmMedium;
    @JsonProperty("utm_content")
    public Object utmContent;
    @JsonProperty("utm_term")
    public Object utmTerm;
    @JsonProperty("salesforce_uuid")
    public Object salesforceUuid;
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

}
