package com.augmentis.ayp.alarmclock.db;

import java.util.UUID;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class Alarm {

    UUID mAlarmId;
    String mName;
    int mHour;
    int mMin;

    public Alarm() {
        mAlarmId = UUID.randomUUID();
    }

    public UUID getAlarmId() {
        return mAlarmId;
    }

    public void setAlarmId(UUID alarmId) {
        mAlarmId = alarmId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        mHour = hour;
    }

    public int getMin() {
        return mMin;
    }

    public void setMin(int min) {
        mMin = min;
    }
}
