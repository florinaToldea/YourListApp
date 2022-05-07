package com.example.yourlistapp.Data.library;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.yourlistapp.Models.Library;

@Database(entities = {Library.class}, version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

    public static LibraryDatabase instance;
    public abstract LibraryDAO libraryDAO();

    public static LibraryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase.class,
                    "LibraryDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
