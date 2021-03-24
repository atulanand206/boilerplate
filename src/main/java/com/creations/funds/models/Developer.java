package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Developer extends DeveloperDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("team_id")
    private UUID teamId;

    public Developer() {
    }

    public Developer(UUID id, UUID teamId, String name, String phoneNumber) {
        super(name, phoneNumber);
        this.id = id;
        this.teamId = teamId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

}
