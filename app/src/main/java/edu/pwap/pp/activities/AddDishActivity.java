package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.pwap.pp.R;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.services.DishService;

public class AddDishActivity extends AppCompatActivity
{
    private EditText dishNameText;
    private EditText dishPriceText;
    private EditText estimatedPrepTimeText;
    private EditText dishCategoryIdText;
    private EditText dishCategoryNameText;
    private Button addDishToDBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        addDishToDBButton = findViewById(R.id.buttonAddDishToDB);
        dishNameText = findViewById(R.id.editTextDishName);
        dishPriceText = findViewById(R.id.editTextDishPrice);
        estimatedPrepTimeText = findViewById(R.id.editTextEstimatedPrepTime);
        dishCategoryIdText = findViewById(R.id.editTextDishCategoryId);
        dishCategoryNameText = findViewById(R.id.editTextDishCategoryName);

        setAddDishButtonListener();
    }

    public void addDish(Dish dish)
    {
        DishService dishService = new DishService(new DishRepository());
        dishService.addDish(dish);
    }

    public void setAddDishButtonListener()
    {
        addDishToDBButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = dishNameText.getText().toString();
                double price = Double.parseDouble(dishPriceText.getText().toString());
                long estimatedPrepTime = Long.parseLong(estimatedPrepTimeText.getText().toString());
                long categoryId = Long.parseLong(dishCategoryIdText.getText().toString());
                String categoryName = dishCategoryNameText.getText().toString();

                DishCategory dishCategory = new DishCategory(categoryId, categoryName);
                Dish dish = new Dish(name, price, estimatedPrepTime, dishCategory);
                addDish(dish);

                Toast.makeText(v.getContext(),"Dish added to database", Toast.LENGTH_SHORT).show();
                dishNameText.setText("");
                dishPriceText.setText("");
                estimatedPrepTimeText.setText("");
                dishCategoryIdText.setText("");
                dishCategoryNameText.setText("");
            }
        });
    }


    /*
    public void getDishCategoryWithId(long id)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        //getEverything = new GetEverything("http:/192.168.43.79:8080/");
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        Call<DishCategory> call = dishCategoryService.getDishCategory(id);
        call.enqueue(new Callback<DishCategory>()
        {
            @Override
            public void onResponse(Call<DishCategory> call, Response<DishCategory> response)
            {
                if(!response.isSuccessful())
                {
                    textViewAddingDish.setText("Code: " + response.code());
                    return;
                }

                DishCategory category = response.body();

                category = new DishCategory(category.getId(), category.getCategoryName());
                setCategory(category);
            }

            @Override
            public void onFailure(Call<DishCategory> call, Throwable t)
            {
                textViewAddingDish.setText(t.getMessage());
            }
        });
    }
    */

}
