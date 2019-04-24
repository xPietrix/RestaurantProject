package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import edu.pwap.pp.R;

public class DieticianViewActivity extends AppCompatActivity
{
    private Button addDishCategoryButton;
    private Button addDishButton;
    private Button getAllDishesButton;
    private Button getAllDishCategoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietician_view);

        addDishCategoryButton = findViewById(R.id.addDishCategoryButton);
        addDishButton = findViewById(R.id.addDishButton);
        getAllDishesButton = findViewById(R.id.getAllDishesButton);
        getAllDishCategoriesButton = findViewById(R.id.getAllDishCategoriesButton);

        setAddDishCategoryButtonListener();
        setAddDishButtonListener();
        setGetAllDishesButtonListener();
        setGetAllDishCategoriesButtonListener();
    }

    public void setAddDishCategoryButtonListener()
    {
        addDishCategoryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDishCategoryActivity();
            }
        });
    }

    public void setAddDishButtonListener()
    {
        addDishButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddDishActivity();
            }
        });
    }

    public void openAddDishCategoryActivity()
    {
        Intent intent = new Intent(this, AddDishCategoryActivity.class);
        startActivity(intent);
    }

    public void openAddDishActivity()
    {
        Intent intent = new Intent(this, AddDishActivity.class);
        startActivity(intent);
    }

    public void openGetAllDishesActivity()
    {
        Intent intent = new Intent(this, GetAllDishesActivity.class);
        startActivity(intent);
    }

    public void openGetAllDishCategoriesActivity()
    {
        Intent intent = new Intent(this, GetAllDishCategoriesActivity.class);
        startActivity(intent);
    }

    public void setGetAllDishesButtonListener()
    {
        getAllDishesButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGetAllDishesActivity();
            }
        });
    }

    public void setGetAllDishCategoriesButtonListener()
    {
        getAllDishCategoriesButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGetAllDishCategoriesActivity();
            }
        });
    }
}
