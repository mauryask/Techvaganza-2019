/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/


package com.pnstech.finalactivity;

import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RatingActivity extends DarkMode
{

    RatingBar ratingbar;
    Button button;
    TextView rating_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        ratingbar= findViewById(R.id.ratingBar);
        button= findViewById(R.id.button);
        rating_value= findViewById(R.id.rating_value);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(ratingbar.getRating()!=0.0)
                {
                    String rating = String.valueOf(ratingbar.getRating());
                    rating_value.setText(rating);
                    final String ratersRating = rating_value.getText().toString().trim();
                    sendRating(ratersRating);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please select some stars", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void sendRating(String rating_value) {
        Toast.makeText(getApplicationContext(), "Processing......", Toast.LENGTH_LONG).show();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Rating");
        //getting android id of a device
        String androidID=android.provider.Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        RatingProfile ratingProfile = new RatingProfile(rating_value);
        myRef.child(androidID).setValue(ratingProfile);
        Toast.makeText(getApplicationContext(), "Your Response Has Been Submitted", Toast.LENGTH_SHORT).show();
    }


}

