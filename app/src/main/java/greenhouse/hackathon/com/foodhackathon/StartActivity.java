package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by KSH on 2017-07-21.
 */

public class StartActivity extends Activity {
    // To get the list of the Recipes
    private FoodApplication mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = FoodApplication.getInstance();
        mApplication.setRecipes(); // Set recipes
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
