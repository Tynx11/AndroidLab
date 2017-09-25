package com.example.tony.home;

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
