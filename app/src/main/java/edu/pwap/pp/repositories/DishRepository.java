package edu.pwap.pp.repositories;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import retrofit2.Call;
import retrofit2.Callback;

public class DishRepository
{
    private ConnectionInitializer connectionInitializer;

    public DishRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public void getDishWithCategory(String id)
    {
        //TODO
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
