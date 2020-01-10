package com.example.healthy.Nutrition;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionPageFragment extends Fragment implements View.OnClickListener, Observer {

    TextView day, week, month, goal, log, points;
    ImageButton buttonPlus;
    AppLogic appLogic = AppLogic.getInstance();
    int nutritionPoints;

    FragmentTransaction fragmentTransaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_nutrition_page, container, false);

        day = root.findViewById(R.id.day);
        day.setOnClickListener(this);
        week = root.findViewById(R.id.week);
        week.setOnClickListener(this);
        month = root.findViewById(R.id.month);
        month.setOnClickListener(this);
        goal = root.findViewById(R.id.goal);
        goal.setOnClickListener(this);
        buttonPlus = root.findViewById(R.id.plus);
        buttonPlus.setOnClickListener(this);
        log = root.findViewById(R.id.log);
        log.setOnClickListener(this);
        points = root.findViewById(R.id.points);

        nutritionPoints = appLogic.getNutritionPoints();
        points.setText("Points: " + nutritionPoints );

        if(savedInstanceState == null) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutNutrition,new Day());
            fragmentTransaction.commit();
        }
        return root;
    }
        public void onClick(View view) {
            if (view == day){
                day.setTypeface(null, Typeface.BOLD);
                week.setTypeface(null,Typeface.NORMAL);
                month.setTypeface(null,Typeface.NORMAL);
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutNutrition, new Day())
                    .commit();

            }
            if (view == week){
                day.setTypeface(null,Typeface.NORMAL);
                week.setTypeface(null, Typeface.BOLD);
                month.setTypeface(null,Typeface.NORMAL);
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutNutrition, new Week())
                    .commit();
            }
            if (view == month){
                day.setTypeface(null,Typeface.NORMAL);
                week.setTypeface(null, Typeface.NORMAL);
                month.setTypeface(null,Typeface.BOLD);
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutNutrition, new Month())
                    .commit();
            }
            if (view == buttonPlus){
                Intent i = new Intent(getActivity(), AddFood.class);
                startActivity(i);
            }
            if (view == log){
                Intent i = new Intent(getActivity(), FruitsVeggies.class);
                startActivity(i);

            }
        }

    @Override
    public void updateView() {
        points.setText("Points: " + appLogic.getNutritionPoints() );
    }
}
