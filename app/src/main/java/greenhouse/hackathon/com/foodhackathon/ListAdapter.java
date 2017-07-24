package greenhouse.hackathon.com.foodhackathon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private int[] mColors;
    private ArrayList<Recipe> mRecipes;
    private Context mContext;

    // UI Component
    private RelativeLayout mFoodItemLayout;
    private static TextView mFoodItemName;
    private static ImageView mVeganIcon;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view){
            super(view);

            mFoodItemName = (TextView)view.findViewById(R.id.food_name);
            mVeganIcon = (ImageView)view.findViewById(R.id.vegan_icon);
        }
    }

    public ListAdapter(Context context, int[] colors, ArrayList<Recipe> recipes){
        mContext = context;
        mColors = colors;
        mRecipes = recipes;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        if(mRecipes.get(position).getIsVegan()){
         //   mVeganIcon.setVisibility(View.VISIBLE);
        }else{
            mVeganIcon.setVisibility(View.GONE);
        }
        mFoodItemName.setText(mRecipes.get(position).getName());

        if(position == 0){
            mFoodItemName.setBackgroundResource(mColors[position]);
        }else{
            mFoodItemName.setBackgroundResource(mColors[position % mColors.length]);
        }
        mFoodItemName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowFoodActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt(Constant.TAG_RECIPE, position);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        return mRecipes.size();
    }
}
