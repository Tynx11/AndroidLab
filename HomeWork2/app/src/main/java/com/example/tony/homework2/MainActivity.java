package com.example.tony.homework2;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentHostActivity {
    @Override
    protected Fragment getFragment() {
        return MainFragment.newInstance("");
    }
}