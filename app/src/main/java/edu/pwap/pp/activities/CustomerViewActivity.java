package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pwap.pp.R;

public class CustomerViewActivity extends AppCompatActivity
{
    private Button makeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        makeOrderButton = findViewById(R.id.makeOrderButton);

        setMakeOrderButtonListener();
    }

    private void setMakeOrderButtonListener()
    {
        makeOrderButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openShowDishMenuView();
            }
        });
    }

    private void openShowDishMenuView()
    {
        Intent intent = new Intent(this, ShowDishMenuActivity.class);
        startActivity(intent);
    }
}

