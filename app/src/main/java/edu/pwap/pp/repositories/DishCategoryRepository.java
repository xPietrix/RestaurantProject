package edu.pwap.pp.repositories;

import java.util.List;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.User;
import retrofit2.Call;
import retrofit2.Callback;

public class DishCategoryRepository
{
    private ConnectionInitializer connectionInitializer;

    public DishCategoryRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public String getDishCategories()
    {
        String content = "";
        Call<List<DishCategory>> call = connectionInitializer.getDishCategories();
        Callback<List<DishCategory>> dishCategoriesCallback = connectionInitializer.setGetDishCategoriesCallback();
        call.enqueue(dishCategoriesCallback);
        content = connectionInitializer.getContent();
        return content;
    }

    public void getDishCategory(long id)
    {
        //TODO
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        Call<DishCategory> call = connectionInitializer.addDishCategory(dishCategory);
        Callback<DishCategory> loginCallback = connectionInitializer.setAddDishCategoryCallback();
        call.enqueue(loginCallback);
    }
}
