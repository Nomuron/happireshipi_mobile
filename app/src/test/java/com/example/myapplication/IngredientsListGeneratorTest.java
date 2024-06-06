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

import static org.junit.Assert.assertEquals;
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
    public void testGenerateIngredientsList() {
        CartItem cartItem1 = new CartItem("Spaghetti", 2);
        CartItem cartItem2 = new CartItem("Sałatka", 1);
        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getName()).thenReturn("pomidor");
        when(ingredient1.getUnit()).thenReturn("kawałki");
        when(ingredient1.getAmount()).thenReturn(2.0);

        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getName()).thenReturn("makaron");
        when(ingredient2.getUnit()).thenReturn("gramy");
        when(ingredient2.getAmount()).thenReturn(200.0);

        Ingredient ingredient3 = mock(Ingredient.class);
        when(ingredient3.getName()).thenReturn("sałata");
        when(ingredient3.getUnit()).thenReturn("gramy");
        when(ingredient3.getAmount()).thenReturn(100.0);

        Recipe recipe1 = mock(Recipe.class);
        when(recipe1.getName()).thenReturn("Spaghetti");
        when(recipe1.getIngredients()).thenReturn(Arrays.asList(ingredient1, ingredient2));

        Recipe recipe2 = mock(Recipe.class);
        when(recipe2.getName()).thenReturn("Sałatka");
        when(recipe2.getIngredients()).thenReturn(Arrays.asList(ingredient3));

        try (MockedStatic<RecipesProvider> mockedStatic = Mockito.mockStatic(RecipesProvider.class)) {
            mockedStatic.when(() -> RecipesProvider.getRecipes(resources)).thenReturn(Arrays.asList(recipe1, recipe2));

            List<List<String>> ingredientsList = generator.generateIngredientsList(cartItems);

            assertEquals(3, ingredientsList.size());

            assertEquals("pomidor", ingredientsList.get(0).get(0));
            assertEquals("kawałki", ingredientsList.get(0).get(1));
            assertEquals("2.0", ingredientsList.get(0).get(2));

            assertEquals("sałata", ingredientsList.get(1).get(0));
            assertEquals("gramy", ingredientsList.get(1).get(1));
            assertEquals("100.0", ingredientsList.get(1).get(2));

            assertEquals("makaron", ingredientsList.get(2).get(0));
            assertEquals("gramy", ingredientsList.get(2).get(1));
            assertEquals("200.0", ingredientsList.get(2).get(2));
        }
    }

    @Test
    public void testCombineIngredients() {
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("pomidor", "kawałki", "2.0"),
                Arrays.asList("pomidor", "kawałki", "3.0"),
                Arrays.asList("makaron", "gramy", "200.0"),
                Arrays.asList("sałata", "gramy", "100.0"),
                Arrays.asList("makaron", "gramy", "100.0")
        );

        List<List<String>> combinedIngredients = IngredientsListGenerator.combineIngredients(ingredients);

        assertEquals(3, combinedIngredients.size());

        assertEquals("pomidor", combinedIngredients.get(0).get(0));
        assertEquals("kawałki", combinedIngredients.get(0).get(1));
        assertEquals("5.0", combinedIngredients.get(0).get(2));

        assertEquals("sałata", combinedIngredients.get(1).get(0));
        assertEquals("gramy", combinedIngredients.get(1).get(1));
        assertEquals("100.0", combinedIngredients.get(1).get(2));

        assertEquals("makaron", combinedIngredients.get(2).get(0));
        assertEquals("gramy", combinedIngredients.get(2).get(1));
        assertEquals("300.0", combinedIngredients.get(2).get(2));
    }
}