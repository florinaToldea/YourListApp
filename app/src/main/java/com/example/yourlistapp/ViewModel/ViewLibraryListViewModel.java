package com.example.yourlistapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yourlistapp.Data.library.LibraryDatabase;
import com.example.yourlistapp.Models.Library;
import com.example.yourlistapp.Repositories.LibraryRepository;

import java.util.List;

public class ViewLibraryListViewModel extends AndroidViewModel {
    private MutableLiveData<List<Library>> libraryList;
    private LibraryDatabase libraryDatabase;
    private LibraryRepository libraryRepository;

    public ViewLibraryListViewModel(@NonNull Application application){
        super(application);
        libraryRepository = LibraryRepository.getInstance(application);
    }

    public MutableLiveData<List<Library>> getLibraryListObserver(){
        return libraryList;
    }

    public LiveData<List<Library>> getAllLibraryItems(){
        return libraryRepository.getAllItems();
    }

    public void insertLibraryItem(Library library){
        libraryRepository.insertLibraryItem(library);
    }

    public void updateLibraryItem(Library library){
        libraryRepository.updateLibraryItem(library);
    }

    public void deleteLibraryItem(Library library){
        libraryRepository.deleteItem(library);
    }
}
