package com.example.yourlistapp.View;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlistapp.Adapter.LibraryAdapter;
import com.example.yourlistapp.Models.Library;
import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.ViewLibraryListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LibraryFragment extends Fragment implements LibraryAdapter.HandleItemClick{
    private View view;
    private ViewLibraryListViewModel libraryViewModel;
    private RecyclerView recyclerView;
    private LibraryAdapter libraryAdapter;
    private Library libraryToManipulate = null;

    @Override
    public void itemClick(Library library) {
        if(library.status){
            library.status = false;
        } else {
            library.status = true;
        }
        libraryViewModel.updateLibraryItem(library);
    }

    @Override
    public void removeItem(Library library) {
        libraryViewModel.deleteLibraryItem(library);
    }

    @Override
    public void editItem(Library library) {
        this.libraryToManipulate = library;
        ((EditText) view.findViewById(R.id.add_item_items)).setText(library.itemName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstace){

        view = inflater.inflate(R.layout.fragment_library, container, false);

        final EditText addLibraryItem = view.findViewById(R.id.add_item_items);

        FloatingActionButton addButton = view.findViewById(R.id.floating_button);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String libraryItemName = addLibraryItem.getText().toString();

                if(TextUtils.isEmpty(libraryItemName)){
                    Toast.makeText(LibraryFragment.this.getContext(), "Enter new library item",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(libraryToManipulate == null){
                    saveNewLibraryItem(libraryItemName);
                } else {
                    updateLibraryItem(libraryItemName);
                }
            }
        });
        initializeViewModel();
        initializeRecyclerView();
        return view;
    }

    private void initializeViewModel() {
        libraryViewModel = new ViewModelProvider(this).get(ViewLibraryListViewModel.class);
        libraryViewModel.getAllLibraryItems().observe(getViewLifecycleOwner(), libraryItems ->{
            if(libraryItems == null){
                recyclerView.setVisibility(view.GONE);
            } else {
                libraryAdapter.setLibraryList(libraryItems);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeRecyclerView(){
        recyclerView = view.findViewById(R.id.recycler_view_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        libraryAdapter = new LibraryAdapter(this.getContext(), this);
        recyclerView.setAdapter(libraryAdapter);
    }

    private void saveNewLibraryItem(String itemName){
        Library library = new Library();
        library.itemName = itemName;
        libraryViewModel.insertLibraryItem(library);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
    }

    private void updateLibraryItem(String newName){
        libraryToManipulate.itemName = newName;
        libraryViewModel.updateLibraryItem(libraryToManipulate);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
        libraryToManipulate = null;
    }
}
