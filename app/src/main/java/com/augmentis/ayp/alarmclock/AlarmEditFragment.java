package com.augmentis.ayp.alarmclock;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.augmentis.ayp.alarmclock.db.Alarm;
import com.augmentis.ayp.alarmclock.db.AlarmDatabase;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by wind on 8/29/2016 AD.
 */
public class AlarmEditFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private static final String KEY_UUID = "KEY_UUID";
    private static final String PICKER1_DIALOG = "PICKER1_DIALOG";

    private Button mSetTimeButton;
    private Button mSaveButton;
    private Button mDeleteButton;
    private TextView mAlarmText;
    private EditText mNameEditText;

    private AlarmDatabase mDb;
    private Alarm mAlarm;

    public static AlarmEditFragment newInstance(UUID uid) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_UUID, uid);
                
        AlarmEditFragment fragment = new AlarmEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = AlarmDatabase.get(getActivity());
        UUID uuid = (UUID) getArguments().getSerializable(KEY_UUID);

        if(uuid != null) {
            mAlarm = mDb.findAlarmById(uuid);
        } else {
            mAlarm = new Alarm();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm_edit, container, false);

        mNameEditText = (EditText) v.findViewById(R.id.alarm_edit_alarm_name);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAlarm.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAlarmText = (TextView) v.findViewById(R.id.alarm_edit_alarm_text);

        mDeleteButton = (Button) v.findViewById(R.id.alarm_edit_delete_button);

        mSaveButton = (Button) v.findViewById(R.id.alarm_edit_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDb.saveOrUpdateAlarm(mAlarm);
                // TODO set time here
                getActivity().finish();
            }
        });

        mSetTimeButton = (Button) v.findViewById(R.id.alarm_edit_set_alarm_button);
        mSetTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultTimePicker timePicker = new DefaultTimePicker();
                timePicker.setOnTimeSetListener(AlarmEditFragment.this);
                timePicker.show(getFragmentManager(), PICKER1_DIALOG);
            }
        });

        return v;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mAlarm.setHour(hourOfDay);
        mAlarm.setMin(minute);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);

        String time = DateFormat
                .getTimeFormat(getActivity())
                .format(cal.getTime());

        mAlarmText.setText(time);
    }
}
