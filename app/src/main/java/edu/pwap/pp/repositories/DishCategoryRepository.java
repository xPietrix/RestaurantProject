package edu.pwap.pp.repositories;

import java.util.List;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishCategoryRepository
{
    private ConnectionInitializer connectionInitializer;

    public DishCategoryRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public String getDishCategoriesString(Response<List<DishCategory>> response)
    {
        List<DishCategory> dishCategories = response.body();

        String content = "";

        content = content + "\n";
        for(DishCategory dishCategory: dishCategories)
        {
            content = content + "ID: " + dishCategory.getId() + "\n";
            content = content + "Dish category name: " + dishCategory.getCategoryName() + "\n" + "\n";
        }
        content = content + "\n";

        return content;
    }

    public Call<DishCategory> getDishCategory(long id)
    {
        Call<DishCategory> call = connectionInitializer.getDishCategoryCall(id);

        return call;
    }

    public Call<List<DishCategory>> getAllDishCategories()
    {
        Call<List<DishCategory>> call = connectionInitializer.getDishCategoriesCall();
        return call;
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        Call<DishCategory> call = connectionInitializer.getAddDishCategoryCall(dishCategory);
        Callback<DishCategory> dishCategoryCallback = connectionInitializer.setAddDishCategoryCallback();
        call.enqueue(dishCategoryCallback);
    }
}
