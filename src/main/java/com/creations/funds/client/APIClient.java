package com.creations.funds.client;

import com.creations.funds.models.Alert;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIClient {

    @POST("/alert")
    Call<Void> sendAlert(@Body Alert alert);

}
