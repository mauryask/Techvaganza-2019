/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/

package com.pnstech.finalactivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SendNotification extends DarkMode
{

    private EditText title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        title= findViewById(R.id.title);
        message= findViewById(R.id.message);
    }

//store notification to data base o which you need to send

  public void  storeNotification(View view)
    {

        String xtitle= title.getText().toString();
        String xmessage = message.getText().toString();

        if(!xtitle.isEmpty()|| !xmessage.isEmpty())
        {
            Toast.makeText(this, "sending...", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference().child("Notifications");
            NotificationStore nstr = new NotificationStore(xtitle, xmessage);

            myRef.child(xtitle + "-" + getID()).setValue(nstr);
            Toast.makeText(getApplicationContext(), "Notification Sent", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(SendNotification.this, SendNotification.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please write the message first", Toast.LENGTH_SHORT).show();
        }
    }

    //creating notification

    public String getID()
    {
        int key = (int)(Math.random()*1000);
        String id="TV-"+ key;
        return id;

    }


}
