package com.example.stageappv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

// Coded By Resul Bozburun
public class ProfileFragment extends Fragment {

    ImageButton btnSharePost;
    RecyclerView postRecyclerView;

    // TO DO: Create a "Posts" path for users. Then upload user's port to Posts path with UserID.
    DatabaseReference dbRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        //Recylcer View on shared posts by user (on Profile Fragment)
        postRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_posts_onProfileFragment);


        btnSharePost = (ImageButton) view.findViewById(R.id.sharePostBtn_onProfileFragment);
        // Listen clicks for Share Post button on Profile Fragment
        btnSharePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO: Implement post sharing functionality with a recycler view.
                startActivity(new Intent(getActivity(), AddPostActivityBase.class));


            }
        });

        return view;


    }

}
