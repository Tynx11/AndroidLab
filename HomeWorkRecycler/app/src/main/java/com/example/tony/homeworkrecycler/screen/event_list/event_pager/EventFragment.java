package com.example.tony.homeworkrecycler.screen.event_list.event_pager;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tony.homeworkrecycler.R;
import com.example.tony.homeworkrecycler.model.Event;

/**
 * Created by Tony on 08.10.2017.
 */

public class EventFragment extends Fragment {

    public static final String KEY_EVENT = "event";

    private ImageView eventImage;
    private TextView nameEvent;
    private TextView eventDescription;
    private TextView eventDate;
    private LinearLayout linearLayout;


    public static EventFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_EVENT,event); // ???
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.event_fragment, container, false);

        Event event = getArguments().getParcelable(KEY_EVENT);


        eventImage = (ImageView) view.findViewById(R.id.event_image_fragment);
        nameEvent = (TextView) view.findViewById(R.id.event_name_fragment);
        eventDescription = (TextView) view.findViewById(R.id.description_view_name);
        eventDate = (TextView) view.findViewById(R.id.data_view_name);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        nameEvent.setText(event.getName());
        eventImage.setImageResource(event.getPhotoId());
        eventDescription.setText(event.getEventDescription());

        return view;




    }
}

