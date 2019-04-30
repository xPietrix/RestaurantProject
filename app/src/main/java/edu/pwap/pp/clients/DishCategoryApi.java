package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.DishCategory;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DishCategoryApi
{
    @GET("dishcategory/{id}")
    Call<DishCategory> getDishCategory(@Path("id") long id);

    @GET("dishcategory/all")
    Single<List<DishCategory>> getAllDishCategories();

    @POST("dishcategory/add")
    Single<DishCategory> addDishCategoryToDatabase(@Body DishCategory dishCategory);
}
