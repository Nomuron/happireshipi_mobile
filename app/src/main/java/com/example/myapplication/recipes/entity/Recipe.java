package com.example.myapplication.recipes.entity;

import java.util.List;

/**
 * Represents a cooking recipe
 *
 * @author  Katarzyna Popieniuk
 */
public class Recipe {

    /**
     * Recipe's name
     */
    private String name;

    /**
     * Recipe's category
     */
    private String category;

    /**
     * Recipe's preparation instruction
     */
    private String instruction;

    /**
     * Meal's image file name present in application's resources
     */
    private String imageFileName;

    /**
     * List of necessary ingredients for meal's preparation
     */
    private List<Ingredient> ingredients;

    public String getImageFileName() {
        return imageFileName;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getInstruction() {
        return instruction;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
