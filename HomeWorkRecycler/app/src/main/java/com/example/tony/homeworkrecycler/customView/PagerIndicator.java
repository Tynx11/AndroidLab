package com.example.tony.homeworkrecycler.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.tony.homeworkrecycler.R;


/**
 * Created by Tony on 25.09.2017.
 */

public class PagerIndicator extends LinearLayout {

    private int currentPosition = 0;

    public PagerIndicator(Context context) {
        super(context);
        init();
    }

    public PagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // TODO: 25.09.2017 сделать ориентацию и зададим гравити
    private void init(){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

    }

    // TODO: 25.09.2017 Логика заполнения въюшки

    public void setItemsCount(int count, int position){
        removeAllViews();
        for (int i = 0; i < count; i++){
            ImageView imageView = new ImageView(getContext());
            // TODO: 25.09.2017 назначить пустое изображение
            imageView.setImageResource(R.drawable.indicator_unactive);
            addView(imageView);

        }
        currentPosition = 0;
        setCurrentPosition(currentPosition);

    }
    public void setCurrentPosition(int position){
        if(position >= 0 && position < getChildCount()){
            // TODO назначить пустое изображение
            ((ImageView) getChildAt(currentPosition)).setImageResource(R.drawable.indicator_unactive);
            currentPosition = position;
            //TODO назначить не пустое изображение
            ((ImageView) getChildAt(currentPosition)).setImageResource(R.drawable.indicator_active);
        }
    }



}