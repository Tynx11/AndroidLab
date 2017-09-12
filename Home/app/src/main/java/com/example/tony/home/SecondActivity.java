package com.example.tony.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SecondActivity extends AppCompatActivity{


    final String KEY_RES = "key";
    RadioGroup radioGroup;
    TextView textView;
    Button button;
    String[] answers;
    int mCountable = 1;
    int saveCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final String [] quenstions = getResources().getStringArray(R.array.questions);
        final int [] rightAnswers = getResources().getIntArray(R.array.right_answers);
        final int [] selectedAnswers =  new int [rightAnswers.length];
        // TODO: 10.09.2017   Вывод первого текста и обозначения радио кнопок
        textView = (TextView) findViewById(R.id.question);
        textView.setText(quenstions[0]);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        getAnswers(0);

        for (int i = 0; i < radioGroup.getChildCount();i++){
            ((RadioButton) radioGroup.getChildAt(i)).setText(answers[i]);
        }

        button = (Button) findViewById(R.id.go_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 10.09.2017  Вывод разных вопросов и описания радиобаттона
                if (mCountable < quenstions.length) {

                    textView.setText(quenstions[mCountable]);
                    getAnswers(mCountable);
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setText(answers[i]);
                    }
                    selectedAnswers[saveCount] = getIdRb();
                    mCountable++;
                    saveCount++;
                    radioGroup.clearCheck();


                } else {
                    selectedAnswers[saveCount] = getIdRb();
                    int sovp = 0;
                    for (int z = 0; z < selectedAnswers.length;z++){
                        if(rightAnswers[z] == (selectedAnswers[z])){
                            sovp++;
                        }
                    }
                    Intent thirdIntent = new Intent (SecondActivity.this, ThirdActivity.class);
                    thirdIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    thirdIntent.putExtra(KEY_RES,sovp);
                    startActivity(thirdIntent);

                }
            }
        });
    }

    // TODO: 10.09.2017 Получение ресурсов и присваивание Id
    @Override
    protected void onStart() {
        super.onStart();
    }
    private void getAnswers(int i){
        switch (i){
            case 0: answers = getResources().getStringArray(R.array.answers1); break;
            case 1: answers = getResources().getStringArray(R.array.answers2); break;
            case 2: answers = getResources().getStringArray(R.array.answers3); break;
            case 3: answers = getResources().getStringArray(R.array.answers4); break;
            case 4: answers = getResources().getStringArray(R.array.answers5); break;
            case 5: answers = getResources().getStringArray(R.array.answers6); break;
            case 6: answers = getResources().getStringArray(R.array.answers7); break;
            case 7: answers = getResources().getStringArray(R.array.answers8); break;
            case 8: answers = getResources().getStringArray(R.array.answers9); break;
            case 9: answers = getResources().getStringArray(R.array.answers10); break;
        }
    }
    private int getIdRb(){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        int a;
        switch (radioButtonId) {
            case R.id.first_radio_button : a = 1; break;
            case R.id.second_radio_button: a = 2; break;
            case R.id.third_radio_button : a = 3; break;
            default: a = 0;
        }
        return a;
    }

}
