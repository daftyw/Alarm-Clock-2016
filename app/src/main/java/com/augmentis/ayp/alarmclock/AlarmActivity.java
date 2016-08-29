package com.augmentis.ayp.alarmclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class AlarmActivity extends SingleFragmentActivity {

    private static final String TAG = "AlarmActivity";

    @Override
    protected Fragment onCreateFragment(Bundle savedInstanceState) {
        return AlarmFragment.newInstance();
    }
}
