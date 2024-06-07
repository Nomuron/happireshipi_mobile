package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.recipes.adapters.SelectedRecipesAdapter;
import com.example.myapplication.recipes.adapters.SelectedRecipesIngredientsAdapter;

/**
 * {@link Fragment} subclass used for selected recipes view.
 *
 * @author Katarzyna Popieniuk, Patryk Klimek
 */
public class ChosenDishesTitleFragment extends Fragment {

    /**
     * Selected recipes RecyclerView Adapter
     */
    private SelectedRecipesAdapter selectedRecipesAdapter;
    private SelectedRecipesIngredientsAdapter selectedRecipesIngredientsAdapter;

    public ChosenDishesTitleFragment() {
        // Required empty public constructor
    }

    /**
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chosen_dishes_title, container, false);
        RecyclerView selectedDishesListRecyclerView = view.findViewById(R.id.recyclerViewSelectedRecipesList);
        selectedDishesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        selectedRecipesAdapter = new SelectedRecipesAdapter(getContext());
        selectedDishesListRecyclerView.setAdapter(selectedRecipesAdapter);

        RecyclerView selectedRecipesIngredientsList = view.findViewById(R.id.recyclerViewSelectedRecipesIngredientsList);
        selectedRecipesIngredientsList.setLayoutManager(new LinearLayoutManager(getContext()));

        selectedRecipesIngredientsAdapter = new SelectedRecipesIngredientsAdapter(getContext());
        selectedRecipesIngredientsList.setAdapter(selectedRecipesIngredientsAdapter);
        return view;
    }

    /**
     * Notifies selected recipes RecyclerView Adapter that dataset changed
     */
    @SuppressLint("NotifyDataSetChanged")
    public void notifyDataSetChanged() {
        selectedRecipesAdapter.notifyDataSetChanged();
        selectedRecipesIngredientsAdapter.notifyDataSetChanged();
    }
}