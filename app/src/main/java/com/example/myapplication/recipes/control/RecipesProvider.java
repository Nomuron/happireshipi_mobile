package com.example.myapplication.recipes.control;

import android.content.res.Resources;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class used to get recipes list from application's resources.
 * Loads necessary data on first getRecipes method usage.
 *
 * @author  Katarzyna Popieniuk
 */
public class RecipesProvider {

    /**
     * List of recipes loaded from file in application resources.
     */
    private static List<Recipe> recipes;

    private RecipesProvider() {
    }

    /**
     * Loads a list of recipes from a file in application resources. Returns the list of recipes.
     *
     * @author  Katarzyna Popieniuk
     * @param  resources  application resources
     * @return  list of recipes
     */
    public static List<Recipe> getRecipes(Resources resources) {
        if (recipes == null) {
            try (InputStream inputStream = resources.getAssets().open(resources.getString(R.string.recipes_filename))) {
                recipes = RecipesLoader.load(inputStream);
            } catch (IOException e) {
                Log.e(RecipesProvider.class.getSimpleName(), resources.getString(R.string.error_loading_recipes));
            }
        }

        return recipes;
    }
}
