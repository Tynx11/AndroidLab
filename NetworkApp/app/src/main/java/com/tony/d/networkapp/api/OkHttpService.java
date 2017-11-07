package com.tony.d.networkapp.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tony on 30.10.2017.
 */

public class OkHttpService {

    public String load (@NonNull String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
