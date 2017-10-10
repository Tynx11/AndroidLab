package com.example.tony.homeworkrecycler.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.tony.homeworkrecycler.R;
import com.example.tony.homeworkrecycler.screen.event_list.MainActivity;

/**
 * Created by Tony on 15.10.2017.
 */

public class SplashScreen extends AppCompatActivity {
    private final int TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }),TIME);

    }
}


