package com.example.healthy;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.healthy.Activity.ActivityPage;
import com.example.healthy.Nutrition.NutritionPage;
import com.example.healthy.Reward.RewardPage;
import com.example.healthy.Social.SocialPage;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomMenu extends Fragment implements View.OnClickListener {


    public BottomMenu() {
        // Required empty public constructor
    }

    TabLayout tabMenu;
    TabItem activityTab, nutritionTab, homeTab, socialTab, rewardTab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_menu, container, false);

        tabMenu = root.findViewById(R.id.tabMenu);
        activityTab = root.findViewById(R.id.tabActivity);
        nutritionTab = root.findViewById(R.id.tabNutrition);
        homeTab = root.findViewById(R.id.tabHome);
        socialTab = root.findViewById(R.id.tabSocial);
        rewardTab = root.findViewById(R.id.tabReward);
        tabMenu.getTabAt(2).select();
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            public void onTabSelected(TabLayout.Tab tab){

                switch (tab.getPosition()){
                    case 0:
                        startActivity(new Intent (getActivity(), ActivityPage.class));
                        tabMenu.getTabAt(0).select();
                        break;
                    case 1:
                        startActivity(new Intent (getActivity(), NutritionPage.class));
                        tabMenu.getTabAt(1).select();
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), StartPage.class));
                        tabMenu.getTabAt(2).select();
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), SocialPage.class));
                        tabMenu.getTabAt(3).select();
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), RewardPage.class));
                        tabMenu.getTabAt(4).select();
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                getActivity().finish();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Inflate the layout for this fragment
        return root;

    }


    @Override
    public void onClick(View view) {

    }
}
