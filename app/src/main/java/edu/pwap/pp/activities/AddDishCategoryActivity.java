package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.pwap.pp.R;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;

public class AddDishCategoryActivity extends AppCompatActivity
{
    private TextView addingDishCategoryTextView;
    private TextView dishCategoryNameTextView;
    private EditText nameText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish_category);

        addingDishCategoryTextView = findViewById(R.id.textViewAddingDishCategory);
        dishCategoryNameTextView = findViewById(R.id.textViewDishCategoryName);
        nameText = findViewById(R.id.editTextDishCategoryName);
        addButton = findViewById(R.id.buttonAddDishCategory);

        setAddButtonListener();
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        dishCategoryService.addDishCategory(dishCategory);
    }

    public void setAddButtonListener()
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = nameText.getText().toString();
                DishCategory dishCategory = new DishCategory(name);
                addDishCategory(dishCategory);

                Toast.makeText(v.getContext(),"Dish category added to database", Toast.LENGTH_SHORT).show();
                nameText.setText("");
            }
        });
    }
}
