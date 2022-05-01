package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yourlistapp.Repositories.LoginRepository;
import com.example.yourlistapp.Repositories.MemeRepository;
import com.example.yourlistapp.Services.Meme;
import com.google.firebase.auth.FirebaseUser;

public class MemeViewModel extends ViewModel {

    private MemeRepository memeRepository;

    public MemeViewModel(){
        memeRepository = MemeRepository.getInstance();
    }

    public LiveData<Meme> getSearchedMeme(){
        return memeRepository.getSearchedMeme();
    }

    public void searchForMeme(){
        memeRepository.searchForMeme();
    }
}
