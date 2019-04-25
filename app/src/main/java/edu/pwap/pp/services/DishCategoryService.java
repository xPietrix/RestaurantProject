package edu.pwap.pp.services;

import java.util.List;

import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import retrofit2.Call;
import retrofit2.Response;

public class DishCategoryService
{
    private DishCategoryRepository dishCategoryRepository;

    public DishCategoryService(DishCategoryRepository dishCategoryRepository)
    {
        this.dishCategoryRepository = dishCategoryRepository;
    }

    public String getDishCategoriesString(Response<List<DishCategory>> response)
    {
        return this.dishCategoryRepository.getDishCategoriesString(response);
    }

    public Call<List<DishCategory>> getAllDishCategories()
    {
        return this.dishCategoryRepository.getAllDishCategories();
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
