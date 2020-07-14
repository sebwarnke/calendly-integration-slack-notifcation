
package com.sebwarnke.calendlyintegration.model;

import java.util.HashMap;
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
    "1_question",
    "1_response",
    "2_question",
    "2_response",
    "3_question",
    "3_response",
    "4_question",
    "4_response"
})
public class QuestionsAndResponses {

    @JsonProperty("1_question")
    public String _1Question;
    @JsonProperty("1_response")
    public String _1Response;
    @JsonProperty("2_question")
    public String _2Question;
    @JsonProperty("2_response")
    public String _2Response;
    @JsonProperty("3_question")
    public String _3Question;
    @JsonProperty("3_response")
    public String _3Response;
    @JsonProperty("4_question")
    public String _4Question;
    @JsonProperty("4_response")
    public String _4Response;
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
