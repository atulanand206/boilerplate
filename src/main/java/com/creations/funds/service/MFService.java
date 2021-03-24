package com.creations.funds.service;

import com.creations.funds.Preconditions;
import com.creations.funds.client.APIClient;
import com.creations.funds.exceptions.ServiceException;
import com.creations.funds.models.*;
import org.springframework.http.HttpStatus;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

public class MFService {

    private final APIClient fMfApiClient;

    private final IRepository fMfRepository;

    public MFService(final APIClient mfApiClient, final IRepository mfRepository) {
        fMfApiClient = mfApiClient;
        fMfRepository = mfRepository;
    }

    private <T> void validateResponseSuccess(Response<T> response) throws IOException {
        if (!response.isSuccessful()) {
            final var responseErrorBody = response.errorBody();
            throw new IOException(responseErrorBody != null ? responseErrorBody.string() : "Unknown error");
        }
    }

    public TeamWithDevelopersDto createTeam(TeamDto teamDto) {
        Preconditions.validateIsNotEmpty(teamDto.getName());
        Preconditions.validateIsTrue(!teamDto.getDevelopers().isEmpty(), "Teams can't be without developers");
        final var team = fMfRepository.createTeam(teamDto);
        for (DeveloperDto developerDto : teamDto.getDevelopers())
            fMfRepository.createDeveloper(new DeveloperDB(developerDto.getName(), developerDto.getPhoneNumber(), team.getId()));
        final var developers = fMfRepository.getDevelopers(team.getId());
        return new TeamWithDevelopersDto(team, developers);
    }

    public void sendAlert(Message message) {
        Preconditions.validateNotNull(message);
        Preconditions.validateNotNull(message.getTeamId());
        Preconditions.validateIsNotEmpty(message.getMessage());
        final var developers = fMfRepository.getDevelopers(message.getTeamId());
        Optional<Developer> first = developers.stream().findFirst();
        if (first.isEmpty())
            throw new ServiceException("Developer not available", HttpStatus.INTERNAL_SERVER_ERROR);
        final var alert = new Alert(first.get().getPhoneNumber(), message.getMessage());
        try {
            fMfApiClient.sendAlert(alert).execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("Alert send failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
