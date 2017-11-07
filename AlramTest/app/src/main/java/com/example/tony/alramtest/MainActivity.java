package com.example.tony.alramtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlarmTestUtils alarm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        alarm = new  AlarmTestUtils();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startRepeatingTimer(View view) {
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelRepeatingTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.CancelAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void onetimeTimer(View view){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_widget_alarm_manager, menu);
        return true;
    }
}
