package com.example.yourlistapp.Repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepository {

    private Application application;

    //use this for registering and signing in the user.
    private FirebaseAuth firebaseAuth;

    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public LoginRepository(Application application){

        this.application = application;

        firebaseAuth = FirebaseAuth.getInstance();

        userMutableLiveData = new MutableLiveData<>();

        loggedOutMutableLiveData = new MutableLiveData<>();
    }

    //LiveData is used in order to share information between View, ViewModel and Repository.
    //LiveData is a jetpack architecture component.
    //Use MutableLiveData, because data can change all of the time
    public void register(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        }else {
                            Toast.makeText(application, "Registration FAILED! "
                                    + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        }else {
                            Toast.makeText(application, "Login FAILED! "
                                    + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void logout(){
        firebaseAuth.signOut();
        loggedOutMutableLiveData.postValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData(){
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData(){
        return loggedOutMutableLiveData;
    }
}
