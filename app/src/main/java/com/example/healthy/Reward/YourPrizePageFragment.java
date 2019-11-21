package com.example.healthy.Reward;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourPrizePageFragment extends Fragment {


    public YourPrizePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_your_prize_page, container, false);

        return root;

    }

}
