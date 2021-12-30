package com.example.stageappv3;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stageappv3.Adapter.UserAdapter;
import com.example.stageappv3.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Looking4MusicianActivity extends AppCompatActivity{

    private ArrayList<User> users;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking4_musician);

        viewSettings();
        fillUserList();
        // Update users
        userAdapter.notifyDataSetChanged();



    }

    private void fillUserList(){
        users.add(new User("John Doe","john@john.com","Vocal","default"));
        users.add(new User("Alice Doe","alice@john.com","Guitarist","default"));
        users.add(new User("Jessica Jones","jessica@jessica.com","Vocal","default"));
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.recyclerViewLooking4Musicians);
        users = new ArrayList<>();
        userAdapter = new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);

        // Add items vertically to recycler view.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}