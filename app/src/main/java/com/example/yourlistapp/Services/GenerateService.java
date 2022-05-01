package com.example.yourlistapp.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenerateService {
    private static MemeApi memeApi;

    public static MemeApi getRandomApi(){
        if(memeApi == null){
            memeApi = new Retrofit.Builder().baseUrl("https://meme-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MemeApi.class);
        }
        return memeApi;
    }
}
