package com.tony.d.alarmclock2.screen.main.alarming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tony.d.alarmclock2.Alarm.AlarmReciever;
import com.tony.d.alarmclock2.Alarm.RingtoneService;
import com.tony.d.alarmclock2.R;

/**
 * Created by Tony on 11/9/2017.
 */

public class AlarmingActivity extends AppCompatActivity {
    Button stopButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarming_activity);
        stopButton = (Button) findViewById(R.id.stop_alarming_button);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RingtoneService.class);
                intent.putExtra("key",0);
                startService(intent);
                finish();
            }
        });


    }
}
