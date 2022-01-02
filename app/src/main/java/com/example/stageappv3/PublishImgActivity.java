package com.example.stageappv3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.stageappv3.R;

public class PublishImgActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_img);

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