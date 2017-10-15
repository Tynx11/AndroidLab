package com.example.tony.homeworkrecycler.screen.event_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.homeworkrecycler.R;
import com.example.tony.homeworkrecycler.model.Event;

import java.util.List;

/**
 * Created by Tony on 25.09.2017.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    private List<Event> events;

    private OnItemClickListener onItemClickListener;

    public EventsListAdapter(List<Event> events, OnItemClickListener onItemClickListener) {
        this.events = events;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_model,parent,false);
        return new EventsViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

        Event event = events.get(position);

        holder.photoImageView.setImageResource(event.getPhotoId());

        holder.name.setText(event.getName());

        holder.eventDescription.setText(event.getEventDescription());

        holder.dateTextView.setText(event.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
