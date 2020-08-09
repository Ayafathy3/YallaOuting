package com.example.yallaouting.ui.Login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yallaouting.Prefs.PreferencesHelperImp;
import com.example.yallaouting.R;
import com.example.yallaouting.ui.home.HomeActivity;
import com.example.yallaouting.ui.signUp.SignUpActivity;
import com.example.yallaouting.util.LoadingDialog;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    Button login;
    EditText userNameEdit, passwordEdit;
    private TextView signUpTextView;
    LoginPresenterImp loginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initiViews();
        setListener();

    }

    private void initiViews() {
        signUpTextView = findViewById(R.id.signup_tx);
        login = findViewById(R.id.login_bt);
        userNameEdit = findViewById(R.id.username_ed);
        passwordEdit = findViewById(R.id.password_ed);
    }

    private void setListener() {
        loginPresenterImp = new LoginPresenterImp(this, LoginActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEdit.getText().toString();
                String passwordUser = passwordEdit.getText().toString();

                if (!userName.isEmpty() && !passwordUser.isEmpty()) {
                    loginPresenterImp.login(userName, passwordUser);
                } else {
                    Toast.makeText(LoginActivity.this, "please write user name and password first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });

    }

    private void goToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finishAffinity();
    }

    @Override
    public void goToHome(int userId) {
        PreferencesHelperImp.getInstance().setUserIsLogged(true);
        PreferencesHelperImp.getInstance().setUserId(userId);
        openHomeActivity();
    }
}
