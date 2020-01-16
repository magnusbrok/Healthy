package com.example.healthy.Nutrition;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class WeekNutrition extends Fragment implements View.OnClickListener, Observer {
    private PieChartView nutritionPie;
    private TextView goals, history, day_points;
    private AppLogic appLogic = AppLogic.getInstance();
    private List<SliceValue> nutritionDataMonth = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_nutrition, container, false);
        // Snuppet fra activity
        nutritionPie = root.findViewById(R.id.pieChartView_nutritionDay);
        goals = root.findViewById(R.id.textView_nutritionDay_goals);
        history = root.findViewById(R.id.textView_nutritionDay_log);
        day_points = root.findViewById(R.id.dayNutrition_TextView_points);
        day_points.setText("" + appLogic.getNutritionPoints());
        goals.setOnClickListener(this);
        history.setOnClickListener(this);

        //set
        nutritionDataMonth.add(new SliceValue(50, ContextCompat.getColor(getContext(), R.color.colorTurkis)));
        nutritionDataMonth.add(new SliceValue(35, ContextCompat.getColor(getContext(), R.color.nutritionSecondary)));
        nutritionDataMonth.add(new SliceValue(15, ContextCompat.getColor(getContext(), R.color.nutritionTertiary)));

        PieChartData activityPieData = new PieChartData(nutritionDataMonth);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        nutritionPie.setPieChartData(activityPieData);

        appLogic.attachObserverToNutritionPoints(this);

        return root;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void updateView() {

    }
}
