package com.tony.d.networkapp.View;

import android.app.Application;

import com.tony.d.networkapp.api.RetrofitService;

import retrofit2.Retrofit;

/**
 * Created by Tony on 30.10.2017.
 */

public class AppDelegate extends Application {

    private RetrofitService retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(Const.URL_BASE)
                .build()
                .create(RetrofitService.class);



    }
    public RetrofitService getRetrofit(){
        return retrofit;
    }

}
