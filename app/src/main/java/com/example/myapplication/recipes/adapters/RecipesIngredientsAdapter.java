package com.example.myapplication.recipes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Ingredient;

import java.util.List;

/**
 * Class used to load recipes ingredients and adapt it to RecyclerView component
 *
 * @author  Patryk Klimek
 */
public class RecipesIngredientsAdapter extends RecyclerView.Adapter<RecipesIngredientsAdapter.MyViewHolder> {
    /**
     * App context
     */
    private final Context context;
    /**
     * List of ingredients
     */
    private final List<Ingredient> recipesIngredients;

    /**
     * Constructs a new {@link RecipesAdapter}.
     *
     * @param context The context in which the adapter is operating. Typically, this is the Activity or Fragment.
     * @param recipesIngredients The list of {@link Ingredient} objects to display in the RecyclerView.
     */
    public RecipesIngredientsAdapter(Context context, List<Ingredient> recipesIngredients) {
        this.context = context;
        this.recipesIngredients = recipesIngredients;
    }

    /**
     * Called when the RecyclerView needs a new {@link MyViewHolder} to represent an item.
     * This method is responsible for inflating the item layout and creating the ViewHolder.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View. This parameter is used to choose the layout type if your RecyclerView has more than one item type.
     * @return A new instance of {@link MyViewHolder} that holds a View of the given view type.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_detail_ingredient, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method updates the contents
     * of the {@link MyViewHolder#itemView} to reflect the item at the given position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredient ingredient = recipesIngredients.get(position);
        holder.recipeDetailIngredient.setText(ingredient.getName());
        holder.recipeDetailIngredientUnit.setText(ingredient.getUnit());
        holder.recipeDetailIngredientAmount.setText(String.valueOf(ingredient.getAmount()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return recipesIngredients.size();
    }

    /**
     * Retrieves a string value from the application's resources.
     *
     * @param resourceId The resource identifier of the string.
     * @return The string data associated with the resource, stripped of styled text information.
     */
    private String getString(int resourceId) {
        return context.getResources().getString(resourceId);
    }

    /**
     * Class which constructs a new MyViewHolder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recipeDetailIngredientAmount;
        TextView recipeDetailIngredientUnit;
        TextView recipeDetailIngredient;

        /**
         * Constructs a new MyViewHolder with the specified itemView.
         *
         * @param itemView The view of the item represented by this ViewHolder.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeDetailIngredientAmount = itemView.findViewById(R.id.RecipeDetailIngredientAmount);
            recipeDetailIngredientUnit = itemView.findViewById(R.id.RecipeDetailIngredientUnit);
            recipeDetailIngredient = itemView.findViewById(R.id.RecipeDetailIngredient);
        }
    }
}
