package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yourlistapp.Data.groceries.GroceriesDatabase;
import com.example.yourlistapp.Models.Groceries;
import com.example.yourlistapp.Repositories.GroceriesRepository;

import java.util.List;

public class ViewGroceriesListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Groceries>> groceriesList;
    private GroceriesDatabase groceriesDatabase;
    private GroceriesRepository groceriesRepository;

    public ViewGroceriesListViewModel(@NonNull Application application){
        super(application);
        groceriesRepository = GroceriesRepository.getInstance(application);
    }

    public MutableLiveData<List<Groceries>> getGroceriesListObserver(){
        return groceriesList;
    }

    public LiveData<List<Groceries>> getAllGroceriesItems(){
        return groceriesRepository.getAllItems();
    }

    public void insertGroceriesItem(Groceries groceries){
        groceriesRepository.insertGroceriesItem(groceries);
    }

    public void updateGroceriesItem(Groceries groceries){
        groceriesRepository.updateGroceriesItem(groceries);
    }

    public void deleteGroceriesItem(Groceries groceries){
        groceriesRepository.deleteItem(groceries);
    }
}
