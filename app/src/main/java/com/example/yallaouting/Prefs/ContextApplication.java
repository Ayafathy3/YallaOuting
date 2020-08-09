package com.example.yallaouting.Prefs;

import android.app.Application;

public class ContextApplication extends Application {

    private static ContextApplication instance;

    public ContextApplication() {
        instance = this;
    }

    public static ContextApplication getInstance() {
        return instance;
    }


}