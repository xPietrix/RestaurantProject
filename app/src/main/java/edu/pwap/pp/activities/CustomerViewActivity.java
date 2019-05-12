package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pwap.pp.R;

public class CustomerViewActivity extends AppCompatActivity
{
    private Button makeDishOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        makeDishOrderButton = findViewById(R.id.makeDishOrderButton);
        setMakeDishOrderButtonListener();
    }

    private void setMakeDishOrderButtonListener()
    {
        makeDishOrderButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openMakeDishOrderView();
            }
        });
    }

    private void openMakeDishOrderView()
    {
        Intent intent = new Intent(this, MakeDishOrder.class);
        startActivity(intent);
    }
}

