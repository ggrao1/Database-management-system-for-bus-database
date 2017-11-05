
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "depoNo",
    "location"
})
public class Depo {

    @JsonProperty("depoNo")
    private int depoNo;
    @JsonProperty("location")
    private String location;

    @JsonProperty("depoNo")
    public int getDepoNo() {
        return depoNo;
    }

    @JsonProperty("depoNo") 
    public void setDepoNo(int depoNo) {
        this.depoNo = depoNo;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

}
