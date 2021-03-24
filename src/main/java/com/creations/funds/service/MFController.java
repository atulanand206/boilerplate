package com.creations.funds.service;

import com.creations.funds.Preconditions;
import com.creations.funds.models.Message;
import com.creations.funds.models.TeamDto;
import com.creations.funds.models.TeamWithDevelopersDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MFController {

    private final MFService fMfService;

    public MFController(MFService fMfService) {
        this.fMfService = fMfService;
    }

    @PostMapping("/team")
    public TeamWithDevelopersDto createTeam(@RequestBody TeamDto teamDto) {
        Preconditions.validateIsNotEmpty(teamDto.getName());
        return fMfService.createTeam(teamDto);
    }

    @PostMapping("/alert")
    public void sendAlert(@RequestBody Message message) {
        Preconditions.validateNotNull(message);
        Preconditions.validateNotNull(message.getTeamId());
        Preconditions.validateIsNotEmpty(message.getMessage());
        fMfService.sendAlert(message);
    }

}
