package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import edu.pwap.pp.R;
import retrofit2.Response;

public class ConnectionErrorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_error);
    }

    public void showNoResponseError(Response response)
    {
        Toast.makeText(ConnectionErrorActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
    }

    public void showOnFailureError(Throwable t)
    {
        Toast.makeText(ConnectionErrorActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
