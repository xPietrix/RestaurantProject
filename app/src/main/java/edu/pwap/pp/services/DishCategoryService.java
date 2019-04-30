package edu.pwap.pp.services;

import java.util.List;

import edu.pwap.pp.clients.DishCategoryApi;
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

    public List<DishCategory> getAllDishCategories(DishCategoryApi service)
    {
        return this.dishCategoryRepository.getAllDishCategories(service);
    }

    public Call<DishCategory> getDishCategory(long id)
    {
        return this.dishCategoryRepository.getDishCategory(id);
    }

    public void addDishCategoryToDatabase(DishCategory dishCategory, DishCategoryApi api)
    {
        this.dishCategoryRepository.addDishCategoryToDatabase(dishCategory, api);
    }
}
