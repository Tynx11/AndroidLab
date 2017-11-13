package com.tony.d.alarmclock2.screen.main.second;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tony.d.alarmclock2.Alarm.AlarmReciever;
import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.loader.MainPresenter;
import com.tony.d.alarmclock2.loader.MainView;
import com.tony.d.alarmclock2.model.database.DatabaseHelper;
import com.tony.d.alarmclock2.model.entity.AlarmItem;
import com.tony.d.alarmclock2.screen.base.BaseFragment;
import com.tony.d.alarmclock2.screen.main.first.MainActivity;

import java.util.List;

/**
 * Created by Tony on 29.10.2017.
 */

public class SecondFragment extends BaseFragment implements MainView {

    private final static String KEY = "key";
    private FloatingActionButton floatingActionButton;

    private TimePicker timePicker;
    private RadioGroup radioGroup;

    private DatabaseHelper databaseHelper;

    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private AlarmItem alarmItem;
    private boolean request;
    private String time;
    private MainPresenter presenter;


    public static SecondFragment newInstance(Bundle data) {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);

         final java.util.Calendar calendar = java.util.Calendar.getInstance();
        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        radioGroup = view.findViewById(R.id.radio);
        floatingActionButton = view.findViewById(R.id.floating_action_bar1);
        alarmItem =(AlarmItem) getArguments().getParcelable("asd");
       // Toast.makeText(getContext(),String.valueOf( alarmItem.getIdd()),Toast.LENGTH_LONG).show();

        monday = view.findViewById(R.id.monday);
        tuesday = view.findViewById(R.id.tuesday);
        wednesday = view.findViewById(R.id.wednesday);
        thursday = view.findViewById(R.id.Thursday);
        friday = view.findViewById(R.id.friday);
        saturday = view.findViewById(R.id.saturday);
        sunday = view.findViewById(R.id.sunday);

        presenter = new MainPresenter(databaseHelper,getLoaderManager(),getActivity(),this);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(java.util.Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(java.util.Calendar.MINUTE,timePicker.getMinute());

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour < 10){
                    hour_string = "0" + String.valueOf(hour);
                }

                if (minute < 10){
                    minute_string ="0" + String.valueOf(minute);
                }

                time = hour_string+":"+minute_string;

                // TODO: 23.10.2017 если есть такой id то метод update, если его нет, то метод insert, еще нужно добавить дин.кноипку удалить.
                if (alarmItem != null) {
                    alarmItem.setTime(time);
                    alarmItem.setDescription(getDays());
                    alarmItem.setSwitchedOn(1);
                    presenter.updateQuery(alarmItem);

                }
                if (alarmItem == null) {
                    presenter.insertAlarmItems(time,getDays());

                }
                Intent intent = new Intent(getContext(),MainActivity.class);

                startActivity(intent);
            }
        });

        return view;
    }


    public String getDays(){
        String answer = "'";
        if (monday.isChecked()){
            answer += monday.getText()+" ";
        }if (tuesday.isChecked()){
            answer += tuesday.getText()+" ";
        }if (wednesday.isChecked()){
            answer += wednesday.getText()+" ";
        }if (thursday.isChecked()){
            answer += thursday.getText()+" ";
        }if (friday.isChecked()){
            answer += friday.getText()+" ";
        }if (saturday.isChecked()){
            answer += saturday.getText()+" ";
        }if (sunday.isChecked()){
            answer += sunday.getText()+"";
        }
        return answer+"'";
    }



    @Override
    public void onPersonsLoaded(List<AlarmItem> alarmItems) {

    }
}
