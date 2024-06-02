package com.example.myapplication.recipes.interfaces;

import android.view.View;

/**
 * Interface definition for a callback to be invoked when a recipe item is clicked.
 *
 * @author  Patryk Klimek
 */
public interface RecipeOnClick {

    /**
     * Called when a recipe item has been clicked.
     *
     * @param view The view within the RecyclerView that was clicked.
     * @param position The position of the view in the adapter.
     */
    void onItemClick(View view, int position);
}
