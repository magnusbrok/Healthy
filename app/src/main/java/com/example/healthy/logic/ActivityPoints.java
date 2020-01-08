package com.example.healthy.logic;

public class ActivityPoints extends Points {

    private int points;
    private int steps;


    private int stepPointIncrementer = 50;
    private int floorPointIncrementer = 100;
    private int highIntensityIncrementer = 40;

    private int stepGoal = 25;
    private int floorGoal = 5;
    private int highIntensityGoal = 60;

    private int[] stepGoals = new int[100];
    private int[] floorGoals = new int[5];
    private int[] highIntensityGoals = new int[6];


    public ActivityPoints(){
        // Generate step milestones with intervals of 2500
        for (int i = 0; i < stepGoals.length; i++){
            stepGoals[i] = stepGoal*(i+1);
        }
        for (int i = 0; i < floorGoals.length; i++){
            floorGoals[i] = floorGoal*(i+1);
        }
        for (int i = 0; i < highIntensityGoals.length; i++){
            highIntensityGoals[i] = highIntensityGoal*(i+1);
        }
    }

    @Override
    public int computePoints(){
        //TODO implement code to compute points from steps taken

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
            if (stepGoals[i] < steps){
                points += stepPointIncrementer;
            }
        }

        return points;
    }

    public int computeHighIntensityPoints() {
        int points = 0;

        int min = steps/100;

        for (int i = 0; i < highIntensityGoals.length; i++){
            if (highIntensityGoals[i] < min){
                points += highIntensityIncrementer;
            }
        }
        return points;
    }

    public int computeFloorPoints() {
        int points = 0;

        int floors = steps/75;

        for (int i = 0; i < floorGoals.length; i++){
            if (floorGoals[i] < floors){
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



}
