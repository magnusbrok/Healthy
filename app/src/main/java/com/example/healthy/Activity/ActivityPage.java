package com.example.healthy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;

public class ActivityPage extends AppCompatActivity {

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

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ButtomFrame, new BottomMenu())
                    .commit();
        }
    }
}
