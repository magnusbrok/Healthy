package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    SensorManager sensorManager;
    Sensor stepCounter;
    FrameLayout topMenuView;
    BottomNavigationView bottomMenu;
    AppLogic appLogic = AppLogic.getInstance();
    Calendar calendar = Calendar.getInstance();

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String HAS_RUN = "has_run";
    public static final String CALIBRATOR = "calibrator";
    public static final String LAST_USEDATE = "last_usedate";
    private boolean unCalibrated;
    private LocationManager lm;

    FirebaseFirestore db;
    SharedPreferences preferences;
    SharedPreferences.Editor preferenceEditor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();

        if (!preferences.getBoolean(HAS_RUN,false)){
            unCalibrated = true;
            appLogic.setSteps(0);
            preferenceEditor.putBoolean(HAS_RUN,true).apply();
        }

        if(preferences.getInt(LAST_USEDATE,0) == 0){
            preferenceEditor.putInt(LAST_USEDATE,calendar.get(Calendar.DAY_OF_MONTH)).apply();
        }

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE)
                != PackageManager.PERMISSION_GRANTED) {

        }
        lm = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);

        //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO implement code to set steps taken for current day

        // Check if steps of the day are uncalibrated
        // Checks if new day
        if(preferences.getInt(LAST_USEDATE,0) != calendar.get(Calendar.DAY_OF_MONTH)){
            unCalibrated = true;
            preferenceEditor.putInt(LAST_USEDATE,calendar.get(Calendar.DAY_OF_MONTH)).apply();
        }
        // Checks if phone has rebooted
        else if ((int) event.values[0] < preferences.getInt(CALIBRATOR,0)){
            unCalibrated = true;
        }

        //Calibration of steps of the day
        if (unCalibrated){
            preferenceEditor.putInt(CALIBRATOR, (int) event.values[0]).apply();
            unCalibrated = false;
        }

        int currentSteps = ((int) event.values[0]) - preferences.getInt(CALIBRATOR,0);

        appLogic.setSteps(currentSteps);
        appLogic.computePoints();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void changeMenu(int itemId) {
        bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setSelectedItemId(itemId);


    }

    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1234);
            return;
        }
        double altitude = location.getAltitude();
        appLogic.setAltitude(altitude);
        System.out.println("........................."+altitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void updateDatabase() {
        db = FirebaseFirestore.getInstance();

        // Add new user with points
        Map<String, Object> newUser = new HashMap<>();
        newUser.put ("Name", "Magnus");
        newUser.put ("ActivityPoints", appLogic.getActivityPoints());
        newUser.put ("RewardPoints", appLogic.getRewardPoints());
        newUser.put ("NutritionPoints", 100);

        db.collection("Brugere med point").document() // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Ny bruger er tilføjet", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - brugeren blev ikke tilføjet", e.getMessage());
                    }
                });
    }
}