package com.firstdataproblem.rest;

import retrofit2.Retrofit;

/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */

public class ApiClient {

    public static final String BASE_URL = "http://app-api.offers.net/";
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