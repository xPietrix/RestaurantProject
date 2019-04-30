package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import edu.pwap.pp.R;

public class ChefViewActivity extends AppCompatActivity
{
    private Button buttonCheckOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_view);

        buttonCheckOrders = findViewById(R.id.buttonCheckOrders);

        setCheckOrdersButtonListener();
    }

    private void setCheckOrdersButtonListener()
    {
        buttonCheckOrders.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGetOrdersToPrepareView();
            }
        });
    }

    private void openGetOrdersToPrepareView()
    {
        Intent intent = new Intent(this, GetOrdersToPrepareActivity.class);
        startActivity(intent);
    }
}
