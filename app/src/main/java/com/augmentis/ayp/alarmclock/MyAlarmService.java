package com.augmentis.ayp.alarmclock;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

public class MyAlarmService extends IntentService {

    private static final String TAG = "MyAlarmService";

    public MyAlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // call notification here

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder.setTicker("Ticker!");
        builder.setAutoCancel(true);
        builder.setContentTitle("Title Here !!");
        builder.setContentText("Hello!");
        builder.setSmallIcon(android.R.drawable.ic_menu_camera);
        builder.setSound(soundUri);

        // build the notification object
        Notification notification = builder.build();

        // call notification base from notification object
        NotificationManagerCompat.from(this).notify(0, notification);
    }
}
