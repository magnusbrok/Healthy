package com.example.healthy.Activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayActivities extends Fragment implements Observer {

    PieChartView activityPie;
    private SliceValue stepSlice, floorSlice;
    TextView steps, points;
    AppLogic appLogic = AppLogic.getInstance();
    List<SliceValue> activityData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        activityPie = root.findViewById(R.id.dayActivityPie);
        steps = root.findViewById(R.id.dayActivity_TextView_steps);
        points = root.findViewById(R.id.dayActivity_TextView_points);

        appLogic.attachObserverToActivityPoints(this);
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());

        if (appLogic.getActivityPoints() == 0) {
            stepSlice = new SliceValue(1, Color.BLUE).setLabel("Skridt");
            floorSlice = new SliceValue(1, Color.GREEN).setLabel("Etager");
        } else {
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
        activityPie.setPieChartData(activityPieData);


    }
}
