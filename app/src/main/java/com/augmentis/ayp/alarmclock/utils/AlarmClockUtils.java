package com.augmentis.ayp.alarmclock.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class AlarmClockUtils {

    private static final String TAG = "AlarmClockUtils";

    public static void setAlarm(Context context, Intent i, int code, int hourOfDay, int min) {
        Log.d(TAG, "Time set: " + hourOfDay + " : " + min);

        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        alarmTime.set(Calendar.MINUTE, min);

        Calendar now = Calendar.getInstance();
        if(alarmTime.before(now)) {
            alarmTime.add(Calendar.DATE, 1);
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getService(context, code, i, PendingIntent.FLAG_NO_CREATE);
        if(pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        } else {
            pendingIntent = PendingIntent.getService(context, code, i, PendingIntent.FLAG_ONE_SHOT);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(
                    new AlarmManager.AlarmClockInfo(alarmTime.getTimeInMillis(), pendingIntent), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);
        }
    }
}
