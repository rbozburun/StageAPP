package com.example.stageappv3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stageappv3.Models.User;
import com.example.stageappv3.Models.Venue;
import com.example.stageappv3.R;

import java.util.ArrayList;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.MyViewHolder> {

    private ArrayList<Venue> venues;

    public VenueAdapter(ArrayList<Venue> venues){ this.venues = venues; }

    @NonNull
    @Override
    // onCreateViewHolder creates relation between venue_item and view.
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create recycler view with venue_item layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get name of venue from modal and hold it.
        holder.txtName.setText(venues.get(position).getName());

        // Get location of venue from modal and hold it.
        holder.txtLocation.setText(venues.get(position).getLocation());

        // Get profile image of venue from modal and hold it.
        holder.venueImg.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtLocation;
        ImageView venueImg;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            // Get Attrs from user_item.xml layout.
            venueImg = itemView.findViewById(R.id.profileImgofVenue);
            txtName = itemView.findViewById(R.id.nameofVenueItem);
            txtLocation = itemView.findViewById(R.id.locationofVenue);

        }
    }

}
