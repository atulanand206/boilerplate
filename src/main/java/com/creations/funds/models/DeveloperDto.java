package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeveloperDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public DeveloperDto() {
    }

    public DeveloperDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
