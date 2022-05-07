package com.example.yourlistapp.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.yourlistapp.Data.home.HomeDatabase;
import com.example.yourlistapp.Data.library.LibraryDAO;
import com.example.yourlistapp.Data.library.LibraryDatabase;
import com.example.yourlistapp.Models.Library;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibraryRepository {
    private static LibraryRepository instance;
    private LibraryDatabase libraryDatabase;
    private ExecutorService executorService;

    public LibraryRepository(Application application){
        libraryDatabase = LibraryDatabase.getInstance(application.getApplicationContext());
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized LibraryRepository getInstance(Application application){
        if(instance == null){
            instance = new LibraryRepository(application);
        }
        return instance;
    }

    public LiveData<List<Library>> getAllItems(){
        return libraryDatabase.libraryDAO().getAllLibraryItems();
    }

    public void insertLibraryItem(Library library){
        executorService.execute(() -> libraryDatabase.libraryDAO().insertLibraryItem(library));
    }

    public void updateLibraryItem(Library library){
        executorService.execute(() -> libraryDatabase.libraryDAO().updateLibraryItem(library));
    }

    public void deleteItem(Library library){
        executorService.execute(() -> libraryDatabase.libraryDAO().deleteLibraryItem(library));
    }
}
