package com.example.yallaouting.Prefs;

public interface PreferencesHelper {

    boolean getUserIsLogged();

    void setUserIsLogged(boolean userIsLogged);

    int getUserId();

    void setUserId(int userId);

    void removeAllValues();

}
