package com.example.healthy.logic;

public class ActivityPoints extends Points {

    private int points;
    private int steps;


    private int pointIncrementer = 50;

    private int stepGoal = 25;

    private int[] stepGoals = new int[100];
    public ActivityPoints(){
        // Generate step milestones with intervals of 2500
        for (int i = 0; i < stepGoals.length; i++){
            stepGoals[i] = stepGoal*(i+1);
        }
    }

    @Override
    public int computePoints(){
        //TODO implement code to compute points from steps taken
        int currentPoints = points;

        int points = computeStepPoints();

        //Increments local points if steps taken are exceeds an milestone

        setPoints(points);

        int difference = points - currentPoints;

        return (difference);
    }

    //getters and setters

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
        notifyChangeToObservers();
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
        notifyChangeToObservers();
    }

    public int getPointIncrementer() {
        return pointIncrementer;
    }

    public void setPointIncrementer(int pointIncrementer) {
        this.pointIncrementer = pointIncrementer;
    }

    public int computeStepPoints() {
        int points = 0;

        for (int i = 0; i < stepGoals.length; i++){
            if (stepGoals[i] < steps){
                points += pointIncrementer;
            }
        }
        return points;
    }

    public int getStepGoal() {
        return stepGoal;
    }

    public void setStepGoal(int stepGoal) {
        this.stepGoal = stepGoal;
    }
}