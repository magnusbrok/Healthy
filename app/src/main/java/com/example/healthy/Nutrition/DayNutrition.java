package com.example.healthy.Nutrition;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayNutrition extends Fragment implements View.OnClickListener {

    PieChartView nutritionPie;
    TextView goals, history;
    FloatingActionButton addFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_nutrition, container, false);
        // Snuppet fra activity
        nutritionPie = root.findViewById(R.id.pieChartView_nutritionDay);
        goals = root.findViewById(R.id.textView_nutritionDay_goals);
        history = root.findViewById(R.id.textView_nutritionDay_log);
        addFood = root.findViewById(R.id.floatingActionButton_nutritionDay_addFood);

        goals.setOnClickListener(this);
        history.setOnClickListener(this);
        addFood.setOnClickListener(this);

        //set
        List<SliceValue> activityData = new ArrayList<>();
        activityData.add(new SliceValue(50, Color.GREEN).setLabel("XXX"));
        activityData.add(new SliceValue(25, Color.BLUE).setLabel("XXX"));
        activityData.add(new SliceValue(25, Color.YELLOW).setLabel("XXX"));

        PieChartData activityPieData = new PieChartData(activityData);

        nutritionPie.setPieChartData(activityPieData);

        activityPieData.setHasLabels(true);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        nutritionPie.setPieChartData(activityPieData);
        return root;
    }

    @Override
    public void onClick(View v) {

            if (v == addFood){
                Intent i = new Intent(getActivity(), AddFood.class);
                startActivity(i);
            }
            if (v == history){
                Intent i = new Intent(getActivity(), FruitsVeggies.class);
                startActivity(i);

            }
    }
}
