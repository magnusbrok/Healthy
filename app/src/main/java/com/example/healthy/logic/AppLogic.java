package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Observer;

public class AppLogic {
        private ActivityPoints activityPoints = new ActivityPoints();

        private static AppLogic instance = new AppLogic();

        public static AppLogic getInstance(){
            return instance;
        }

        public void computePoints(){
            activityPoints.computePoints();
        }

        public int getSteps(){
            return activityPoints.getSteps();
        }

        public void setSteps(int steps){
            activityPoints.setSteps(steps);
        }

        public int getActivityPoints(){
            return activityPoints.getPoints();
        }

        public void setActivityPoints(int points){
            activityPoints.setPoints(points);
        }

        public void attachObserverToActivityPoints(Observer observer){
            activityPoints.attachObserver(observer);
        }


}
