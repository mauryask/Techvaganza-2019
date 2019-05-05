package com.pnstech.finalactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String getmessage = intent.getStringExtra("toastMessage");
        Toast.makeText(context, getmessage, Toast.LENGTH_SHORT).show();
    }
}
