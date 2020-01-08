package com.example.healthy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.healthy.SensorService;

public class RestartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        context.startForegroundService(new Intent(context, SensorService.class));

//        context.startService(new Intent(context, SensorService.class));
    }
}
