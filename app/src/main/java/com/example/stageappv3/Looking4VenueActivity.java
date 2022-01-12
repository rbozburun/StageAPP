package com.example.stageappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.stageappv3.Adapter.UserAdapter;
import com.example.stageappv3.Adapter.VenueAdapter;
import com.example.stageappv3.Models.User;
import com.example.stageappv3.Models.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// Coded by Nesim Özgün
public class Looking4VenueActivity extends AppCompatActivity{

    private ArrayList<Venue> venues;
    private RecyclerView recyclerView;
    private VenueAdapter venueAdapter;
    private DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking4_venue);

        recyclerView = findViewById(R.id.recyclerViewLooking4Venues);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        venues = new ArrayList<>();


        dbRef = FirebaseDatabase.getInstance().getReference("Venues");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Venue venue = dataSnapshot.getValue(Venue.class);
                    String tag = "v";
                    Log.d(tag,"vvvv");
                    Log.d(tag,venue.toString());
                    venues.add(venue);
                }
                venueAdapter = new VenueAdapter(venues);
                recyclerView.setAdapter(venueAdapter);

                if (venueAdapter != null) {
                    venueAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}