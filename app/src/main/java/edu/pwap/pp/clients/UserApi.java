package edu.pwap.pp.clients;

import edu.pwap.pp.models.User;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi
{
    @POST("login")
    Call<User> loginUser(@Body User user);

    @POST("user/add/{role}")
    Single<User> addUserToDatabase(@Path("role") String role, @Body User user);
}
