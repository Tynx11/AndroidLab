package com.tony.d.alarmclock2.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.tony.d.alarmclock2.model.database.DatabaseHelper;

/**
 * Created by Tony on 11/13/2017.
 */

public class MainDeleteLoader extends AsyncTaskLoader {

    private DatabaseHelper databaseHelper;


    public MainDeleteLoader(Context context, DatabaseHelper databaseHelper) {
        super(context);
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Object loadInBackground() {
        databaseHelper.clearAlarmItems();
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
