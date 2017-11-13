package com.tony.d.alarmclock2.loader;

import android.content.Context;
import android.database.Cursor;

import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.database.SQLiteCursorLoader;

/**
 * Created by Tony on 11/13/2017.
 */

public class MainSelectLoader extends SQLiteCursorLoader {

    private DatabaseHelper databaseHelper;

    public MainSelectLoader(Context context, DatabaseHelper databaseHelper) {
        super(context);
        this.databaseHelper = databaseHelper;
    }

    @Override
    protected Cursor loadCursor() {
        return databaseHelper.selectAlarmItemsCursor();
    }
}
