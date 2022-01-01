package com.example.stageappv3.Adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stageappv3.Models.User;
import com.example.stageappv3.R;

import java.util.ArrayList;
import java.util.List;

// Coded by Resul Bozburun
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> users;

    public UserAdapter(ArrayList<User> users){
        this.users = users;
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
        // Get name and surname of user from modal and hold it.
        holder.txtNameSurname.setText(users.get(position).getNameSurname());

        // Get name and surname of user from modal and hold it.
        holder.txtRole.setText(users.get(position).getRole());

        // Get profile image of user from modal and hold it.
        holder.userImg.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtNameSurname;
        TextView txtRole;
        ImageView userImg;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            // Get Attrs from user_item.xml layout.
            userImg = itemView.findViewById(R.id.profileImgofUser);
            txtNameSurname = itemView.findViewById(R.id.nameSurnameofUserItem);
            txtRole = itemView.findViewById(R.id.roleofUser);

        }
    }

}
