package com.pnstech.finalactivity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrganizerAdapter extends RecyclerView.Adapter<OrganizerAdapter.MyViewHolder> {
   private ArrayList<OrganizerProfile>profiles;
   private Context context;
    public OrganizerAdapter(Context c, ArrayList<OrganizerProfile> p)
    {
        context=c;
        profiles=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.organizer_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.email.setText(profiles.get(position).getTeamName());
        Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        ImageView profilePic;



        public MyViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.organizer_name);
            email= itemView.findViewById(R.id.organizer_team_name);
            profilePic= itemView.findViewById(R.id.profilePic);

        }
    }
}

