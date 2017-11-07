package com.tony.d.alarmclock2.screen.main.first;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tony.d.alarmclock2.R;
import com.tony.d.alarmclock2.model.entity.AlarmItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 29.10.2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

    private List<AlarmItem> alarmItems;

    private OnItemClickListener onItemClickListener;
    private int pos;



    public MainAdapter() {
        this.alarmItems = new ArrayList<>();
    }

    private AlarmItem alarmItem;



    public MainAdapter(List<AlarmItem> alarmItems,OnItemClickListener onItemClickListener) {
        this.alarmItems = alarmItems;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new MainViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
      holder.alarmItem = alarmItems.get(holder.getAdapterPosition());
        alarmItem = alarmItems.get(position);


        holder.time.setText(String.valueOf(alarmItem.getTime().toString()));
        holder.description.setText(String.valueOf(alarmItem.getDescription().toString()));
        if (alarmItem.isSwitchedOn() == 1){
            holder.switchCompat.setChecked(true);
        } else {
            holder.switchCompat.setChecked(false);
        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onItemClickListener != null){
//                    onItemClickListener.onClick(alarmItem);
//                }
//
//            }
//       });

    }

    @Override
    public int getItemCount() {
        return alarmItems.size();
    }



    public void setAlarmItems(@NonNull List<AlarmItem> alarmItems){
        this.alarmItems = alarmItems;
        notifyDataSetChanged();
    }
}

