package com.example.healthy.Nutrition;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.healthy.R;
import com.example.healthy.TopMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionPageFragment extends Fragment implements View.OnClickListener {

    Button buttonDag, buttonUge, buttonMåned, buttonMål, buttonLog;
    ImageButton buttonPlus;

    FragmentTransaction fragmentTransaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_nutrition_page, container, false);


        buttonDag = root.findViewById(R.id.button7);
        buttonDag.setOnClickListener(this);
        buttonUge = root.findViewById(R.id.button2);
        buttonUge.setOnClickListener(this);
        buttonMåned = root.findViewById(R.id.button3);
        buttonMåned.setOnClickListener(this);
        buttonMål = root.findViewById(R.id.button4);
        buttonMål.setOnClickListener(this);
        buttonPlus = root.findViewById(R.id.button5);
        buttonPlus.setOnClickListener(this);
        buttonLog = root.findViewById(R.id.button6);
        buttonLog.setOnClickListener(this);

        if(savedInstanceState == null) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout,new Day());
            fragmentTransaction.commit();
        }


        return root;
    }
        public void onClick(View view) {
            if (view == buttonDag){
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Day())
                    .commit();
            }
            if (view == buttonUge){
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Week())
                    .commit();
            }
            if (view == buttonMåned){
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Month())
                    .commit();
            }
            if (view == buttonPlus){
                Intent i = new Intent(getActivity(), AddFood.class);
                startActivity(i);
            }
        }

}
