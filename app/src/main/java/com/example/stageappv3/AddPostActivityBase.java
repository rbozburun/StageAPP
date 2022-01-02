package com.example.stageappv3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class AddPostActivityBase extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // Create fragments -------------------------------------------
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = new ChoosePostTypeFragment();
        fragmentTransaction.add(R.id.frameLayout_onAddPostActivity, fragment);
        fragmentTransaction.commit();
        //---------------------------------------------------------------

        //Show logo on toolbar and add back icon to toolbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.stageapp_vector_logo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to the previous activity
        return super.onSupportNavigateUp();
    }
}