package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.pwap.pp.R;
import edu.pwap.pp.models.*;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
    //private GetEverything getEverything;
    private String role;
    final User user = new User("Piotr", "haslo");
    long id = 2;
    private DishCategory dishCategory = new DishCategory("Obiady");
    private Dish dish = new Dish("Golabki", 10.99, 1000, dishCategory);
    private Button adminButton;
    private Button dieticianButton;
    private Button waiterButton;
    private Button chefButton;
    private Button customerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_result);
        textView.setText("CHOOSE YOUR ROLE:");

        adminButton = findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAdminViewActivity();
            }
        });

        dieticianButton = findViewById(R.id.dieticianButton);
        dieticianButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDieticianViewActivity();
            }
        });

        waiterButton = findViewById(R.id.waiterButton);
        waiterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openWaiterViewActivity();
            }
        });

        chefButton = findViewById(R.id.chefButton);
        chefButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openChefViewActivity();
            }
        });

        customerButton = findViewById(R.id.customerButton);
        customerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openCustomerViewActivity();
            }
        });

        /*
        addDishCategoryButton = findViewById(R.id.addDishCategoryButton);
        addDishCategoryButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddDishCategoryActivity();
            }
        });

        getDishCategoriesButton = findViewById(R.id.getDishCategoriesButton);
        getDishCategoriesButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGetDishCategoriesActivity();
            }
        });
*/

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
        Order order = new Order(1, 100, 7, date, 1200, dishes);
        Order orderToDeliver = new Order(2, 100,7, date, 1400, dishes);
        //addOrder(orderToDeliver);
        //addOrder(order);
        //getOrdersToPrepare();
        //getOrdersToDeliver();
        //String orderId = "3";
        //addOrderToPrepare(orderId);
        //addOrderToDeliver(orderId); */


    }

    private void getDishCategory(long id)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        /*getEverything = new GetEverything("http:/192.168.43.79:8080/");
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
        }); */
    }

    private void getDishWithId(String id)
    {
       // getEverything = new GetEverything("http:/192.168.1.101:8080/");
     //   getEverything = new GetEverything("http:/192.168.43.79:8080/");



     /*   Call<Dish> call = getEverything.setDishApi().getDishWithId(id);

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
        }); */
    }

    private void addOrder(Order order)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        // getEverything = new GetEverything("http:/192.168.43.79:8080/");
        //getEverything = new GetEverything("http:/10.128.161.100:8080/");
       // Call<Order> call = getEverything.setOrderApi().addOrder(order);

        /*call.enqueue(new Callback<Order>()
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
        });*/
    }

    private void getOrdersToPrepare()
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        /*getEverything = new GetEverything("http:/192.168.43.79:8080/");
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
        }); */
    }

    private void addOrderToPrepare(String orderId)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        /*getEverything = new GetEverything("http:/192.168.43.79:8080/");
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
        }); */
    }

    private void openLoggingActivity()
    {
        Intent intent = new Intent(this, LoggingActivity.class);
        startActivity(intent);
    }

    private void openErrorActivity()
    {
        Intent intent = new Intent(this, ConnectionErrorActivity.class);
        startActivity(intent);
    }

    private void openAdminViewActivity()
    {
        Intent intent = new Intent(this, AdminViewActivity.class);
        startActivity(intent);
    }

    private void openDieticianViewActivity()
    {
        Intent intent = new Intent(this, DieticianViewActivity.class);
        startActivity(intent);
    }

    private void openWaiterViewActivity()
    {
        Intent intent = new Intent(this, WaiterViewActivity.class);
        startActivity(intent);
    }

    private void openChefViewActivity()
    {
        Intent intent = new Intent(this, ChefViewActivity.class);
        startActivity(intent);
    }

    private void openCustomerViewActivity()
    {
        Intent intent = new Intent(this, CustomerViewActivity.class);
        startActivity(intent);
    }
}
