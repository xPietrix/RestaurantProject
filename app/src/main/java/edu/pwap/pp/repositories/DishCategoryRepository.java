package edu.pwap.pp.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.activities.GetCategoriesActivity;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.models.DishCategory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DishCategoryRepository
{
    private List<DishCategory> dishCategoriesList;

    public List<DishCategory> getAllDishCategories(DishCategoryApi service)
    {
        service.getAllDishCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DisposableSingleObserver<List<DishCategory>>()
            {
                @Override
                public void onSuccess(List<DishCategory> categories)
                {
                    dishCategoriesList = new ArrayList<>(categories);
                    GetCategoriesActivity.changeTextView(dishCategoriesList);
                }

                @Override
                public void onError(Throwable e)
                {
                    Log.d("ERROR", "ERROR WITH GET ALL DISH CATEGORIES METHOD!");
                }
            });

        return dishCategoriesList;
    }

    public void addDishCategoryToDatabase(DishCategory dishCategory, DishCategoryApi api)
    {
        api.addDishCategoryToDatabase(dishCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<DishCategory>()
                {
                    @Override
                    public void onSuccess(DishCategory categories)
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
