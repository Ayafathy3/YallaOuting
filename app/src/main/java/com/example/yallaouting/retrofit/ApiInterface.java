package com.example.yallaouting.retrofit;


import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("User/SignIn")
    Call<User> login(@Query("userName") String userName, @Query("Password") String password);

    @Headers("Content-Type: application/json")
    @POST("User/SignUp")
    Call<Integer> postData(
            @Body JsonObject body);
}