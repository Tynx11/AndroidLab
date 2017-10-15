package com.example.tony.homeworkrecycler.screen.event_list.event_pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tony.homeworkrecycler.R;
import com.example.tony.homeworkrecycler.customView.PagerIndicator;
import com.example.tony.homeworkrecycler.model.Event;

import java.util.ArrayList;

/**
 * Created by Tony on 08.10.2017.
 */

public class EventPagerActivity extends AppCompatActivity {

    private static final String KEY_POSITION = "position";

    private static final String KEY_EVENTS = "event";

    private ViewPager viewPager;

    private EventPagerAdapter adapter;

    private ArrayList<Event> events;

    private PagerIndicator pagerIndicator;


    public static Intent makeIntent (Context context, int position, ArrayList<Event> events){
        Intent intent = new Intent(context, EventPagerActivity.class);
        intent.putExtra(KEY_POSITION, position);
        intent.putParcelableArrayListExtra(KEY_EVENTS,events);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);

        events = getIntent().getParcelableArrayListExtra(KEY_EVENTS);

        adapter = new EventPagerAdapter(getSupportFragmentManager(), events);

        pagerIndicator = (PagerIndicator) findViewById(R.id.pager_indicator);
        pagerIndicator.setItemsCount(events.size(),getIntent().getIntExtra(KEY_POSITION,0 ));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getIntent().getIntExtra(KEY_POSITION,0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pagerIndicator.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}