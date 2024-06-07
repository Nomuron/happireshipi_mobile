package com.example.myapplication.recipes.control;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.myapplication.recipes.entity.CartItem;
import com.example.myapplication.recipes.entity.Ingredient;
import com.example.myapplication.recipes.entity.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for generating a list of ingredients based on cart items.
 *
 * @author Patryk Klimek, Katarzyna Popieniuk
 */
public class IngredientsListGenerator {

    private final Context context;

    /**
     * Constructs a new IngredientsListGenerator with the specified context.
     *
     * @param context The context used to retrieve resources.
     */
    public IngredientsListGenerator(Context context) {
        this.context = context;
    }

    /**
     * Generates a list of ingredients with their names, units, and total amounts based on the given cart items.
     * This method takes a list of cart items, retrieves the corresponding recipes, and calculates the total amount
     * of each ingredient required for all the cart items. The ingredients are combined by their names and units,
     * and the total quantities are computed.
     *
     * @param cartItems A list of {@link CartItem} objects representing the items in the cart.
     * @return A list of ingredients.
     */
    public List<Ingredient> generateIngredientsList(List<CartItem> cartItems) {
        Resources resources = context.getResources();
        List<Recipe> recipes = RecipesProvider.getRecipes(resources);

        List<Ingredient> ingredients = new ArrayList<>();

        cartItems.forEach(cartItem -> populateIngredients(ingredients, cartItem, recipes));

        return ingredients;
    }

    private void populateIngredients(List<Ingredient> ingredients, CartItem cartItem, List<Recipe> recipes) {
        findMatchingRecipe(cartItem, recipes)
                .ifPresent(recipe -> populateIngredients(ingredients, recipe.getIngredients(), cartItem.getAmount()));

    }

    private void populateIngredients(List<Ingredient> allIngredients, List<Ingredient> recipeIngredients, int portionsAmount) {
        recipeIngredients.forEach(ingredient -> populateIngredients(allIngredients, ingredient, portionsAmount));
    }

    private void populateIngredients(List<Ingredient> allIngredients, Ingredient recipeIngredient, int portionsAmount) {
        Optional<Ingredient> matchingIngredient = findMatchingIngredient(allIngredients, recipeIngredient);
        if (matchingIngredient.isPresent()) {
            matchingIngredient.get().increaseAmount(recipeIngredient.getAmount() * portionsAmount);
        } else {
            allIngredients.add(new Ingredient(recipeIngredient, portionsAmount));
        }
    }

    @NonNull
    private static Optional<Ingredient> findMatchingIngredient(List<Ingredient> allIngredients, Ingredient recipeIngredient) {
        return allIngredients.stream()
                .filter(ingredient -> ingredient.getName().equals(recipeIngredient.getName()))
                .filter(ingredient -> ingredient.getUnit().equals(recipeIngredient.getUnit()))
                .findFirst();
    }

    private Optional<Recipe> findMatchingRecipe(CartItem cartItem, List<Recipe> recipes) {
        return recipes.stream()
                .filter(recipe -> cartItem.getName().equals(recipe.getName()))
                .findFirst();
    }
}
