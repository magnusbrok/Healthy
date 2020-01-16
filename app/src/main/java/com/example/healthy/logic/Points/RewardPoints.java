package com.example.healthy.logic.Points;

import com.example.healthy.ObserverPattern.Subject;
import com.example.healthy.R;
import com.example.healthy.logic.Items.Reward;

import java.util.ArrayList;
import java.util.Random;

public class RewardPoints extends Subject {

    private int rewardPoints = 0;
    private Reward prize;
    private final int prizePrice = 150;
    private ArrayList<Object> rewards = new ArrayList<>();

    public void addPoints(int activityPoints, int nutritionPoints) {
        int totalPoints = rewardPoints+activityPoints+nutritionPoints;
        setRewardPoints(totalPoints);
    }

    public Reward buyPrize() {
        setRewardPoints(rewardPoints-prizePrice);
        prize = (Reward) rewards.get(new Random().nextInt(rewards.size()));
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
}
