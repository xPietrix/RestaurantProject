package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.Dish;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DishApi
{
    @GET("dish/{id}")
    Call<Dish> getDishWithId(@Path("id") String id);

    @GET("dish/category/{id}")
    Single<List<Dish>> getDishesWithCategory(@Path("id") String id);

    @POST("dish/add")
    Single<Dish> addDishToDatabase(@Body Dish dish);
}
