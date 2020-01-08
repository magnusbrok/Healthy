package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.healthy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.healthy.Nutrition.FruitsVeggies.NUTRITION_HISTORY;


public class AddFood extends AppCompatActivity implements View.OnClickListener {
    ImageButton fruitsAndVeggies, fish, wholemeal, dairy, water,beverages, meat,plus,button9;
    Button done;
    ArrayList<String> addFood = new ArrayList<>();
    int points = 20;
    Type history = new TypeToken<ArrayList<String>>(){}.getType();
    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        fruitsAndVeggies = findViewById(R.id.fruitsAndVeggies);
        fruitsAndVeggies.setOnClickListener(this);
        fish = findViewById(R.id.fish);
        fish.setOnClickListener(this);
        wholemeal = findViewById(R.id.wholeMeal);
        wholemeal.setOnClickListener(this);
        dairy = findViewById(R.id.dairy);
        dairy.setOnClickListener(this);
        water = findViewById(R.id.water);
        water.setOnClickListener(this);
        beverages = findViewById(R.id.beverages);
        beverages.setOnClickListener(this);
        meat = findViewById(R.id.meat);
        meat.setOnClickListener(this);
        plus = findViewById(R.id.extra);
        plus.setOnClickListener(this);
        done = findViewById(R.id.done);
        done.setOnClickListener(this);
        if (addFood.size()> 11){
            addFood.remove(0);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == fruitsAndVeggies){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }
            String fruitsAndVeggies = "Frugt og grønt udløser: " + points + " point";
            addFood.add(fruitsAndVeggies);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Frugt og Grønt (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == fish){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String fish = "fish udløser: " + points +" point" ;
            addFood.add(fish);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet fish (20 point)!", Toast.LENGTH_LONG).show();

        }
        else if (v == wholemeal){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String wholemeal = "wholemeal udløser: " + points +" point" ;
            addFood.add(wholemeal);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet wholemeal (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v== dairy){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String dairy = "dairy udløser: " + points +" point";
            addFood.add(dairy);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet dairy (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == water){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String water ="Vand udløser: " + points +" point";
            addFood.add(water);
            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Vand (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == beverages){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String drikkevarer ="Drikkevarer udløser: " + points +" point";
            addFood.add(drikkevarer);
            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Drikkevarer (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == meat){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String meat ="Magert kød udløser: " + points +" point";
            addFood.add(meat);
            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet meat (20 point)!", Toast.LENGTH_LONG).show();
        }
        
        else if (v == done){
            finish();
        }
    }

}
