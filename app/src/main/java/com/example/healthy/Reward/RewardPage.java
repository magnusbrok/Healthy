package com.example.healthy.Reward;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;

public class RewardPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_page);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, new BottomMenu()).commit();
        }
    }
}
