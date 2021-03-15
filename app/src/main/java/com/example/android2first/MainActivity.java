package com.example.android2first;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android2first.Prefs.Prefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(!App.getPrefs().isShown()) {
            navController.navigate(R.id.board);
       }



    }
    private void init() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_profile)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                ArrayList<Integer>list=new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_profile);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
               if(list.contains(destination.getId())){
                   navView.setVisibility(View.VISIBLE);
               }else {
                   navView.setVisibility(View.INVISIBLE);

               }
               if(destination.getId()==R.id.board){
                   getSupportActionBar().hide();
               }else{
                   getSupportActionBar().show();
               }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }
}

