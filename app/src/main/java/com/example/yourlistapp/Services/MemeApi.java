package com.example.yourlistapp.Services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MemeApi {
    @GET("/gimme")
    Call<MemeResponse> getRandom();
}
