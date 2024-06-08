package com.example.myapplication.recipes.control;

import androidx.annotation.NonNull;

import com.example.myapplication.recipes.entity.CartItem;
import com.example.myapplication.recipes.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for caching ingredients that are checked on displayed ingredients list.
 *
 * @author Katarzyna Popieniuk
 */
public class CheckedIngredientsCache {

    /**
     * Ingredients that are checked on displayed ingredients list.
     *
     */
    private static final List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Changes checked value of the Ingredient
     *
     * @param ingredient {@link Ingredient}.
     */
    public static void toggle(Ingredient ingredient) {
        Optional<Ingredient> matchingIngredient = findMatchingIngredient(ingredient);
        if (matchingIngredient.isPresent()) {
            ingredients.remove(matchingIngredient.get());
        } else {
            ingredients.add(ingredient);
        }
    }

    /**
     * Checks if Ingredient was checked on displayed ingredient list and returns the result
     *
     * @param ingredient {@link Ingredient}.
     * @return true if checked, false otherwise.
     */
    public static boolean getIsChecked(Ingredient ingredient) {
        return findMatchingIngredient(ingredient).isPresent();
    }

    @NonNull
    private static Optional<Ingredient> findMatchingIngredient(Ingredient recipeIngredient) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(recipeIngredient.getName()))
                .filter(ingredient -> ingredient.getUnit().equals(recipeIngredient.getUnit()))
                .findFirst();
    }
}
