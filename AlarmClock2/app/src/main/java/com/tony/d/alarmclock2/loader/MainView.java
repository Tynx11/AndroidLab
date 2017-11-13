package com.tony.d.alarmclock2.loader;

import com.tony.d.alarmclock2.model.entity.AlarmItem;

import java.util.List;

/**
 * Created by Tony on 11/13/2017.
 */

public interface MainView {
    void onPersonsLoaded(List<AlarmItem> alarmItems);
}
