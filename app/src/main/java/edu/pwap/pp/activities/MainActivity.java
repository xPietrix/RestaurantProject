package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.dataGetters.GetEverything;
import edu.pwap.pp.models.*;
import edu.pwap.pp.repositories.UserRepository;
import edu.pwap.pp.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
    private GetEverything getEverything;
    private String role;
    final User user = new User("Piotr", "haslo");
    long id = 2;
    private DishCategory dishCategory = new DishCategory(id, "Obiady");
    private Dish dish = new Dish(4, "Golabki", 10.99, 1000, dishCategory);
    private Button addUserButton;
    private Button loginButton;
    private Button addDishCategoryButton;
    private Button getDishCategoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_result);
        textView.setText("TESTOWANKO");

        addUserButton = findViewById(R.id.addButton);
        addUserButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddUserActivity();
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openLoggingActivity();
            }
        });

        addDishCategoryButton = findViewById(R.id.addDishCategoryButton);
        addDishCategoryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddDishCategoryActivity();
            }
        });

        getDishCategoriesButton = findViewById(R.id.getDishCategoriesButton);
        getDishCategoriesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGetDishCategoriesActivity();
            }
        });


        //text = getEverything.getDishCategories();
        ///textView.setText(text);
        //getNotesFromJsonPlaceholder();
        //getDishCategories();
        //String categoryId = "3";
        //getDishCategory(categoryId);

        //String dishCategoryId = "3";
        //getDishesWithCategory(dishCategoryId);

        //String dishId = "1";
       // getDishWithId(dishId);

        //login(user);

        //this.role = "waiter";
        //addUser();

       // login();

        //addDish(dish);

        //Calendar today = Calendar.getInstance();
        //Date date = today.getTime();

        /*String date = "2016-11-09T11:44:44.797";

        long id = 10;
        Dish secondDish = new Dish(3,"Schab", 20.99, 1500, dishCategory);
        List<Dish> dishes = new ArrayList<Dish>();
        dishes.add(dish);
        dishes.add(secondDish);
        //addDish(dish);
        //addDish(secondDish);
        Order order = new Order(1, 100,7, date, 1200, dishes);
        Order orderToDeliver = new Order(2, 100,7, date, 1400, dishes);
        //addOrder(orderToDeliver);
        //addOrder(order);
        //getOrdersToPrepare();
        //getOrdersToDeliver();
        //String orderId = "3";
        //addOrderToPrepare(orderId);
        //addOrderToDeliver(orderId); */


    }

    private void getDishCategory(String id)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<DishCategory> call = getEverything.setDishCategoryApi().getDishCategory(id);
        call.enqueue(new Callback<DishCategory>()
        {
            @Override
            public void onResponse(Call<DishCategory> call, Response<DishCategory> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getDishCategoryString(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<DishCategory> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getNotesFromJsonPlaceholder()
    {
        getEverything = new GetEverything("https://jsonplaceholder.typicode.com/");
        Call<List<Note>> call = getEverything.setApi().getNotes();

        call.enqueue(new Callback<List<Note>>()
        {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getNotes(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getDishCategories()
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<List<DishCategory>> call = getEverything.setDishCategoryApi().getDishCategories();
        call.enqueue(new Callback<List<DishCategory>>()
        {
            @Override
            public void onResponse(Call<List<DishCategory>> call, Response<List<DishCategory>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getDishCategoriesString(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<DishCategory>> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getDishesWithCategory(String id)
    {
        getEverything = new GetEverything("http:/192.168.1.101:8080/");
        //getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<List<Dish>> call = getEverything.setDishApi().getDishWithCategory(id);
        call.enqueue(new Callback<List<Dish>>()
        {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getDishesWithCategory(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getDishWithId(String id)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<Dish> call = getEverything.setDishApi().getDishWithId(id);
        call.enqueue(new Callback<Dish>()
        {
            @Override
            public void onResponse(Call<Dish> call, Response<Dish> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getDishWithId(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<Dish> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void addDish(Dish dish)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<Dish> call = getEverything.setDishApi().addDish(dish);

        call.enqueue(new Callback<Dish>()
        {
            @Override
            public void onResponse(Call<Dish> call, Response<Dish> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = "Dodano";
                    textView.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Dish> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void addOrder(Order order)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        //getEverything = new GetEverything("http:/10.128.161.100:8080/");
        Call<Order> call = getEverything.setOrderApi().addOrder(order);

        call.enqueue(new Callback<Order>()
        {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = "Dodano";
                    textView.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void getOrdersToPrepare()
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<List<Order>> call = getEverything.setOrderApi().getOrdersToPrepare();
        call.enqueue(new Callback<List<Order>>()
        {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getOrdersToPrepareOrDeliver(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void getOrdersToDeliver()
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<List<Order>> call = getEverything.setOrderApi().getOrdersToDeliver();
        call.enqueue(new Callback<List<Order>>()
        {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = getEverything.getOrdersToPrepareOrDeliver(response);
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void addOrderToPrepare(String orderId)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<Integer> call = getEverything.setOrderApi().addOrderToPrepare(orderId);
        call.enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = "Dodano order do przygotowania";
                    textView.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void addOrderToDeliver(String orderId)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        getEverything = new GetEverything("http:/192.168.43.79:8080/");
        Call<Integer> call = getEverything.setOrderApi().addOrderToDeliver(orderId);
        call.enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else
                {
                    String content = "Dodano order do przygotowania";
                    textView.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t)
            {
                textView.setText(t.getMessage());
            }
        });
    }

    public void openAddUserActivity()
    {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    public void openLoggingActivity()
    {
        Intent intent = new Intent(this, LoggingActivity.class);
        startActivity(intent);
    }

    public void openAddDishCategoryActivity()
    {
        Intent intent = new Intent(this, AddDishCategoryActivity.class);
        startActivity(intent);
    }

    public void openGetDishCategoriesActivity()
    {
        Intent intent = new Intent(this, GetDishCategoriesActivity.class);
        startActivity(intent);
    }

    public void openErrorActivity()
    {
        Intent intent = new Intent(this, ConnectionErrorActivity.class);
        startActivity(intent);
    }
}
