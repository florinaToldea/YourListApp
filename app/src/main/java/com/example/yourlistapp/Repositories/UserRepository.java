package com.example.yourlistapp.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yourlistapp.Data.FirebaseLiveData;
import com.example.yourlistapp.Data.UserLiveData;
import com.example.yourlistapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private static UserRepository instance;
    private FirebaseDatabase firebaseDatabase;
    private Application application;
    private UserLiveData userLiveData;
    private FirebaseAuth firebaseAuth;
    private FirebaseLiveData currentFirebaseLiveData;
    private MutableLiveData<String> errorMessage;
    private DatabaseReference databaseReference;

    private UserRepository(Application application){
        this.application = application;
        currentFirebaseLiveData = new FirebaseLiveData();
    }

    public static UserRepository getInstance(Application application) {
        if(instance == null){
            instance = new UserRepository(application);
        }
        return instance;
    }

    public void initializeCurrentUser(){
        userLiveData = new UserLiveData(databaseReference.child("user").child(firebaseAuth.getUid()));
    }

    public LiveData<User> getCurrentUser(){
        return userLiveData;
    }

    public void LogOut(){
        firebaseAuth.signOut();
    }

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser() {
        return currentFirebaseLiveData;
    }

}
