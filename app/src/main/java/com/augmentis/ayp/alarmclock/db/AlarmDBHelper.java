package com.augmentis.ayp.alarmclock.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.augmentis.ayp.alarmclock.db.AlarmDatabase.Tables.*;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class AlarmDBHelper extends SQLiteOpenHelper {
    public static final String NAME = "alarm.db";
    public static final int VERSION = 1;

    public AlarmDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();

        sql.append("create table " + MyAlarm.NAME + " ");
        sql.append(" ( ");
        sql.append(" id int auto_increment primary key, ");
        sql.append(" " + MyAlarm.Cols.ALARM_ID + ", ");
        sql.append(" " + MyAlarm.Cols.NAME + ", ");
        sql.append(" " + MyAlarm.Cols.HOUR + ", ");
        sql.append(" " + MyAlarm.Cols.MINUTE + " ");
        sql.append(" ) ");

        // create table
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + MyAlarm.NAME);
    }
}
