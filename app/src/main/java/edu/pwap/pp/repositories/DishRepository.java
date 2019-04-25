package edu.pwap.pp.repositories;

import java.util.List;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishRepository
{
    private ConnectionInitializer connectionInitializer;

    public DishRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public Call<List<Dish>> getDishesWithCategory(String id)
    {
        Call<List<Dish>> call = connectionInitializer.getGetDishesWithCategoryCall(id);
        return call;
    }

    public String getDishesWithCategoryString(Response<List<Dish>> response)
    {
        List<Dish> dishes = response.body();

        String content = "";
        content = content + "Dish category name: " + dishes.get(0).getDishCategory().getCategoryName() + "\n" + "\n";
        for(Dish dish: dishes)
        {
            content = content + "ID: " + dish.getId() + "\n";
            content = content + "Dish name: " + dish.getDishName() + "\n";
            content = content + "Dish price: " + dish.getDishPrice() + "\n";
            content = content + "Estimated preparation time: " + dish.getEstimatedPreparationTime() + "\n";
            content = content + "Dish category id: " + dish.getDishCategory().getId() + "\n" + "\n";
        }
        content = content + "\n";

        return content;
    }

    public void getDishWithId(String id)
    {
        //TODO
    }

    public void addDish(Dish dish)
    {
        Call<Dish> call = connectionInitializer.getAddDishCall(dish);
        Callback<Dish> dishCallback = connectionInitializer.setAddDishCallback();
        call.enqueue(dishCallback);
    }
}
