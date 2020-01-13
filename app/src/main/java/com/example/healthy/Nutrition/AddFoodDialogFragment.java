package com.example.healthy.Nutrition;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.healthy.Nutrition.LogHistory.NUTRITION_HISTORY;

public class AddFoodDialogFragment extends DialogFragment implements View.OnClickListener {

    ImageButton fruitsAndVeggies, fish, wholemeal, dairy, water,beverages, meat,plus, doneButton;
    ArrayList<String> addFood = new ArrayList<>();
    Type history = new TypeToken<ArrayList<String>>(){}.getType();
    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AppLogic appLogic = AppLogic.getInstance();

    FirebaseFirestore db;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_add_food,container,false);
        fruitsAndVeggies = v.findViewById(R.id.fruitsAndVeggies);
        fruitsAndVeggies.setOnClickListener(this);
        fish = v.findViewById(R.id.fish);
        fish.setOnClickListener(this);
        wholemeal = v.findViewById(R.id.wholeMeal);
        wholemeal.setOnClickListener(this);
        dairy = v.findViewById(R.id.dairy);
        dairy.setOnClickListener(this);
        water = v.findViewById(R.id.water);
        water.setOnClickListener(this);
        meat = v.findViewById(R.id.meat);
        meat.setOnClickListener(this);
        doneButton = v.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == fruitsAndVeggies) {
            String fruitsAndVeggies = "Frugt og grønt";
            saveInPrefs(fruitsAndVeggies, addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Frugt og Grønt!", Toast.LENGTH_LONG).show();
        }
        else if (v == fish){

            String fish = "Fisk" ;
            saveInPrefs(fish,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Fisk!", Toast.LENGTH_LONG).show();

        }
        else if (v == wholemeal){
            String wholemeal = "Fuldkorn" ;
            saveInPrefs(wholemeal,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Fuldkorn!", Toast.LENGTH_LONG).show();
        }
        else if (v== dairy){

            String dairy = "Mejeri";
            saveInPrefs(dairy,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Mejeri!", Toast.LENGTH_LONG).show();
        }
        else if (v == water){

            String water ="Vand";
            saveInPrefs(water,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Vand!", Toast.LENGTH_LONG).show();
        }
        else if (v == beverages){
            String drikkevarer ="Drikkevarer";
            saveInPrefs(drikkevarer,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Drikkevarer!", Toast.LENGTH_LONG).show();
        }
        else if (v == meat){
            String meat ="Magert kød";
            saveInPrefs(meat,addFood);
            Toast.makeText(getActivity(), "Der er nu tilføjet Magert kød!", Toast.LENGTH_LONG).show();
        }

        else if (v == doneButton){
            sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

            if (!nutritionHistory.equals("null")){
                addFood = gson.fromJson(nutritionHistory,history);
            }

            appLogic.setFoodList(addFood);
            appLogic.computePoints();
            getDialog().dismiss();
        }
    }

    private void saveInPrefs(String string, ArrayList addFood){
        sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String nutritionHistory = sharedPreferences.getString(NUTRITION_HISTORY,"null");

        if (!nutritionHistory.equals("null")){
            addFood = gson.fromJson(nutritionHistory,history);
        }
        addFood.add(string);
        editor.putString(NUTRITION_HISTORY, gson.toJson(addFood, history));
        editor.apply();
    }

    public void updateDatabase() {
        db = FirebaseFirestore.getInstance();

        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("Food added", addFood);

        db.collection("Brugere med point").document("1").collection("FoodLog").document("2") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(updateUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - redigeringerne blev ikke gemt", e.getMessage());
                    }
                });
    }
    }