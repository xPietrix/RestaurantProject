package edu.pwap.pp.repositories;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.User;
import retrofit2.Call;
import retrofit2.Callback;

public class UserRepository
{
    private ConnectionInitializer connectionInitializer;

    public UserRepository()
    {
        connectionInitializer = new ConnectionInitializer();
    }

    public void addUser(String role, User user)
    {
        Call<User> call = connectionInitializer.addUser(role, user);
        Callback<User> addDataCallback = connectionInitializer.setUserCallback();
        call.enqueue(addDataCallback);
    }

    public void login(User user)
    {
        Call<User> call = connectionInitializer.login(user);
        Callback<User> loginCallback = connectionInitializer.setUserCallback();
        call.enqueue(loginCallback);
    }
}

