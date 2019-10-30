package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthy.R;

import org.w3c.dom.Text;

public class FruitsVeggies extends AppCompatActivity implements View.OnClickListener {
    TextView textview;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_veggies);
        textview = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        button.setText("Tilføjet!");
    }
}
