package com.example.yourlistapp.Data;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseLiveData extends LiveData<FirebaseUser> {

        private FirebaseAuth.AuthStateListener authStateListener = auth -> {
            setValue(auth.getCurrentUser());
        };

        @Override
        protected void onActive(){
            super.onActive();
            FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        }

        @Override
        protected void onInactive(){
            super.onInactive();
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
}
