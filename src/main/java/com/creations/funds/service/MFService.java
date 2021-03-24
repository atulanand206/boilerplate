package com.creations.funds.service;

import com.creations.funds.client.APIClient;
import retrofit2.Response;

import java.io.IOException;

public class MFService {

    private final APIClient fMfApiClient;

    private final MFRepository fMfRepository;

    public MFService(final APIClient mfApiClient, final MFRepository mfRepository) {
        fMfApiClient = mfApiClient;
        fMfRepository = mfRepository;
    }

    private <T> void validateResponseSuccess(Response<T> response) throws IOException {
        if (!response.isSuccessful()) {
            final var responseErrorBody = response.errorBody();
            throw new IOException(responseErrorBody != null ? responseErrorBody.string() : "Unknown error");
        }
    }
}
