package com.example.healthy.Activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.healthy.MainActivity;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayActivities extends Fragment implements Observer, View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    PieChartView activityPie;
    private SliceValue stepSlice, floorSlice;
    TextView steps, points, altitude;
    AppLogic appLogic = AppLogic.getInstance();
    List<SliceValue> activityData = new ArrayList<>();
    ProgressBar stepProgress, floorProgress, highIntensityProgress;
    FloatingActionButton addHi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        activityPie = root.findViewById(R.id.dayActivityPie);
        steps = root.findViewById(R.id.dayActivity_TextView_steps);
        points = root.findViewById(R.id.dayActivity_TextView_points);
        stepProgress = root.findViewById(R.id.activity_day_step_Progress);
        stepProgress.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        altitude = root.findViewById(R.id.altitude);
        altitude.setText(""+appLogic.getAltitude());
        addHi = root.findViewById(R.id.addHi);
        addHi.setOnClickListener(this);
        highIntensityProgress = root.findViewById(R.id.activity_day_highIntensity_progress);
        highIntensityProgress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        floorProgress = root.findViewById(R.id.activity_day_floor_progress);
        floorProgress.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);



        appLogic.attachObserverToActivityPoints(this);
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());


        stepSlice = new SliceValue(1, Color.BLUE).setLabel("Skridt");
        floorSlice = new SliceValue(1, Color.GREEN).setLabel("Etager");

        if (appLogic.getSteps() > 0){
            stepSlice.setValue(appLogic.getStepPoints());
            floorSlice.setValue((10));
        }

        //set

        activityData.add(stepSlice);
        activityData.add(floorSlice);



        PieChartData activityPieData = new PieChartData(activityData);

        activityPie.setPieChartData(activityPieData);
        activityPie.setChartRotationEnabled(false);
        activityPieData.setHasLabels(true);
        activityPieData.setValueLabelBackgroundEnabled(false);
        activityPieData.setValueLabelsTextColor(Color.BLACK);


        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        activityPie.setPieChartData(activityPieData);



        return root;
    }

    @Override
    public void updateView() {
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());
        stepSlice.setValue(appLogic.getStepPoints());
        floorSlice.setValue((10)); // change this later to be dynamic
        activityData.clear();
        activityData.add(stepSlice);
        activityData.add(floorSlice);
        PieChartData activityPieData = new PieChartData(activityData);
        activityPieData.setHasLabels(true);
        activityPieData.setValueLabelBackgroundEnabled(false);
        activityPieData.setValueLabelsTextColor(Color.BLACK);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);
        activityPie.setPieChartData(activityPieData);

        // Progressbar
        stepProgress.setMax(appLogic.getStepGoal());
        stepProgress.setProgress(appLogic.getSteps()%appLogic.getStepGoal());
    }

    @Override
    public void onClick(View v) {
        if (v == addHi) {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getActivity(), this, hour, minute, true);
            mTimePicker.setTitle("Indtast høj intensitet");
            mTimePicker.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

    }
}
