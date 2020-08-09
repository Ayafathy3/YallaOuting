package com.example.yallaouting.ui.signUp;


public interface ISignUpPresenterContract {

    interface View {
        void goToHome(int userId);
    }

    interface Presenter {
        void signUp(String firstName, String lastName, String userName,
                    int gender, String phone, String pass);

    }
}
