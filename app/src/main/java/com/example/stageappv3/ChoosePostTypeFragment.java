package com.example.stageappv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ChoosePostTypeFragment extends Fragment {

    Button btnPublishImg;
    Button btnPublishVideo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_post_type, container, false);

        //Get publish buttons
        btnPublishImg = (Button) view.findViewById(R.id.publishImg);
        btnPublishVideo = (Button) view.findViewById(R.id.publishVideo);

        //Listen publish buttons

        // Start PublishImgActivity if user clicks Publish Image
        btnPublishImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishImgActivity.class);
                startActivity(intent);
            }
        });

        // Start PublishVideoActivity if user clicks Publish Video
        btnPublishVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishVideoActivity.class);
                startActivity(intent);

            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}