package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.Order;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi
{
    @POST("order/add")
    Single<Order> addOrder(@Body Order order);

    @GET("order/prepare")
    Single<List<Order>> getOrdersToPrepare();

    @GET("order/delivery")
    Single<List<Order>> getOrdersToDeliver();

    @POST("order/prepare/{id}")
    Single<Integer> addOrderToPrepare(@Path("id") String id);

    @POST("order/delivery/{id}")
    Single<Order> deliverOrder(@Path("id") long id);

}
