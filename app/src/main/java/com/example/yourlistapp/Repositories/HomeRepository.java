package com.example.yourlistapp.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.yourlistapp.Data.home.HomeDAO;
import com.example.yourlistapp.Data.home.HomeDatabase;
import com.example.yourlistapp.Models.Home;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeRepository {
    private static HomeRepository instance;
    private HomeDatabase homeDatabase;
    private ExecutorService executorService;

    public HomeRepository(Application application){
          homeDatabase = HomeDatabase.getInstance(application.getApplicationContext());
          executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HomeRepository getInstance(Application application){
        if(instance == null){
            instance = new HomeRepository(application);
        }
        return instance;
    }

    public LiveData<List<Home>> getAllItems(){
        return homeDatabase.homeDAO().getAllHomeItems();
    }

    public void insertHomeItem(Home home){
        executorService.execute(() -> homeDatabase.homeDAO().insertHomeItem(home));
    }

    public void updateHomeItem(Home home){
        executorService.execute(() -> homeDatabase.homeDAO().updateHomeItem(home));
    }

    public void deleteItem(Home home){
        executorService.execute(() -> homeDatabase.homeDAO().deleteHomeItem(home));
    }
}
