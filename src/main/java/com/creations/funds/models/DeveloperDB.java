package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DeveloperDB extends DeveloperDto {

    @JsonProperty("team_id")
    private UUID teamId;

    public DeveloperDB() {
    }

    public DeveloperDB(String name, String phoneNumber, UUID teamId) {
        super(name, phoneNumber);
        this.teamId = teamId;
    }
}
