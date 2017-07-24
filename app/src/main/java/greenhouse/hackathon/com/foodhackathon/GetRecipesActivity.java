package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KSH on 2017-07-21.
 */

public class GetRecipesActivity extends Activity {
    private static final String mListUrl = "https://raw.githubusercontent.com/raywenderlich/recipes/master/Recipes.json";
    // To get the list of the Recipes
    private FoodApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_view);

        mApplication = FoodApplication.getInstance();

        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(mListUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0; i<response.length(); i++){
                        Recipe newRecipe = new Recipe();

                        JSONObject foodObj = response.getJSONObject(i);
                        if(foodObj.has(Constant.TAG_NAME)){
                            newRecipe.setName(foodObj.getString(Constant.TAG_NAME));
                        }
                        if(foodObj.has(Constant.TAG_INGREDIENTS)){
                            JSONArray ingArray = foodObj.getJSONArray(Constant.TAG_INGREDIENTS);
                            String ingredients = "";
                            for(int j=0; j<ingArray.length(); j++){
                                JSONObject ingObj = ingArray.getJSONObject(j);
                                ingredients = ingredients + "\n";
                                ingredients = ingredients + ingObj.getString(Constant.TAG_NAME) + " : " + ingObj.getString(Constant.TAG_QTT);

                                for(int k=0; k<Constant.ARR_X_VEGAN.length; k++){
                                    if(ingObj.getString(Constant.TAG_TYPE).toLowerCase().contains(Constant.ARR_X_VEGAN[k])){
                                        newRecipe.setIsVegan(false);
                                    }
                                }
                            }
                            newRecipe.setIngredients(ingredients);
                        }
                        if(foodObj.has(Constant.TAG_IMG_URL)){
                            newRecipe.setImgUrl(foodObj.getString(Constant.TAG_IMG_URL));
                        }

                        if(foodObj.has(Constant.TAG_STEPS) && foodObj.has(Constant.TAG_TIMERS)){
                            JSONArray stepsArr = foodObj.getJSONArray(Constant.TAG_STEPS);
                            Step[] steps = new Step[stepsArr.length()];

                            for(int j=0; j<stepsArr.length(); j++){
                                steps[j] = new Step();
                                steps[j].setInstruction(stepsArr.getString(j));
                            }

                            JSONArray durationArr = foodObj.getJSONArray(Constant.TAG_TIMERS);
                            int foodDuration = 0; // Can decide the difficulty of the food depends on duration time of cooking.

                            for(int j=0; j<stepsArr.length(); j++){
                                foodDuration += durationArr.getInt(j);
                                steps[j].setDuration(durationArr.getInt(j));
                                newRecipe.addStep(steps[j]);
                            }
                            newRecipe.setDuration(foodDuration);
                        }

                        mApplication.addRecipe(newRecipe);
                    }
                }catch (JSONException ex){
                    Log.e("Error", "JSONException" + ex.getLocalizedMessage());
                }
                Intent intent = new Intent(GetRecipesActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest); //Trigger
    }
}
