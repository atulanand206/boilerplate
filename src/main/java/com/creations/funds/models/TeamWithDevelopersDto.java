package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamWithDevelopersDto {

    @JsonProperty("team")
    private Team team;

    @JsonProperty("developers")
    private List<Developer> developers;

    public TeamWithDevelopersDto() {
    }

    public TeamWithDevelopersDto(Team team, List<Developer> developers) {
        this.team = team;
        this.developers = developers;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }
}
