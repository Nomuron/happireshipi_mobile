package com.example.myapplication;

import static org.junit.Assert.*;

import com.example.myapplication.recipes.control.RecipesLoader;
import com.example.myapplication.recipes.entity.Recipe;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RecipesLoaderTest {

    private final String jsonRecipe = "[\n" +
            "  {\n" +
            "    \"name\": \"Jajko z majonezem\",\n" +
            "    \"category\": \"śniadanie\",\n" +
            "    \"ingredients\": [\n" +
            "      {\n" +
            "        \"name\": \"jajko\",\n" +
            "        \"amount\": 1,\n" +
            "        \"unit\": \"sztuk\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"majonez\",\n" +
            "        \"amount\": 1,\n" +
            "        \"unit\": \"łyżek\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"instruction\": \"Przekrój jajko, posmaruj majonezem\",\n" +
            "    \"imageFileName\": \"image.jpeg\"\n" +
            "  }\n" +
            "]";

    @Test
    public void shouldParseRecipes() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(jsonRecipe.getBytes());

        List<Recipe> recipes = RecipesLoader.load(inputStream);

        assertEquals(1, recipes.size());
        assertEquals("Jajko z majonezem", recipes.get(0).getName());
        assertEquals("śniadanie", recipes.get(0).getCategory());
        assertEquals("Przekrój jajko, posmaruj majonezem", recipes.get(0).getInstruction());
        assertEquals("image.jpeg", recipes.get(0).getImageFileName());
        assertEquals(2, recipes.get(0).getIngredients().size());
        assertEquals("jajko", recipes.get(0).getIngredients().get(0).getName());
        assertEquals("sztuk", recipes.get(0).getIngredients().get(0).getUnit());
        assertEquals(1.0, recipes.get(0).getIngredients().get(0).getAmount(), 0.01);
        assertEquals("majonez", recipes.get(0).getIngredients().get(1).getName());
        assertEquals("łyżek", recipes.get(0).getIngredients().get(1).getUnit());
        assertEquals(1.0, recipes.get(0).getIngredients().get(1).getAmount(), 0.01);
    }
}