package com.example.healthy.Reward;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardPageFragment extends Fragment implements View.OnClickListener {
    Button seGevinster;


    public RewardPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reward_page, container, false);

        seGevinster = root.findViewById(R.id.showRewardButton);
        seGevinster.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == seGevinster) {
            Fragment yourPrizes = new YourPrizePageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, yourPrizes);
            transaction.commit();
            }
    }

}
