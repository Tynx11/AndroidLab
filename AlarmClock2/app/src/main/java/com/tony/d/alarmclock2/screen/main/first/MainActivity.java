package com.tony.d.alarmclock2.screen.main.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.screen.base.BaseActivity;
import com.tony.d.alarmclock2.screen.main.second.SecondActivity;

/**
 * Created by Tony on 29.10.2017.
 */

public class MainActivity extends BaseActivity {

    private final static String KEY = "key";

    private FloatingActionButton floatingActionButton;
    private boolean request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_bar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = false;
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra(KEY,request);
                startActivity(intent);
            }
        });
    }

    @NonNull
    protected MainFragment makeFragment() {
        return MainFragment.newInstance();
    }
}

