package com.firstdataproblem.thirdway;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.firstdataproblem.extra.IntentServiceResult;
import com.firstdataproblem.rest.ApiClient;
import com.firstdataproblem.rest.ApiInterface;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Avdhesh AKhani on 12/4/2016.
 */

public class PaymentIntentService extends IntentService {

    private static final String TAG = "PaymentIntentService";
    private String json = "";


    public PaymentIntentService() {
        super("PaymentIntentService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "Transection Success");

                try {
                    json = response.body().string();
                  //  sendResultBroadcast(json);
                    EventBus.getDefault().post(new IntentServiceResult(Activity.RESULT_OK, json));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, json);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }



    void sendResultBroadcast(String jsonResult) {

        Intent in = new Intent();
        in.putExtra("jsonresult", jsonResult);
        in.setAction("com.firstdataproblem.CUSTOM_BROADCAST");
        sendBroadcast(in);
    }

}
