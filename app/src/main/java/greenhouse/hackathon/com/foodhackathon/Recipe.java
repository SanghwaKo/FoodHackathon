package greenhouse.hackathon.com.foodhackathon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class Recipe implements Parcelable{
    private String name;
    private ArrayList<Step> steps;
    private int currentStep;

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);

        out.writeInt(currentStep);
    }

    private Recipe(Parcel in){
        name = in.readString();
        currentStep = in.readInt();
    }
    public Recipe(String name){
        this.name = name;
        steps = new ArrayList<>();
        currentStep = 0;
    }

    public static final Parcelable.Creator<Recipe> CREATOR =
            new Parcelable.Creator<Recipe>(){
                public Recipe createFromParcel(Parcel in){
                    return new Recipe(in);
                }

                public Recipe[] newArray(int size){
                    return new Recipe[size];
                }
            };

    public String getFoodName(){
        return name;
    }

    public void addStep(Step step){
        steps.add(step);
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
