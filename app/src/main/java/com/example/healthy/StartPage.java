package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        //hej
        //hej fra hella
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, new BottomMenu()).commit();
        }
    }
}
