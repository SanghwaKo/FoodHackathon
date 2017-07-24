package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by KSH on 2017-07-21.
 */

public class RecipeListActivity extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FoodApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_list_view);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApplication = FoodApplication.getInstance();

        mAdapter = new ListAdapter(this, Constant.ARR_COLOR, mApplication.getRecipes());

        mRecyclerView.setAdapter(mAdapter);
    }
}
