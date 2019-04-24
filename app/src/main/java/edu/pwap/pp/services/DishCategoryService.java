package edu.pwap.pp.services;

import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import retrofit2.Call;

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

    public Call<DishCategory> getDishCategory(long id)
    {
        return this.dishCategoryRepository.getDishCategory(id);
    }

    public void addDishCategory(DishCategory dishCategory)
    {
        this.dishCategoryRepository.addDishCategory(dishCategory);
    }
}
