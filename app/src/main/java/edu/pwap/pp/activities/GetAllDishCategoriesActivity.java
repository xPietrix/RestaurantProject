package edu.pwap.pp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;

public class GetAllDishCategoriesActivity extends AppCompatActivity {

    private static TextView textViewAllDishCategories;
    private DishCategoryService dishCategoryService;
    private List<DishCategory> dishCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dish_categories);
        textViewAllDishCategories = findViewById(R.id.textViewDishCategoriesList);

        getAllDishCategories();
    }

    private void getAllDishCategories()
    {
        DishCategoryApi service = RetrofitInitializer.getClient().create(DishCategoryApi.class);
        dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        dishCategoriesList = dishCategoryService.getAllDishCategories(service);
    }

    public static void changeTextView(List<DishCategory> list)
    {
        textViewAllDishCategories.append(StringProvider.allDishCategoriesString(list));
    }
}
