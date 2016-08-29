package com.augmentis.ayp.alarmclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Rawin on 29-Aug-16.
 */

public class AlarmListActivity extends SingleFragmentActivity {
    private static final String TAG = "AlarmListActivity";

    @Override
    protected Fragment onCreateFragment(Bundle savedInstanceState) {
        return AlarmListFragment.newInstance();
    }
}
