package edu.pwap.pp.dataGetters;

import java.io.IOException;
import java.util.List;

import edu.pwap.pp.activities.AddDishCategoryActivity;
import edu.pwap.pp.activities.AddUserActivity;
import edu.pwap.pp.activities.GetDishCategoriesActivity;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.User;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionInitializer
{
    private GetEverything getEverything;
    private String content;

    public ConnectionInitializer()
    {
                                /* HOME ADDRESS */
        this.getEverything = new GetEverything("http:/192.168.1.101:8080/");

                             /* UNIVERSITY ADDRESS */
        //this.getEverything = new GetEverything("http:/192.168.43.79:8080/");
    }

    public Callback<User> setUserCallback()
    {
        Callback<User> callback = new CustomCallback();
        return callback;
    }

    public Callback<DishCategory> setAddDishCategoryCallback()
    {
        Callback<DishCategory> callback = new Callback<DishCategory>()
        {
            AddDishCategoryActivity adca = new AddDishCategoryActivity();
            @Override
            public void onResponse(Call<DishCategory> call, Response<DishCategory> response)
            {
                if(!response.isSuccessful())
                {
                    adca.showNoResponseError(response);
                    return;
                }
                else
                {
                    adca.showSuccessfulCall();
                }
            }

            @Override
            public void onFailure(Call<DishCategory> call, Throwable t)
            {
                adca.showOnFailureError(t);
            }
        };

        return callback;
    }

    public Callback<List<DishCategory>> setGetDishCategoriesCallback()
    {
        Callback<List<DishCategory>> callback = new Callback<List<DishCategory>>()
        {
            GetDishCategoriesActivity gdca = new GetDishCategoriesActivity();
            @Override
            public void onResponse(Call<List<DishCategory>> call, Response<List<DishCategory>> response)
            {
                if(!response.isSuccessful())
                {
                    gdca.showNoResponseError(response);
                    return;
                }
                else
                {
                    setContent(getEverything.getDishCategoriesString(response));
                }
            }

            @Override
            public void onFailure(Call<List<DishCategory>> call, Throwable t)
            {
                gdca.showOnFailureError(t);
            }
        };

        return callback;
    }

    public Call<User> addUser(String role, User user)
    {
        return this.getEverything.setUserApi().addUser(role, user);
    }

    public Call<User> login(User user)
    {
        return this.getEverything.setUserApi().loginUser(user);
    }

    public Call<DishCategory> addDishCategory(DishCategory dishCategory)
    {
        return this.getEverything.setDishCategoryApi().addDishCategory(dishCategory);
    }

    public Call<List<DishCategory>> getDishCategories()
    {
        return this.getEverything.setDishCategoryApi().getDishCategories();
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return this.content;
    }
}
