package com.example.healthy.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayNutrition extends Fragment implements View.OnClickListener, Observer {

    private List<SliceValue> nutritionData = new ArrayList<>();
    private TextView goals, history, day_points;
    private FloatingActionButton addFood;
    private AppLogic appLogic = AppLogic.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_nutrition, container, false);
        // Copied from activityPage
        PieChartView nutritionPie = root.findViewById(R.id.pieChartView_nutritionDay);
        goals = root.findViewById(R.id.textView_nutritionDay_goals);
        history = root.findViewById(R.id.textView_nutritionDay_log);
        addFood = root.findViewById(R.id.floatingActionButton_nutritionDay_addFood);
        day_points = root.findViewById(R.id.dayNutrition_TextView_points);
        day_points.setText("" + appLogic.getNutritionPoints());
        goals.setOnClickListener(this);
        history.setOnClickListener(this);
        addFood.setOnClickListener(this);

        //set
        nutritionData.add(new SliceValue(50, ContextCompat.getColor(getContext(), R.color.nutritionPrimary)));
        nutritionData.add(new SliceValue(35, ContextCompat.getColor(getContext(), R.color.nutritionSecondary)));
        nutritionData.add(new SliceValue(15, ContextCompat.getColor(getContext(), R.color.nutritionTertiary)));

        PieChartData activityPieData = new PieChartData(nutritionData);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        nutritionPie.setPieChartData(activityPieData);

        appLogic.attachObserverToNutritionPoints(this);

        return root;
    }

    @Override
    public void onClick(View v) {

        if (v == addFood) {
            AddFoodDialogFragment addFoodDialogFragment = new AddFoodDialogFragment();
            addFoodDialogFragment.show(getFragmentManager(), "activity_add_food");
        }
        if (v == history) {
            Intent i = new Intent(getActivity(), LogHistory.class);
            startActivity(i);
        }

        if (v==goals){
            Intent i = new Intent(getActivity(), GoalsNutrition.class);
            startActivity(i);
        }
    }

    @Override
    public void updateView() {
        day_points.setText("" + appLogic.getNutritionPoints());
    }
}