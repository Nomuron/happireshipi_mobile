package com.example.myapplication.recipes.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {
    private Context context;
    private List<Recipe> recipes;

    AssetManager assets;


    public RecipesAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        this.assets = context.getAssets();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_tile, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.portionsNumberInput.setHint(getString(R.string.portions_number_input));
        try (InputStream stream =
                     assets.open(getString(R.string.images_directory) + recipe.getImageFileName())) {
            Drawable recipeImg = Drawable.createFromStream(stream, recipe.getImageFileName());
            holder.recipeImage.setImageDrawable(recipeImg);

        } catch (IOException exception) {
            Log.e(recipe.getName(), getString(R.string.error_loading) + recipe.getImageFileName());
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private String getString(int resourceId) {
        return context.getResources().getString(resourceId);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        EditText portionsNumberInput;
        ImageView recipeImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            portionsNumberInput = itemView.findViewById(R.id.portionsNumberInput);
            recipeImage = itemView.findViewById(R.id.recipeImage);
        }
    }
}
