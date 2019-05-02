package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.activities.GetAllDishesActivity;
import edu.pwap.pp.activities.MakeOrderActivity;
import edu.pwap.pp.activities.ShowDishMenuActivity;
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.models.Dish;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DishRepository
{
    private List<Dish> dishesList;
    private Dish dishToReturn;

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

    public List<Dish> getDishesToOrderWithCategory(String id, DishApi api)
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
                        ShowDishMenuActivity.changeTextView(dishesList);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ALL DISH CATEGORIES METHOD!");
                    }
                });

        return dishesList;
    }

    public void getDishWithId(String id, DishApi api)
    {
        api.getDishWithId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Dish>()
                {
                    @Override
                    public void onSuccess(Dish dish)
                    {
                        dishToReturn = dish;
                        MakeOrderActivity.changeCustomerChosenDishesList(dishToReturn);
                        MakeOrderActivity.setAddOrderButtonListener(dish);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET DISH WITH CATEGORY METHOD!");
                    }
                });
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
                        Log.d("ERROR", "ERROR WITH ADD DISH TO DATABASE METHOD!");
                    }
                });
    }
}
