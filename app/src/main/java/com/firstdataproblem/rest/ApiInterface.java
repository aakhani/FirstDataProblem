package com.firstdataproblem.rest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */

public interface ApiInterface {

    /*@GET("info")
    Call<ResponseBody> getStocks(@Query("client") String client, @Query("q") String stocks);
*/
    @GET("categories")
    Call<ResponseBody> getCategories();
}