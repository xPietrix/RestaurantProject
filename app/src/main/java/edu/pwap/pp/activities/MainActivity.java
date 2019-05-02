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
    long id = 2;
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
        textView.setText("CHOOSE YOUR ROLE:");

        adminButton = findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAdminViewActivity();
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
                openWaiterViewActivity();
            }
        });

        chefButton = findViewById(R.id.chefButton);
        chefButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openChefViewActivity();
            }
        });

        customerButton = findViewById(R.id.customerButton);
        customerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openCustomerViewActivity();
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

    private void openAdminViewActivity()
    {
        Intent intent = new Intent(this, AdminViewActivity.class);
        startActivity(intent);
    }

    private void openDieticianViewActivity()
    {
        Intent intent = new Intent(this, DieticianViewActivity.class);
        startActivity(intent);
    }

    private void openWaiterViewActivity()
    {
        Intent intent = new Intent(this, WaiterViewActivity.class);
        startActivity(intent);
    }

    private void openChefViewActivity()
    {
        Intent intent = new Intent(this, ChefViewActivity.class);
        startActivity(intent);
    }

    private void openCustomerViewActivity()
    {
        Intent intent = new Intent(this, CustomerViewActivity.class);
        startActivity(intent);
    }
}
