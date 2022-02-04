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
    "lastCheckupDate",
    "canPerformCheckupsSince",
    "units"
})
public class CheckupData {

    @JsonProperty("lastCheckupDate")
    private Object lastCheckupDate;
    @JsonProperty("canPerformCheckupsSince")
    private Object canPerformCheckupsSince;
    @JsonProperty("units")
    private List<Unit> units = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lastCheckupDate")
    public Object getLastCheckupDate() {
        return lastCheckupDate;
    }

    @JsonProperty("lastCheckupDate")
    public void setLastCheckupDate(Object lastCheckupDate) {
        this.lastCheckupDate = lastCheckupDate;
    }

    @JsonProperty("canPerformCheckupsSince")
    public Object getCanPerformCheckupsSince() {
        return canPerformCheckupsSince;
    }

    @JsonProperty("canPerformCheckupsSince")
    public void setCanPerformCheckupsSince(Object canPerformCheckupsSince) {
        this.canPerformCheckupsSince = canPerformCheckupsSince;
    }

    @JsonProperty("units")
    public List<Unit> getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(List<Unit> units) {
        this.units = units;
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
