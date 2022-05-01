package com.example.yourlistapp.Data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.yourlistapp.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserLiveData extends LiveData<User> {

    private DatabaseReference databaseReference;
    private User user;

    public UserLiveData(DatabaseReference databaseReference){
        this.databaseReference = databaseReference;
    }

    private ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            user = snapshot.getValue(User.class);
            setValue(user);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.d("userLiveData", error.getMessage());
        }
    };

    @Override
    protected void onActive(){
        super.onActive();
        databaseReference.addValueEventListener(eventListener);
    }

    @Override
    protected void onInactive(){
        super.onInactive();
        databaseReference.removeEventListener(eventListener);
    }
}
