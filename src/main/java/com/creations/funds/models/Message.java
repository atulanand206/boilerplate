package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Message {

    @JsonProperty("team_id")
    private UUID teamId;

    @JsonProperty("message")
    private String message;

    public Message() {
    }

    public Message(UUID teamId, String message) {
        this.teamId = teamId;
        this.message = message;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
