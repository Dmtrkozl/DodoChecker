package com.dvobient.dodoChecker.Parser.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "checkType",
    "notAvailableInUnit",
    "slots"
})
public class CheckType {

    @JsonProperty("checkType")
    private Integer checkType;
    @JsonProperty("notAvailableInUnit")
    private Boolean notAvailableInUnit;
    @JsonProperty("slots")
    private List<Slot> slots = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("checkType")
    public Integer getCheckType() {
        return checkType;
    }

    @JsonProperty("checkType")
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    @JsonProperty("notAvailableInUnit")
    public Boolean getNotAvailableInUnit() {
        return notAvailableInUnit;
    }

    @JsonProperty("notAvailableInUnit")
    public void setNotAvailableInUnit(Boolean notAvailableInUnit) {
        this.notAvailableInUnit = notAvailableInUnit;
    }

    @JsonProperty("slots")
    public List<Slot> getSlots() {
        return slots;
    }

    @JsonProperty("slots")
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
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
