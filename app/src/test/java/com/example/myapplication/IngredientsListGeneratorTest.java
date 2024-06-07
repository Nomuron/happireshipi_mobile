package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;

import com.example.myapplication.recipes.control.IngredientsListGenerator;
import com.example.myapplication.recipes.control.RecipesProvider;
import com.example.myapplication.recipes.entity.CartItem;
import com.example.myapplication.recipes.entity.Ingredient;
import com.example.myapplication.recipes.entity.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientsListGeneratorTest {

    @Mock
    private Context context;

    @Mock
    private Resources resources;

    private IngredientsListGenerator generator;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        generator = new IngredientsListGenerator(context);
        when(context.getResources()).thenReturn(resources);
    }

    @Test
    public void shouldGenerateIngredientsList() {
        CartItem cartItem1 = new CartItem("Spaghetti", 2);
        CartItem cartItem2 = new CartItem("Sałatka", 1);
        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

        Ingredient ingredient1 = new Ingredient("pomidor", "kawałki", 2.0);
        Ingredient ingredient2 = new Ingredient("makaron", "gramy", 200.0);
        Ingredient ingredient3 = new Ingredient("sałata", "gramy", 100.0);

        Recipe recipe1 = mock(Recipe.class);
        when(recipe1.getName()).thenReturn("Spaghetti");
        when(recipe1.getIngredients()).thenReturn(Arrays.asList(ingredient1, ingredient2));

        Recipe recipe2 = mock(Recipe.class);
        when(recipe2.getName()).thenReturn("Sałatka");
        when(recipe2.getIngredients()).thenReturn(Arrays.asList(ingredient3));

        try (MockedStatic<RecipesProvider> mockedStatic = Mockito.mockStatic(RecipesProvider.class)) {
            mockedStatic.when(() -> RecipesProvider.getRecipes(resources)).thenReturn(Arrays.asList(recipe1, recipe2));

            List<Ingredient> ingredientsList = generator.generateIngredientsList(cartItems);

            assertEquals(3, ingredientsList.size());

            Optional<Ingredient> matchingIngredient = findMatchingIngredient("pomidor", ingredientsList);
            assertTrue(matchingIngredient.isPresent());
            assertEquals("kawałki", matchingIngredient.get().getUnit());
            assertEquals(4, matchingIngredient.get().getAmount(), 0.01);

            matchingIngredient = findMatchingIngredient("sałata", ingredientsList);
            assertTrue(matchingIngredient.isPresent());
            assertEquals("gramy", matchingIngredient.get().getUnit());
            assertEquals(100, matchingIngredient.get().getAmount(), 0.01);

            matchingIngredient = findMatchingIngredient("makaron", ingredientsList);
            assertTrue(matchingIngredient.isPresent());
            assertEquals("gramy", matchingIngredient.get().getUnit());
            assertEquals(400, matchingIngredient.get().getAmount(), 0.01);
        }
    }

    private Optional<Ingredient> findMatchingIngredient(String name, List<Ingredient> ingredients) {
        return ingredients.stream()
                .filter(ingredient -> name.equals(ingredient.getName()))
                .findFirst();
    }
}