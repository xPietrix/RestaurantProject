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

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.MyViewHolder>
{
    private Context context;
    private List<Dish> dishesList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.dishIndex)
        TextView dishId;

        @BindView(R.id.dishName)
        TextView dishName;

        @BindView(R.id.dishPrice)
        TextView dishPrice;

        @BindView(R.id.dishPreparationTime)
        TextView dishPreparationTime;

        @BindView(R.id.dishCategoryName)
        TextView dishCategoryName;

        public MyViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public DishesAdapter(Context context, List<Dish> dishesList)
    {
        this.context = context;
        this.dishesList = dishesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dish dish = dishesList.get(position);

        holder.dishId.setText("Numer: " + Long.toString(dish.getId()));
        holder.dishName.setText("Nazwa: " + dish.getDishName());
        holder.dishPrice.setText("Cena: " + Double.toString(dish.getDishPrice()) + " zł");
        holder.dishPreparationTime.setText("Czas przygotowania: " + Long.toString(dish.getEstimatedPreparationTime()) + "min");
        holder.dishCategoryName.setText("Kategoria: " + dish.getDishCategory().getCategoryName() + "\n");
    }

    @Override
    public int getItemCount() {
        return dishesList.size();
    }
    
}
