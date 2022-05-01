package com.example.yourlistapp.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlistapp.Adapter.HomeAdapter;
import com.example.yourlistapp.Models.Home;
import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.ViewHomeListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment implements HomeAdapter.HandleItemClick {

    private View view;
    private ViewHomeListViewModel homeViewModel;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private Home homeToManipulate = null;

    @Override
    public void itemClick(Home home) {
        if(home.status){
            home.status = false;
        } else {
            home.status = true;
        }
        homeViewModel.updateHomeItem(home);
    }

    @Override
    public void removeItem(Home home) {
        homeViewModel.deleteHomeItem(home);
    }

    @Override
    public void editItem(Home home) {
        this.homeToManipulate = home;
        ((EditText) view.findViewById(R.id.add_item_items)).setText(home.itemName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstace){

        view = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText addHomeItem = view.findViewById(R.id.add_item_items);

        FloatingActionButton addButton = view.findViewById(R.id.floating_button);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String homeItemName = addHomeItem.getText().toString();

                if(TextUtils.isEmpty(homeItemName)){
                    Toast.makeText(HomeFragment.this.getContext(), "Enter new home item",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(homeToManipulate == null){
                    saveNewHomeItem(homeItemName);
                } else {
                    updateHomeItem(homeItemName);
                }
            }
        });
        initializeViewModel();
        initializeRecyclerView();
        return view;
    }

    private void initializeViewModel() {
        homeViewModel = new ViewModelProvider(this).get(ViewHomeListViewModel.class);
        homeViewModel.getAllHomeItems().observe(getViewLifecycleOwner(), homeItems ->{
            if(homeItems == null){
                recyclerView.setVisibility(view.GONE);
            } else {
                homeAdapter.setHomeList(homeItems);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeRecyclerView(){
        recyclerView = view.findViewById(R.id.recycler_view_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        homeAdapter = new HomeAdapter(this.getContext(), this);
        recyclerView.setAdapter(homeAdapter);
    }

    private void saveNewHomeItem(String itemName){
        Home home = new Home();
        home.itemName = itemName;
        homeViewModel.insertHomeItem(home);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
    }

    private void updateHomeItem(String newName){
        homeToManipulate.itemName = newName;
        homeViewModel.updateHomeItem(homeToManipulate);
        ((EditText) view.findViewById(R.id.add_item_items)).setText("");
        homeToManipulate = null;
    }
}
