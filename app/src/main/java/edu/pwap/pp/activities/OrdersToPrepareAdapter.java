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

public class OrdersToPrepareAdapter extends RecyclerView.Adapter<OrdersToPrepareAdapter.MyViewHolder>
{
    private Context context;
    private List<Order> ordersList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.orderId)
        TextView orderId;

        @BindView(R.id.dishToPrepareId)
        TextView dishId;

        @BindView(R.id.dishFromOrderName)
        TextView dishOfOrderName;

        @BindView(R.id.orderToPreparePrice)
        TextView orderPrice;

        @BindView(R.id.tableNumberOfOrder)
        TextView tableNumber;

        @BindView(R.id.orderToPrepareDate)
        TextView orderDate;

        @BindView(R.id.orderToPrepareTime)
        TextView orderPreparationTime;


        public MyViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public OrdersToPrepareAdapter(Context context, List<Order> ordersList)
    {
        this.context = context;
        this.ordersList = ordersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order order = ordersList.get(position);
        Dish dish = order.getDishes().get(0);

        holder.orderId.setText("\n" + "Numer zamówienia: " + Long.toString(order.getId()));
        holder.dishId.setText("Numer dania: " + Long.toString(dish.getId()));
        holder.dishOfOrderName.setText("Nazwa posiłku: " + dish.getDishName());
        holder.orderPrice.setText("Cena: " + Double.toString(dish.getDishPrice()) + " zł");
        holder.tableNumber.setText("Numer stolika: " + Long.toString(order.getTableId()));
        holder.orderDate.setText("Data: " + order.getOrderDate());
        holder.orderPreparationTime.setText("Czas przygotow. : " + Long.toString(dish.getEstimatedPreparationTime()) + " min" + "\n");
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public void setData(List<Order> ordersList)
    {
        this.ordersList = ordersList;
        notifyDataSetChanged();
    }

}
