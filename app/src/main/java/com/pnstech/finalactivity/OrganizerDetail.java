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
import android.support.v7.widget.Toolbar;
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

public class OrganizerDetail extends DarkMode {

   private DatabaseReference reference ;
   private RecyclerView recyclerView;
   private ArrayList<OrganizerProfile>list;
  private  OrganizerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView= findViewById(R.id.myrecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference().child("Profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    OrganizerProfile p = dataSnapshot1.getValue(OrganizerProfile.class);
                    list.add(p);
                }
                adapter= new OrganizerAdapter(OrganizerDetail.this,list);
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
                startActivity(new Intent(OrganizerDetail.this,AboutApp.class));
                break;
            case R.id.notifications:
                startActivity(new Intent(OrganizerDetail.this, PopupNotification.class));
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
