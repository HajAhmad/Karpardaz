package com.s.karpardaz;

import android.app.Application;


import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class KarpardazApplication extends Application {

    private static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Application getInstance() {
        return sInstance;
    }
}
