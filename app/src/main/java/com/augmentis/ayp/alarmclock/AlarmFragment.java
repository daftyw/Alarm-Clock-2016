package com.augmentis.ayp.alarmclock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.augmentis.ayp.alarmclock.db.AlarmDatabase;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class AlarmFragment extends Fragment {

    private AlarmDatabase mAlarmDb;

    public static AlarmFragment newInstance() {

        Bundle args = new Bundle();

        AlarmFragment fragment = new AlarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAlarmDb = AlarmDatabase.get(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
