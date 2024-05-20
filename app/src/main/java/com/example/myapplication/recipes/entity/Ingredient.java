package com.example.myapplication.recipes.entity;

/**
 * Represents an ingredient necessary to prepare a meal from recipe.
 *
 * @author  Katarzyna Popieniuk
 */
public class Ingredient {


    /**
     * Ingredient's name
     */
    private String name;

    /**
     * A metric unit used to specify the ingredient's amount necessary to prepare one portion of meal
     */
    private String unit;

    /**
     * Amount of the ingredient, combined with unit describes amount necessary to prepare one portion of meal
     */
    private double amount;
    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }
}
