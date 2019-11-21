package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.healthy.Activity.ActivityPage;
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
                        Intent i = new Intent(BottomMenuActivity.this, ActivityPage.class);
                        startActivity(i);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_main, menu);
        return true;
    }
}
