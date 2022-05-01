package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yourlistapp.Repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser(){
        return userRepository.getCurrentFirebaseUser();
    }
}
