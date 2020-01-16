package com.example.healthy.Nutrition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
        setContentView(R.layout.activity_fruits_veggies);
        textView = findViewById(R.id.nutritionSearch);
        textView.setOnClickListener(this);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);

        foodAddedArray = appLogic.getFoodList();

        ListView listView = findViewById(R.id.addedFoodList);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,foodAddedArray);
        listView.setAdapter(arrayAdapter);
        if(foodAddedArray.size()>10){
            foodAddedArray.remove(1);
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
            if (doneButton==v){
                finish();
            }
    }
}