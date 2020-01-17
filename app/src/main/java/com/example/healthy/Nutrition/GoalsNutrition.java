package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.healthy.R;

public class GoalsNutrition extends AppCompatActivity implements View.OnClickListener {
    ImageButton goalsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_nutrition);

        goalsDone = findViewById(R.id.goalsDoneButton);
        goalsDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();}
}