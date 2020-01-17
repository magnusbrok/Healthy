package com.example.healthy.logic.Points;

import android.content.SharedPreferences;

import com.example.healthy.logic.Items.*;

import java.util.ArrayList;

public class NutritionPoints extends Points {

    private int points;
    private ArrayList<Item> foodItemList = new ArrayList<>();
    private int pointIncrementer = 5;

    @Override
    public int computePoints(){

        int currentPoints = points;


        points = foodItemList.size()*pointIncrementer;

        setPoints(points);

        int diff = points - currentPoints;
        return (diff);
    }


    public int getPoints() {
        return  points;
    }

    public void setPoints(int points) {
        this.points = points;
        notifyChangeToObservers();
    }

    public void setFoodItemList(ArrayList<Item> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public ArrayList<Item> getFoodItemList() {
        return foodItemList;
    }

    public void addToFoodItemList(ArrayList<Item> addedFoodItems){
        if (foodItemList == null) {
            setFoodItemList(addedFoodItems);
        } else {
            foodItemList.addAll(0,addedFoodItems);
            
        }
    }
}
