package com.example.yallaouting.ui.Login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.yallaouting.retrofit.ApiInterface;
import com.example.yallaouting.retrofit.RetrofitInstance;
import com.example.yallaouting.retrofit.User;
import com.example.yallaouting.util.LoadingDialog;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenterImp implements ILoginContract.Presenter {

    private ILoginContract.View mView;
    private Context context;
    public static final String TAG="LoginPresenterImp";

    public LoginPresenterImp(ILoginContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void login(String userName, String pass) {
        LoadingDialog.showProgress(context);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://azharguide.somee.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<User> call = apiInterface.login(userName, pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                LoadingDialog.hideProgress();
                if (response.isSuccessful()) {
                    int userId = response.body().getId();
                    mView.goToHome(userId);
                } else {
                    Toast.makeText(context, "login not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                LoadingDialog.hideProgress();
                if (t instanceof IOException) {
                    Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    Log.i(TAG, t.getMessage());
                }
            }

        });
    }


}
