package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by KSH on 2017-07-21.
 */

public class RecipeListActivity extends Activity implements AdapterView.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FoodApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_view);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApplication = FoodApplication.getInstance();

        mAdapter = new ListAdapter(mApplication.getRecipes());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(RecipeListActivity.this, ShowStepsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(Constant.TAG_RECIPE, position);
        startActivity(intent);

    }
}
