package com.example.healthy.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    ImageButton frugtOgGrønt, fisk, fuldkorn, ost, dråbe,kop, magertkød,plus,button9;
    Button færdigKnap;
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
        frugtOgGrønt = findViewById(R.id.frugtOgGrønt);
        frugtOgGrønt.setOnClickListener(this);
        fisk = findViewById(R.id.Fisk);
        fisk.setOnClickListener(this);
        fuldkorn = findViewById(R.id.Fuldkorn);
        fuldkorn.setOnClickListener(this);
        ost = findViewById(R.id.Ost);
        ost.setOnClickListener(this);
        dråbe = findViewById(R.id.Dråbe);
        dråbe.setOnClickListener(this);
        kop = findViewById(R.id.Kop);
        kop.setOnClickListener(this);
        magertkød = findViewById(R.id.MagertKød);
        magertkød.setOnClickListener(this);
        plus = findViewById(R.id.Plus);
        plus.setOnClickListener(this);
        færdigKnap = findViewById(R.id.button);
        færdigKnap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == frugtOgGrønt){
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
        else if (v == fisk){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String fisk = "Fisk udløser: " + points +" point" ;
            addFood.add(fisk);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Fisk (20 point)!", Toast.LENGTH_LONG).show();

        }
        else if (v == fuldkorn){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String fuldkorn = "Fuldkorn udløser: " + points +" point" ;
            addFood.add(fuldkorn);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Fuldkorn (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v== ost){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String ost = "Ost udløser: " + points +" point";
            addFood.add(ost);

            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Ost (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == dråbe){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String dråbe ="Vand udløser: " + points +" point";
            addFood.add(dråbe);
            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Vand (20 point)!", Toast.LENGTH_LONG).show();
        }
        else if (v == kop){
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
        else if (v == magertkød){
            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            String magertkød ="Magert kød udløser: " + points +" point";
            addFood.add(magertkød);
            editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Der er nu tilføjet Magertkød (20 point)!", Toast.LENGTH_LONG).show();
        }
        
        else if (v == færdigKnap){
            finish();
        }
    }

}
