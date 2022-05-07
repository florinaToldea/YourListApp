package com.example.yourlistapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {
    private final MutableLiveData<String> text;

    public MapViewModel() {
        this.text = new MutableLiveData<>();
        text.setValue("This is map fragment");
    }

    public LiveData<String> getText(){
        return text;
    }
}
