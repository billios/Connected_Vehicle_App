package com.example.root.car_control;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PressureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PressureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final Handler mHandler = new Handler();
    private Runnable mTimer2;
    private LineGraphSeries mSeries2;
    private double graph2LastXValue = 0d;

    public PressureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PressureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PressureFragment newInstance(String param1, String param2) {
        PressureFragment fragment = new PressureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_pressure, container, false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.pressureGraph);

        graph.setTitle("Live MAF_PRESSURE Diagram");
        graph.setTitleColor(Color.parseColor("#98DBC6"));


        mSeries2 = new LineGraphSeries<>();

        mSeries2.setColor(Color.MAGENTA);
        mSeries2.setDrawBackground(true);
        mSeries2.setDrawDataPoints(false);
        graph.addSeries(mSeries2);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " Pa";
                }
            }
        });

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        //graph.getViewport().setBackgroundColor(Color.BLACK);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1000);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(40);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                mSeries2.appendData(new DataPoint(graph2LastXValue, getLivePressure()), true, 40);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);
    }

    @Override
    public void onPause() {
        //mHandler.removeCallbacks(mTimer1);
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }


    private Integer getLivePressure() {
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        int i = test.get(test.size() - 1).getMafPressure();
        return i;
    }

}
