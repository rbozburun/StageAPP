package com.example.stageappv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ChoosingFragment extends Fragment{

    private Button btnMusician;
    private Button btnVenue;
    private Button btnBand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choosing_fragment, container, false);

        btnMusician = (Button) view.findViewById(R.id.musicianBtn);
        btnMusician.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Looking4MusicianActivity.class);
                startActivity(intent);
            }
        });

        btnVenue = (Button) view.findViewById(R.id.venueBtn);
        btnVenue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Looking4VenueActivity.class);
                startActivity(intent);
            }
        });

        btnBand = (Button) view.findViewById(R.id.bandBtn);
        btnBand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Looking4BandActivity.class);
                startActivity(intent);
            }
        });





        return view;





    }


}
