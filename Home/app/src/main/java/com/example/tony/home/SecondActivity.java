package com.example.tony.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SecondActivity extends FragmentHostActivity{




    @Override
    protected Fragment getFragment() {
        return SecondFragment.newInstance(0,0,new int[getResources().getIntArray(R.array.right_answers).length]);
    }




}
