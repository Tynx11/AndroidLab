package com.example.tony.homework2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Tony on 19.09.2017.
 */

public class AlertDialogExit extends DialogFragment implements DialogInterface.OnClickListener {

    private Uri uri = Uri.parse("https://vk.com/id267510603");
    private int a;
    private int b;
    public AlertDialogExit(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_next_step, null,false);
        return new AlertDialog
                .Builder(getActivity())
                .setView(v)
                .setPositiveButton(R.string.dialog_positive,this)
                .setNegativeButton(R.string.dialog_negative,this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case DialogInterface.BUTTON_POSITIVE:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                // TODo в стринги
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Я очень умный "+ "ответил на " + a+
                        " вопросов из "+ b + ". Попробуй повтори. "
                        + "отправлено из самодельного android приложения"
                        + "\n"+ uri);
                // TODO в константы
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                // TODO диалог можно закрыть с помощью  getFragmentManager().beginTransaction().remove(this).commit()
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }
    public void onBackPressed(){
    }
}