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
public class ActivityPageFragment extends Fragment implements SensorEventListener {

    SensorManager sensorManager;
    Sensor stepCounter;
    AppLogic appLogic = AppLogic.getInstance();
    FragmentTransaction fragmentTransaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_activity_page, container, false);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO implement code to set steps taken for current day
        appLogic.setSteps((int) event.values[0]);
        appLogic.computePoints();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
