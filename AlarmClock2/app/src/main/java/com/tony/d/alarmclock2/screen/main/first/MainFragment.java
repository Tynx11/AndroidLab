package com.tony.d.alarmclock2.screen.main.first;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import com.tony.d.alarmclock2.Alarm.AlarmReciever;
import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.loader.MainPresenter;
import com.tony.d.alarmclock2.loader.MainView;
import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.screen.base.BaseFragment;
import com.tony.d.alarmclock2.screen.main.second.SecondActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tony on 29.10.2017.
 */

public class MainFragment extends BaseFragment implements OnItemClickListener,MainView {

    private final int WEEK = 1000 * 60 * 60 * 24 * 7;
    private final String KEY = "key";
    public static final String ALARM_KEY = "asd";

    private RecyclerView recyclerView;

    private MainAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private DatabaseHelper databaseHelper;

    List<AlarmItem> alarmItems;

    private boolean request;

    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private MainPresenter presenter;

    private  boolean[] arrayDay;




    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        databaseHelper = new DatabaseHelper(getActivity());
        presenter = new MainPresenter(databaseHelper,getLoaderManager(),getActivity(),this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        alarmItems = new ArrayList<>();



        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MainAdapter(alarmItems, this);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.loadAlarmItems();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_clear) {
            presenter.clearAll();
            presenter.loadAlarmItems();
        }
        return true;
    }


    @Override
    public void onClick(int position, AlarmItem alarmItem) {
        request = true;

        Toast.makeText(getContext(), String.valueOf(alarmItem.getIdd()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra(ALARM_KEY, alarmItem);
        intent.putExtra(KEY, request);

        startActivity(intent);

    }

    @Override
    public void onSwitchClick(int position, SwitchCompat switchCompat, AlarmItem alarmItem) {
//        String[] alertDays = alarmItem.getDescription().split(" ");
//
//        Locale ru = new Locale("US");
//        SimpleDateFormat dayFormatter = new SimpleDateFormat("F",ru);
//        String todayText = dayFormatter.format(new Date(System.currentTimeMillis()));

        if (switchCompat.isChecked()) {
            alarmItem.setSwitchedOn(1);
          //  pendingIntent = PendingIntent.getBroadcast(getContext(), alarmItem.getIdd(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
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
                        Toast.makeText(getContext(), calendar.getTime().toString(), Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getContext(), AlarmReciever.class);

                        pendingIntent = PendingIntent.getBroadcast(getContext(), alarmItem.getIdd(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), WEEK,pendingIntent);
                    }
                }
            }



         else {
            alarmItem.setSwitchedOn(0);
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent);

            }
        }
        presenter.updateSwitch(alarmItem);
    }



    private boolean[] fillDays(AlarmItem alarmItem) {
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

    @Override
    public void onPersonsLoaded(List<AlarmItem> alarmItems) {
        adapter.setAlarmItems(alarmItems);
    }
}


//}

