package com.example.healthy.logic;

import java.util.ArrayList;
import java.util.Random;

public class RewardPoints extends Points {

    private int rewardPoints;
    private Reward prize;
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

    public void computePoints(int activityPoints) {
        rewardPoints = activityPoints;
    }

    public Reward buyPrize() {
        prize = (Reward) rewards.get(new Random().nextInt(rewards.size()));
        return prize;
    }


}
