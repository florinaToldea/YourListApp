package com.example.yourlistapp.Data.groceries;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yourlistapp.Data.home.HomeDAO;
import com.example.yourlistapp.Data.home.HomeDatabase;
import com.example.yourlistapp.Models.Groceries;

@Database(entities = {Groceries.class}, version = 1, exportSchema = false)
public abstract class GroceriesDatabase extends RoomDatabase {
    public static GroceriesDatabase instance;
    public abstract GroceriesDAO groceriesDAO();

    public static GroceriesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), GroceriesDatabase.class, "GroceriesDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
