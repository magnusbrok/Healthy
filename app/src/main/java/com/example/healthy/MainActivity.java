package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.healthy.Activity.ActivityPageFragment;
import com.example.healthy.Nutrition.NutritionPageFragment;
import com.example.healthy.Reward.RewardPageFragment;
import com.example.healthy.Social.SocialPageFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomMenu;
    public static final String SHARED_PREFS = "shared_prefs";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        startService(new Intent(this, SensorService.class));

        if (savedInstanceState == null) {
            final HomePageFragment fragment = new HomePageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
        }

        if (savedInstanceState ==  null) {
            final TopMenu topMenu = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.TopMenuView, topMenu).commit();
        }

        final BottomNavigationView  bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setItemIconTintList(null);
        bottomMenu.setSelectedItemId(R.id.homePage);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.activityPage:
                        //Toast.makeText(MainActivity.this, "ActivityPage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final ActivityPageFragment fragment = new ActivityPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.nutriotionPage:
                        //Toast.makeText(MainActivity.this, "Nutrition picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final NutritionPageFragment fragment = new NutritionPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.homePage:
                        //Toast.makeText(MainActivity.this, "HomePage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final HomePageFragment fragment = new HomePageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.socialPage:
                        //Toast.makeText(MainActivity.this, "SocialPage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final SocialPageFragment fragment = new SocialPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.rewardPage:
                        //Toast.makeText(MainActivity.this, "RewardPage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final RewardPageFragment fragment = new RewardPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
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

    public void changeMenu(int itemId) {
        bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setSelectedItemId(itemId);


    }
}
