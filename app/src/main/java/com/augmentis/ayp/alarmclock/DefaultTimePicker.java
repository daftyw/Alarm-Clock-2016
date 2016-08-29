package com.augmentis.ayp.alarmclock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class DefaultTimePicker extends DialogFragment {

    TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

    public DefaultTimePicker() {}

    @NonNull
    public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        mOnTimeSetListener = onTimeSetListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),
                mOnTimeSetListener,
                hourOfDay, min,
                DateFormat.is24HourFormat(getActivity()));
    }

}
