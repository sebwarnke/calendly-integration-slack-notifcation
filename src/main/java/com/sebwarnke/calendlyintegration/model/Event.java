
package com.sebwarnke.calendlyintegration.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uuid",
    "assigned_to",
    "extended_assigned_to",
    "start_time",
    "start_time_pretty",
    "invitee_start_time",
    "invitee_start_time_pretty",
    "end_time",
    "end_time_pretty",
    "invitee_end_time",
    "invitee_end_time_pretty",
    "created_at",
    "location",
    "canceled",
    "canceler_name",
    "cancel_reason",
    "canceled_at"
})
public class Event implements Serializable {

    @JsonProperty("uuid")
    public String uuid;
    @JsonProperty("assigned_to")
    public List<String> assignedTo = null;
    @JsonProperty("extended_assigned_to")
    public List<ExtendedAssignedTo> extendedAssignedTo = null;
    @JsonProperty("start_time")
    public String startTime;
    @JsonProperty("start_time_pretty")
    public String startTimePretty;
    @JsonProperty("invitee_start_time")
    public String inviteeStartTime;
    @JsonProperty("invitee_start_time_pretty")
    public String inviteeStartTimePretty;
    @JsonProperty("end_time")
    public String endTime;
    @JsonProperty("end_time_pretty")
    public String endTimePretty;
    @JsonProperty("invitee_end_time")
    public String inviteeEndTime;
    @JsonProperty("invitee_end_time_pretty")
    public String inviteeEndTimePretty;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("location")
    public String location;
    @JsonProperty("canceled")
    public Boolean canceled;
    @JsonProperty("canceler_name")
    public Object cancelerName;
    @JsonProperty("cancel_reason")
    public Object cancelReason;
    @JsonProperty("canceled_at")
    public Object canceledAt;
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
