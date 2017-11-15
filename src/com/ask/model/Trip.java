
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tripId",
    "tripDate",
    "tripTime",
    "busNo",
    "price",
    "fromStop",
    "toStop",
    "availableSeats"
})
public class Trip {

    @JsonProperty("tripId")
    private int tripId;
    @JsonProperty("tripDate")
    private String tripDate;
    @JsonProperty("tripTime")
    private String tripTime;
    @JsonProperty("busNo")
    private int busNo;
    @JsonProperty("price")
    private int price;
    @JsonProperty("fromStop")
    private String fromStop;
    @JsonProperty("toStop")
    private String toStop;
    @JsonProperty("availableSeats")
    private int availableSeats;

    @JsonProperty("tripId")
    public int getTripId() {
        return tripId;
    }

    @JsonProperty("tripId")
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @JsonProperty("tripDate")
    public String getTripDate() {
        return tripDate;
    }

    @JsonProperty("tripDate")
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    @JsonProperty("tripTime")
    public String getTripTime() {
        return tripTime;
    }

    @JsonProperty("tripTime")
    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    @JsonProperty("busNo")
    public int getBusNo() {
        return busNo;
    }

    @JsonProperty("busNo")
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
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

    @JsonProperty("availableSeats")
    public int getAvailableSeats() {
        return availableSeats;
    }

    @JsonProperty("availableSeats")
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
