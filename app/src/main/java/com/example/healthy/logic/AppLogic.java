package com.example.healthy.logic;

public class AppLogic {
        private ActivityPoints activityPoints = new ActivityPoints();

        public int getSteps(){
            return activityPoints.getSteps();
        }

        public void setSteps(int steps){
            activityPoints.setSteps(steps);
        }


}
