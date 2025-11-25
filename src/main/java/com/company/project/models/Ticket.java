package com.company.project.models;

import com.company.project.utils.JsonReader;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function Ticket - Model for ticket booking data
 */
public class Ticket {
    @JsonProperty("departFrom")
    private String departFrom;

    @JsonProperty("arriveAt")
    private String arriveAt;

    @JsonProperty("seatType")
    private String seatType;

    @JsonProperty("amount")
    private String amount;

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public String getArriveAt() {
        return arriveAt;
    }

    public void setArriveAt(String arriveAt) {
        this.arriveAt = arriveAt;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static Ticket getBookingData() {
        return JsonReader.getTestData("booking", Ticket.class);
    }
}
