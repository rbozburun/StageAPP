package com.example.stageappv3;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


// Coded by Resul Bozburun & Nesim Ozgun
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Show logo on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.stageapp_vector_logo);

        // Create fragments -------------------------------------------
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = new ChoosingFragment();
        fragmentTransaction.add(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
        //---------------------------------------------------------------

        // Listen for clicks on bottom nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

    }


    // Change fragments according to the users click. Default fragment = choosing fragment
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new
            BottomNavigationView.OnNavigationItemSelectedListener(){

                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem){
                    Fragment thisFragment = null;
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                    switch(menuItem.getItemId()) {
                        case R.id.navigation_messages:
                            thisFragment = new MessagesFragment();
                            break;

                        case R.id.navigation_notifications:
                            thisFragment = new NotificationsFragment();
                            break;

                        case R.id.navigation_profile:
                            thisFragment = new ProfileFragment();
                            break;

                    }
                    assert thisFragment != null;
                    transaction.replace(R.id.fragment_layout,thisFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
            };


}
