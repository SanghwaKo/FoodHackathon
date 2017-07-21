package greenhouse.hackathon.com.foodhackathon;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class FoodApplication extends Application {
    private static FoodApplication INSTANCE = null;
    private ArrayList<Recipe> mRecipes;

    // Singleton
    public static synchronized FoodApplication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FoodApplication();
        }
        return INSTANCE;
    }

    public void onCreate(){
        super.onCreate();

        mRecipes = new ArrayList<>();
        setRecipes();
    }

    public void setRecipes(){
        //
    }

    public void addRecipe(Recipe recipe){
        mRecipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes(){
        return mRecipes;
    }

    public Recipe getRecipe(int position){
        return mRecipes.get(position);
    }
}
