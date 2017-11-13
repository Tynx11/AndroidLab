package com.tony.d.alarmclock2.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;

/**
 * Created by Tony on 11/13/2017.
 */

public class MainUpdateQuery extends AsyncTaskLoader {

    private DatabaseHelper databaseHelper;

    private AlarmItem alarmItem;

    public MainUpdateQuery(Context context,DatabaseHelper databaseHelper,AlarmItem alarmItem) {
        super(context);
        this.databaseHelper = databaseHelper;
        this.alarmItem = alarmItem;
    }

    @Override
    public Object loadInBackground() {
        databaseHelper.uptadeQuery(alarmItem);
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
