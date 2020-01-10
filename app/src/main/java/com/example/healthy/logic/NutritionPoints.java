package com.example.healthy.logic;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class NutritionPoints extends Points {

    private int points;
    private ArrayList<String> foodList;
    private int pointIncrementer = 5;

    SharedPreferences.Editor editor;

    @Override
    public int computePoints(){

        int currentPoints = points;

        points = foodList.size()*pointIncrementer;

        int diff = points - currentPoints;
        return (diff);
    }


    public int getPoints() {
        return  points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<String> foodList) {
        this.foodList = foodList;
    }
}
