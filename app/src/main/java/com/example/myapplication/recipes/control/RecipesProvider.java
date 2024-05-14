package com.example.myapplication.recipes.control;

import android.content.res.Resources;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.recipes.entity.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RecipesProvider {

    private static List<Recipe> recipes;

    private RecipesProvider() {
    }

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
