package com.example.myapplication.recipes.entity;

import java.util.List;

public class Recipe {

    private String name;
    private String category;
    private String instruction;

    private String imageFileName;
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
