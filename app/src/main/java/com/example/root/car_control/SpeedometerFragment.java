package com.example.root.car_control;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.anastr.speedviewlib.base.Gauge;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpeedometerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeedometerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SpeedometerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpeedometerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpeedometerFragment newInstance(String param1, String param2) {
        SpeedometerFragment fragment = new SpeedometerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Gauge gauge;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private final Handler mHandler = new Handler();
    private Runnable mTimer2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_speedometer, container, false);

        gauge = (Gauge) rootview.findViewById(R.id.deluxeSpeedView);
        gauge.setMinMaxSpeed(0,260);


        //gauge.speedTo(100, 4000);

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer2 = new Runnable() {
            @Override
            public void run() {
                float speed=(float)getLiveSpeed();
                gauge.speedTo(speed);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);

    }

    private Integer getLiveSpeed() {
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        int i = test.get(test.size() - 1).getSpeed();
        return i;
    }

}
