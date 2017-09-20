package com.example.tony.homework2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Tony on 19.09.2017.
 */

public class MainFragment extends Fragment {

    private Button start;

    public static MainFragment newInstance(String str) {
        Bundle args = new Bundle();
        // TODO в константы
        args.putString("key",str);
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.activity_main_fragment,container,false);

        start = (Button) view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO тут должна создаваться новая активность(second), а не происходить replace фрагмента
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,SecondFragment.newInstance(0,0,new int[getResources().getIntArray(R.array.right_answers).length]))
                        .commit();

            }
        });
        return view;
    }
}