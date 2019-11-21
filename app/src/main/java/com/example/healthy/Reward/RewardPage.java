package com.example.healthy.Reward;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthy.R;
import com.example.healthy.TopMenu;


public class RewardPage extends AppCompatActivity implements View.OnClickListener {
    Button seGevinster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_page);

        /*if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }*/

        if (savedInstanceState == null) {
            final TopMenu fragment = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.topMenuFragment, fragment).commit();
        }
        seGevinster = findViewById(R.id.showRewardButton);
        seGevinster.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == seGevinster){
        Intent intent = new Intent(this, YourPrizePage.class);
        startActivity(intent);}
    }
}
