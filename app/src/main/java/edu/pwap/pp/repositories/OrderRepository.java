package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.activities.GetAllOrdersToDeliverActivity;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderRepository
{
    private List<Order> ordersToDeliverList;

    public void addOrder(Order order)
    {
        //TODO
    }

    public void addOrderToPrepare(String id)
    {
        //TODO
    }

    public void deliverOrder(long id, OrderApi api)
    {
        api.deliverOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Order>()
                {
                    @Override
                    public void onSuccess(Order order)
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH DELIVER ORDER METHOD!");
                    }
                });
    }

    public void getOrdersToPrepare()
    {
        //TODO
    }

    public void getOrdersToDeliver(OrderApi api)
    {
        api.getOrdersToDeliver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Order>>()
                {
                    @Override
                    public void onSuccess(List<Order> ordersToDeliver)
                    {
                        ordersToDeliverList = new ArrayList<>(ordersToDeliver);
                        GetAllOrdersToDeliverActivity.changeTextView(ordersToDeliverList);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ORDERS TO DELIVER METHOD!");
                    }
                });
    }
}
