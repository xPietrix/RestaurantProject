package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.activities.GetAllDishesActivity;
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.models.Dish;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DishRepository
{
    private List<Dish> dishesList;

    public List<Dish> getDishesWithCategory(String id, DishApi api)
    {
        api.getDishesWithCategory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Dish>>()
                {
                    @Override
                    public void onSuccess(List<Dish> categories)
                    {
                        dishesList = new ArrayList<>(categories);
                        GetAllDishesActivity.changeTextView(dishesList);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ALL DISH CATEGORIES METHOD!");
                    }
                });

        return dishesList;
    }

    public void getDishWithId(String id)
    {
        //TODO
    }

    public void addDishToDatabase(Dish dish, DishApi api)
    {
        api.addDishToDatabase(dish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Dish>()
                {
                    @Override
                    public void onSuccess(Dish dish)
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ALL DISH CATEGORIES METHOD!");
                    }
                });
    }
}
