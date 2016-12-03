package com.firstdataproblem.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstdataproblem.R;

/**
 * Created by Avdhesh AKhani on 12/2/2016.
 */

public class FragmentA  extends Fragment {


    private static final String TAG = "Fragment A";

    public static FragmentA newInstance() {
        FragmentA fragment = new FragmentA();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container, false);

        Log.e(TAG, "In Fragment A");
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {

                Fragment fragment = FragmentB.newInstance();
                if (fragment != null) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment).addToBackStack("FragmentB").commit();
                }
            }
        }, 3000);
        return view;
    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
    }*/
}
