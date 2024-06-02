package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recipes.adapters.RecipesAdapter;
import com.example.myapplication.recipes.adapters.RecipesIngredientsAdapter;
import com.example.myapplication.recipes.control.RecipesProvider;
import com.example.myapplication.recipes.entity.Recipe;
import com.example.myapplication.recipes.interfaces.RecipeOnClick;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Class used to load home page of HappiReshipi app
 *
 * @author Alicja Szczypior, Patryk Klimek, Katarzyna Popieniuk
 */
public class MainActivity extends AppCompatActivity implements RecipeOnClick {
    private List<Recipe> recipes;

    /**
     * Method called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data from savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();
        recipes = RecipesProvider.getRecipes(resources);

        RecyclerView dishesListRecyclerView = findViewById(R.id.recyclerViewDishesList);
        dishesListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipesAdapter adapter = new RecipesAdapter(this, recipes, this);
        dishesListRecyclerView.setAdapter(adapter);

        View selectedDishesListView = findViewById(R.id.fragmentContainerView);
        selectedDishesListView.setVisibility(View.INVISIBLE);

        findViewById(R.id.list_icon).setOnClickListener(v -> {
            dishesListRecyclerView.setVisibility(View.INVISIBLE);
            selectedDishesListView.setVisibility(View.VISIBLE);
            ChosenDishesTitleFragment selectedDishesListFragment = (ChosenDishesTitleFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
            Objects.requireNonNull(selectedDishesListFragment).notifyDataSetChanged();
        });

        findViewById(R.id.logo).setOnClickListener(v -> {
            dishesListRecyclerView.setVisibility(View.VISIBLE);
            selectedDishesListView.setVisibility(View.INVISIBLE);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Handles the click event on a recipe item, displaying its details in a dialog.
     *
     * @param view     The view within the RecyclerView that was clicked.
     * @param position The position of the clicked item in the adapter.
     */
    @Override
    public void onItemClick(View view, int position) {
        Recipe clickedItem = recipes.get(position);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dish_details, null);

        TextView dishDetailName = dialogView.findViewById(R.id.recipeDetailName);
        TextView dishDetailSteps = dialogView.findViewById(R.id.recipeDetailSteps);
        ImageView dishDetailImage = dialogView.findViewById(R.id.recipeDetailImage);
        Button dishDetailCloseButton = dialogView.findViewById(R.id.recipeDetailCloseButton);

        dishDetailName.setText(clickedItem.getName());

        try (InputStream stream =
                     getAssets().open(getString(R.string.images_directory) + clickedItem.getImageFileName())) {
            Drawable dishDetailImg = Drawable.createFromStream(stream, clickedItem.getImageFileName());
            dishDetailImage.setImageDrawable(dishDetailImg);

        } catch (IOException exception) {
            Log.e(clickedItem.getName(), getString(R.string.error_loading) + clickedItem.getImageFileName());
        }

        RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerViewDetailIngredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipesIngredientsAdapter adapter = new RecipesIngredientsAdapter(this, clickedItem.getIngredients());
        recyclerView.setAdapter(adapter);

        dishDetailSteps.setText(clickedItem.getInstruction());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        dishDetailCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}