package com.example.healthy.logic.Points;

import com.example.healthy.logic.Items.*;
import java.util.ArrayList;

public class NutritionPoints extends Points {

    private ArrayList<Item> foodItemList = new ArrayList<>();
    private int pointIncrementer = 5;

    @Override
    public int computePoints(){
        int points = foodItemList.size()*pointIncrementer;

        int diff = computeDifference(points);

        return diff;
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