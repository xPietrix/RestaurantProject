package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;
import retrofit2.Response;

public class AddDishCategoryActivity extends AppCompatActivity
{
    private TextView addingDishCategoryTextView;
    private TextView dishCategoryIdTextView;
    private TextView dishCategoryNameTextView;
    private EditText idText;
    private EditText nameText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish_category);

        addingDishCategoryTextView = findViewById(R.id.textViewAddingDishCategory);
        dishCategoryIdTextView = findViewById(R.id.textViewDishCategoryId);
        dishCategoryNameTextView = findViewById(R.id.textViewDishCategoryName);
        idText = findViewById(R.id.editTextDishCategoryId);
        nameText = findViewById(R.id.editTextDishCategoryName);
        addButton = findViewById(R.id.buttonAddDishCategory);

        setAddButtonListener();
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        dishCategoryService.addDishCategory(dishCategory);
    }


    public void showNoResponseError(Response<DishCategory> response)
    {
        Toast.makeText(AddDishCategoryActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
    }

    public void showOnFailureError(Throwable t)
    {
        Toast.makeText(AddDishCategoryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showSuccessfulCall()
    {
        Toast.makeText(AddDishCategoryActivity.this, "Successful call", Toast.LENGTH_SHORT).show();
    }

    public void setAddButtonListener()
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long id = idText.getId();
                String name = nameText.getText().toString();
                DishCategory dishCategory = new DishCategory(id, name);
                addDishCategory(dishCategory);
            }
        });
    }
}
