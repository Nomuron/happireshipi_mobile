package com.example.myapplication.recipes.entity;

/**
 * Represents an ingredient necessary to prepare a meal from recipe.
 *
 * @author Katarzyna Popieniuk
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

    public Ingredient(Ingredient recipeIngredient, int portionsAmount) {
        name = recipeIngredient.name;
        unit = recipeIngredient.unit;
        amount = recipeIngredient.getAmount() * portionsAmount;
    }

    // jackson-databind requirement
    public Ingredient() {
    }

    public Ingredient(String name, String unit, double amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Increases amount of the ingredient by specified value
     */
    public void increaseAmount(double addition) {
        amount += addition;
    }

}
