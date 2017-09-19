package com.example.tony.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.tony.home.R.array.questions;

/**
 * Created by Tony on 19.09.2017.
 */

public class SumFragment extends Fragment {

    private TextView textView;

    private Button repeatButton;
    private Button exitButton;

    private static final String KEY = "key4";

    private int count;
    private static final String TAG_NEXT_STEP = " TAG";

    public static SumFragment newInstance(int count) {

        Bundle args = new Bundle();
        args.putInt(KEY,count);
        SumFragment fragment = new SumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third,container,false);

        textView = (TextView) view.findViewById(R.id.result);


        repeatButton = (Button) view.findViewById(R.id.repeat);
        exitButton = (Button) view.findViewById(R.id.exit);

        count = getArguments().getInt(KEY);



        textView.setText(String.valueOf(count) + " / " + getResources().getStringArray(R.array.right_answers).length );
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllertDialogRestart fragment = new AllertDialogRestart(count,questions);
                fragment.show(getFragmentManager(),TAG_NEXT_STEP);


            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogExit fragment = new AlertDialogExit(count,questions);
                fragment.show(getFragmentManager(),TAG_NEXT_STEP);
            }
        });
        return view;
    }
}
