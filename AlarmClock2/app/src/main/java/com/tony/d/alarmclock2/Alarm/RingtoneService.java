package com.tony.d.alarmclock2.Alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.tony.d.alarmclock2.R;

/**
 * Created by Tony on 11/9/2017.
 */

public class RingtoneService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int value = intent.getIntExtra("key",1);

        if (value == 1 ) {
            mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
            if(!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }
        else  {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_NOT_STICKY;
    }
}
