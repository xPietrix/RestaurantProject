package edu.pwap.pp.services;

import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;

public class DishCategoryService
{
    private DishCategoryRepository dishCategoryRepository;

    public DishCategoryService(DishCategoryRepository dishCategoryRepository)
    {
        this.dishCategoryRepository = dishCategoryRepository;
    }

    public String getDishCategories()
    {
        return this.dishCategoryRepository.getDishCategories();
    }

    public void getDishCategory(long id)
    {
        this.dishCategoryRepository.getDishCategory(id);
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        this.dishCategoryRepository.addDishCategory(dishCategory);
    }
}
