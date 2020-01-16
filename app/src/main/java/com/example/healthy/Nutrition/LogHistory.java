package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthy.R;
import com.example.healthy.Reward.CustomAdapter;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Items.*;

import java.util.ArrayList;

public class LogHistory extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    ImageButton doneButton;
    ArrayList<String> foodAddedArray = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    AppLogic appLogic = AppLogic.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_log);
        textView = findViewById(R.id.nutritionSearch);
        textView.setOnClickListener(this);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);

        foodAddedArray = appLogic.getFoodList();

        ListView listView = findViewById(R.id.addedFoodList);

        ArrayList<Item> foodItemList = appLogic.getFoodItemList();
        arrayAdapter = new CustomAdapter(this, foodItemList);
        listView.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
            if (doneButton==v){
                finish();
            }
    }
}