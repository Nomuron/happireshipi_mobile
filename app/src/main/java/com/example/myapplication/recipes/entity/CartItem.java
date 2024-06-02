package com.example.myapplication.recipes.entity;

/**
 * Class representing cart item.
 *
 * @author Katarzyna Popieniuk
 */
public class CartItem {


    /**
     * Recipe's name
     */
    private final String name;

    /**
     * Recipe's portion amount
     */
    private final int amount;

    public CartItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
