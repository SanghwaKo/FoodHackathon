package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by KSH on 2017-07-21.
 */

public class ShowFoodActivity extends Activity {
    private FoodApplication mApplication;
    private Recipe mRecipe;
    private TextView mTxtName;
    private TextView mTxtIngredients;
    private ImageView mImgFood;
    private int mSelectedFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_view);

        mTxtName = (TextView)findViewById(R.id.txt_name);
        mTxtIngredients = (TextView)findViewById(R.id.txt_ingredients);
        mImgFood = (ImageView)findViewById(R.id.food_pic);

        mApplication = FoodApplication.getInstance();
        mSelectedFood = getIntent().getIntExtra(Constant.TAG_RECIPE, 0);
        mRecipe = mApplication.getRecipe(mSelectedFood);

        mTxtName.setText(mRecipe.getName() + "\n" + getResources().getString(R.string.duration).replace("%%DD", "" + mRecipe.getDuration()));
        mTxtIngredients.setText(mRecipe.getIngredients());
        Glide.with(this).load(mRecipe.getImgUrl()).into(mImgFood);
    }

    public void onStart(View view){
        Intent intent = new Intent(ShowFoodActivity.this, ShowStepsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.TAG_RECIPE, mSelectedFood);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
