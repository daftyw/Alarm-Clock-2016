package com.augmentis.ayp.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.augmentis.ayp.alarmclock.db.Alarm;
import com.augmentis.ayp.alarmclock.db.AlarmDatabase;

import java.util.List;

/**
 * Created by Rawin on 29-Aug-16.
 */
public class AlarmListFragment extends Fragment {

    private static final String TAG = "AlarmListFragment";

    private RecyclerView mRecyclerView;
    private AlarmDatabase mAlarmDatabase;

    public static AlarmListFragment newInstance() {

        Bundle args = new Bundle();

        AlarmListFragment fragment = new AlarmListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAlarmDatabase = AlarmDatabase.get(getActivity());

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Ok");
        View v = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        List<Alarm> alarmList = mAlarmDatabase.getList();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.alarm_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new AlarmAdapter(alarmList));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "Create option menu");
        inflater.inflate(R.menu.menu_tool, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_new_alarm:

                Intent i = AlarmEditActivity.newIntent(getActivity(), null);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AlarmHolder extends RecyclerView.ViewHolder {

        TextView mTimeText;
        TextView mNameText;
        Switch mSwitch;

        Alarm mAlarm;

        AlarmHolder(View itemView) {
            super(itemView);

            mNameText = (TextView) itemView.findViewById(R.id.list_item_alarm_name);
            mTimeText = (TextView) itemView.findViewById(R.id.list_item_alarm_time);
            mSwitch = (Switch) itemView.findViewById(R.id.list_item_switch);
        }

        public void bindAlarm(Alarm alarm) {
            mAlarm = alarm;
        }
    }

    class AlarmAdapter extends RecyclerView.Adapter<AlarmHolder> {

        List<Alarm> mAlarmList;
        AlarmAdapter(List<Alarm> alarmList) {
            mAlarmList = alarmList;
        }

        @Override
        public AlarmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_list_card_alarm, parent);

            return new AlarmHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AlarmHolder holder, int position) {
            Alarm alarm = mAlarmList.get(position);

            holder.bindAlarm(alarm);
        }

        @Override
        public int getItemCount() {
            return mAlarmList.size();
        }
    }
}
