package com.pnstech.finalactivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title, String message)
    {
        NotificationManagerCompat notificationManager = null;
        Notification notification = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.techvaganza).setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(message).build();

        notificationManager.notify(0,notification);

    }
}
