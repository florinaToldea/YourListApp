package com.example.yourlistapp.Data.home;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yourlistapp.Models.Home;

import java.util.List;

@Dao
public interface HomeDAO {

    @Query("Select * from Home")
    LiveData<List<Home>> getAllHomeItems();

    @Insert
    void insertHomeItem(Home home);

    @Update
    void updateHomeItem(Home home);

    @Delete
    void deleteHomeItem(Home home);
}
