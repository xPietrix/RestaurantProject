package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import edu.pwap.pp.R;

public class WaiterViewActivity extends AppCompatActivity
{
    private Button showPreparedOrdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_view);

        showPreparedOrdersButton = findViewById(R.id.buttonShowPreparedOrders);

        setShowOrdersToDeliverButtonListener();
    }

    private void setShowOrdersToDeliverButtonListener()
    {
        showPreparedOrdersButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openOrdersToDeliverView();
            }
        });
    }

    private void openOrdersToDeliverView()
    {
        Intent intent = new Intent(this, GetOrdersToDeliverActivity.class);
        startActivity(intent);
    }
}
