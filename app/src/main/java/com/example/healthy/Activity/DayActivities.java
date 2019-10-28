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

public class DayActivities extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        text = root.findViewById(R.id.AnnouncementText);

        //set
        text.setText("Hello empty fragment!");

        return root;
    }
}
