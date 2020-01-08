package com.example.healthy.Nutrition;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Day extends Fragment {

    PieChartView dag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day, container, false);
        // Snuppet fra activity
        dag = root.findViewById(R.id.day_pie);
        //set
        List<SliceValue> activityData = new ArrayList<>();
        activityData.add(new SliceValue(50, Color.GREEN).setLabel("XXX"));
        activityData.add(new SliceValue(25, Color.BLUE).setLabel("XXX"));
        activityData.add(new SliceValue(25, Color.YELLOW).setLabel("XXX"));

        PieChartData activityPieData = new PieChartData(activityData);

        dag.setPieChartData(activityPieData);

        activityPieData.setHasLabels(true);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        dag.setPieChartData(activityPieData);
        return root;
    }

}
