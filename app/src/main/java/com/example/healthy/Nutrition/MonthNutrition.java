package com.example.healthy.Nutrition;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.healthy.R;

public class MonthNutrition extends Fragment {
    ImageView måned;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_nutrition, container, false);
        //måned = view.findViewById(R.id.imageView4);
        // Inflate the layout for this fragment
        return view;
    }
}