package com.example.healthy.logic;

public class ActivityPoints extends Points {

    private int points;
    private int steps;
    private int[] stepGoals = new int[100];

    public ActivityPoints(){
        // Generate step milestones with intervals of 2500
        for (int i = 0; i < stepGoals.length; i++){
            stepGoals[i] = 25*(i+1);
        }
    }

    @Override
    public void computePoints(){
        //TODO implement code to compute points from steps taken

        int points = 0;

        //Increments local points if steps taken are exceeds an milestone
        for (int i = 0; i < stepGoals.length; i++){
            if (stepGoals[i] < steps){
                points += 50;
            }
        }

        setPoints(points);
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


}
