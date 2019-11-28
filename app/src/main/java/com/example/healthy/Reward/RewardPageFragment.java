package com.example.healthy.Reward;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Reward;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardPageFragment extends Fragment implements View.OnClickListener, Observer {
    Button seGevinster, buyPrize;
    TextView rewardPoints;
    AppLogic appLogic = AppLogic.getInstance();


    public RewardPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reward_page, container, false);

        appLogic.attachObserverToRewardPoints(this);

        rewardPoints = root.findViewById(R.id.rewardPoints);
        rewardPoints.setText("Belønningspoint: " + appLogic.getRewardPoints()+"");

        seGevinster = root.findViewById(R.id.showRewardButton);
        seGevinster.setOnClickListener(this);

        buyPrize = root.findViewById(R.id.buyRewardButton);
        buyPrize.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == seGevinster) {
            getFragmentManager().popBackStack();
            Fragment yourPrizes = new YourPrizePageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
            transaction.replace(R.id.fragmentView, yourPrizes);
            transaction.commit();
            }


        if (v == buyPrize) {

            if (appLogic.canBuyPrize()) {
                Reward prize = appLogic.buyPrize();
                Toast.makeText(getActivity(), "Du vandt: "+   prize.getName(), Toast.LENGTH_LONG).show();
            } else Toast.makeText(getActivity(), "Du har ikke nok point", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateView() {
        rewardPoints.setText("Bellønningspoint: " + appLogic.getRewardPoints());

    }
}
