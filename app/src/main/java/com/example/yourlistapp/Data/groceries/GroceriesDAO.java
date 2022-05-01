package com.example.yourlistapp.Data.groceries;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yourlistapp.Models.Groceries;
import com.example.yourlistapp.Models.Home;

import java.util.List;

@Dao
public interface GroceriesDAO {
    @Query("Select * from Groceries")
    LiveData<List<Groceries>> getAllGroceriesItems();

    @Insert
    void insertGroceriesIem(Groceries home);

    @Update
    void updateGroceriesItem(Groceries groceries);

    @Delete
    void deleteGroceriesItem(Groceries groceries);
}
