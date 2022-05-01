package com.example.yourlistapp.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.yourlistapp.Data.groceries.GroceriesDatabase;
import com.example.yourlistapp.Models.Groceries;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GroceriesRepository {
    private static GroceriesRepository instance;
    private GroceriesDatabase groceriesDatabase;
    private ExecutorService executorService;

    public GroceriesRepository(Application application){
        groceriesDatabase = GroceriesDatabase.getInstance(application.getApplicationContext());
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized GroceriesRepository getInstance(Application application){
        if(instance == null){
            instance = new GroceriesRepository(application);
        }
        return instance;
    }

    public LiveData<List<Groceries>> getAllItems(){
        return groceriesDatabase.groceriesDAO().getAllGroceriesItems();
    }

    public void insertGroceriesItem(Groceries groceries){
        executorService.execute(() -> groceriesDatabase.groceriesDAO().insertGroceriesIem(groceries));
    }

    public void updateGroceriesItem(Groceries groceries){
        executorService.execute(() -> groceriesDatabase.groceriesDAO().updateGroceriesItem(groceries));
    }

    public void deleteItem(Groceries groceries){
        executorService.execute(() -> groceriesDatabase.groceriesDAO().deleteGroceriesItem(groceries));
    }
}
