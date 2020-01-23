package com.example.healthy;

        import com.example.healthy.logic.Items.Food;
        import com.example.healthy.logic.Items.Item;
        import com.example.healthy.logic.Points.ActivityPoints;
        import com.example.healthy.logic.Points.NutritionPoints;
        import org.junit.Test;
        import java.util.ArrayList;
        import static org.junit.Assert.*;

public class PointsJUnitTest {

    @Test
    public void computeStepPointTest(){

        ActivityPoints activityPoints = new ActivityPoints();
        int milestone = activityPoints.getStepMilestone();
        int incrementer = activityPoints.getStepPointIncrementer();
        int steps = 15;

        int expectedPoints = (steps/milestone)*incrementer;
        activityPoints.setSteps(steps);
        int actualPoints = activityPoints.computeStepPoints();
        assertEquals(expectedPoints,actualPoints);

        steps += 10;
        expectedPoints = (steps/milestone)*incrementer;
        activityPoints.setSteps(steps);
        actualPoints = activityPoints.computeStepPoints();
        assertEquals(expectedPoints,actualPoints);
    }

    @Test
    public void computeHIPointsTest(){
        ActivityPoints activityPoints = new ActivityPoints();
        int milestone = activityPoints.getHighIntensityMilestone();
        int incrementer = activityPoints.getHighIntensityIncrementer();
        int highIntensity = 15;

        int expectedPoints = (highIntensity/milestone)*incrementer;
        activityPoints.addToHighIntensity(highIntensity);
        int actualPoints = activityPoints.computeHighIntensityPoints();
        assertEquals(expectedPoints, actualPoints);

        activityPoints.addToHighIntensity(30);
        highIntensity = activityPoints.getHighIntensity();
        expectedPoints = (highIntensity/milestone)*incrementer;
        actualPoints = activityPoints.computeHighIntensityPoints();
        assertEquals(expectedPoints, actualPoints);

        // when minutes of HI exceeds HI-goal
        activityPoints.addToHighIntensity(30);
        highIntensity = activityPoints.getHighIntensity();
        expectedPoints = (highIntensity/milestone)*incrementer;
        actualPoints = activityPoints.computeHighIntensityPoints();
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void computeActivityPointsTest(){
        ActivityPoints activityPoints = new ActivityPoints();

        activityPoints.setSteps(30);
        activityPoints.addToHighIntensity(15);

        int stepPoints = activityPoints.computeStepPoints();
        int hiPoints = activityPoints.computeHighIntensityPoints();
        int floorPoints = activityPoints.computeFloorPoints();

        int expectedPoints = stepPoints + hiPoints + floorPoints;

        activityPoints.computePoints();
        int actualPoints = activityPoints.getPoints();

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void computeNutritionPointsTest(){
        NutritionPoints nutritionPoints = new NutritionPoints();

        assertEquals(0, nutritionPoints.getPoints());

        ArrayList<Item> foodList = new ArrayList<>();
        foodList.add(new Food("food", 5));
        foodList.add(new Food("foodie", 3));
        foodList.add(new Food("foodies", 10));

        nutritionPoints.addToFoodItemList(foodList);
        nutritionPoints.computePoints();

        assertEquals(15, nutritionPoints.getPoints());
    }

}

