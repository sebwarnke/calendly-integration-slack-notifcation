
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uuid",
    "first_name",
    "last_name",
    "name",
    "email",
    "text_reminder_number",
    "timezone",
    "created_at",
    "is_reschedule",
    "payments",
    "canceled",
    "canceler_name",
    "cancel_reason",
    "canceled_at"
})
public class Invitee implements Serializable {

    @JsonProperty("uuid")
    public String uuid;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    @JsonProperty("text_reminder_number")
    public String textReminderNumber;
    @JsonProperty("timezone")
    public String timezone;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("is_reschedule")
    public Boolean isReschedule;
    @JsonProperty("payments")
    public List<Payment> payments = null;
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
