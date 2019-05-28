package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.List;

import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import io.reactivex.Single;
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

    public Single<Order> deliverOrder(long id, OrderApi api)
    {
        return api.deliverOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Order> prepareOrder(long id, final OrderApi api)
    {
        return api.prepareOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }
}
