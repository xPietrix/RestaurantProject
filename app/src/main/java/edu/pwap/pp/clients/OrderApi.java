package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi
{
    @POST("order/add")
    Call<Order> addOrder(@Body Order order);

    @GET("order/prepare")
    Call<List<Order>> getOrdersToPrepare();

    @GET("order/delivery")
    Call<List<Order>> getOrdersToDeliver();

    @POST("order/prepare/{id}")
    Call<Integer> addOrderToPrepare(@Path("id") String id);

    @POST("order/delivery/{id}")
    Call<Integer> addOrderToDeliver(@Path("id") String id);
}
