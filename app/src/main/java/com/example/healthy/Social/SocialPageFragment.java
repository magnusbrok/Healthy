package com.example.healthy.Social;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialPageFragment extends Fragment {
    PieChartView socialPieChart;
    List<SliceValue> socialPageData = new ArrayList<>();



    public SocialPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_social_page, container, false);
        socialPieChart = root.findViewById(R.id.socialPie);


        socialPageData.add(new SliceValue(50, ContextCompat.getColor(getContext(), R.color.socialPrimary)));
        socialPageData.add(new SliceValue(35, ContextCompat.getColor(getContext(), R.color.socialSecondary)));
        socialPageData.add(new SliceValue(15, ContextCompat.getColor(getContext(), R.color.socialTertiary)));

        PieChartData socialPieData = new PieChartData(socialPageData);
        socialPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        socialPieChart.setPieChartData(socialPieData);

        return root;


    }

}
