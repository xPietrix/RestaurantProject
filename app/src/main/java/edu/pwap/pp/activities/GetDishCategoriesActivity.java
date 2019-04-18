package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;
import retrofit2.Response;

public class GetDishCategoriesActivity extends AppCompatActivity
{
    private TextView tvGetCategories;
    private TextView tvGetCategoriesList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_dish_categories);

        tvGetCategories = findViewById(R.id.textViewGetDishCategories);
        tvGetCategoriesList = findViewById(R.id.tVTextViewCategoriesList);

        String list = getDishCategoriesList();
        tvGetCategoriesList.setText(list);

        button = findViewById(R.id.getDishCategoriesButton);
    }

    public String getDishCategoriesList()
    {
        String content = "";
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        content = content + dishCategoryService.getDishCategories();
        return content;
    }

    public void showNoResponseError(Response<List<DishCategory>> response)
    {
        Toast.makeText(GetDishCategoriesActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
    }

    public void showOnFailureError(Throwable t)
    {
        Toast.makeText(GetDishCategoriesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showSuccessfulCall()
    {
        Toast.makeText(GetDishCategoriesActivity.this, "Successful call", Toast.LENGTH_SHORT).show();
    }
}
