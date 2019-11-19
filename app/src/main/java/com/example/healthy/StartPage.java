package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        /*if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }*/
        if (savedInstanceState == null) {
            final TopMenu fragment = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.topMenuFragment, fragment).commit();
        }
    }
}
