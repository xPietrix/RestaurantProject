package edu.pwap.pp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class ShowDishMenuActivity extends AppCompatActivity
{
    private static TextView tVAllDishesToOrder;
    private Spinner dishCategorySpinner;
    private DishCategorySelectedListener listener;
    private Button showDishesButton;
    private Button openMakeOrderViewButton;
    private List<Dish> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish_menu);

        dishCategorySpinner = findViewById(R.id.spinnerDishCategory);
        showDishesButton = findViewById(R.id.showDishesToOrderWithCategoryButton);
        tVAllDishesToOrder = findViewById(R.id.textViewAllDishesToOrderList);
        openMakeOrderViewButton = findViewById(R.id.buttonOpenMakeOrderView);

        listener = new DishCategorySelectedListener();

        addItemsOnSpinner();
        setShowDishesButtonListener();
        setOpenMakeOrderViewButtonListener();
        addListenerOnSpinnerItemSelection();
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

    private void setShowDishesButtonListener()
    {
        showDishesButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String selectedCategory = listener.getSelectedItem();
                getDishesWithCategory(selectedCategory);
            }
        });
    }

    private void addListenerOnSpinnerItemSelection()
    {
        dishCategorySpinner.setOnItemSelectedListener(listener);
    }

    private void setOpenMakeOrderViewButtonListener()
    {
        openMakeOrderViewButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openMakeOrderActivity();
            }
        });
    }

    private void getDishesWithCategory(String dishCategoryId)
    {
        DishApi api = RetrofitInitializer.getClient().create(DishApi.class);
        DishService dishService = new DishService(new DishRepository());
        dishList = dishService.getDishesToOrderWithCategory(dishCategoryId, api);
    }

    public static void changeTextView(List<Dish> list)
    {
        tVAllDishesToOrder.setText(StringProvider.dishesWithCategoryString(list));
    }

    private void openMakeOrderActivity()
    {
        Intent intent = new Intent(this, MakeOrderActivity.class);
        startActivity(intent);
    }
}