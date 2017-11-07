package com.tony.d.alarmclock2.screen.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tony.d.alarmclock2.R;

/**
 * Created by Tony on 29.10.2017.
 */

public abstract class  BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        toolbar = (Toolbar) findViewById(getToolbarId());
        setSupportActionBar(toolbar);

        if(getFragmentManager().findFragmentById(getContainerId()) == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(getContainerId(), makeFragment())
                    .commit();
        }
    }

    @NonNull
    protected abstract Fragment makeFragment();

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_base;
    }

    @IdRes
    protected int getContainerId(){
        return R.id.fragment_container;
    }

    @IdRes
    protected int getToolbarId(){
        return R.id.toolbar;
    }
}

