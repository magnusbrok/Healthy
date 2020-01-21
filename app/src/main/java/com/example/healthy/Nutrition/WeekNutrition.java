package com.example.healthy.Nutrition;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.healthy.R;
import lecho.lib.hellocharts.view.PieChartView;

public class WeekNutrition extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week_nutrition, container, false);
        PieChartView week = view.findViewById(R.id.month_pie);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_nutrition, container, false);
    }
}