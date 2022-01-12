package com.example.stageappv3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stageappv3.Models.Band;
import com.example.stageappv3.Models.User;
import com.example.stageappv3.R;

import java.util.ArrayList;

// Coded by Nesim Özgün
public class BandAdapter extends RecyclerView.Adapter<BandAdapter.MyViewHolder> {

    private ArrayList<Band> bands;

    public BandAdapter(ArrayList<Band> bands){
        this.bands = bands;
    }

    @NonNull
    @Override
    // onCreateViewHolder creates relation between user_item (single user item) and  view
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create recycler view with user_item layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get name of the band from modal and hold it.
        holder.txtName.setText(bands.get(position).getName());

        //Get location of the band from modal and hold it.
        holder.txtLocation.setText(bands.get(position).getLocation());

        // Get genre of the band from modal and hold it.
        holder.txtGenre.setText(bands.get(position).getGenre());

        // Get profile image of the band from modal and hold it.
        holder.userImg.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return bands.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtLocation;
        TextView txtGenre;
        ImageView userImg;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            // Get Attrs from band_item.xml layout.
            userImg = itemView.findViewById(R.id.profileImgofBand);
            txtName = itemView.findViewById(R.id.nameofBandItem);
            txtLocation = itemView.findViewById(R.id.locationofBand);
            txtGenre = itemView.findViewById(R.id.genreofBand);

        }
    }

}
