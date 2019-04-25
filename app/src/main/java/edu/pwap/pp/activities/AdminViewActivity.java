package edu.pwap.pp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.pwap.pp.R;

public class AdminViewActivity extends AppCompatActivity
{
    private Button addUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_actitivity);

        addUserButton = findViewById(R.id.buttonAddUser);
        setAddUserButtonListener();
    }

    public void setAddUserButtonListener()
    {
        addUserButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAddUserActivity();
            }
        });
    }

    public void openAddUserActivity()
    {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }
}
