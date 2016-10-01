package com.att.rest;

import retrofit2.Retrofit;

/**
 * Created by Avdhesh AKhani on 9/30/2016.
 */
public class ATTClient {

    public static final String BASE_URL = "http://citymani.ezrdv.org/main/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
