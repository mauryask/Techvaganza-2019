package com.pnstech.finalactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.MyViewHolder> {
    private ArrayList<WinnerProfile> profiles;
    private Context context;
    public WinnerAdapter(Context c, ArrayList<WinnerProfile> p)
    {
        context=c;
        profiles=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.winner_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.year_sem.setText(profiles.get(position).getYear_sem());
        holder.winnerEvent.setText(profiles.get(position).getWinnerEvent());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,year_sem,winnerEvent;

        public MyViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.winner_name);
            year_sem= itemView.findViewById(R.id.winner_year_sem);
            winnerEvent= itemView.findViewById(R.id.winner_event);
        }
    }
}


