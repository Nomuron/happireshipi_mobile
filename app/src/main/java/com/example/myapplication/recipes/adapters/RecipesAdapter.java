package com.example.myapplication.recipes.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Cart;
import com.example.myapplication.recipes.entity.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class used to load recipes and adapt it to RecyclerView component
 *
 * @author  Alicja Szczypior
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {
    /**
     * App context
     */
    private final Context context;
    /**
     * List of recipes
     */
    private final List<Recipe> recipes;
    /**
     * Asset manager
     */
    AssetManager assets;

    /**
     * Constructs a new {@link RecipesAdapter}.
     *
     * @param context The context in which the adapter is operating. Typically, this is the Activity or Fragment.
     * @param recipes The list of {@link Recipe} objects to display in the RecyclerView.
     */
    public RecipesAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        this.assets = context.getAssets();
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
        View view = LayoutInflater.from(context).inflate(R.layout.dish_tile, parent, false);
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

        holder.addButton.setOnClickListener(v -> {
            int previousNumber = getCurrentPortionNumber(holder);
            holder.portionsNumberInput.setText(String.valueOf(previousNumber + 1));
        });

        holder.substractButton.setOnClickListener(v -> {
            int previousNumber = getCurrentPortionNumber(holder);
            holder.portionsNumberInput.setText(String.valueOf(Math.max(previousNumber - 1, 1)));
        });

        holder.addDishToListButton.setOnClickListener(v -> {
            int portionNumber = getCurrentPortionNumber(holder);
            Cart.add(holder.recipeName.getText().toString(), portionNumber);
        });
    }

    /**
     * Returns the current meal portion number.
     *
     * @return The current meal portion number.
     */
    private static int getCurrentPortionNumber(@NonNull MyViewHolder holder) {
        String portionInputText = holder.portionsNumberInput.getText().toString();
        try {
            return Integer.parseInt(portionInputText);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return recipes.size();
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
     * Class which represents  associated with the adapter ViewHolder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        EditText portionsNumberInput;
        ImageView recipeImage;

        Button addButton;
        Button substractButton;
        Button addDishToListButton;

        /**
         * Constructs a new MyViewHolder with the specified itemView.
         *
         * @param itemView The view of the item represented by this ViewHolder.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            portionsNumberInput = itemView.findViewById(R.id.portionsNumberInput);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            addButton = itemView.findViewById(R.id.addButton);
            substractButton = itemView.findViewById(R.id.substractButton);
            addDishToListButton = itemView.findViewById(R.id.addDishToList);
        }
    }
}
