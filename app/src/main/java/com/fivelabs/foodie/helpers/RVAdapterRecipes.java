package com.fivelabs.foodie.helpers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivelabs.foodie.R;
import com.fivelabs.foodie.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by breogangf on 16/10/15.
 */
public class RVAdapterRecipes extends RecyclerView.Adapter<RVAdapterRecipes.RecipeViewHolder>{

    List<Recipe> recipes;
    private Context mContext;

    public RVAdapterRecipes(Context context, List<Recipe> recipes){
        this.recipes = recipes;
        this.mContext =  context;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView cookingTime;
        TextView type;
        ImageView image;

        RecipeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            name = (TextView)itemView.findViewById(R.id.recipe_name);
            cookingTime = (TextView)itemView.findViewById(R.id.cooking_time);
            type = (TextView)itemView.findViewById(R.id.recipe_type);
            image = (ImageView) itemView.findViewById(R.id.recipe_photo);
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder pvh = new RecipeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.name.setText(recipes.get(position).getName());
        holder.cookingTime.setText(String.valueOf(recipes.get(position).getCook_time()));
        holder.type.setText(recipes.get(position).getCategory());

        //Download image using picasso library
        Picasso.with(mContext).load(recipes.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}