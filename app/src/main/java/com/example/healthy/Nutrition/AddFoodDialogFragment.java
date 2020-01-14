package com.example.healthy.Nutrition;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFoodDialogFragment extends DialogFragment implements View.OnClickListener {

    ImageButton fruitsAndVeggies, fish, wholemeal, dairy, water,beverages, meat, doneButton;
    ArrayList<String> addFood = new ArrayList<>();
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

            addFood.add(fruitsAndVeggies);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Frugt og Grønt!", Toast.LENGTH_SHORT).show();
        }
        else if (v == fish){

            String fish = "Fisk" ;

            addFood.add(fish);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Fisk!", Toast.LENGTH_SHORT).show();

        }
        else if (v == wholemeal){
            String wholemeal = "Fuldkorn" ;

            addFood.add(wholemeal);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Fuldkorn!", Toast.LENGTH_SHORT).show();
        }
        else if (v== dairy){

            String dairy = "Mejeri";

            addFood.add(dairy);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Mejeri!", Toast.LENGTH_SHORT).show();
        }
        else if (v == water){

            String water ="Vand";

            addFood.add(water);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Vand!", Toast.LENGTH_SHORT).show();
        }
        else if (v == beverages){
            String drikkevarer ="Drikkevarer";

            addFood.add(drikkevarer);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Drikkevarer!", Toast.LENGTH_SHORT).show();
        }
        else if (v == meat){
            String meat ="Magert kød";

            addFood.add(meat);
            updateDatabase();
            Toast.makeText(getActivity(), "Der er nu tilføjet Magert kød!", Toast.LENGTH_SHORT).show();
        }

        else if (v == doneButton){

            // En af nedenstående 3 metodekald opdaterer points i NutriotionPage
            appLogic.setFoodList(addFood);
            appLogic.computePoints();
            appLogic.getNutritionPoints();

            updateDatabase();
            getDialog().dismiss();
        }
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