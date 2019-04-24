package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.listeners.DishCategorySelectedListener;
import edu.pwap.pp.dataGetters.GetEverything;
import edu.pwap.pp.models.Dish;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllDishesActivity extends AppCompatActivity
{
    private TextView textViewAllDishesList;
    private TextView textViewDishCategory;
    private Spinner dishCategorySpinner;
    private DishCategorySelectedListener listener;
    private GetEverything getEverything;
    private Button showDishesWithCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dishes);

        textViewDishCategory = findViewById(R.id.textViewChooseDishCategory);
        dishCategorySpinner = findViewById(R.id.spinnerDishCategory);
        textViewAllDishesList = findViewById(R.id.textViewAllDishesList);
        showDishesWithCategoryButton = findViewById(R.id.showDishesWithCategoryButton);

        listener = new DishCategorySelectedListener();

        addItemsOnSpinner();
        setAddButtonListener();
        addListenerOnSpinnerItemSelection();
    }

    public void getAllDishesWithCategory(String id)
    {
        getEverything = new GetEverything("http:/192.168.1.101:8080/");
        //getEverything = new GetEverything("http:/192.168.43.79:8080/");
        String dishCategoryId = id;
        Call<List<Dish>> call = getEverything.setDishApi().getDishWithCategory(dishCategoryId);
        call.enqueue(new Callback<List<Dish>>()
        {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response)
            {
                if(!response.isSuccessful())
                {
                    textViewAllDishesList.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getDishesWithCategoryString(response);
                    textViewAllDishesList.setText(content);
                }
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t)
            {
                textViewAllDishesList.setText(t.getMessage());
            }
        });
    }

    public void addListenerOnSpinnerItemSelection()
    {
        dishCategorySpinner.setOnItemSelectedListener(listener);
    }

    public void addItemsOnSpinner()
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

    public void setAddButtonListener()
    {
        showDishesWithCategoryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String selectedCategory = listener.getSelectedItem();
                getAllDishesWithCategory(selectedCategory);
            }
        });
    }
}
