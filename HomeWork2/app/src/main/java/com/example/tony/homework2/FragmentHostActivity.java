package com.example.tony.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;


/**
 * Created by Tony on 19.09.2017.
 */

public abstract class FragmentHostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(getContainerId()) == null) {
            fm.beginTransaction()
                    .add(getContainerId(), getFragment())
                    .commit();
        }
    }


    private int getContainerId() {
        return R.id.fragment_container;
    }

    protected abstract Fragment getFragment();

    protected int getLayoutResId() {
        return R.layout.activity_fragment_host;
    }
}