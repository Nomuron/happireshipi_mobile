package com.example.myapplication;

import android.content.res.Resources;
import android.os.Bundle;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDishesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipesAdapter adapter = new RecipesAdapter(this, recipes);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}