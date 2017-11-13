package com.tony.d.alarmclock2.Alarm;

import android.app.*;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;

import com.tony.d.alarmclock2.loader.MainPresenter;
import com.tony.d.alarmclock2.model.entity.AlarmItem;

import java.util.Calendar;

import static java.security.AccessController.getContext;

/**
 * Created by Tony on 11/13/2017.
 */

public class AlarmUtils {

    private static Calendar calendar;
    private static AlarmManager alarmManager;
    private static PendingIntent pendingIntent;
    private MainPresenter presenter;
    private static boolean[] arrayDay;
    private static final int WEEK = 1000 * 60 * 60 * 24 * 7;

    public static void alarming (AlarmItem alarmItem,MainPresenter presenter,Context context){

            //  pendingIntent = PendingIntent.getBroadcast(getContext(), alarmItem.getIdd(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            arrayDay = fillDays(alarmItem);
            for (int i = 1; i < arrayDay.length; i++) {
                if (arrayDay[i] == true) {

                    calendar = Calendar.getInstance();
                    String hours = alarmItem.getTime().substring(0, 2);
                    String minute = alarmItem.getTime().substring(3, 5);

                    if(Calendar.DAY_OF_MONTH < System.currentTimeMillis()){
                        calendar.set(Calendar.DAY_OF_MONTH,Calendar.DAY_OF_MONTH + 7);
                    }
                    calendar.set(Calendar.DAY_OF_WEEK, i);
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
                    calendar.set(Calendar.SECOND, 0);

                    Calendar now = Calendar.getInstance();
                    if (now.after(calendar) ) {
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);

                    }
                    Toast.makeText(context, calendar.getTime().toString(), Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(context, AlarmReciever.class);

                    pendingIntent = PendingIntent.getBroadcast(context, alarmItem.getIdd(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                    alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), WEEK,pendingIntent);
                }
            }
        }




    private static boolean[] fillDays(AlarmItem alarmItem) {
        String[] str = alarmItem.getDescription().split(" ");
        boolean[] dayArray = {false, false, false, false, false, false, false,false};

        for(String item : str) {
            if ( item.equals("понедельник") ) {
                dayArray[2] = true;
            }if ( item.equals("вторник") ) {
                dayArray[3] = true;
            }if ( item.equals("среда")) {
                dayArray[4] = true;
            }if (item.equals("четверг")) {
                dayArray[5] = true;
            }if ( item.equals("пятница")) {
                dayArray[6] = true;
            }if ( item.equals("суббота")) {
                dayArray[7] = true;
            }if ( item.equals("воскресенье") ) {
                dayArray[1] = true;
            }

        }
        return dayArray;
    }
    }

