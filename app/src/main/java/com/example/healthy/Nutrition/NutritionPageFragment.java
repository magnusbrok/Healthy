package com.example.healthy.Nutrition;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthy.Activity.TimeMenu;
import com.example.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionPageFragment extends Fragment implements View.OnClickListener {

//    TextView day, week, month, goal, log;
//    ImageButton buttonPlus;

    FragmentTransaction fragmentTransaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_activity_page, container, false);

//        day = root.findViewById(R.id.meat);
//        day.setOnClickListener(this);
//        week = root.findViewById(R.id.fish);
//        week.setOnClickListener(this);
//        month = root.findViewById(R.id.wholeMeal);
//        month.setOnClickListener(this);
//        goal = root.findViewById(R.id.dairy);
//        goal.setOnClickListener(this);
//        buttonPlus = root.findViewById(R.id.water);
//        buttonPlus.setOnClickListener(this);
//        log = root.findViewById(R.id.beverages);
//        log.setOnClickListener(this);

        if(savedInstanceState == null) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout ,new Day());
            fragmentTransaction.commit();

            Bundle bundle = new Bundle();
            bundle.putString("Page", "NP");

            Fragment fragment = new TimeMenu();
            fragment.setArguments(bundle);

            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.TimeFrame, new TimeMenu())
                    .commit();
        }
        return root;
    }
        public void onClick(View view) {

//            if (view == buttonPlus){
//                Intent i = new Intent(getActivity(), AddFood.class);
//                startActivity(i);
//            }
//            if (view == log){
//                Intent i = new Intent(getActivity(), FruitsVeggies.class);
//                startActivity(i);
//
//            }
        }

}
