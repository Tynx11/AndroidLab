package com.tony.d.networkapp;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tony.d.networkapp.View.AppDelegate;
import com.tony.d.networkapp.View.Const;
import com.tony.d.networkapp.loader.InputStreamLoader;
import com.tony.d.networkapp.loader.OkHttpLoader;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks, Callback<Object> {


    private static final int LOADER_NETWORK = 1;
    @BindView(R.id.text_view_result) TextView resultTextView;

    @BindView(R.id.start_button1) Button startButton;

    @BindView(R.id.start_button2) Button startRetrofitButton;



    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       unbinder = ButterKnife.bind(this);


        // TODO: 30.10.2017 Бинд для фрагментов
        //ButterKnife.bind(this,view);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    @OnClick(R.id.start_button1)
    public void onStartClick(){
        getLoaderManager().initLoader(LOADER_NETWORK,null,this);
    }

    @OnClick (R.id.start_button2)
    public void onStartRetrofitClick(){
        // TODO: 30.10.2017
        ((AppDelegate)getApplication()).getRetrofit().getData().enqueue(this);
    }
    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args){
        switch (id){
            case LOADER_NETWORK:
                return new OkHttpLoader(this, Const.URI_GET);
        }
        return  null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        switch (loader.getId()){
            case LOADER_NETWORK:
                onNetworkLoadingSuccess((String) o);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void onNetworkLoadingSuccess(@Nullable String result) {
        startButton.setVisibility(View.GONE);
        startRetrofitButton.setVisibility(View.GONE);
        resultTextView.setText(result);
    }




    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

            onNetworkLoadingSuccess(response.body().toString());

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {

    }
}
