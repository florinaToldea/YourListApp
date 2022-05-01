package com.example.yourlistapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yourlistapp.Services.GenerateService;
import com.example.yourlistapp.Services.Meme;
import com.example.yourlistapp.Services.MemeApi;
import com.example.yourlistapp.Services.MemeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemeRepository {
    private static MemeRepository memeRepository;
    private final MutableLiveData<Meme> searchedMeme;

    public MemeRepository() {
        searchedMeme = new MutableLiveData<>();
    }

    public static synchronized MemeRepository getInstance(){
        if(memeRepository == null){
            memeRepository = new MemeRepository();
        }
        return memeRepository;
    }

    public LiveData<Meme> getSearchedMeme(){
        return searchedMeme;
    }

    public void searchForMeme(){
        MemeApi memeApi = GenerateService.getRandomApi();
        Call<MemeResponse> call = memeApi.getRandom();
        call.enqueue(new Callback<MemeResponse>() {
            @Override
            public void onResponse(Call<MemeResponse> call, Response<MemeResponse> response) {
                if(response.isSuccessful()){
                    searchedMeme.setValue(response.body().getMeme());
                    Log.w("test", response.body().getMeme().getUrl());
                }
            }

            @Override
            public void onFailure(Call<MemeResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong");
            }
        });
    }
}
