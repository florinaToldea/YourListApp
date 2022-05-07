package com.example.yourlistapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initializeView();
        setupNavigation();
    }

    private void prepareAuthentication() {
        mainViewModel.getCurrentFirebaseUser().observe(this, user ->{
            if(user == null){
                navController.navigate(R.id.fragment_login);
            }
        });
    }

    private void setupNavigation(){
        navController = Navigation.findNavController(this, R.id.navigation_fragment_host);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder()
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Bundles are used for passing data from one activity to another.
                Bundle bundle = new Bundle();
                if(item.getItemId() == R.id.groceriesFragment){
                    bundle.putString("groceries", "list");
                }
                if(item.getItemId() == R.id.homeFragment){
                    bundle.putString("home", "list");
                }
                if(item.getItemId() == R.id.randomFragment){
                    bundle.putString("meme", "list");
                }
                if(item.getItemId() == R.id.mapFragment){
                    bundle.putString("map", "map");
                }
                navController.navigate(item.getItemId(), bundle);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void initializeView() {
        navigationView = findViewById(R.id.navigation_drawer_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        toolbar = findViewById(R.id.toolbar_id);
    }
}