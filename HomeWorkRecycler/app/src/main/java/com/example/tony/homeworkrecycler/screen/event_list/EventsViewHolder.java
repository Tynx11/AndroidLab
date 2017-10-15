package com.example.tony.homeworkrecycler.screen.event_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tony.homeworkrecycler.R;

/**
 * Created by Tony on 25.09.2017.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {

    public ImageView photoImageView;

    public TextView name;

    public TextView eventDescription;

    public TextView dateTextView;

    public EventsViewHolder(View itemView,final OnItemClickListener onItemClickListener) {
        super(itemView);

        photoImageView = (ImageView) itemView.findViewById(R.id.event_photo);

        name = (TextView) itemView.findViewById(R.id.event_name);

        eventDescription = (TextView) itemView.findViewById(R.id.event_description);

        dateTextView = (TextView) itemView.findViewById(R.id.event_date);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    // TODO: 18.09.2017 позволяет узнать текущую позицию при клике
                    onItemClickListener.onClick(getAdapterPosition());


                }
            }
        });


    }
}
