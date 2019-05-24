package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.pwap.pp.R;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
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

        adminButton = findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddUserActivity();
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
                openOrdersToDeliverActivity();
            }
        });

        chefButton = findViewById(R.id.chefButton);
        chefButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openOrdersToPrepareActivity();
            }
        });

        customerButton = findViewById(R.id.customerButton);
        customerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openMakeDishOrderActivity();
            }
        });
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

    private void openDieticianViewActivity()
    {
        Intent intent = new Intent(this, DieticianViewActivity.class);
        startActivity(intent);
    }

    private void openAddUserActivity()
    {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    private void openMakeDishOrderActivity()
    {
        Intent intent = new Intent(this, MakeDishOrder.class);
        startActivity(intent);
    }

    private void openOrdersToPrepareActivity()
    {
        Intent intent = new Intent(this, GetOrdersToPrepareActivity.class);
        startActivity(intent);
    }

    private void openOrdersToDeliverActivity()
    {
        Intent intent = new Intent(this, GetOrdersToDeliverActivity.class);
        startActivity(intent);
    }
}
