package com.example.stageappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.stageappv3.Adapter.BandAdapter;
import com.example.stageappv3.Models.Band;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Coded by Nesim OZGUN
public class Looking4BandActivity extends AppCompatActivity{

    private ArrayList<Band> bands;
    private RecyclerView recyclerView;
    private BandAdapter bandAdapter;
    private DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking4_band);

        recyclerView = findViewById(R.id.recyclerViewLooking4Bands);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bands = new ArrayList<>();


        dbRef = FirebaseDatabase.getInstance().getReference("Bands");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Band band = dataSnapshot.getValue(Band.class);
                    String tag = "n";
                    Log.d(tag,"nnnnn");
                    Log.d(tag,band.toString());
                    bands.add(band);
                }
                bandAdapter = new BandAdapter(bands);
                recyclerView.setAdapter(bandAdapter);

                if (bandAdapter != null) {
                    bandAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

}