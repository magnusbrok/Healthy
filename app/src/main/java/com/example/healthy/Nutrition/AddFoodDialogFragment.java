package com.example.healthy.Nutrition;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.healthy.R;
import com.example.healthy.logic.AppDAO;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Items.Food;
import com.example.healthy.logic.Items.Item;
import java.util.ArrayList;

public class AddFoodDialogFragment extends DialogFragment implements View.OnClickListener {

    ImageButton fruitsAndVeggies, fish, wholemeal, dairy, water, meat, doneButton;
    ArrayList<Item> addedFoodItems = new ArrayList<>();
    AppLogic appLogic = AppLogic.getInstance();
    AppDAO appDAO = AppDAO.getInstance();

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
        String itemName;

        if (v == fruitsAndVeggies) {
            itemName = "Frugt og grønt";
            addedFoodItems.add(0, new Food(itemName, R.drawable.nutrition_fruitsandveggies));
            Toast.makeText(getActivity(), "Der er nu tilføjet Frugt og Grønt!", Toast.LENGTH_SHORT).show();
        }
        else if (v == fish){
            itemName = "Fisk" ;
            addedFoodItems.add(0, new Food(itemName, R.drawable.nutritionpage_fish));
            Toast.makeText(getActivity(), "Der er nu tilføjet Fisk!", Toast.LENGTH_SHORT).show();

        }
        else if (v == wholemeal){
            itemName = "Fuldkorn" ;
            addedFoodItems.add(0, new Food(itemName, R.drawable.nutrition_page_fuldkorn));
            Toast.makeText(getActivity(), "Der er nu tilføjet Fuldkorn!", Toast.LENGTH_SHORT).show();
        }
        else if (v== dairy){
            itemName = "Mejeri";
            addedFoodItems.add(0, new Food(itemName, R.drawable.nutrition_mejeri));
            Toast.makeText(getActivity(), "Der er nu tilføjet Mejeri!", Toast.LENGTH_SHORT).show();
        }
        else if (v == water){
            itemName ="Vand";
            addedFoodItems.add(0, new Food(itemName, R.drawable.water_nutrition));
            Toast.makeText(getActivity(), "Der er nu tilføjet Vand!", Toast.LENGTH_SHORT).show();
        }
        else if (v == meat){
            itemName ="Magert kød";
            addedFoodItems.add(0, new Food(itemName, R.drawable.nutrition_page_magertkoed));
            Toast.makeText(getActivity(), "Der er nu tilføjet Magert kød!", Toast.LENGTH_SHORT).show();
        }

        else if (v == doneButton){
            //appLogic.addFoodToList(addFood);
            appLogic.addFoodToItemList(addedFoodItems);
            appDAO.addFoodToLog();
            appLogic.computePoints();
            getDialog().dismiss();
        }
    }
}