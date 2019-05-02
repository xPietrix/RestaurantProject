package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

public class GetOrdersToDeliverActivity extends AppCompatActivity
{
    private static TextView tVOrdersToDeliver;
    private Button buttonDeliverOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_orders_to_deliver);

        tVOrdersToDeliver = findViewById(R.id.tVOrdersToDeliver);
        buttonDeliverOrder = findViewById(R.id.buttonDeliverOrder);

        getOrdersToDeliver();
        setDeliverButtonListener();
    }

    private void getOrdersToDeliver()
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.getOrdersToDeliver(api);
    }

    private void setDeliverButtonListener()
    {
        buttonDeliverOrder.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long orderId = 4;
                deliverOrder(orderId);
                Toast.makeText(v.getContext(),"Order delivered", Toast.LENGTH_SHORT).show();
                getOrdersToDeliver();
            }
        });
    }

    private void deliverOrder(long orderId)
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.deliverOrder(orderId, api);
    }

    public static void changeTextView(List<Order> preparedOrders)
    {
        tVOrdersToDeliver.setText(StringProvider.getAllOrdersToPrepareOrDeliverString(preparedOrders));
    }
}
