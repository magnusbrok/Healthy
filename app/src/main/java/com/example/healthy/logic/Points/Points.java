package com.example.healthy.logic.Points;

import com.example.healthy.ObserverPattern.Subject;

public abstract class Points extends Subject {

    private int points;

    public int computePoints(){
        return 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        notifyChangeToObservers();
    }

    protected int computeDifference(int newPoints){
        int oldPoints = points;
        setPoints(newPoints);

        int difference = newPoints - oldPoints;

        return difference;
    }
}