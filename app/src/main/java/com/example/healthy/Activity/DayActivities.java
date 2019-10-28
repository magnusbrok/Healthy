package com.example.healthy.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthy.R;

public class DayActivities extends Fragment implements View.OnClickListener {

    TextView day, week, month;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

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
        
    }
}
