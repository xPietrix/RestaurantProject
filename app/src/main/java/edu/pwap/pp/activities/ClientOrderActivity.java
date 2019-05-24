package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import edu.pwap.pp.R;
import edu.pwap.pp.activities.utils.ClientOrder;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;

public class ClientOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_order);

        List<Dish> dishes = ClientOrder.getOrderedDishes();
        TextView tv = findViewById(R.id.tvClientOrderedDishes);

        tv.setText("MOJE ZAMÓWIENIE" + "\n");

        for(Dish dish: dishes)
        {
            tv.append("\n" + "Numer: "+ dish.getId());
            tv.append("\n" + "Nazwa: " + dish.getDishName());
            tv.append("\n" + "Cena: " + dish.getDishPrice() + " zł");
            tv.append("\n" + "Czas przygotow. : " + dish.getEstimatedPreparationTime() + " min");
            tv.append("\n" + "Kategoria: " + dish.getDishCategory().getCategoryName() + "\n");
        }
    }
}
