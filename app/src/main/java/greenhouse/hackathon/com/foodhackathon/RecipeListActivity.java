package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class RecipeListActivity extends Activity implements AdapterView.OnItemClickListener{
    private ArrayList<Recipe> mRecipes;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_view);
        mRecipes = new ArrayList<>();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ListAdapter(mRecipes);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(RecipeListActivity.this, ShowStepsActivity.class);

        Bundle bundle = new Bundle();
        //bundle.putBundle("recipe", mRecipes.get(position));

        startActivity(intent);

    }
}
