package com.tony.d.alarmclock2.screen.main.first;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.model.entity.AlarmItem;

/**
 * Created by Tony on 29.10.2017.
 */

class MainViewHolder extends RecyclerView.ViewHolder {

    public TextView time;

    public TextView description;

    public SwitchCompat switchCompat;

    public AlarmItem alarmItem;



    public MainViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);

        time = itemView.findViewById(R.id.time);

        description = itemView.findViewById(R.id.description);

        switchCompat = itemView.findViewById(R.id.switch_main);

      itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    // TODO: 18.09.2017 позволяет узнать текущую позицию при клике
                    onItemClickListener.onClick(getAdapterPosition(),alarmItem);


                }
            }
       });




    }
}
