package com.example.yourlistapp.Data.home;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yourlistapp.Models.Home;

@Database(entities = {Home.class}, version = 1, exportSchema = false)
public abstract class HomeDatabase extends RoomDatabase{
    public static HomeDatabase instance;
    public abstract HomeDAO homeDAO();

    public static HomeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HomeDatabase.class, "HomeDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
