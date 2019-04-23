package edu.pwap.pp.dataGetters;

import java.util.List;

import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.User;
import retrofit2.Call;
import retrofit2.Callback;

public class ConnectionInitializer
{
    private GetEverything getEverything;

    public ConnectionInitializer()
    {
                                /* HOME ADDRESS */
        this.getEverything = new GetEverything("http:/192.168.1.101:8080/");

                             /* UNIVERSITY ADDRESS */
        //this.getEverything = new GetEverything("http:/192.168.43.79:8080/");
    }

    public Callback<User> setUserCallback()
    {
        Callback<User> callback = new CustomCallback();
        return callback;
    }

    public Callback<DishCategory> setAddDishCategoryCallback()
    {
        Callback<DishCategory> callback = new CustomCallback();
        return callback;
    }

    public Callback<List<DishCategory>> setGetDishCategoriesCallback()
    {
        Callback<List<DishCategory>> callback = new CustomCallback();
        return callback;
    }

    public Callback<Dish> setAddDishCallback()
    {
        Callback<Dish> callback = new CustomCallback();
        return callback;
    }

    public Callback<DishCategory> setGetDishCategoryCallback()
    {
        GetDishCategoryCallback callback = new GetDishCategoryCallback();
        return callback;
    }


    public Call<User> getAddUserCall(String role, User user)
    {
        return this.getEverything.setUserApi().addUser(role, user);
    }

    public Call<User> getLoginCall(User user)
    {
        return this.getEverything.setUserApi().loginUser(user);
    }

    public Call<DishCategory> getAddDishCategoryCall(DishCategory dishCategory)
    {
        return this.getEverything.setDishCategoryApi().addDishCategory(dishCategory);
    }

    public Call<List<DishCategory>> getDishCategoriesCall()
    {
        return this.getEverything.setDishCategoryApi().getDishCategories();
    }

    public Call<DishCategory> getDishCategoryCall(long id)
    {
        return this.getEverything.setDishCategoryApi().getDishCategory(id);
    }

    public Call<Dish> getAddDishCall(Dish dish)
    {
        return this.getEverything.setDishApi().addDish(dish);
    }
}
