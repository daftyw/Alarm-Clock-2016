package com.augmentis.ayp.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCallNotifButton;
    private Button mCallNotifLaterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alam);

        mCallNotifButton = (Button) findViewById(R.id.call_notif_button);
        mCallNotifButton.setOnClickListener(this); // << AlarmActivity Must be View.OnClickListener to use "THIS"

        mCallNotifLaterButton = (Button) findViewById(R.id.call_notif_later_button);
        mCallNotifLaterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //
        // We call service
        if (view == mCallNotifLaterButton) {
            Intent intent = new Intent(this, MyAlarmService.class);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            long currentMils = System.currentTimeMillis();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(currentMils);
            c.add(Calendar.MINUTE, 1);
            long aMinLater = c.getTimeInMillis(); // Ok get the time 1 min later

            // set alarm for the time
            alarmManager.set(AlarmManager.RTC_WAKEUP, aMinLater, pendingIntent);

            Toast.makeText(this, "Notification will be here in next minute.", Toast.LENGTH_LONG).show();

        } else if(view == mCallNotifButton) {
            Intent intent = new Intent(this, MyAlarmService.class);
            startService(intent);
        }
    }
}
