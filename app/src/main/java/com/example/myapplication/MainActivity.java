package com.example.myapplication;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recipes.adapters.RecipesAdapter;
import com.example.myapplication.recipes.control.RecipesProvider;
import com.example.myapplication.recipes.entity.Recipe;

import java.util.List;
import java.util.Objects;

/**
 * Class used to load home page of HappiReshipi app
 *
 * @author Alicja Szczypior
 */
public class MainActivity extends AppCompatActivity {
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
        List<Recipe> recipes = RecipesProvider.getRecipes(resources);

        RecyclerView dishesListRecyclerView = findViewById(R.id.recyclerViewDishesList);
        dishesListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipesAdapter adapter = new RecipesAdapter(this, recipes);
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
}