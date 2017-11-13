package com.tony.d.alarmclock2.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;

/**
 * Created by Tony on 11/13/2017.
 */

public  class MainInsertLoader extends AsyncTaskLoader {

    private DatabaseHelper databaseHelper;

    private AlarmItem alarmItem;

    public MainInsertLoader(Context context, DatabaseHelper databaseHelper, AlarmItem alarmItem) {

        super(context);
        this.databaseHelper = databaseHelper;
        this.alarmItem = alarmItem;
    }

    @Override
    public Object loadInBackground() {
        databaseHelper.insertAlarmItem(alarmItem);
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

