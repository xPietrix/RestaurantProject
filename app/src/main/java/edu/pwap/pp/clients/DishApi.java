package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.Dish;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DishApi
{
    @GET("dish/category/{id}")
    Call<List<Dish>> getDishWithCategory(@Path("id") String id);

    @GET("dish/{id}")
    Call<Dish> getDishWithId(@Path("id") String id);

    @POST("dish/add")
    Call<Dish> addDish(@Body Dish dish);
}
