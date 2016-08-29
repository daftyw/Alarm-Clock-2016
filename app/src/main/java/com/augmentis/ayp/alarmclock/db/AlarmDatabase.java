package com.augmentis.ayp.alarmclock.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class AlarmDatabase {

    public static class Tables {
        public static class MyAlarm {
            public static final String NAME = "my_alarm";

            public static class Cols {
                public static final String ALARM_ID = "alarm_id";
                public static final String NAME = "name";
                public static final String HOUR = "hour";
                public static final String MINUTE = "minute";
            }
        }
    }

    private static AlarmDatabase instance;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private AlarmDatabase(Context context) {
        mContext = context;

        AlarmDBHelper helper = new AlarmDBHelper(context);
        mDatabase = helper.getWritableDatabase();
    }

    public static AlarmDatabase get(Context context) {
        if( instance == null ) {
            instance = new AlarmDatabase(context);
        }
        return instance;
    }

    private ContentValues getContentValues(Alarm alarm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tables.MyAlarm.Cols.ALARM_ID, alarm.getAlarmId().toString());
        contentValues.put(Tables.MyAlarm.Cols.NAME, alarm.getName());
        contentValues.put(Tables.MyAlarm.Cols.HOUR, alarm.getHour());
        contentValues.put(Tables.MyAlarm.Cols.MINUTE, alarm.getMin());
        return contentValues;
    }

    public void addAlarm(Alarm alarm) {
        mDatabase.insert(Tables.MyAlarm.NAME, null, getContentValues(alarm));
    }

    public void saveOrUpdateAlarm(Alarm alarm) {
        Alarm foundAlarm = findAlarmById(alarm.getAlarmId());

        if(foundAlarm == null) {
            addAlarm(alarm);
        } else {
            updateAlarm(alarm);
        }
    }

    private void updateAlarm(Alarm alarm) {
        mDatabase.update(Tables.MyAlarm.NAME, getContentValues(alarm),
                Tables.MyAlarm.Cols.ALARM_ID + " = ? ", new String[] { alarm.getAlarmId().toString() });
    }

    public List<Alarm> getList() {
        return queryAlarm(null, null);
    }

    public Alarm findAlarmById(UUID uuid) {
        return queryAlarmSingle(Tables.MyAlarm.Cols.ALARM_ID + " = ? ", new String[]{ uuid.toString() });
    }

    private List<Alarm> queryAlarm(String selection, String[] selectionArrays) {
        Cursor cursor = mDatabase.query(Tables.MyAlarm.NAME, null, selection, selectionArrays, null, null, null);
        AlarmCursor alarmCursor = new AlarmCursor(cursor);
        List<Alarm> list = new ArrayList<>();

        alarmCursor.moveToFirst();
        while (!alarmCursor.isAfterLast()) {
            list.add(alarmCursor.getAlarm());
            alarmCursor.moveToNext();
        }

        return list;
    }

    private static class AlarmCursor extends CursorWrapper {
        public AlarmCursor(Cursor cursor) {
            super(cursor);
        }

        public Alarm getAlarm() {
            String id = getString(getColumnIndex(Tables.MyAlarm.Cols.ALARM_ID));
            String name = getString(getColumnIndex(Tables.MyAlarm.Cols.NAME));
            int hour = getInt(getColumnIndex(Tables.MyAlarm.Cols.HOUR));
            int min = getInt(getColumnIndex(Tables.MyAlarm.Cols.MINUTE));

            Alarm newAlarm = new Alarm();
            newAlarm.setAlarmId(UUID.fromString(id));
            newAlarm.setName(name);
            newAlarm.setHour(hour);
            newAlarm.setMin(min);
            return newAlarm;
        }
    }

    private Alarm queryAlarmSingle(String selection, String[] selectionArrays) {

        Cursor cursor = mDatabase.query(Tables.MyAlarm.NAME, null, selection, selectionArrays, null, null, null);
        AlarmCursor alarmCursor = new AlarmCursor(cursor);

        alarmCursor.moveToFirst();
        if (!alarmCursor.isAfterLast()) {
            return alarmCursor.getAlarm();
        }

        return null;
    }

}
