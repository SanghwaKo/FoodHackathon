package greenhouse.hackathon.com.foodhackathon;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class Recipe {
    private String name;
    private ArrayList<Step> steps = new ArrayList<>();
    private int currentStep;
    private String imgUrl;
    private String ingredients;
    private boolean isVegan = true;
    private int duration;

    public Recipe(){
        currentStep = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public Step getStep(int step){
        return steps.get(step);
    }

    public void increaseStep(){
        currentStep++;
    }

    public void decreaseStep(){
        currentStep--;
    }

    public boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(boolean vegan) {
        isVegan = vegan;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }
}
