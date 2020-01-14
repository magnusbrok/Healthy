package com.example.healthy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthy.Activity.ActivityPageFragment;
import com.example.healthy.Nutrition.NutritionPageFragment;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.Reward.RewardPageFragment;
import com.example.healthy.Social.SocialPageFragment;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener, Observer {

    private ImageButton activityButton, rewardButton, socialButton, nutritionButton;
    private TextView activityPoints, rewardPoints, socialPoints, nutritionPoints;

    AppLogic appLogic = AppLogic.getInstance();

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_page, container, false);

        rewardButton = root.findViewById(R.id.homepage_reward);
        rewardButton.setOnClickListener(this);
        activityButton = root.findViewById(R.id.homepage_activity);
        activityButton.setOnClickListener(this);
        socialButton = root.findViewById(R.id.homepage_social);
        socialButton.setOnClickListener(this);
        nutritionButton = root.findViewById(R.id.homepage_nutrition);
        nutritionButton.setOnClickListener(this);



        appLogic.attachObserverToActivityPoints(this);
        appLogic.attachObserverToRewardPoints(this);
        appLogic.attachObserverToNutritionPoints(this);


        activityPoints = root.findViewById(R.id.homepage_activity_points);
        rewardPoints = root.findViewById(R.id.homepage_reward_points);
        nutritionPoints = root.findViewById(R.id.homepage_nutrition_points);

        updateView();
        readLog();

        // Implement nutrition and social later

        return root;
    }

    @Override
    public void onClick(View v) {
        MainActivity bottomMenu = (MainActivity) getActivity();

        if (v == rewardButton) {

            bottomMenu.changeMenu(R.id.rewardPage);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment fragment = new RewardPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, fragment).addToBackStack(null);
            transaction.commit();
        }

        if (v == nutritionButton) {
            bottomMenu.changeMenu(R.id.nutriotionPage);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment fragment = new NutritionPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, fragment).addToBackStack(null);
            transaction.commit();

        }

        if (v == activityButton) {

            bottomMenu.changeMenu(R.id.activityPage);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment fragment = new ActivityPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, fragment).addToBackStack(null);
            transaction.commit();
        }

        if (v == socialButton) {
            bottomMenu.changeMenu(R.id.socialPage);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();

            Fragment fragment = new SocialPageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentView, fragment).addToBackStack(null);
            transaction.commit();
        }

    }

    @Override
    public void updateView() {
        rewardPoints.setText(""+appLogic.getRewardPoints());
        activityPoints.setText(""+appLogic.getActivityPoints());
        nutritionPoints.setText(""+appLogic.getNutritionPoints());
    }


    private void readLog () {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userLog = db.collection("Brugere med point").document("1").collection("FoodLog").document("2");
        userLog.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    appLogic.setFoodList((ArrayList<String>) doc.get("Food added"));
                    appLogic.computePoints();
                }
            }
        });
    }

}
