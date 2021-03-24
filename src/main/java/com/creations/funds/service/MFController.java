package com.creations.funds.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MFController {

    private final MFService fMfService;

    public MFController(MFService fMfService) {
        this.fMfService = fMfService;
    }

}
