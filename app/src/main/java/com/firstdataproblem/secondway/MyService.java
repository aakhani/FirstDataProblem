package com.firstdataproblem.secondway;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firstdataproblem.rest.ApiClient;
import com.firstdataproblem.rest.ApiInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.firstdataproblem.ui.MainActivity.MyPREFERENCES;

public class MyService extends Service {
    private static final String TAG = "Started MyService";
    private SharedPreferences sharedpreferences;

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "Started MyService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "Started MyService OnStartCommand");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        },5000);

        return START_REDELIVER_INTENT;
    }


    private void loadData() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "Transection Success");

                try {
                    String json = response.body().string();
                    Log.e(TAG, json);

                    sharedpreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    if (sharedpreferences.getBoolean("active",false)){
                      //  showNotification();
                        Log.e(TAG, "Active");

                    }else{
                        Log.e(TAG, "Not Active");
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("TransectionID","1234");
                        editor.commit();
                    }

                    stopSelf();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


  /*  void sendResultBroadcast(String jsonResult) {
        Intent in = new Intent();
        in.putExtra("jsonresult", jsonResult);
        in.setAction("com.firstdataproblem.CUSTOM_BROADCAST");
        sendBroadcast(in);
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
*/
    @Override
    public void onDestroy() {
        Log.i(TAG, "Started MyService onDestroy");
    }

}
