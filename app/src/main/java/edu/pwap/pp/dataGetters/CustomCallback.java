package edu.pwap.pp.dataGetters;

import android.content.Intent;

import edu.pwap.pp.activities.ConnectionErrorActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallback implements Callback
{
    private ConnectionErrorActivity connErrorAct;

    public CustomCallback()
    {
        connErrorAct = new ConnectionErrorActivity();
    }

    @Override
    public void onResponse(Call call, Response response)
    {
        if(!response.isSuccessful())
        {
            connErrorAct.showNoResponseError(response);
            return;
        }
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {
        connErrorAct.showOnFailureError(t);
    }
}
