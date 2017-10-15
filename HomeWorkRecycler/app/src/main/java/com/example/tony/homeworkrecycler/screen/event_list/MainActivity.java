package com.example.tony.homeworkrecycler.screen.event_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tony.homeworkrecycler.R;
import com.example.tony.homeworkrecycler.model.Event;
import com.example.tony.homeworkrecycler.screen.event_list.event_pager.EventPagerActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView recyclerView;
    private List<Event> events;
    private LinearLayoutManager layoutManager;
    EventsListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = getEvents();

        adapter = new EventsListAdapter(events,this);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


    }

    private  List<Event> getEvents() {
        List<Event> events = new ArrayList<>();

        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Я проснулся", "Антон", new Date(2017,11,31)));
        events.add(new Event(1,R.mipmap.ic_launcher_round,"Пора кушать", "Холодильник LG", new Date(2017,11,31)));
        events.add(new Event(2,R.mipmap.ic_launcher_round,"Ты мало ходишь", "Браслет miband", new Date(2017,11,31)));
        events.add(new Event(3,R.mipmap.ic_launcher_round,"Сделать таск по андройду", "Календарь", new Date(2017,11,31)));
        events.add(new Event(4,R.mipmap.ic_launcher_round,"Спать", "Браслет miband", new Date(2017,11,31)));

        return events;
    }

    @Override
    public void onClick(int position) {
        startActivity(EventPagerActivity.makeIntent(this,position, (ArrayList<Event>) getEvents()));
    }
}
