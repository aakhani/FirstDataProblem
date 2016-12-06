package com.firstdataproblem.extra;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firstdataproblem.rest.ApiClient;
import com.firstdataproblem.rest.ApiInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutService extends Service {

    private static final String TAG = "CheckooutService";
    private CheckoutBinder binder = new CheckoutBinder();
    private String json = "";
    private String respose = "";
    public static final String isSuccessful = "Successful";

    public CheckoutService() {
    }


    public  String getJson(){

        Log.e(TAG,"In Service");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                respose = loadData();
            }
        },10000);

        return respose;

    }

    public String loadData() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "Transection Success");

             /*   Context ctx = getApplicationContext();
                SharedPreferences prefs = ctx.getSharedPreferences("MyPrefs",0);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(isSuccessful, "JSON");
                editor.commit();*/

               showNotification();

                try {
                    json = response.body().string();

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


        return json;
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentTitle("Notifications Title");
        builder.setContentText("Your notification content here.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }


    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    public class CheckoutBinder  extends Binder{

        public CheckoutService getService(){
            return CheckoutService.this;
        }
    }
}
