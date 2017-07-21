package greenhouse.hackathon.com.foodhackathon;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class Recipe {
    private String name;
    private ArrayList<Step> steps;
    private int currentStep;

    public Recipe(String name, ArrayList<Step> steps){
        this.name = name;
        this.steps = steps;
        currentStep = 0;
    }

    public String getFoodName(){
        return name;
    }

    public ArrayList<Step> getSteps(){
        return steps;
    }

    public Step getStep(int step){
        return steps.get(step);
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void increaseStep(){
        currentStep++;
    }

    public void decreaseStep(){
        currentStep--;
    }
}
