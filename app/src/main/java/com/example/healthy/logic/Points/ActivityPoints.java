package com.example.healthy.logic.Points;

public class ActivityPoints extends Points {

    private int points;
    private int steps;
    private int highIntensity;


    private int stepPointIncrementer = 50;
    private int floorPointIncrementer = 200;
    private int highIntensityIncrementer = 75;



    private int stepGoal = 25;
    private int floorGoal = 1;
    private int highIntensityGoal = 10;

    private int[] stepGoals = new int[100];
    private int[] floorGoals = new int[5];
    private int[] highIntensityGoals = new int[6];

    private int endStepGoal;
    private int endHighIntensityGoal;
    private int endFloorGoal;




    public ActivityPoints(){
        // Generate step milestones with intervals of 2500
        for (int i = 0; i < stepGoals.length; i++){
            stepGoals[i] = stepGoal*(i+1);
        }
        endStepGoal = stepGoals[stepGoals.length-1];

        for (int i = 0; i < floorGoals.length; i++){
            floorGoals[i] = floorGoal*(i+1);
        }
        endFloorGoal = floorGoals[floorGoals.length-1];

        for (int i = 0; i < highIntensityGoals.length; i++){
            highIntensityGoals[i] = highIntensityGoal*(i+1);
        }
        endHighIntensityGoal = highIntensityGoals[highIntensityGoals.length-1];
    }

    @Override
    public int computePoints(){

        int currentPoints = points;

        int points = computeStepPoints() + computeHighIntensityPoints() + computeFloorPoints();

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

    public int getStepPointIncrementer() {
        return stepPointIncrementer;
    }

    public void setStepPointIncrementer(int stepPointIncrementer) {
        this.stepPointIncrementer = stepPointIncrementer;
    }

    public int computeStepPoints() {
        int points = 0;

        for (int i = 0; i < stepGoals.length; i++){
            if (stepGoals[i] <= steps){
                points += stepPointIncrementer;
            }
        }

        return points;
    }

    public int computeHighIntensityPoints() {
        int points = 0;

        int min = highIntensity;

        for (int i = 0; i < highIntensityGoals.length; i++){
            if (highIntensityGoals[i] <= min){
                points += highIntensityIncrementer;
            }
        }
        return points;
    }

    public int computeFloorPoints() {
        int points = 0;

        int floors = steps/100;

        for (int i = 0; i < floorGoals.length; i++){
            if (floorGoals[i] <= floors){
                points += floorPointIncrementer;
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

    public int getFloorPointIncrementer() {
        return floorPointIncrementer;
    }

    public void setFloorPointIncrementer(int floorPointIncrementer) {
        this.floorPointIncrementer = floorPointIncrementer;
    }

    public int getHighIntensityIncrementer() {
        return highIntensityIncrementer;
    }

    public void setHighIntensityIncrementer(int highIntensityIncrementer) {
        this.highIntensityIncrementer = highIntensityIncrementer;
    }

    public int getFloorGoal() {
        return floorGoal;
    }

    public void setFloorGoal(int floorGoal) {
        this.floorGoal = floorGoal;
    }

    public int getHighIntensityGoal() {
        return highIntensityGoal;
    }

    public void setHighIntensityGoal(int highIntensityGoal) {
        this.highIntensityGoal = highIntensityGoal;
    }


    public void addToHighIntensity(int minutes) {
        highIntensity += minutes;
        notifyChangeToObservers();
    }

    public int getHighIntensity() {
        return highIntensity;
    }


    public int getEndStepGoal() {
        return endStepGoal;
    }

    public void setEndStepGoal(int endStepGoal) {
        this.endStepGoal = endStepGoal;
    }

    public int getEndHighIntensityGoal() {
        return endHighIntensityGoal;
    }

    public void setEndHighIntensityGoal(int endHighIntensityGoal) {
        this.endHighIntensityGoal = endHighIntensityGoal;
    }

    public int getEndFloorGoal() {
        return endFloorGoal;
    }

    public void setEndFloorGoal(int endFloorGoal) {
        this.endFloorGoal = endFloorGoal;
    }

}
