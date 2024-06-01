package com.example.myapplication.recipes.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class used to collect chosen recipes names with amount.
 *
 * @author Katarzyna Popieniuk
 */
public class Cart {

    /**
     * Map of portion amounts by recipe names
     */
    private static final Map<String, Integer> recipesNamesWithAmount = new HashMap<>();

    /**
     * Adds to the map of portion amounts by recipe names another portions of dish
     *
     * @param recipeName    recipe name to add portions
     * @param portionAmount portion amount to add
     * @author Katarzyna Popieniuk
     */
    public static void add(String recipeName, int portionAmount) {
        recipesNamesWithAmount.put(recipeName, recipesNamesWithAmount.getOrDefault(recipeName, 0) + portionAmount);
    }

    /**
     * Returns list of cart items
     *
     * @return list of cart items
     * @author Katarzyna Popieniuk
     */
    public static List<CartItem> getCartItems() {
        return recipesNamesWithAmount.entrySet().stream()
                .map(entry -> new CartItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
