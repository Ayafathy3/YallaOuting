package com.example.yallaouting.ui.signUp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.yallaouting.retrofit.ApiInterface;
import com.example.yallaouting.retrofit.RetrofitInstance;
import com.example.yallaouting.util.LoadingDialog;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpPresenterImp implements ISignUpPresenterContract.Presenter {

    private ISignUpPresenterContract.View mView;
    private static final String TAG = "SignUpPresenterImp";
    private Context context;

    SignUpPresenterImp(ISignUpPresenterContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }


    @Override
    public void signUp(String firstName, String lastName, String userName, int gender, String phone, String pass) {
        LoadingDialog.showProgress(context);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("GenderId", gender);
        jsonObject.addProperty("UserName", userName);
        jsonObject.addProperty("FirstName", firstName);
        jsonObject.addProperty("LastName", lastName);
        jsonObject.addProperty("Password", pass);
        jsonObject.addProperty("phoneNumber", phone);

        ApiInterface apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Call<Integer> call = apiInterface.postData(jsonObject);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                LoadingDialog.hideProgress();
                if (response.isSuccessful()) {
                    Log.i(TAG, "retrofit successful");
                    mView.goToHome(response.body());
                } else {
                    Toast.makeText(context, "Server returned an error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
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