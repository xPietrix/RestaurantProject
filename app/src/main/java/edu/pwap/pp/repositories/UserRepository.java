package edu.pwap.pp.repositories;

import android.util.Log;

import edu.pwap.pp.clients.UserApi;
import edu.pwap.pp.models.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository
{
    public void addUser(UserApi api, String role, User user)
    {
        api.addUserToDatabase(role, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>()
                {
                    @Override
                    public void onSuccess(User user)
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d("ERROR", "ERROR WITH GET ALL DISH CATEGORIES METHOD!");
                    }
                });
    }

    public void login(User user)
    {
        // TODO
    }
}

