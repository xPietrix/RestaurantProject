package edu.pwap.pp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.listeners.DishCategorySelectedListener;
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.services.DishService;

public class GetAllDishesActivity extends AppCompatActivity
{
    private static TextView textViewAllDishesList;
    private static Spinner dishCategorySpinner;
    private DishCategorySelectedListener listener;
    private Button showDishesWithCategoryButton;
    private DishService dishService;
    private List<Dish> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dishes);

        dishCategorySpinner = findViewById(R.id.spinnerDishCategory);
        textViewAllDishesList = findViewById(R.id.textViewAllDishesList);
        showDishesWithCategoryButton = findViewById(R.id.showDishesWithCategoryButton);

        listener = new DishCategorySelectedListener();

        addItemsOnSpinner();
        setAddButtonListener();
        addListenerOnSpinnerItemSelection();
    }

    private void addListenerOnSpinnerItemSelection()
    {
        dishCategorySpinner.setOnItemSelectedListener(listener);
    }

    private void addItemsOnSpinner()
    {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dishCategorySpinner.setAdapter(dataAdapter);
    }

    private void setAddButtonListener()
    {
        showDishesWithCategoryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String selectedCategory = listener.getSelectedItem();
                getDishesWithCategory(selectedCategory);
            }
        });
    }

    private void getDishesWithCategory(String dishCategoryId)
    {
        DishApi api = RetrofitInitializer.getClient().create(DishApi.class);
        dishService = new DishService(new DishRepository());
        dishList = dishService.getDishesWithCategory(dishCategoryId, api);
    }

    public static void changeTextView(List<Dish> list)
    {
        textViewAllDishesList.setText(StringProvider.dishesWithCategoryString(list));
    }
}
