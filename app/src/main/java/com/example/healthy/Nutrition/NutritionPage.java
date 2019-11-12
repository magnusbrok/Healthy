package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;
import com.example.healthy.TopMenu;

public class NutritionPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonDag, buttonUge, buttonMåned, buttonMål, buttonPlus, buttonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page);
        buttonDag = findViewById(R.id.button7);
        buttonDag.setOnClickListener(this);
        buttonUge = findViewById(R.id.button2);
        buttonUge.setOnClickListener(this);
        buttonMåned = findViewById(R.id.button3);
        buttonMåned.setOnClickListener(this);
        buttonMål = findViewById(R.id.button4);
        buttonMål.setOnClickListener(this);
        buttonPlus = findViewById(R.id.button5);
        buttonPlus.setOnClickListener(this);
        buttonLog = findViewById(R.id.button6);
        buttonLog.setOnClickListener(this);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new Day()).commit();
        }

        if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }

        if (savedInstanceState == null) {
            final TopMenu fragment = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.topMenuFragment, fragment).commit();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == buttonDag){
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new Day()).commit();
             }
        if (v == buttonUge){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Week()).commit();
        }
        if (v == buttonMåned){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Month()).commit();
        }
        if (v==buttonPlus){
            Intent i = new Intent(this, AddFood.class);
            startActivity(i);
        }
        }

    }
