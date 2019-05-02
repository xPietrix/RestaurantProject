package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.activities.GetOrdersToDeliverActivity;
import edu.pwap.pp.activities.GetOrdersToPrepareActivity;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderRepository
{
    private List<Order> ordersToDeliverList;
    private List<Order> ordersToPrepareList;

    public void addOrder(Order order, OrderApi api)
    {
        api.addOrder(order)
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
                        Log.d("ERROR", "ERROR WITH ADD ORDER METHOD!");
                    }
                });
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

    public void getOrdersToPrepare(OrderApi api)
    {
        api.getOrdersToPrepare()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Order>>()
                {
                    @Override
                    public void onSuccess(List<Order> ordersToPrepare)
                    {
                        ordersToPrepareList = new ArrayList<>(ordersToPrepare);
                        GetOrdersToPrepareActivity.changeTextView(ordersToPrepareList);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ORDERS TO DELIVER METHOD!");
                    }
                });
    }

    public void prepareOrder(long id, final OrderApi api)
    {
        api.prepareOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Order>()
                {
                    @Override
                    public void onSuccess(Order order)
                    {
                        //getOrdersToPrepare(api);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH DELIVER ORDER METHOD!");
                    }
                });
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
                        GetOrdersToDeliverActivity.changeTextView(ordersToDeliverList);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ORDERS TO DELIVER METHOD!");
                    }
                });
    }
}
