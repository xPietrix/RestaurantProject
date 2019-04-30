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

    public List<Dish> getDishesWithCategory(String id, DishApi service)
    {
        return this.dishRepository.getDishesWithCategory(id, service);
    }

    public void getDishWithId(String id)
    {
        this.dishRepository.getDishWithId(id);
    }

    public void addDishToDatabase(Dish dish, DishApi api)
    {
        this.dishRepository.addDishToDatabase(dish, api);
    }
}
