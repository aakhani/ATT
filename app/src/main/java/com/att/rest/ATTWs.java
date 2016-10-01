package com.att.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Avdhesh AKhani on 9/14/2016.
 */
public interface ATTWs {

    @GET("test")
Call<ResponseBody> loadJson();
    @GET("test?")
    Call<ResponseBody> loadMorepages(@Query("page") int currentpage);
}
