package com.example.healthy.logic.Points;

import com.example.healthy.ObserverPattern.Subject;
import com.example.healthy.logic.Items.Reward;

import java.util.ArrayList;
import java.util.Random;

public class RewardPoints extends Subject {

    private int rewardPoints = 0;
    private Reward prize;
    private final int prizePrice = 150;
    private ArrayList<Reward> rewards = new ArrayList<>();


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


    public Reward getPrize() {
        return prize;
    }

    public void setPrize(Reward prize) {
        this.prize = prize;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }
}
