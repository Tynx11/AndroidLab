package com.tony.d.alarmclock2.screen.main.first;

import android.support.v7.widget.SwitchCompat;

import com.tony.d.alarmclock2.model.entity.AlarmItem;

/**
 * Created by Tony on 29.10.2017.
 */

public interface OnItemClickListener {

    void onClick(int position,AlarmItem alarmItem);
    void onSwitchClick (int position, SwitchCompat switchCompat,AlarmItem alarmItem);



}

