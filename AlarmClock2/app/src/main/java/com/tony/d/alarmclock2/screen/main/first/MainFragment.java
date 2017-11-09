package com.tony.d.alarmclock2.screen.main.first;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
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

import com.tony.d.alarmclock2.Alarm.AlarmReciever;
import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.screen.base.BaseFragment;
import com.tony.d.alarmclock2.screen.main.second.SecondActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 29.10.2017.
 */

public class MainFragment extends BaseFragment implements OnItemClickListener {

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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        alarmItems = new ArrayList<>();


        for(int i = 0; i < alarmItems.size();i++){
            databaseHelper.insertAlarmItem(alarmItems.get(i));
        }

        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MainAdapter(alarmItems,this);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.setAlarmItems(databaseHelper.selectAlarmItems());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_clear){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    databaseHelper.clearAlarmItems();
                    adapter.setAlarmItems(databaseHelper.selectAlarmItems());
                }
            });
        }
        return true;
    }




    @Override
    public void onClick(int position,AlarmItem alarmItem) {
        request = true;

        Toast.makeText(getContext(),String.valueOf(alarmItem.getIdd()),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra(ALARM_KEY,alarmItem);
        intent.putExtra(KEY,request);

        startActivity(intent);

    }

    @Override
    public void onSwitchClick(int position, SwitchCompat switchCompat,AlarmItem alarmItem) {
        if (switchCompat.isChecked()) {
            calendar = Calendar.getInstance();
            String hour = alarmItem.getTime().substring(0,2);
            String min = alarmItem.getTime().substring(3,5);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
            calendar.set(Calendar.MINUTE, Integer.parseInt(min));

            Calendar now = Calendar.getInstance();{
                if(now.after(calendar))
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
            }

            setAlarm(calendar,position);
            alarmItem.setIsSwitchedOn(1);
        } else {
            alarmItem.setIsSwitchedOn(0);
            pendingIntent.cancel();
            alarmManager.cancel(pendingIntent);

        }
        Toast.makeText(getContext(), String.valueOf(alarmItem.getIdd()), Toast.LENGTH_LONG).show();
        databaseHelper.updateSwitch(alarmItem);
    }


    private  void setAlarm(Calendar calendar,int position) {
        Intent intent = new Intent(getContext(), AlarmReciever.class);
        intent.putExtra("key",1);
        pendingIntent = PendingIntent.getBroadcast(this.getActivity(),position,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getActivity().getSystemService(getContext().ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }




}

