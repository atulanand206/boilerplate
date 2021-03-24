package com.creations.funds.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("developers")
    private List<DeveloperDto> developers;

    public TeamDto() {
    }

    public TeamDto(String name, List<DeveloperDto> developers) {
        this.name = name;
        this.developers = developers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeveloperDto> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<DeveloperDto> developers) {
        this.developers = developers;
    }
}
