package com.example.healthy.Activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.healthy.MainActivity;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class DayActivities extends Fragment implements Observer, View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    PieChartView activityPie;
    private SliceValue HISlice ,stepSlice, floorSlice;
    TextView steps, points, altitude, tvStepProgress, tvHIProgress, tvFloorProgress;
    AppLogic appLogic = AppLogic.getInstance();
    List<SliceValue> activityData = new ArrayList<>();
    ProgressBar stepProgress, floorProgress, highIntensityProgress;
    FloatingActionButton addHi;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day_activities, container, false);

        // Inflate the layout for this fragment
        activityPie = root.findViewById(R.id.dayActivityPie);
        steps = root.findViewById(R.id.dayActivity_TextView_steps);
        points = root.findViewById(R.id.dayActivity_TextView_points);
        tvStepProgress = root.findViewById(R.id.dayActivity_TextView_stepProgress);
        tvHIProgress = root.findViewById(R.id.dayActivity_TextView_highIntensity_progress);
        tvFloorProgress = root.findViewById(R.id.dayActivity_TextView_floor_progress);

        altitude = root.findViewById(R.id.altitude);
        altitude.setText(""+appLogic.getAltitude());
        addHi = root.findViewById(R.id.addHi);
        addHi.setOnClickListener(this);
        stepProgress = root.findViewById(R.id.activity_day_step_Progress);
        highIntensityProgress = root.findViewById(R.id.activity_day_highIntensity_progress);
        floorProgress = root.findViewById(R.id.activity_day_floor_progress);


        stepProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorStep), android.graphics.PorterDuff.Mode.SRC_IN);
        highIntensityProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorHighIntensity), android.graphics.PorterDuff.Mode.SRC_IN);
        floorProgress.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorFloors), android.graphics.PorterDuff.Mode.SRC_IN);


        appLogic.attachObserverToActivityPoints(this);
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());

        stepSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorStep));
        floorSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorFloors));
        HISlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorHighIntensity));



        if (appLogic.getSteps() > 0){
            stepSlice.setValue(appLogic.getStepPoints());
            floorSlice.setValue((appLogic.getFloorPoints()));
            HISlice.setValue(appLogic.getHighIntensityPoints());
        }

        //set

        activityData.add(stepSlice);
        activityData.add(floorSlice);
        activityData.add(HISlice);



        PieChartData activityPieData = new PieChartData(activityData);

        activityPie.setPieChartData(activityPieData);

        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);

        activityPie.setPieChartData(activityPieData);

        updateView();



        return root;
    }

    // Method to read data from FireStore (DON'T DELETE)
    private void readUser () {
        db = FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("Brugere med point").document("1");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder data = new StringBuilder();
                    //data.append("Name: ").append(doc.getString("Name"));
                    data.append("\nPoints: ").append(doc.get("ActivityPoints"));
                    //data.append("\nPoints: ").append(doc.get("NutritionPoints"));
                    //data.append("\nPoints: ").append(doc.get("RewardPoints"));
                    points.setText(data.toString());
                    //textview.setText(data.toString());
                }
            }
        });
    }

    @Override
    public void updateView() {
        steps.setText("Steps: " + appLogic.getSteps());
        points.setText("Points: " + appLogic.getActivityPoints());

        stepSlice.setValue(appLogic.getStepPoints());
        floorSlice.setValue((appLogic.getFloorPoints()));
        HISlice.setValue(appLogic.getHighIntensityPoints());

        if (appLogic.getActivityPoints() == 0) {
            stepSlice.setValue(1);
            floorSlice.setValue(1);
            HISlice.setValue(1);
        }
        activityData.clear();
        activityData.add(stepSlice);
        activityData.add(floorSlice);
        activityData.add(HISlice);

        PieChartData activityPieData = new PieChartData(activityData);
        activityPieData.setHasLabels(false);
        activityPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);
        activityPie.setPieChartData(activityPieData);

        // Progressbar
        stepProgress.setMax(appLogic.getStepGoal());
        stepProgress.setProgress(appLogic.getSteps()%appLogic.getStepGoal());
        highIntensityProgress.setMax(appLogic.getHighIntensityGoal());
        highIntensityProgress.setProgress((appLogic.getSteps()/10)%appLogic.getHighIntensityGoal());
        floorProgress.setMax(appLogic.getFloorGoal());
        floorProgress.setProgress((appLogic.getSteps()/7)%appLogic.getFloorGoal());
        // progressbar textviews
        tvStepProgress.setText(appLogic.getSteps()%appLogic.getStepGoal()+" / "+appLogic.getStepGoal()+" Skridt");
        tvHIProgress.setText(appLogic.getSteps()/10%appLogic.getHighIntensityGoal()+" / "+appLogic.getHighIntensityGoal()+" Minuters HighIntesity");
        tvFloorProgress.setText(appLogic.getSteps()/7%appLogic.getFloorGoal()+" / "+appLogic.getFloorGoal()+" Etager");


    }

    @Override
    public void onClick(View v) {
        if (v == addHi) {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getActivity(), this, hour, minute, true);
            mTimePicker.setTitle("Indtast høj intensitet");
            mTimePicker.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

    }
}
