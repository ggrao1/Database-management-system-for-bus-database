
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ticketNo",
    "busNo",
    "passengerId",
    "tripId",
    "status"
})
public class Ticket {

    @JsonProperty("ticketNo")
    private int ticketNo;
    @JsonProperty("busNo")
    private int busNo;
    @JsonProperty("passengerId")
    private int passengerId;
    @JsonProperty("tripId")
    private int tripId;
    @JsonProperty("status")
    private String status;

    @JsonProperty("ticketNo")
    public int getTicketNo() {
        return ticketNo;
    }

    @JsonProperty("ticketNo")
    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    @JsonProperty("busNo")
    public int getBusNo() {
        return busNo;
    }

    @JsonProperty("busNo")
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    @JsonProperty("passengerId")
    public int getPassengerId() {
        return passengerId;
    }

    @JsonProperty("passengerId")
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @JsonProperty("tripId")
    public int getTripId() {
        return tripId;
    }

    @JsonProperty("tripId")
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

}
