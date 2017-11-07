package com.tony.d.networkapp.loader;

import android.content.Context;
import android.util.Log;

import com.tony.d.networkapp.api.InputStreamService;
import com.tony.d.networkapp.api.OkHttpService;

import java.io.IOException;

/**
 * Created by Tony on 30.10.2017.
 */

public class OkHttpLoader extends BaseAsyncTaskLoader<String> {

    private static final String LOG_TAG = "InputStreamLoader";
    private String url;

    public OkHttpLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected String onLoad() {
        try {
            return new OkHttpService().load(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,e.getMessage());
        }
        return null;
    }

}
