package com.example.healthy;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.Activity.ActivityPage;
import com.example.healthy.Nutrition.NutritionPage;
import com.example.healthy.Reward.RewardPage;
import com.example.healthy.Social.SocialPage;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomMenu extends AppCompatActivity {


    @Override
    public View onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        BottomNavigationView bottomMenu = root.findViewById(R.id.bottom_navigation);

        BottomNavigationView.
        return root;
    }
}
