package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.healthy.Nutrition.LogHistory.NUTRITION_HISTORY;


public class AddFood extends AppCompatActivity implements View.OnClickListener {
    ImageButton fruitsAndVeggies, fish, wholemeal, dairy, water,beverages, meat,plus,button9;
    Button done;
    ArrayList<String> addFood = new ArrayList<>();
    int points = 20;
    int totalPoints;
    Type history = new TypeToken<ArrayList<String>>(){}.getType();
    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AppLogic appLogic = AppLogic.getInstance();

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

    }

    @Override
    public void onClick(View v) {
        if (v == fruitsAndVeggies) {
            String fruitsAndVeggies = "Frugt og grønt udløser: " + points + " point";
            saveInPrefs(fruitsAndVeggies, addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Frugt og Grønt!", Toast.LENGTH_LONG).show();
        }
        else if (v == fish){

            String fish = "Fisk udløser: " + points +" point" ;
            saveInPrefs(fish,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Fisk!", Toast.LENGTH_LONG).show();

        }
        else if (v == wholemeal){
            String wholemeal = "Fuldkorn udløser: " + points +" point" ;
            saveInPrefs(wholemeal,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Fuldkorn!", Toast.LENGTH_LONG).show();
        }
        else if (v== dairy){

            String dairy = "Mejeri udløser: " + points +" point";
            saveInPrefs(dairy,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Mejeri!", Toast.LENGTH_LONG).show();
        }
        else if (v == water){

            String water ="Vand udløser: " + points +" point";
            saveInPrefs(water,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Vand!", Toast.LENGTH_LONG).show();
        }
        else if (v == beverages){
            String drikkevarer ="Drikkevarer udløser: " + points +" point";
            saveInPrefs(drikkevarer,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Drikkevarer!", Toast.LENGTH_LONG).show();
        }
        else if (v == meat){
            String meat ="Magert kød udløser: " + points +" point";
            saveInPrefs(meat,addFood);
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Magert kød!", Toast.LENGTH_LONG).show();
        }

        else if (v == done){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            appLogic.setFoodList(addFood);

            appLogic.computePoints();

            finish();
        }
    }

    private void saveInPrefs(String string, ArrayList addFood){
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

        if (!nutritionHistory.equals("null")){
            addFood = gson.fromJson(nutritionHistory,history);
        }
        addFood.add(string);
        editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
        editor.apply();
    }
}
