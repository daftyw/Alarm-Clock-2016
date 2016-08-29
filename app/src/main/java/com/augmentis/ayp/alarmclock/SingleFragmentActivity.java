package com.augmentis.ayp.alarmclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Rawin on 29-Aug-16.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    private static final String TAG = "SingleFragmentActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        Log.d(TAG, "onCreate: Create and add fragment");
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.single_fragment_frame);

        if(f == null) {
            f = onCreateFragment(savedInstanceState);

            Log.d(TAG, "onCreate: Got fragment: " + f);
            fm.beginTransaction()
                    .add(R.id.single_fragment_frame, f)
                    .commit();
        }
    }

    protected abstract Fragment onCreateFragment(Bundle savedInstanceState);
}
