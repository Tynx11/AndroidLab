package com.example.tony.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.Contacts.SettingsColumns.KEY;

public class ThirdActivity extends AppCompatActivity {
    TextView textView;
    final String KEY = "key";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button button = (Button) findViewById(R.id.repeat);
        textView = (TextView) findViewById(R.id.result);
        int count = getIntent().getIntExtra(KEY,0);
        textView.setText(String.valueOf(count) + " / " + getResources().getStringArray(R.array.right_answers).length );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
