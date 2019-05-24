package edu.pwap.pp.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.pwap.pp.R;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.Order;

public class OrdersToDeliverAdapter extends RecyclerView.Adapter<OrdersToDeliverAdapter.MyViewHolder>
{
    private Context context;
    private List<Order> ordersList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.orderToDeliverId)
        TextView orderToDeliverId;

        @BindView(R.id.dishToDeliverId)
        TextView dishToDeliverId;

        @BindView(R.id.dishFromOrderToDeliverName)
        TextView dishFromOrderToDeliverName;

        @BindView(R.id.orderToDeliverPrice)
        TextView orderToDeliverPrice;

        @BindView(R.id.tableNumberOfOrderToDeliver)
        TextView tableNumberOfOrderToDeliver;

        @BindView(R.id.orderToDeliverDate)
        TextView orderToDeliverDate;

        @BindView(R.id.orderToDeliverPrepTime)
        TextView orderToDeliverPrepTime;


        public MyViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public OrdersToDeliverAdapter(Context context, List<Order> ordersList)
    {
        this.context = context;
        this.ordersList = ordersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_to_deliver_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order order = ordersList.get(position);
        Dish dish = order.getDishes().get(0);

        holder.orderToDeliverId.setText("\n" + "Numer zamówienia: " + Long.toString(order.getId()));
        holder.dishToDeliverId.setText("Numer dania: " + Long.toString(dish.getId()));
        holder.dishFromOrderToDeliverName.setText("Nazwa posiłku: " + dish.getDishName());
        holder.orderToDeliverPrice.setText("Cena: " + Double.toString(dish.getDishPrice()) + " zł");
        holder.tableNumberOfOrderToDeliver.setText("Numer stolika: " + Long.toString(order.getTableId()));
        holder.orderToDeliverDate.setText("Data: " + order.getOrderDate());
        holder.orderToDeliverPrepTime.setText("Czas przygotow. : " + Long.toString(dish.getEstimatedPreparationTime()) + " min" + "\n");
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

}
