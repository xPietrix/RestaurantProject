package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.DishCategory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DishCategoryApi
{
    @GET("dishcategory/all")
    Call<List<DishCategory>> getDishCategories();

    @GET("dishcategory/{id}")
    Call<DishCategory> getDishCategory(@Path("id") String id);

    @POST("dishcategory/add")
    Call<DishCategory> addDishCategory(@Body DishCategory dishCategory);
}
