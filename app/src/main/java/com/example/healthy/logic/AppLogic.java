package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.logic.Items.Food;
import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;
import com.example.healthy.logic.Points.ActivityPoints;
import com.example.healthy.logic.Points.NutritionPoints;
import com.example.healthy.logic.Points.RewardPoints;
import com.example.healthy.logic.Points.SocialPoints;

import java.util.ArrayList;

public class AppLogic {

    private ActivityPoints activityPoints = new ActivityPoints();
    private NutritionPoints nutritionPoints = new NutritionPoints();
    private SocialPoints socialPoints = new SocialPoints();
    private RewardPoints rewardPoints = new RewardPoints();
    private User user = new User();
    private int totalPrizes;


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

    public void attachObserverToUser(Observer observer) {
        user.attachObserver(observer);
    }


    public Item buyPrize() {
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

    public void addToHighIntensity(int minutes) {
        activityPoints.addToHighIntensity(minutes);
    }

    public int getHighIntensity() { return activityPoints.getHighIntensity();}

    public int getEndStepGoal() {
        return activityPoints.getEndStepGoal();
    }

    public int getEndFloorGoal() {
        return activityPoints.getEndFloorGoal();
    }

    public int getEndHighIntensityGoal() {
        return activityPoints.getEndHighIntensityGoal();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRewards(ArrayList<Item> rewards) {
        rewardPoints.setRewards(rewards);
    }

    public ArrayList<Item> getRewards() {
        return rewardPoints.getRewards();
    }

    public int getTotalPrizes() {
        return totalPrizes;
    }

    public void setTotalPrizes(int totalPrizes) {
        this.totalPrizes = totalPrizes;
    }

    public void addFoodToItemList(ArrayList<Item> addedFoodItems) {
        nutritionPoints.addToFoodItemList(addedFoodItems);
    }

    public ArrayList<Item> getFoodItemList() {
        return nutritionPoints.getFoodItemList();
    }

    public void setFoodItemList(ArrayList<Item> newFoodList) {
        nutritionPoints.setFoodItemList(newFoodList);
    }
}
