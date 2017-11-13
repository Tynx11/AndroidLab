package com.tony.d.alarmclock2.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.model.table.AlarmItemTable;
import com.tony.d.alarmclock2.model.wrapper.AlarmItemWrapper;

import java.util.List;

/**
 * Created by Tony on 29.10.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NAME = "data.db";

    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AlarmItemTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @NonNull
    public List<AlarmItem> selectAlarmItems(){
        Cursor cursor = getReadableDatabase().rawQuery(AlarmItemTable.getSelectQuery(),null);
        List <AlarmItem> alarmItems = new AlarmItemWrapper(cursor).getAlarmItems();
        cursor.close();
        return alarmItems;
    }

    public void insertAlarmItem(@NonNull AlarmItem alarmItem){
        getWritableDatabase().execSQL(AlarmItemTable.getInsertQuery(alarmItem));
    }

    public void clearAlarmItems(){
        getWritableDatabase().execSQL(AlarmItemTable.getDeleteQuery());
    }
    public void uptadeQuery(AlarmItem alarmItem) {
        getWritableDatabase().execSQL(AlarmItemTable.updateAlarm(alarmItem));
    }
    public void updateSwitch(AlarmItem alarmItem){
        getWritableDatabase().execSQL(AlarmItemTable.updateSwitchAlarm(alarmItem));

    }

    @NonNull
    public Cursor selectAlarmItemsCursor(){
        return getReadableDatabase().rawQuery(AlarmItemTable.getSelectQuery(), null);

    }



}

