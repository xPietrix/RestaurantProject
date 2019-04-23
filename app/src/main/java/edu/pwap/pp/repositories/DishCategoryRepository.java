package edu.pwap.pp.repositories;

import java.util.List;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.dataGetters.GetDishCategoryCallback;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishCategoryRepository
{
    private ConnectionInitializer connectionInitializer;
    private DishCategory dishCategory;

    public DishCategoryRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public String getDishCategories()
    {
        String content = "";
        Call<List<DishCategory>> call = connectionInitializer.getDishCategoriesCall();
        Callback<List<DishCategory>> dishCategoriesCallback = connectionInitializer.setGetDishCategoriesCallback();
        call.enqueue(dishCategoriesCallback);
        //content = connectionInitializer.getContent();
        return content;
    }

    public Call<DishCategory> getDishCategory(long id)
    {
        Call<DishCategory> call = connectionInitializer.getDishCategoryCall(id);
        //Callback<DishCategory> dishCategoryCallback = connectionInitializer.setGetDishCategoryCallback();
        //Callback<DishCategory> callback = connectionInitializer.setGetDishCategoryCallback();

        return call;
    }

    public void setDishCategory(DishCategory dishCategory)
    {
        this.dishCategory = dishCategory;
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        Call<DishCategory> call = connectionInitializer.getAddDishCategoryCall(dishCategory);
        Callback<DishCategory> dishCategoryCallback = connectionInitializer.setAddDishCategoryCallback();
        call.enqueue(dishCategoryCallback);
    }
}
