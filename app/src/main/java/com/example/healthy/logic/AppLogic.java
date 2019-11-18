package com.example.healthy.logic;

public class AppLogic {
        private ActivityPoints activityPoints = new ActivityPoints();

        private static AppLogic instance = new AppLogic();

        public static AppLogic getInstance(){
            return instance;
        }

        public int getSteps(){
            return activityPoints.getSteps();
        }

        public void setSteps(int steps){
            activityPoints.setSteps(steps);
        }


}
