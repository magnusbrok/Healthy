package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthy.R;

import org.w3c.dom.Text;

public class FruitsVeggies extends AppCompatActivity implements View.OnClickListener {
    TextView textview;
    ImageButton buttonPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_veggies);
        textview = findViewById(R.id.textView);
        buttonPlus = findViewById(R.id.button);
        buttonPlus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Der er nu tilføjet en madvare!", Toast.LENGTH_LONG).show();
    }
}
