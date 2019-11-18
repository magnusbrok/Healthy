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
    TextView steps;
    AppLogic appLogic = AppLogic.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        activityPie = root.findViewById(R.id.dayActivityPie);
        steps = root.findViewById(R.id.dayActivity_TextView_steps);

        steps.setText("Steps: " + appLogic.getSteps());
        appLogic.attachObserverToActivityPoints(this);

        //set
        List<SliceValue> activityData = new ArrayList<>();
        activityData.add(new SliceValue(50, Color.BLUE).setLabel("Gang: 50%"));
        activityData.add(new SliceValue(25, Color.GREEN).setLabel("Etager: 25%"));
        activityData.add(new SliceValue(25, Color.RED).setLabel("HI: 25%"));

        PieChartData activityPieData = new PieChartData(activityData);

        activityPie.setPieChartData(activityPieData);

        activityPieData.setHasLabels(true);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        activityPie.setPieChartData(activityPieData);



        return root;
    }

    @Override
    public void updateView() {
        steps.setText("Steps: " + appLogic.getSteps());
    }
}
