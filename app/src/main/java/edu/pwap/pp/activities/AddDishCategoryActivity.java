package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.pwap.pp.R;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;

public class AddDishCategoryActivity extends AppCompatActivity
{
    private EditText nameText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish_category);

        nameText = findViewById(R.id.editTextDishCategoryName);
        addButton = findViewById(R.id.buttonAddDishCategory);

        setAddButtonListener();
    }

    private void setAddButtonListener()
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = nameText.getText().toString();
                DishCategory dishCategory = new DishCategory(name);
                addDishCategoryToDatabase(dishCategory);

                Toast.makeText(v.getContext(),"Dish category added to database", Toast.LENGTH_SHORT).show();
                nameText.setText("");
            }
        });
    }

    private void addDishCategoryToDatabase(DishCategory dishCategory)
    {
        DishCategoryApi api = RetrofitInitializer.getClient().create(DishCategoryApi.class);
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        dishCategoryService.addDishCategoryToDatabase(dishCategory, api);
    }
}
