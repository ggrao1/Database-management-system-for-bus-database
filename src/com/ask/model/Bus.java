
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "busNo",
    "depoNo",
    "capacity",
    "fromStop",
    "toStop"
})
public class Bus {

    @JsonProperty("busNo")
    private int busNo;
    @JsonProperty("depoNo")
    private int depoNo;
    @JsonProperty("capacity")
    private int capacity;
    @JsonProperty("fromStop")
    private String fromStop;
    @JsonProperty("toStop")
    private String toStop;

    @JsonProperty("busNo")
    public int getBusNo() {
        return busNo;
    }

    @JsonProperty("busNo")
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    @JsonProperty("depoNo")
    public int getDepoNo() {
        return depoNo;
    }

    @JsonProperty("depoNo")
    public void setDepoNo(int depoNo) {
        this.depoNo = depoNo;
    }

    @JsonProperty("capacity")
    public int getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("fromStop")
    public String getFromStop() {
        return fromStop;
    }

    @JsonProperty("fromStop")
    public void setFromStop(String fromStop) {
        this.fromStop = fromStop;
    }

    @JsonProperty("toStop")
    public String getToStop() {
        return toStop;
    }

    @JsonProperty("toStop")
    public void setToStop(String toStop) {
        this.toStop = toStop;
    }

}
