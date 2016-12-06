package com.firstdataproblem.oneway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstdataproblem.R;

public class FragmentC extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = "Fragment C";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public FragmentC() {
    }

    public static FragmentC newInstance(String param1) {
        FragmentC fragment = new FragmentC();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_c, container, false);

        Log.e(TAG, "In Fragment C");
        Log.e(TAG, mParam1);

        return view;
    }


}
