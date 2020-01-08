package com.example.healthy.Activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayActivities extends Fragment implements Observer{

    PieChartView activityPie;
    private SliceValue HISlice ,stepSlice, floorSlice;
    TextView steps, points, altitude, tvStepProgress, tvHIProgress, tvFloorProgress;
    AppLogic appLogic = AppLogic.getInstance();
    List<SliceValue> activityData = new ArrayList<>();
    ProgressBar stepProgress, floorProgress, highIntensityProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        activityPie = root.findViewById(R.id.dayActivityPie);
        steps = root.findViewById(R.id.dayActivity_TextView_steps);
        points = root.findViewById(R.id.dayActivity_TextView_points);
        tvStepProgress = root.findViewById(R.id.dayActivity_TextView_stepProgress);
        tvHIProgress = root.findViewById(R.id.dayActivity_TextView_highIntensity_progress);
        tvFloorProgress = root.findViewById(R.id.dayActivity_TextView_floor_progress);

        altitude = root.findViewById(R.id.altitude);
        altitude.setText(""+appLogic.getAltitude());

        stepProgress = root.findViewById(R.id.activity_day_step_Progress);
        highIntensityProgress = root.findViewById(R.id.activity_day_highIntensity_progress);
        floorProgress = root.findViewById(R.id.activity_day_floor_progress);


        stepProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorStep), android.graphics.PorterDuff.Mode.SRC_IN);
        highIntensityProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorHighIntensity), android.graphics.PorterDuff.Mode.SRC_IN);
        floorProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorFloors), android.graphics.PorterDuff.Mode.SRC_IN);


        appLogic.attachObserverToActivityPoints(this);
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());

        stepSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorStep));
        floorSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorFloors));
        HISlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorHighIntensity));



        if (appLogic.getSteps() > 0){
            stepSlice.setValue(appLogic.getStepPoints());
            floorSlice.setValue((appLogic.getFloorPoints()));
            HISlice.setValue(appLogic.getHighIntensityPoints());
        }

        //set

        activityData.add(stepSlice);
        activityData.add(floorSlice);
        activityData.add(HISlice);



        PieChartData activityPieData = new PieChartData(activityData);

        activityPie.setPieChartData(activityPieData);

        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        activityPie.setPieChartData(activityPieData);

        updateView();



        return root;
    }

    @Override
    public void updateView() {
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());

        stepSlice.setValue(appLogic.getStepPoints());
        floorSlice.setValue((appLogic.getFloorPoints()));
        HISlice.setValue(appLogic.getHighIntensityPoints());

        if (appLogic.getActivityPoints() == 0) {
            stepSlice.setValue(1);
            floorSlice.setValue(1);
            HISlice.setValue(1);
        }
        activityData.clear();
        activityData.add(stepSlice);
        activityData.add(floorSlice);
        activityData.add(HISlice);

        PieChartData activityPieData = new PieChartData(activityData);
        activityPieData.setHasLabels(false);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);
        activityPie.setPieChartData(activityPieData);

        // Progressbar
        stepProgress.setMax(appLogic.getStepGoal());
        stepProgress.setProgress(appLogic.getSteps()%appLogic.getStepGoal());
        highIntensityProgress.setMax(appLogic.getHighIntensityGoal());
        highIntensityProgress.setProgress((appLogic.getSteps()/10)%appLogic.getHighIntensityGoal());
        floorProgress.setMax(appLogic.getFloorGoal());
        floorProgress.setProgress((appLogic.getSteps()/7)%appLogic.getFloorGoal());
        // progressbar textviews
        tvStepProgress.setText(appLogic.getSteps()%appLogic.getStepGoal()+" / "+appLogic.getStepGoal()+" Skridt");
        tvHIProgress.setText(appLogic.getSteps()/10%appLogic.getHighIntensityGoal()+" / "+appLogic.getHighIntensityGoal()+" Minuters HighIntesity");
        tvFloorProgress.setText(appLogic.getSteps()/7%appLogic.getFloorGoal()+" / "+appLogic.getFloorGoal()+" Etager");


    }
}
