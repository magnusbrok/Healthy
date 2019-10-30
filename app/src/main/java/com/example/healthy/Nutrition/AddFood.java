package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthy.R;


public class AddFood extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4, button5,button6, button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button1){
            Intent i = new Intent(this, FruitsVeggies.class);
            startActivity(i);

        }
        else if (v == button2){

        }
        else if (v == button3){

        }
        else if (v== button4){

        }
        else if (v == button5){

        }
        else if (v == button6){

        }

    }
}
