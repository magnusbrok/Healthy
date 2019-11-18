package com.example.healthy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;
import com.example.healthy.TopMenu;
import static android.hardware.Sensor.TYPE_STEP_COUNTER;

public class ActivityPage extends AppCompatActivity {

    SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    Sensor stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
    StepCountListener stepCountListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_page);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.FrameLayout, new DayActivities())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.TimeFrame, new TimeMenu())
                    .commit();

            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();

            if (savedInstanceState == null) {
                final TopMenu topFragment = new TopMenu();
                getSupportFragmentManager().beginTransaction().add(R.id.TopFrame, topFragment).commit();
            }
        }

        sensorManager.registerListener(stepCountListener, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
