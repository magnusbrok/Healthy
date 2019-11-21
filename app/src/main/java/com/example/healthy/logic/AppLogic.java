package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Observer;

public class AppLogic {

    private ActivityPoints activityPoints = new ActivityPoints();
    private NutritionPoints nutritionPoints = new NutritionPoints();
    private SocialPoints socialPoints = new SocialPoints();
    private RewardPoints rewardPoints = new RewardPoints();

    private static AppLogic instance = new AppLogic();

    public static AppLogic getInstance(){
            return instance;
    }

    public void computePoints() {
        activityPoints.computePoints();
        nutritionPoints.computePoints();
        socialPoints.computePoints();
        rewardPoints.computePoints(activityPoints.getPoints());

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

    public void attachObserverToActivityPoints(Observer observer){
            activityPoints.attachObserver(observer);
    }

    public Reward buyPrize() {
        return rewardPoints.buyPrize();
    }


}