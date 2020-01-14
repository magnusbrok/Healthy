package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Subject;

import java.util.ArrayList;
import java.util.Random;

public class RewardPoints extends Subject {

    private int rewardPoints = 0;
    private Reward prize;
    private final int prizePrice = 30;
    private ArrayList<Object> rewards = new ArrayList<>();


    /**
     * Constructor to set up the possible rewards
     */
    RewardPoints() {
        rewards.add(new Reward("Æble"));
        rewards.add(new Reward("Cykel"));
        rewards.add(new Reward("Basketball"));
        rewards.add(new Reward("iPhone"));
    }

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
