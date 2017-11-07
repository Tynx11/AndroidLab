package com.tony.d.networkapp.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Tony on 30.10.2017.
 */

public interface RetrofitService {
    @GET("/get")
    Call<Object> getData();
}
