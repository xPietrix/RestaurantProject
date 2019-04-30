package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.OrderRepository;
import edu.pwap.pp.services.OrderService;

public class GetOrdersToPrepareActivity extends AppCompatActivity
{
    private static TextView tVOrdersToPrepare;
    private Button buttonPrepareOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_orders_to_prepare);

        tVOrdersToPrepare = findViewById(R.id.tVOrdersToPrepare);
        buttonPrepareOrder = findViewById(R.id.buttonPrepareOrder);

        getOrdersToPrepare();
        setPrepareButtonListener();
    }

    private void getOrdersToPrepare()
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.getOrdersToPrepare(api);
    }

    private void setPrepareButtonListener()
    {
        buttonPrepareOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long orderId = 1;
                prepareOrder(orderId);
                Toast.makeText(v.getContext(),"Order prepared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareOrder(long orderId)
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.prepareOrder(orderId, api);
    }

    public static void changeTextView(List<Order> orders)
    {
        tVOrdersToPrepare.append(StringProvider.getAllOrdersToPrepareOrDeliverString(orders));
    }
}
