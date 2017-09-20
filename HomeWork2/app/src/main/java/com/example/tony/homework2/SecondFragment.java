package com.example.tony.homework2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Tony on 19.09.2017.
 */

public class SecondFragment extends Fragment {
    private final String KEY_RES = "key";
    private RadioGroup radioGroup;
    private TextView textView;
    private Button button;
    private String[] answers;
    private int mCountable;
    private int saveCount;


    public static SecondFragment newInstance(int mCountable, int saveCount,int[] selectedAnswers) {

        Bundle args = new Bundle();
        // TODO ключи в константы
        args.putInt("Key1",mCountable);
        args.putInt("key2",saveCount);
        args.putIntArray("Key3",selectedAnswers);
        SecondFragment fragment = new SecondFragment();
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
        View view = inflater.inflate(R.layout.activity_second,container,false);

        final String [] quenstions = getResources().getStringArray(R.array.questions);
        final int [] rightAnswers = getResources().getIntArray(R.array.right_answers);
        final int [] selectedAnswers =  getArguments().getIntArray("Key3");
        // TODO: 10.09.2017   Вывод первого текста и обозначения радио кнопок
        textView = (TextView) view.findViewById(R.id.question);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        // TODO ключи в константы
        mCountable=getArguments().getInt("Key1");
        saveCount=getArguments().getInt("key2");
        if (mCountable < quenstions.length) {
            getAnswers(mCountable);
            textView.setText(quenstions[mCountable]);

            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton) radioGroup.getChildAt(i)).setText(answers[i]);
            }
        }




        button = (Button) view.findViewById(R.id.go_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 10.09.2017  Вывод разных вопросов и описания радиобаттона
                if (mCountable < quenstions.length - 1) {

                    // TODO ключи в константы
                    // TODO проверка на null?
                    selectedAnswers[getArguments().getInt("key2")] = getIdRb();
                    mCountable++;
                    saveCount++;
                    radioGroup.clearCheck();
                    getFragmentManager()
                            .beginTransaction()
                            // TODO респект , за то, что без статика
                            .replace(R.id.fragment_container,SecondFragment.newInstance(mCountable,saveCount,selectedAnswers))
                            .commit();

                } else {
                    int sovp = 0;
                    // TODO тоже проверку на null требует
                    for (int z = 0; z < selectedAnswers.length;z++){
                        if(rightAnswers[z] == (selectedAnswers[z])){
                            sovp++;
                        }
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,SumFragment.newInstance(sovp))
                            .commit();


                }
            }
        });

        return view;
    }
    // TODO: 10.09.2017 Получение ресурсов и присваивание Id

    private void getAnswers(int i){


        // TODO строки в константы
        answers = getResources().getStringArray(getResources()
                .getIdentifier("answers"+i,"array",getActivity().getPackageName()));


    }
    private int getIdRb(){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        int a;
        switch (radioButtonId) {
            case R.id.first_radio_button : a = 1; break;
            case R.id.second_radio_button: a = 2; break;
            case R.id.third_radio_button : a = 3; break;
            default: a = -1;
        }
        return a;
    }
}