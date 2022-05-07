package com.example.yourlistapp.Data.library;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yourlistapp.Models.Library;

import java.util.List;

@Dao
public interface LibraryDAO {

    @Query("Select * from Library")
    LiveData<List<Library>> getAllLibraryItems();

    @Insert
    void insertLibraryItem(Library library);

    @Update
    void updateLibraryItem(Library library);

    @Delete
    void deleteLibraryItem(Library library);
}
