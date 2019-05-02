package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.Order;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.repositories.OrderRepository;
import edu.pwap.pp.services.DishService;
import edu.pwap.pp.services.OrderService;

public class MakeOrderActivity extends AppCompatActivity
{
    private static EditText eTTableNumber;
    private EditText eTChosenDishId;
    private Button addDishToOrderButton;
    private static TextView tVChosenDishesList;
    private static Button addOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        eTTableNumber = findViewById(R.id.eTTableNumber);
        eTChosenDishId = findViewById(R.id.eTDishToOrderNumber);
        addDishToOrderButton = findViewById(R.id.buttonAddDishToOrder);
        tVChosenDishesList = findViewById(R.id.tVChosenDishes);
        addOrderButton = findViewById(R.id.addOrderButton);

        this.setAddDishToOrderButtonListener();
    }

    private void setAddDishToOrderButtonListener()
    {
        addDishToOrderButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String selectedDish = eTChosenDishId.getText().toString();
                getDishWithId(selectedDish);
                eTChosenDishId.setText("");
            }
        });
    }

    public static void setAddOrderButtonListener(final Dish dish)
    {
        addOrderButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String date = "2019-12-10T11:21:37.999";
                int tableNumber = Integer.parseInt(eTTableNumber.getText().toString());
                List<Dish> dishes = new ArrayList<>();
                Dish dishToOrder = dish;
                dishes.add(dishToOrder);
                Order order = new Order(0, dishToOrder.getDishPrice(), tableNumber, date,
                                        dishToOrder.getEstimatedPreparationTime(), dishes);
                sendOrder(order);
                Toast.makeText(v.getContext(),"Order made " + "(" + dishToOrder.getDishName() + " "
                               + dishToOrder.getDishPrice() + "zł )", Toast.LENGTH_SHORT).show();
                tVChosenDishesList.setText("");
            }
        });
    }

    private void getDishWithId(String dishId)
    {
        DishApi api = RetrofitInitializer.getClient().create(DishApi.class);
        DishService dishService = new DishService(new DishRepository());
        dishService.getDishWithId(dishId, api);
    }

    public static void changeCustomerChosenDishesList(Dish dish)
    {
        tVChosenDishesList.setText(StringProvider.getDishString(dish));
    }

    private static void sendOrder(Order order)
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.addOrder(order, api);
    }


}
