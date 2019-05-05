package com.pnstech.finalactivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.MyViewHolder> {
    private ArrayList<WorkshopProfile> profiles;
    private Context context;
    private String link_name;
    //testing phase
    public WorkshopAdapter(Context c, ArrayList<WorkshopProfile> p) {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.workshop_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.workshopName.setText(profiles.get(position).getWorkshopName());
        holder.workshopDescription.setText(profiles.get(position).getWorkshopDescription());
        Picasso.get().load(profiles.get(position)
                .getWorkshopProfilePic())
                .fit()
                .centerInside()
                .into(holder.workshopProfilePic);
        holder.workshopVenue.setText(profiles.get(position).getWorkshopVenue());
        holder.workshopTiming.setText(profiles.get(position).getWorkshopTiming());
        holder.getWorkshopLink(position);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    //inner class
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView workshopName, workshopDescription;
        private TextView workshopVenue, workshopTiming;
        private   ImageView workshopProfilePic;
        private Button btn_click;



        public MyViewHolder(View itemView)
        {
            super(itemView);
            workshopName = itemView.findViewById(R.id.workshop_name);
            workshopDescription = itemView.findViewById(R.id.workshop_description);
            workshopProfilePic = itemView.findViewById(R.id.workshop_profilePic);
            workshopVenue= itemView.findViewById(R.id.venue_id);
            workshopTiming= itemView.findViewById(R.id.timing_id);
            btn_click= itemView.findViewById(R.id.participate_id);
        }


        public void getWorkshopLink(final int position)
        {

            btn_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    link_name=profiles.get(position).getGetLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(link_name));
                    context.startActivity(intent);
                }
            });
        }

    }

}
