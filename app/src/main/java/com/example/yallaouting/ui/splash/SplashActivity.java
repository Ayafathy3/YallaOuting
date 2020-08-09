package com.example.yallaouting.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.yallaouting.Prefs.PreferencesHelperImp;
import com.example.yallaouting.R;
import com.example.yallaouting.ui.Login.LoginActivity;
import com.example.yallaouting.ui.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    checkIfUserIsLogged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void checkIfUserIsLogged() {

        boolean isLogged = PreferencesHelperImp.getInstance().getUserIsLogged();
        if (isLogged) {
            openHomeActivity();
        } else {
            goToLoginActivity();
        }
    }

    private void goToLoginActivity() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    private void openHomeActivity() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finishAffinity();
    }
}