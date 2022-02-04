package com.dvobient.dodoChecker.Parser.Entity;

import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "unitId",
    "unitName",
    "ratingPeriodId",
    "date",
    "checkType",
    "products"
})
public class Slot {

    @JsonProperty("unitId")
    private String unitId;
    @JsonProperty("unitName")
    private String unitName;
    @JsonProperty("ratingPeriodId")
    private String ratingPeriodId;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("checkType")
    private Integer checkType;
    @JsonProperty("products")
    private List<String> products = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("unitId")
    public String getUnitId() {
        return unitId;
    }

    @JsonProperty("unitId")
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @JsonProperty("unitName")
    public String getUnitName() {
        return unitName;
    }

    @JsonProperty("unitName")
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @JsonProperty("ratingPeriodId")
    public String getRatingPeriodId() {
        return ratingPeriodId;
    }

    @JsonProperty("ratingPeriodId")
    public void setRatingPeriodId(String ratingPeriodId) {
        this.ratingPeriodId = ratingPeriodId;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("checkType")
    public Integer getCheckType() {
        return checkType;
    }

    @JsonProperty("checkType")
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    @JsonProperty("products")
    public List<String> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<String> products) {
        this.products = products;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(unitName, slot.unitName) && Objects.equals(date, slot.date) && Objects.equals(checkType, slot.checkType) && Objects.equals(products, slot.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitName, date, checkType, products);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "unitName='" + unitName + '\'' +
                ", date=" + date +
                ", checkType=" + checkType +
                ", products=" + products +
                '}';
    }
}
