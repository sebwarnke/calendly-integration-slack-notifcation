
package com.sebwarnke.calendlyintegration.model;

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
    "event_type",
    "event",
    "invitee",
    "questions_and_answers",
    "questions_and_responses",
    "tracking",
    "old_event",
    "old_invitee",
    "new_event",
    "new_invitee"
})
public class Payload {

    @JsonProperty("event_type")
    public EventType eventType;
    @JsonProperty("event")
    public Event event;
    @JsonProperty("invitee")
    public Invitee invitee;
    @JsonProperty("questions_and_answers")
    public List<QuestionsAndAnswer> questionsAndAnswers = null;
    @JsonProperty("questions_and_responses")
    public QuestionsAndResponses questionsAndResponses;
    @JsonProperty("tracking")
    public Tracking tracking;
    @JsonProperty("old_event")
    public Object oldEvent;
    @JsonProperty("old_invitee")
    public Object oldInvitee;
    @JsonProperty("new_event")
    public Object newEvent;
    @JsonProperty("new_invitee")
    public Object newInvitee;
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
