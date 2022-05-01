package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yourlistapp.Repositories.LoginRepository;
import com.example.yourlistapp.Repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository(application);
        userMutableLiveData = loginRepository.getUserMutableLiveData();
    }

    public void register(String email, String password){
        loginRepository.register(email, password);
    }

    public void login(String email, String password){
        loginRepository.login(email, password);
    }

    //Expose the userMutableLiveData to the View.
    public MutableLiveData<FirebaseUser> getUserMutableLiveData(){
        return userMutableLiveData;
    }

}
