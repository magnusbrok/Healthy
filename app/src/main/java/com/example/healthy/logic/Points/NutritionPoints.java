package com.example.healthy.logic.Points;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class NutritionPoints extends Points {

    private int points;
    private ArrayList<String> foodList = new ArrayList<>();
    private int pointIncrementer = 5;

    SharedPreferences.Editor editor;

    @Override
    public int computePoints(){

        int currentPoints = points;

        points = foodList.size()*pointIncrementer;

        setPoints(points);

        int diff = points - currentPoints;
        return (diff);
    }

    public void addToFoodLogHistory(ArrayList<String> addedFood) {
        foodList.addAll(addedFood);


    }


    public int getPoints() {
        return  points;
    }

    public void setPoints(int points) {
        this.points = points;
        notifyChangeToObservers();
    }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<String> foodList) {
        this.foodList = foodList;
    }
}
