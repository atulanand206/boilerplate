package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alert {

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("message")
    private String message;

    public Alert() {
    }

    public Alert(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
