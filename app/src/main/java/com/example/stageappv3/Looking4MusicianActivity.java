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

// Coded by Resul Bozburun
public class Looking4MusicianActivity extends AppCompatActivity{

    private ArrayList<User> users;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking4_musician);

        recyclerView = findViewById(R.id.recyclerViewLooking4Musicians);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        users = new ArrayList<>();



        dbRef = FirebaseDatabase.getInstance().getReference("Users");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    String tag = "a";
                    Log.d(tag,"aaaaaaaaaaaaaaa");
                    Log.d(tag,user.toString());
                    users.add(user);
                }
                userAdapter = new UserAdapter(users);
                recyclerView.setAdapter(userAdapter);

                if (userAdapter != null) {
                    userAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }



}