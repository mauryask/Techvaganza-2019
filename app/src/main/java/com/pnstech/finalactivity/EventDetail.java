/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/

package com.pnstech.finalactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventDetail extends DarkMode {

    private  DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<EventProfile>list;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView= findViewById(R.id.myrecycler_viewx);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference().child("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    EventProfile p = dataSnapshot1.getValue(EventProfile.class);
                    list.add(p);
                }
                adapter= new EventAdapter(EventDetail.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Opps! Please check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //testing
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about:
                startActivity(new Intent(EventDetail.this,AboutApp.class));
                break;
            case R.id.notifications:
                startActivity(new Intent(EventDetail.this, PopupNotification.class));
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                break;
            case R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //testing


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
