package com.tony2.test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Button alarmOn, alarmOff;
    TextView textView;
    TimePicker timePicker;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmOff = (Button) findViewById(R.id.alarm_off);
        alarmOn = (Button) findViewById(R.id.alarm_on);
        textView = (TextView) findViewById(R.id.update_time);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        final Calendar calendar = Calendar.getInstance();

        final Intent intent = new Intent(getApplicationContext(),AlarReceiver.class);



        alarmOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());

                int hour = timePicker.getHour();
                int minutes = timePicker.getMinute();

                String hourString = String.valueOf(hour);
                String minutesString = String.valueOf(minutes);

                setTime("Будильник поставлен сука на " + hourString + "ебаных часов  и " + minutesString + " блядских минут" );

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);



            }
        });
        alarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime("Будила ушла нахуй");
            }
        });
    }

    private void setTime(String s) {
        textView.setText(s);

    }
}
