package com.example.myapplication.recipes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.recipes.control.IngredientsListGenerator;
import com.example.myapplication.recipes.entity.Cart;

import java.util.List;

/**
 * RecyclerView Adapter for selected recipes
 *
 * @author Patryk Klimek
 */
public class SelectedRecipesIngredientsAdapter extends RecyclerView.Adapter<SelectedRecipesIngredientsAdapter.ViewHolder> {

    /**
     * Application context
     */
    private final Context context;

    public SelectedRecipesIngredientsAdapter(Context context) {
        this.context = context;
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return associated ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_ingredient, parent, false);
        return new ViewHolder(view);

    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        IngredientsListGenerator ingredientListGenerator = new IngredientsListGenerator(context);
        List<List<String>> ingredientsList = ingredientListGenerator.generateIngredientsList(Cart.getCartItems());
        holder.recipeDetailIngredient.setText(ingredientsList.get(position).get(0));
        holder.recipeDetailIngredientUnit.setText(ingredientsList.get(position).get(1));
        holder.recipeDetailIngredientAmount.setText(ingredientsList.get(position).get(2));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        IngredientsListGenerator ingredientListGenerator = new IngredientsListGenerator(context);
        return ingredientListGenerator.generateIngredientsList(Cart.getCartItems()).size();
    }

    /**
     * Class which represents associated with the adapter ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeDetailIngredientAmount;
        TextView recipeDetailIngredientUnit;
        TextView recipeDetailIngredient;

        /**
         * Constructs a new MyViewHolder with the specified itemView.
         *
         * @param itemView The view of the item represented by this ViewHolder.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeDetailIngredientAmount = itemView.findViewById(R.id.RecipeDetailIngredientAmount);
            recipeDetailIngredientUnit = itemView.findViewById(R.id.RecipeDetailIngredientUnit);
            recipeDetailIngredient = itemView.findViewById(R.id.RecipeDetailIngredient);
        }
    }
}
