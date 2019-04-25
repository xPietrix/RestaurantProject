package edu.pwap.pp.services;

import java.util.List;

import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishRepository;
import retrofit2.Call;
import retrofit2.Response;

public class DishService
{
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository)
    {
        this.dishRepository = dishRepository;
    }

    public Call<List<Dish>> getDishesWithCategory(String id)
    {
        return this.dishRepository.getDishesWithCategory(id);
    }

    public String getDishesWithCategoryString(Response<List<Dish>> response)
    {
        return this.dishRepository.getDishesWithCategoryString(response);
    }

    public void getDishWithId(String id)
    {
        this.dishRepository.getDishWithId(id);
    }

    public void addDish(Dish dish)
    {
        this.dishRepository.addDish(dish);
    }
}
