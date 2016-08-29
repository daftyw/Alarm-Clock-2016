package com.augmentis.ayp.alarmclock;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rawin on 29-Aug-16.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.single_fragment_frame);

        if(f == null) {
            f = onCreateFragment(savedInstanceState);

            fm.beginTransaction()
                    .add(R.id.single_fragment_frame, f)
                    .commit();
        }
    }

    protected abstract Fragment onCreateFragment(Bundle savedInstanceState);
}
