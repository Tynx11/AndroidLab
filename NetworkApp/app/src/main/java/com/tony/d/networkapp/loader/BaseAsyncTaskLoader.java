package com.tony.d.networkapp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Tony on 30.10.2017.
 */

public abstract class BaseAsyncTaskLoader<R> extends AsyncTaskLoader {
    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public R loadInBackground() {
        return null;
    }

    protected abstract R onLoad();



    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


}
