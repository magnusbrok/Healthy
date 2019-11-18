package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        BottomNavigationView  bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.activityPage:
                        Toast.makeText(BottomMenuActivity.this, "ActivityPage picked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nutriotionPage:
                        Toast.makeText(BottomMenuActivity.this, "Nutrition picked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.homePage:
                        Toast.makeText(BottomMenuActivity.this, "HomePage picked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.socialPage:
                        Toast.makeText(BottomMenuActivity.this, "SocialPage picked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rewardPage:
                        Toast.makeText(BottomMenuActivity.this, "RewardPage picked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
