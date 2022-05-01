package com.example.yourlistapp.Data;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MapLocationLiveData extends LiveData<List<Location>> {
    private DatabaseReference databaseReference;

    public MapLocationLiveData(DatabaseReference databaseReference){
        this.databaseReference = databaseReference;
        setValue(new ArrayList<>());
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Location location = snapshot.getValue(Location.class);
            List<Location> currentLocation = getValue();

            currentLocation.add(location);
            setValue(currentLocation);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            Location location = snapshot.getValue(Location.class);

            List<Location> currentLocation = getValue();
            currentLocation.remove(location);
            setValue(currentLocation);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    protected void onInactive(){
        super.onInactive();
        databaseReference.removeValue((DatabaseReference.CompletionListener) childEventListener);
    }

    @Override
    protected void onActive(){
        super.onActive();
        databaseReference.addChildEventListener(childEventListener);
    }
}
