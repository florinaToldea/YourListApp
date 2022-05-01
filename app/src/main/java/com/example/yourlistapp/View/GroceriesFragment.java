package com.example.yourlistapp.View;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlistapp.Adapter.GroceriesAdapter;
import com.example.yourlistapp.Adapter.HomeAdapter;
import com.example.yourlistapp.Models.Groceries;
import com.example.yourlistapp.Models.Home;
import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.ViewGroceriesListViewModel;
import com.example.yourlistapp.ViewModel.ViewHomeListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroceriesFragment extends Fragment implements GroceriesAdapter.HandleItemClick{
    private View view;
    private ViewGroceriesListViewModel groceriesViewModel;
    private RecyclerView recyclerView;
    private GroceriesAdapter groceriesAdapter;
    private Groceries groceriesToManipulate = null;

    @Override
    public void itemClick(Groceries groceries) {
        if(groceries.status){
            groceries.status = false;
        } else {
            groceries.status = true;
        }
        groceriesViewModel.updateGroceriesItem(groceries);
    }

    @Override
    public void removeItem(Groceries groceries) {
        groceriesViewModel.deleteGroceriesItem(groceries);
    }

    @Override
    public void editItem(Groceries groceries) {
        this.groceriesToManipulate = groceries;
        ((EditText) view.findViewById(R.id.add_item_items)).setText(groceries.itemName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstace){

        view = inflater.inflate(R.layout.fragment_groceries, container, false);

        final EditText addGroceriesItem = view.findViewById(R.id.add_item_items);

        FloatingActionButton addButton = view.findViewById(R.id.floating_button);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String groceriesItemName = addGroceriesItem.getText().toString();

                if(TextUtils.isEmpty(groceriesItemName)){
                    Toast.makeText(GroceriesFragment.this.getContext(), "Enter new groceries item",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(groceriesToManipulate == null){
                    saveNewGroceriesItem(groceriesItemName);
                } else {
                    updateGroceriesItem(groceriesItemName);
                }
            }
        });
        initializeViewModel();
        initializeRecyclerView();
        return view;
    }

    private void initializeViewModel() {
        groceriesViewModel = new ViewModelProvider(this).get(ViewGroceriesListViewModel.class);
        groceriesViewModel.getAllGroceriesItems().observe(getViewLifecycleOwner(), groceriesItem ->{
            if(groceriesItem == null){
                recyclerView.setVisibility(view.GONE);
            } else {
                groceriesAdapter.setGroceriesList(groceriesItem);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeRecyclerView(){
        recyclerView = view.findViewById(R.id.recycler_view_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        groceriesAdapter = new GroceriesAdapter(this.getContext(), this);
        recyclerView.setAdapter(groceriesAdapter);
    }

    private void saveNewGroceriesItem(String itemName){
        Groceries groceries = new Groceries();
        groceries.itemName = itemName;
        groceriesViewModel.insertGroceriesItem(groceries);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
    }

    private void updateGroceriesItem(String newName){
        groceriesToManipulate.itemName = newName;
        groceriesViewModel.updateGroceriesItem(groceriesToManipulate);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
        groceriesToManipulate = null;
    }
}
