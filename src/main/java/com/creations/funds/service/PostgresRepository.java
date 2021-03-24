package com.creations.funds.service;

import com.creations.funds.exceptions.ServiceException;
import com.creations.funds.jackson.Serializer;
import com.creations.funds.jackson.Serializers;
import com.creations.funds.models.Developer;
import com.creations.funds.models.DeveloperDB;
import com.creations.funds.models.Team;
import com.creations.funds.models.TeamDto;
import com.creations.funds.postgres.RepoConfig;
import com.creations.funds.postgres.RepositoryJdbcDaoSupport;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PostgresRepository implements IRepository {

    private final RepositoryJdbcDaoSupport fJdbcDaoSupport;
    private final RowMapper<Team> fRowMapper;
    private final RowMapper<Developer> fDeveloperRowMapper;
    private final RepoConfig fRepoConfig;

    public PostgresRepository(RepositoryJdbcDaoSupport jdbcDaoSupport,
                              RowMapper<Team> rowMapper,
                              RowMapper<Developer> developerRowMapper,
                              RepoConfig repoConfig) {
        fJdbcDaoSupport = jdbcDaoSupport;
        fRowMapper = rowMapper;
        fDeveloperRowMapper = developerRowMapper;
        fRepoConfig = repoConfig;
    }

    private final Serializer<TeamDto> SERIALIZER_USER_DTO = Serializers.newJsonSerializer(TeamDto.class);
    private final Serializer<DeveloperDB> SERIALIZER_DEVELOPER_DB = Serializers.newJsonSerializer(DeveloperDB.class);

    public Team createTeam(TeamDto teamDto) {
        try {
            fJdbcDaoSupport.create(teamDto, SERIALIZER_USER_DTO);
            return getTeam(teamDto.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Team with name already exists", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public Team getTeam(String name) {
        return fJdbcDaoSupport.get(name, fRepoConfig.getGetSproc(), fRowMapper);
    }

    @Override
    public Team getTeamById(UUID teamId) {
        return fJdbcDaoSupport.get(teamId.toString(), fRepoConfig.getTeamByIdSproc(), fRowMapper);
    }

    @Override
    public void createDeveloper(DeveloperDB developerDto) {
        try {
            fJdbcDaoSupport.createDeveloper(developerDto, SERIALIZER_DEVELOPER_DB);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Developer create failed", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public List<Developer> getDevelopers(UUID teamId) {
        return fJdbcDaoSupport.getAll(teamId.toString(), fRepoConfig.getGetDeveloperSproc(), fDeveloperRowMapper);
    }
}
