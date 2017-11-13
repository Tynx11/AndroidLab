package com.tony.d.alarmclock2.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import com.tony.d.alarmclock2.screen.main.alarming.AlarmingActivity;
import com.tony.d.alarmclock2.screen.main.first.MainActivity;
import com.tony.d.alarmclock2.screen.main.first.MainFragment;

/**
 * Created by Tony on 29.10.2017.
 */

public class AlarmReciever extends BroadcastReceiver {
    //Intent alarmActivity;
    private  Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {
       Intent serviceIntent = new Intent(context,RingtoneService.class);

        serviceIntent.putExtra("key",1);
        context.startService(serviceIntent);

                Intent intentone = new Intent(context.getApplicationContext(), AlarmingActivity.class)
                        .addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentone);

            }
        }





