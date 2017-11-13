package com.tony.d.alarmclock2.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.model.wrapper.AlarmItemWrapper;
import com.tony.d.alarmclock2.screen.main.second.SecondFragment;

import java.util.List;

/**
 * Created by Tony on 11/13/2017.
 */

public class MainPresenter implements LoaderManager.LoaderCallbacks {

    public static final int LOADER_SELECT_ALARM_ITEMS = 111;

    public static final int LOADER_INSERT_ALARM_ITEMS = 222;

    public static final int LOADER_CLEAR_ALARM_ITEMS = 333;

    public static final int LOADER_UPDATE_SWITCH = 444;

    public static final int LOADER_UPDATE_QUERY = 555;

    private DatabaseHelper databaseHelper;

    private LoaderManager loaderManager;

    private MainView mainView;



    private Context context;

    private SecondFragment secondFragment = new SecondFragment();

    private String day;

    private String tim;

    private AlarmItem alarmItem;
    private AlarmItem alarmItem1;




    public MainPresenter(DatabaseHelper databaseHelper, LoaderManager loaderManager,Context context,MainView mainView) {
        this.databaseHelper = databaseHelper;
        this.loaderManager = loaderManager;
        this.context = context;
        this.mainView = mainView;
    }

    public void loadAlarmItems(){
        loaderManager.initLoader(LOADER_SELECT_ALARM_ITEMS,null,this);

    }

    public void  insertAlarmItems(String time,String days){
        day = days;
        tim = time;
        loaderManager.restartLoader(LOADER_INSERT_ALARM_ITEMS,null,this);
    }

    public void updateSwitch(AlarmItem alarmItem){
        this.alarmItem = alarmItem;
        loaderManager.restartLoader(LOADER_UPDATE_SWITCH,null,this);
    }

    public void updateQuery(AlarmItem alarmItem) {
        this.alarmItem1 = alarmItem;
        loaderManager.restartLoader(LOADER_UPDATE_QUERY,null,this);
    }

    public void clearAll(){
        loaderManager.restartLoader(LOADER_CLEAR_ALARM_ITEMS,null,this);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case MainPresenter.LOADER_SELECT_ALARM_ITEMS:
                return new MainSelectLoader(context,databaseHelper);
            case MainPresenter.LOADER_INSERT_ALARM_ITEMS:
                return new MainInsertLoader(context,databaseHelper,new AlarmItem(tim,day,0,(int) System.currentTimeMillis()));

            case MainPresenter.LOADER_CLEAR_ALARM_ITEMS:
                return new MainDeleteLoader(context,databaseHelper);
            case MainPresenter.LOADER_UPDATE_SWITCH:
                return new MainUpdateSwitch(context,databaseHelper,alarmItem);
            case MainPresenter.LOADER_UPDATE_QUERY:
                return new MainUpdateQuery(context,databaseHelper,alarmItem1);


        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object object) {
        switch (loader.getId()) {
            case LOADER_SELECT_ALARM_ITEMS:
                List<AlarmItem> alarmItems = new AlarmItemWrapper((Cursor) object).getAlarmItems();
                mainView.onPersonsLoaded(alarmItems);
                break;
            case LOADER_INSERT_ALARM_ITEMS:
                loaderManager.restartLoader(LOADER_SELECT_ALARM_ITEMS,null,this);
                break;
            case LOADER_CLEAR_ALARM_ITEMS:
                loaderManager.restartLoader(LOADER_SELECT_ALARM_ITEMS,null,this);
                break;
            case LOADER_UPDATE_SWITCH:
                loaderManager.restartLoader(LOADER_SELECT_ALARM_ITEMS,null,this);
                break;
            case LOADER_UPDATE_QUERY:
                loaderManager.restartLoader(LOADER_SELECT_ALARM_ITEMS,null,this);
        }



    }

    @Override
    public void onLoaderReset(Loader loader) {

    }


}