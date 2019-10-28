package com.example.healthy.Nutrition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.healthy.R;

public class Week extends Fragment {
    ImageView uge;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week, container, false);
         uge = view.findViewById(R.id.imageView2);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false);
    }
}
