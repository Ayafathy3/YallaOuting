package com.example.yallaouting.ui.Login;

public interface ILoginContract {

    interface View {
        void goToHome(int userId);
    }

    interface Presenter {
        void login(String userName, String pass);
    }
}
