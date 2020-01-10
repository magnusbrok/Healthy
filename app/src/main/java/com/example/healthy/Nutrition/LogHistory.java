package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LogHistory extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button doneButton;
    ArrayList<String> foodAddedArray = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Gson gson = new Gson();
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    public static final String NUTRITION_HISTORY = "nutritionHistory";
    Type history = new TypeToken<ArrayList<String>>(){}.getType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_veggies);
        textView = findViewById(R.id.nutritionSearch);
        textView.setOnClickListener(this);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);


        //Listview
        ListView listView = findViewById(R.id.addedFoodList);

        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

        if (!nutritionHistory.equals("null")){
            foodAddedArray = gson.fromJson(nutritionHistory,history);
        }


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,foodAddedArray);
        listView.setAdapter(arrayAdapter);
        if(foodAddedArray.size()>10){
            foodAddedArray.remove(1);
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
            editor.putString(NUTRITION_HISTORY, gson.toJson(foodAddedArray, history));
            editor.apply();
            finish();

            if (doneButton==v){
                finish();
            }
    }
}
