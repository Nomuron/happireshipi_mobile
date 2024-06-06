package com.example.myapplication.recipes.control;

import android.content.Context;
import android.content.res.Resources;

import com.example.myapplication.recipes.entity.CartItem;
import com.example.myapplication.recipes.entity.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for generating a list of ingredients based on cart items.
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
     * @return A list of lists where each inner list contains the name of the ingredient, its unit, and the total amount.
     */
    public List<List<String>> generateIngredientsList(List<CartItem> cartItems) {
        Resources resources = context.getResources();
        List<Recipe> recipes = RecipesProvider.getRecipes(resources);

        List<List<String>> ingredientsList = recipes.stream()
                .filter(recipe -> cartItems.stream().anyMatch(item -> item.getName().equals(recipe.getName())))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .map(ingredient -> Arrays.asList(
                        ingredient.getName(),
                        ingredient.getUnit(),
                        String.valueOf(ingredient.getAmount())))
                .collect(Collectors.toList());

        return combineIngredients(ingredientsList);
    }


    /**
     * Combines quantities of the same ingredients in a list.
     *
     * @param ingredients A list of ingredients with name, unit, and quantity.
     * @return A list of combined ingredients.
     */
    public static List<List<String>> combineIngredients(List<List<String>> ingredients) {
        Map<String, Double> combinedIngredients = new HashMap<>();

        for (List<String> ingredient : ingredients) {
            String name = (String) ingredient.get(0);
            String unit = (String) ingredient.get(1);
            Double quantity = Double.parseDouble((String) ingredient.get(2));

            String key = name + " - " + unit;

            combinedIngredients.put(key, combinedIngredients.getOrDefault(key, 0.0) + quantity);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : combinedIngredients.entrySet()) {
            String[] nameUnit = entry.getKey().split(" - ");
            List<String> ingredient = new ArrayList<>();
            ingredient.add(nameUnit[0]);
            ingredient.add(nameUnit[1]);
            ingredient.add(entry.getValue().toString());
            result.add(ingredient);
        }

        return result;
    }

}
