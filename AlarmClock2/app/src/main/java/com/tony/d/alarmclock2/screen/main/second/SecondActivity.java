package com.tony.d.alarmclock2.screen.main.second;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.screen.base.BaseActivity;
import com.tony.d.alarmclock2.screen.main.first.MainFragment;

/**
 * Created by Tony on 29.10.2017.
 */

public class SecondActivity extends BaseActivity {
    private String KEY = "key";
    private static final String ALARM_KEY = "asd";

    private AlarmItem alarmItem;
    private boolean requset;
    private boolean tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tr = true;

        alarmItem = getIntent().getParcelableExtra(ALARM_KEY);
        requset = getIntent().getBooleanExtra(KEY,true);

    }

    @NonNull
    @Override
    protected Fragment makeFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ALARM_KEY, getIntent().getParcelableExtra (ALARM_KEY));
        bundle.getBoolean(KEY,requset);
        return SecondFragment.newInstance(bundle);
    }
}

