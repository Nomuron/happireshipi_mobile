package com.example.myapplication.recipes.control;

import com.example.myapplication.recipes.entity.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class used to load recipes list
 *
 * @author  Katarzyna Popieniuk
 */
public class RecipesLoader {

    /**
     * Returns the list of recipes.
     *
     * @author  Katarzyna Popieniuk
     * @param  inputStream  input stream with list of recipes data in json format
     * @return  list of recipes
     * @throws  IOException when a problem with I/O handling occurs
     */
    public static List<Recipe> load(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, new TypeReference<List<Recipe>>() {
        });
    }
}
