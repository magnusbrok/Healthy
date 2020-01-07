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
        int activityPointsIncrease = activityPoints.computePoints();
        int nutritionPointIncrease = nutritionPoints.computePoints();
        int socialPointIncrease = socialPoints.computePoints();

        rewardPoints.addPoints(activityPointsIncrease);

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



    public Reward buyPrize() {
        return rewardPoints.buyPrize();
    }


}
