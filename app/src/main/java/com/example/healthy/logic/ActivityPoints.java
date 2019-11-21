package com.example.healthy.logic;

public class ActivityPoints extends Points {

    private int points;
    private int steps;

    public void computePoints(){
        //code to compute points from steps
    }

    //getters and setters
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
        notifyChangeToObservers();
    }


}
