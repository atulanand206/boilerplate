package com.creations.funds.service;

import com.creations.funds.models.Developer;
import com.creations.funds.models.DeveloperDB;
import com.creations.funds.models.Team;
import com.creations.funds.models.TeamDto;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    Team createTeam(TeamDto teamDto);

    Team getTeam(String name);

    Team getTeamById(UUID teamId);

    void createDeveloper(DeveloperDB developerDto);

    List<Developer> getDevelopers(UUID teamId);
}
