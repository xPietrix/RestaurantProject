package edu.pwap.pp.services;

import java.util.List;

import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.repositories.DishRepository;

public class DishService
{
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository)
    {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishesWithCategory(String id, DishApi api)
    {
        return this.dishRepository.getDishesWithCategory(id, api);
    }

    public List<Dish> getDishesToOrderWithCategory(String id, DishApi api)
    {
        return this.dishRepository.getDishesToOrderWithCategory(id, api);
    }

    public void getDishWithId(String id, DishApi api)
    {
        this.dishRepository.getDishWithId(id, api);
    }

    public void addDishToDatabase(Dish dish, DishApi api)
    {
        this.dishRepository.addDishToDatabase(dish, api);
    }
}
