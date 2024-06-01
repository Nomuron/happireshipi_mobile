package com.example.myapplication.recipes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Cart;
import com.example.myapplication.recipes.entity.CartItem;

/**
 * RecyclerView Adapter for selected recipes
 *
 * @author Katarzyna Popieniuk
 */
public class SelectedRecipesAdapter extends RecyclerView.Adapter<SelectedRecipesAdapter.ViewHolder> {

    /**
     * Application context
     */
    private final Context context;

    public SelectedRecipesAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.selected_recipe_with_portions_tile, parent, false);
        return new ViewHolder(view);

    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CartItem cartItem = Cart.getCartItems().get(position);
        holder.cartItem = cartItem;
        holder.recipeNameTextView.setText(cartItem.getName());
        holder.portionAmountTextView.setText(String.valueOf(cartItem.getAmount()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return Cart.getCartItems().size();
    }

    /**
     * Class which represents associated with the adapter ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView recipeNameTextView;
        public final TextView portionAmountTextView;
        public CartItem cartItem;

        /**
         * Constructs a new MyViewHolder with the specified itemView.
         *
         * @param itemView The view of the item represented by this ViewHolder.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.selectedRecipeName);
            portionAmountTextView = itemView.findViewById(R.id.selectedPortionAmount);
        }
    }
}
