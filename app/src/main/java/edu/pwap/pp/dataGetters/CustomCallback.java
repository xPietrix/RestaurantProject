package edu.pwap.pp.dataGetters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallback implements Callback
{
    //private AddUserActivity aua;

    public CustomCallback()
    {
      //  this.aua = new AddUserActivity();
    }

    @Override
    public void onResponse(Call call, Response response)
    {
        if(!response.isSuccessful())
        {
        //    aua.showNoResponseError(response);
            return;
        }
        else
        {
          //  aua.showSuccessfulCall();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {
        //aua.showOnFailureError(t);
    }
}
