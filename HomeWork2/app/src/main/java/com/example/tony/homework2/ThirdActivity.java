package com.example.tony.homework2;


import android.app.Fragment;


import static android.provider.Contacts.SettingsColumns.KEY;

public class ThirdActivity extends FragmentHostActivity {


    @Override
    protected Fragment getFragment() {
        return SumFragment.newInstance(0);
    }
}

