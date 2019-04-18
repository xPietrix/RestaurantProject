package edu.pwap.pp.services;

import edu.pwap.pp.models.Dish;
import edu.pwap.pp.repositories.DishRepository;

public class DishService
{
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository)
    {
        this.dishRepository = dishRepository;
    }

    public void getDishWithCategory(String id)
    {
        this.dishRepository.getDishWithCategory(id);
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
