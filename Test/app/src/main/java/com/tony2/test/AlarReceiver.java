package com.tony2.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Tony on 28.10.2017.
 */

public class AlarReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(),notification);
        ringtone.play();
        Toast.makeText(context,"Сука я сделал будильник",Toast.LENGTH_LONG).show();
    }
}
