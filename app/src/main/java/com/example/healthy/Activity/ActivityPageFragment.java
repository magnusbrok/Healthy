package com.example.healthy.Activity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityPageFragment extends Fragment{

    FragmentTransaction fragmentTransaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_activity_page, container, false);

        if (savedInstanceState == null){
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new DayActivities())
                    .commit();

            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.TimeFrame, new TimeMenu())
                    .commit();
        }
        return root;
    }
}
