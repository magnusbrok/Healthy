package com.example.healthy;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthy.Activity.DayActivities;
import com.example.healthy.Activity.MonthActivities;
import com.example.healthy.Activity.WeekActivities;
import com.example.healthy.Nutrition.*;
import com.example.healthy.Nutrition.WeekNutrition;
import com.example.healthy.R;

public class TimeMenu extends Fragment implements View.OnClickListener {

    TextView day, week, month;
    String page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_time_menu, container, false);

        Bundle bundle = this.getArguments();
        page = bundle.getString("Page");

        // Inflate the layout for this fragment
        day = root.findViewById(R.id.day);
        week = root.findViewById(R.id.week);
        month = root.findViewById(R.id.month);

        //set
        day.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == day){
            if (page == "AP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayActivities())
                        .commit();

            } else if (page == "NP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayNutrition())
                        .commit();
            }
            day.setTypeface(null,Typeface.BOLD);
            week.setTypeface(null,Typeface.NORMAL);
            month.setTypeface(null,Typeface.NORMAL);

        }else if (v == week){
            if (page == "AP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayActivities())
                        .commit();

            } else if (page == "NP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayNutrition())
                        .commit();
            }
            day.setTypeface(null,Typeface.NORMAL);
            week.setTypeface(null,Typeface.BOLD);
            month.setTypeface(null,Typeface.NORMAL);

        }else if (v == month){
            if (page == "AP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayActivities())
                        .commit();

            } else if (page == "NP"){
                getFragmentManager().beginTransaction()
                        .replace(R.id.FrameLayout, new DayNutrition())
                        .commit();
            }

            day.setTypeface(null,Typeface.NORMAL);
            week.setTypeface(null,Typeface.NORMAL);
            month.setTypeface(null,Typeface.BOLD);
        }
    }
}
