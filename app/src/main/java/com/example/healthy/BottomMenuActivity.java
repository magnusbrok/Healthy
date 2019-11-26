package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.healthy.Activity.ActivityPageFragment;
import com.example.healthy.Nutrition.NutritionPageFragment;
import com.example.healthy.Reward.RewardPageFragment;
import com.example.healthy.Social.SocialPageFragment;
import com.example.healthy.logic.AppLogic;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;

public class BottomMenuActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor stepCounter;
    FrameLayout topMenuView;
    BottomNavigationView bottomMenu;
    AppLogic appLogic = AppLogic.getInstance();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

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
                        Toast.makeText(BottomMenuActivity.this, "ActivityPage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final ActivityPageFragment fragment = new ActivityPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.nutriotionPage:
                        Toast.makeText(BottomMenuActivity.this, "Nutrition picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final NutritionPageFragment fragment = new NutritionPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.homePage:
                        Toast.makeText(BottomMenuActivity.this, "HomePage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final HomePageFragment fragment = new HomePageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.socialPage:
                        Toast.makeText(BottomMenuActivity.this, "SocialPage picked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStack();
                        if (savedInstanceState == null) {
                            final SocialPageFragment fragment = new SocialPageFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragmentView, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.rewardPage:
                        Toast.makeText(BottomMenuActivity.this, "RewardPage picked", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO implement code to set steps taken for current day
        appLogic.setSteps((int) event.values[0]);
        appLogic.computePoints();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void changeMenu(int itemId) {
        bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setSelectedItemId(itemId);


    }
}
