package com.firstdataproblem.secondway;

/**
 * Created by Avdhesh AKhani on 12/5/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstdataproblem.R;


/**
 * Created by Avdhesh AKhani on 12/2/2016.
 */

public class FragmentF extends Fragment {

    private static final String TAG = "Fragment E";

    public static FragmentF newInstance() {
        FragmentF fragment = new FragmentF();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container, false);

        Log.e(TAG, "In Fragment F");
        Log.e(TAG, "Waiting for 5 Seconds before network call");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // loadData();
                Intent intent = new Intent(getActivity(),MyService.class);
                getActivity().startService(intent);

            }
        },5000);


        return view;
    }


  /*  @Override
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




    private void setVisible(boolean b) {
        isVisible = b;
    }
*/
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


  /*  private void callFragmentC() {
        Fragment fragment = FragmentC.newInstance(json);
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment).addToBackStack("FragmentB").commit();
        }
    }
*/


}
