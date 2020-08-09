package com.example.yallaouting.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.yallaouting.Prefs.PreferencesHelperImp;
import com.example.yallaouting.R;
import com.example.yallaouting.ui.home.HomeActivity;
import com.example.yallaouting.util.LoadingDialog;
import com.reginald.editspinner.EditSpinner;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity implements ISignUpPresenterContract.View {
    public static final String TAG = "SignUpActivity";

    private EditText firstNameEditText, lastNameEditText, userNameEditText,
            passwordEditText, phoneNumEditText;

    private ArrayList<String> genderList;
    private String genderString;
    private EditSpinner genderEditSpinner;
    private Button signUp;
    private SignUpPresenterImp signUpPresenterImp;
    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initiViews();
        setUpSpinner();
        setListeners();
    }

    private void initiViews() {
        firstNameEditText = findViewById(R.id.firstname_ed);
        lastNameEditText = findViewById(R.id.lastname_ed);
        userNameEditText = findViewById(R.id.username_ed);
        passwordEditText = findViewById(R.id.password_ed);
        genderEditSpinner = findViewById(R.id.gender_ed);
        phoneNumEditText = findViewById(R.id.phonenum_ed);
        signUp = findViewById(R.id.signup_bt);
    }

    private void setUpSpinner() {
        genderList = new ArrayList<>();
        genderList.add("Female");
        genderList.add("Male");
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genderList);
        genderEditSpinner.setAdapter(accountTypeAdapter);
    }


    private void openHomeActivity() {
        startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
        finishAffinity();
    }

    private void setListeners() {
        genderEditSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                genderString = genderList.get(i).toString();
                Log.i("gender", genderString);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpAction();
            }
        });
    }

    private void signUpAction() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String phone = phoneNumEditText.getText().toString();
        String pass = passwordEditText.getText().toString();

        if (genderString.equals("Female")) {
            gender = 2;
        } else if (genderString.equals("Male")) {
            gender = 1;
        }
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty()
                || genderString.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please write all data", Toast.LENGTH_SHORT).show();
        } else {
            // go to signUp
            signUpPresenterImp = new SignUpPresenterImp(this, SignUpActivity.this);
            signUpPresenterImp.signUp(firstName, lastName, userName, gender, phone, pass);
        }
    }


    @Override
    public void goToHome(int userId) {
        PreferencesHelperImp.getInstance().setUserIsLogged(true);
        PreferencesHelperImp.getInstance().setUserId(userId);
        openHomeActivity();
    }
}
