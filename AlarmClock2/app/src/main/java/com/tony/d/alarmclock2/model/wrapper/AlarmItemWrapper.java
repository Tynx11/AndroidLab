package com.tony.d.alarmclock2.model.wrapper;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.NonNull;

import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.model.table.AlarmItemTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 29.10.2017.
 */

public class AlarmItemWrapper extends CursorWrapper {

    public AlarmItemWrapper(Cursor cursor) {
        super(cursor);
    }
    @NonNull
    public List<AlarmItem> getAlarmItems(){
        List<AlarmItem> alarmItems = new ArrayList<>();
        moveToFirst();
        while (!isBeforeFirst() && !isAfterLast()){
            alarmItems.add(getAlarmItem());
            moveToNext();
        }
        return alarmItems;
    }

    private AlarmItem getAlarmItem(){
        if(!isBeforeFirst() && !isAfterLast()) {
            AlarmItem alarmItem = new AlarmItem();
            alarmItem.setTime(getString(getColumnIndex(AlarmItemTable.COLUMN_TIME)));
            alarmItem.setDescription(getString(getColumnIndex(AlarmItemTable.COLUMN_DESCRIPTION)));
            alarmItem.setSwitchedOn(getInt(getColumnIndex(AlarmItemTable.COLUMN_SWITCH)));
            alarmItem.setId(getInt(getColumnIndex(AlarmItemTable.COLUMN_ID)));
            return alarmItem;
        }
        return null;
    }

}
