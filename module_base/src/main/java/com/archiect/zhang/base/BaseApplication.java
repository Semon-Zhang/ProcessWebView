package com.archiect.zhang.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }
}
