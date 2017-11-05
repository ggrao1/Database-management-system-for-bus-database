
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ticketNo",
    "busNo",
    "price",
    "passengerId",
    "toStop",
    "fromStop"
})
public class Ticket {

    @JsonProperty("ticketNo")
    private int ticketNo;
    @JsonProperty("busNo")
    private int busNo;
    @JsonProperty("price")
    private int price;
    @JsonProperty("passengerId")
    private int passengerId;
    @JsonProperty("toStop")
    private String toStop;
    @JsonProperty("fromStop")
    private String fromStop;

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

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
    }

    @JsonProperty("passengerId")
    public int getPassengerId() {
        return passengerId;
    }

    @JsonProperty("passengerId")
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @JsonProperty("toStop")
    public String getToStop() {
        return toStop;
    }

    @JsonProperty("toStop")
    public void setToStop(String toStop) {
        this.toStop = toStop;
    }

    @JsonProperty("fromStop")
    public String getFromStop() {
        return fromStop;
    }

    @JsonProperty("fromStop")
    public void setFromStop(String fromStop) {
        this.fromStop = fromStop;
    }

}
