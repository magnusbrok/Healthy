package com.example.healthy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.R;

public class ActivityPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_page);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, new DayActivities())
                    .commit();
        }
    }
}
