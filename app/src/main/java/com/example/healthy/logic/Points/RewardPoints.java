package com.example.healthy.logic.Points;

import com.example.healthy.ObserverPattern.Subject;
import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;

import java.util.ArrayList;
import java.util.Random;

public class RewardPoints extends Subject {

    private int rewardPoints = 0;
    private Item prize;
    private final int prizePrice = 150;
    private ArrayList<Item> rewards = new ArrayList<>();

    public void addPoints(int activityPoints, int nutritionPoints) {
        int totalPoints = rewardPoints+activityPoints+nutritionPoints;
        setRewardPoints(totalPoints);
    }

    public Item buyPrize() {
        setRewardPoints(rewardPoints-prizePrice);
        prize = rewards.get(new Random().nextInt(rewards.size()));
        return prize;
    }

    public int getPrizePrice() {
        return prizePrice;
    }


    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
        notifyChangeToObservers();
    }


    public Item getPrize() {
        return prize;
    }

    public void setPrize(Reward prize) {
        this.prize = prize;
    }

    public ArrayList<Item> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Item> rewards) {
        this.rewards = rewards;
    }
}