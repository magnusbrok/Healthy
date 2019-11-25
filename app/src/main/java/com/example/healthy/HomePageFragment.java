package com.example.healthy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.healthy.Reward.RewardPageFragment;
import com.example.healthy.Reward.YourPrizePageFragment;
import com.example.healthy.Social.SocialPageFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {

    ImageButton activityButton, rewardButton, socialButton, nutritionButton;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_page, container, false);

        rewardButton = root.findViewById(R.id.homepage_reward);
        rewardButton.setOnClickListener(this);
        activityButton = root.findViewById(R.id.homepage_activity);
        activityButton.setOnClickListener(this);
        socialButton = root.findViewById(R.id.homepage_social);
        socialButton.setOnClickListener(this);
        nutritionButton = root.findViewById(R.id.homepage_nutrition);
        nutritionButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {

        if (v == rewardButton) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment rewardFragment = new RewardPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, rewardFragment).addToBackStack(null);
            transaction.commit();
        }

        if (v == nutritionButton) {
// nutrion fragment not finishrd yet
        }

        if (v == activityButton) {
            // not made yet
        }

        if (v == socialButton) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment socialFragment = new SocialPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, socialFragment).addToBackStack(null);
            transaction.commit();
        }

    }
}
