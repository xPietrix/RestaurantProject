package edu.pwap.pp.dataGetters;

import java.util.List;

import edu.pwap.pp.clients.*;
import edu.pwap.pp.models.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetEverything
{
    private Retrofit retrofit;

    public GetEverything(String url)
    {
        this.retrofit = initRetrofit(url);
    }

    public Retrofit initRetrofit(String url)
    {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    // SET API //

    public JsonPlaceHolderApi setApi()
    {
        JsonPlaceHolderApi api;
        api = this.retrofit.create(JsonPlaceHolderApi.class);
        return api;
    }

    public DishCategoryApi setDishCategoryApi()
    {
        DishCategoryApi dishCategoryApi;
        dishCategoryApi = this.retrofit.create(DishCategoryApi.class);
        return dishCategoryApi;
    }

    public DishApi setDishApi()
    {
        DishApi dishApi;
        dishApi = this.retrofit.create(DishApi.class);
        return dishApi;
    }

    public UserApi setUserApi()
    {
        UserApi userApi;
        userApi = this.retrofit.create(UserApi.class);
        return userApi;
    }

    public OrderApi setOrderApi()
    {
        OrderApi orderApi;
        orderApi = this.retrofit.create(OrderApi.class);
        return orderApi;
    }

    // GET DATA FROM RESPONSE //

    public String getNotes(Response<List<Note>> response)
    {
        List<Note> notes = response.body();
        String content="";
        for(Note note: notes)
        {
            content = content + "ID: " + note.getId() + "\n";
            content = content + "User ID: " + note.getUserId() + "\n";
            content = content + "Title: " + note.getTitle() + "\n";
            content = content + "Text " + note.getText() + "\n";
        }

        return content;
    }

    public String getDishCategoryString(Response<DishCategory> response)
    {
        DishCategory category = response.body();
        String content="";

        content = content + "ID: " + category.getId() + "\n";
        content = content + "User ID: " + category.getCategoryName() + "\n";

        return content;
    }

    public String getDishCategoriesString(Response <List<DishCategory>> response)
    {
        List<DishCategory> dishCategories = response.body();
        String content = "";

        for(DishCategory dishCategory: dishCategories)
        {
            content = content + "ID: " + dishCategory.getId() + "\n";
            content = content + "Category name: " + dishCategory.getCategoryName() + "\n";
        }

        return content;
    }

    public String getDishWithId(Response<Dish> response)
    {
        Dish dish = response.body();
        String content = "";

        content = content + "ID: " + dish.getId() + "\n";
        content = content + "Dish name: " + dish.getDishName() + "\n";
        content = content + "Dish price: " + dish.getDishPrice() + "\n";
        content = content + "Estimated preparation time: " + dish.getEstimatedPreparationTime() + "\n";
        content = content + "Dish category id: " + dish.getDishCategory().getId() + "\n";
        content = content + "Dish category name: " + dish.getDishCategory().getCategoryName() + "\n";

        return content;
    }

    public String getOrdersToPrepareOrDeliver(Response<List<Order>> response)
    {
        List<Order> orders = response.body();
        String content = "";

        for(Order order: orders)
        {
            content = content + "Order status: " + order.getOrderStatus() + "\n";
            content = content + "TotalPrice: " + order.getTotalPrice() + "\n";
            content = content + "TableId: " + order.getTableId() + "\n";
            content = content + "OrderDate: " + order.getOrderDate() + "\n";
            content = content + "Estimated Preparation Time: " + order.getEstimatedPreparationTime() + "\n";

            List<Dish> dishes = order.getDishes();
            for(Dish dish: dishes)
            {
                //content = content + dish.getDishCategoryId() + "\n";
                content = content + dish.getDishName() + "\n";
                content = content + dish.getDishPrice() + "\n";
                content = content + dish.getEstimatedPreparationTime()+ "\n";
            }
        }

        return content;
    }
}
