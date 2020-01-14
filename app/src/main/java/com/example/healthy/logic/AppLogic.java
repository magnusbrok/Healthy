package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Observer;

import java.util.ArrayList;

public class AppLogic {

    private ActivityPoints activityPoints = new ActivityPoints();
    private NutritionPoints nutritionPoints = new NutritionPoints();
    private SocialPoints socialPoints = new SocialPoints();
    private RewardPoints rewardPoints = new RewardPoints();

    private double altitude;

    private static AppLogic instance = new AppLogic();

    public static AppLogic getInstance(){
            return instance;
    }

    public void computePoints() {
        int activityPointsIncrease = activityPoints.computePoints();
        int nutritionPointIncrease = nutritionPoints.computePoints();
        int socialPointIncrease = socialPoints.computePoints();

        rewardPoints.addPoints(activityPointsIncrease, nutritionPointIncrease);
    }


    public int getSteps(){
            return activityPoints.getSteps();
    }

    public void setSteps(int steps){
            activityPoints.setSteps(steps);
    }

    public int getActivityPoints() {
            return activityPoints.getPoints();
    }

    public int getRewardPoints() {
        return rewardPoints.getRewardPoints();
    }
    public int getNutritionPoints() {
        return nutritionPoints.getPoints();

    }

    public void addFoodToList(ArrayList<String> addedFood) {
        nutritionPoints.addToFoodLogHistory(addedFood);
        computePoints();
    }

    public int getStepPoints() {
        return activityPoints.computeStepPoints();
    }

    public int getFloorPoints() {
        return activityPoints.computeFloorPoints();
    }

    public int getHighIntensityPoints() {
        return activityPoints.computeHighIntensityPoints();
    }

    public boolean canBuyPrize() {
        if (rewardPoints.getRewardPoints() >= rewardPoints.getPrizePrice()) {
            return true;
        } else return false;
    }

    public void attachObserverToActivityPoints(Observer observer){
        activityPoints.attachObserver(observer);
    }

    public void attachObserverToRewardPoints(Observer observer) {
        rewardPoints.attachObserver(observer);
    }

    public void attachObserverToNutritionPoints(Observer observer) {
        nutritionPoints.attachObserver(observer);
    }


    public Reward buyPrize() {
        return rewardPoints.buyPrize();
    }

    public int getStepGoal() {
        return activityPoints.getStepGoal();
    }

    public int getHighIntensityGoal() {
        return activityPoints.getHighIntensityGoal();
    }

    public int getFloorGoal() {
        return activityPoints.getFloorGoal();
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void addToHighIntensity(int minutes) {
        activityPoints.addToHighIntensity(minutes);
    }

    public int getHighIntensity() { return activityPoints.getHighIntensity();}

    public void setFoodList(ArrayList<String> addFood) {
        nutritionPoints.setFoodList(addFood);
    }

    public ArrayList<String> getFoodList() {
        return nutritionPoints.getFoodList();
    }

    public int getEndStepGoal() {
        return activityPoints.getEndStepGoal();
    }

    public int getEndFloorGoal() {
        return activityPoints.getEndFloorGoal();
    }

    public int getEndHighIntensityGoal() {
        return activityPoints.getEndHighIntensityGoal();
    }


}
