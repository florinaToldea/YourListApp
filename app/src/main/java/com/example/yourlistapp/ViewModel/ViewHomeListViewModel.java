package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yourlistapp.Data.home.HomeDatabase;
import com.example.yourlistapp.Models.Home;
import com.example.yourlistapp.Repositories.HomeRepository;

import java.util.List;

public class ViewHomeListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Home>> homeList;
    private HomeDatabase homeDatabase;
    private HomeRepository homeRepository;

    public ViewHomeListViewModel(@NonNull Application application){
        super(application);
        homeRepository = HomeRepository.getInstance(application);
    }

    public MutableLiveData<List<Home>> getHomeListObserver(){
        return homeList;
    }

    public LiveData<List<Home>> getAllHomeItems(){
        return homeRepository.getAllItems();
    }

    public void insertHomeItem(Home home){
        homeRepository.insertHomeItem(home);
    }

    public void updateHomeItem(Home home){
        homeRepository.updateHomeItem(home);
    }

    public void deleteHomeItem(Home home){
        homeRepository.deleteItem(home);
    }
}
