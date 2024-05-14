package com.example.myapplication.recipes.control;

import com.example.myapplication.recipes.entity.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RecipesLoader {

    public static List<Recipe> load(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, new TypeReference<List<Recipe>>() {
        });
    }
}
