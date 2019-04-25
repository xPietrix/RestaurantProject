package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.services.DishCategoryService;
import edu.pwap.pp.services.DishService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllDishCategoriesActivity extends AppCompatActivity
{
    private TextView textViewAllDishCategories;
    private DishCategoryService dishCategoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dish_categories);
        textViewAllDishCategories = findViewById(R.id.textViewDishCategoriesList);

        getAllDishCategories();
    }

    public void getAllDishCategories()
    {
        dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        Call<List<DishCategory>> call = dishCategoryService.getAllDishCategories();

        call.enqueue(new Callback<List<DishCategory>>()
        {
            @Override
            public void onResponse(Call<List<DishCategory>> call, Response<List<DishCategory>> response)
            {
                if(!response.isSuccessful())
                {
                    textViewAllDishCategories.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = dishCategoryService.getDishCategoriesString(response);
                    textViewAllDishCategories.setText(content);
                }
            }

            @Override
            public void onFailure(Call<List<DishCategory>> call, Throwable t)
            {
                textViewAllDishCategories.setText(t.getMessage());
            }
        });
    }
}
