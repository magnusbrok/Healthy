package com.example.healthy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;
import com.example.healthy.TopMenu;
import com.example.healthy.logic.AppLogic;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;

public class ActivityPage extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    Sensor stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
    AppLogic appLogic = AppLogic.getInstance();

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

        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        appLogic.setSteps((int) event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
