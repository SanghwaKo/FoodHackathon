package greenhouse.hackathon.com.foodhackathon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KSH on 2017-07-21.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Recipe> mRecipes;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView view){
            super(view);
            mTextView = view;
        }
    }

    public ListAdapter(ArrayList<Recipe> recipes){
        mRecipes = recipes;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.food_name ,parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mTextView.setText(mRecipes.get(position).getFoodName());
    }

    @Override
    public int getItemCount(){
        return mRecipes.size();
    }
}
