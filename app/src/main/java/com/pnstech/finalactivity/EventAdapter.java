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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private ArrayList<EventProfile> profiles;
    private Context context;
    private String link_name;
    //testing phase
    public EventAdapter(Context c, ArrayList<EventProfile> p) {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.event_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eventName.setText(profiles.get(position).getEventName());
        holder.eventDescription.setText(profiles.get(position).getEventDescription());
        Picasso.get().load(profiles.get(position)
                .getEventProfilePic())
                //.resize(300,500)
                .fit()
                .centerInside()
                .into(holder.eventProfilePic);
        holder.eventVenue.setText(profiles.get(position).getEventVenue());
        holder.eventTiming.setText(profiles.get(position).getEventTiming());
        holder.getEventLink(position);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    //inner class
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView eventName, eventDescription;
        private TextView eventVenue, eventTiming;
        private   ImageView eventProfilePic;
        private Button btn_click;



        public MyViewHolder(View itemView)
        {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            eventDescription = itemView.findViewById(R.id.event_description);
            eventProfilePic = itemView.findViewById(R.id.event_profilePic);
            eventVenue= itemView.findViewById(R.id.venue_id);
            eventTiming= itemView.findViewById(R.id.timing_id);
            btn_click= itemView.findViewById(R.id.participate_id);
        }


        public void getEventLink(final int position)
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
