package com.firstdataproblem.extra;

/**
 * Created by Avdhesh AKhani on 12/5/2016.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstdataproblem.R;
import com.firstdataproblem.ui.FragmentC;


/**
 * Created by Avdhesh AKhani on 12/2/2016.
 */

public class FragmentE  extends Fragment {

    private static final String TAG = "Fragment E";
    private static boolean isVisible = true;
    private String json = "";
    private boolean isTransectionDone = true;

    //Service
    CheckoutService checkoutService;
    boolean serviceBound = false;

    public static FragmentE newInstance() {
        FragmentE fragment = new FragmentE();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container, false);

        Log.e(TAG, "In Fragment B");
        Log.e(TAG, "Waiting for 5 Seconds before network call");


        Thread t = new Thread(){
            public void run(){

                Intent intent =  new Intent(getActivity(), CheckoutService.class);
                getActivity().startService(intent);
                getActivity().bindService(intent,mServiceConnection, Context.BIND_IMPORTANT);

                checkoutService.getJson();
            }
        };
        t.start();

/*

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //      loadData();

                Intent intent =  new Intent(getActivity(), CheckoutService.class);
                getActivity().startService(intent);
                getActivity().bindService(intent,mServiceConnection,Context.BIND_IMPORTANT);
                checkoutService.getJson();

            }
        },10000);
*/


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setVisible(true);
        if (isVisible && !isTransectionDone){
            callFragmentC();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        setVisible(false);
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        if (serviceBound){
            getActivity().unbindService(mServiceConnection);
            serviceBound = false;
        }
    }

    private void setVisible(boolean b) {
        isVisible = b;
    }

/*
    private void loadData() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "Transection Success");

                try {
                    json = response.body().string();

                    if (isVisible){
                        callFragmentC();
                    }else {
                        isTransectionDone = false;
                    }

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
*/


    private void callFragmentC() {
        Fragment fragment = FragmentC.newInstance(json);
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment).addToBackStack("FragmentB").commit();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CheckoutService.CheckoutBinder binder = (CheckoutService.CheckoutBinder) iBinder;
            checkoutService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
    };

}
