package com.augmentis.ayp.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by wind on 8/29/2016 AD.
 */
public class AlarmEditActivity extends SingleFragmentActivity {

    private static final String KEY_UUID = "KEY_UUID";

    public static Intent newIntent(Context ctx, UUID uuid) {
        Intent i = new Intent(ctx, AlarmEditActivity.class);
        i.putExtra(KEY_UUID, uuid);
        return i;
    }

    @Override
    protected Fragment onCreateFragment(Bundle savedInstanceState) {
        UUID uuid = (UUID) getIntent().getSerializableExtra(KEY_UUID);
        return AlarmEditFragment.newInstance(uuid);
    }
}
