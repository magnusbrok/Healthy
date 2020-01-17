package com.example.healthy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.example.healthy.logic.AppLogic;
import java.util.Calendar;
import static android.hardware.Sensor.TYPE_STEP_COUNTER;


public class SensorService extends Service implements SensorEventListener {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String HAS_RUN = "has_run";
    public static final String CALIBRATOR = "calibrator";
    public static final String LAST_USEDATE = "last_usedate";
    private boolean unCalibrated;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    SensorManager sensorManager;
    Sensor stepCounter;
    SharedPreferences preferences;
    SharedPreferences.Editor preferenceEditor;
    Calendar calendar = Calendar.getInstance();

    AppLogic appLogic = AppLogic.getInstance();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();

        if (!preferences.getBoolean(HAS_RUN,false)){
            unCalibrated = true;
            appLogic.setSteps(0);
            preferenceEditor.putBoolean(HAS_RUN,true).apply();
        }

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

        createNotificationChannel(CHANNEL_ID);

        createPersistentNotification(CHANNEL_ID);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("start", "sevice started");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("stop", "sevice destroyed");

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
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

    /**
     * @Origin https://androidwave.com/foreground-service-android-example/
     *
     * @Modifications Siff s173998
     */
    private void createNotificationChannel(String channelID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    channelID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    /**
     * @Origin https://androidwave.com/foreground-service-android-example/
     *
     * @Modifications Siff s173998
     */
    private void createPersistentNotification(String channelID) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelID)
                .setContentTitle("Activity")
                .setContentText("Motion sensors running in background")
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
    }
}