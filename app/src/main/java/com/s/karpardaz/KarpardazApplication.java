package com.s.karpardaz;

import android.app.Application;

import com.s.karpardaz.main.MainContract;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public final class KarpardazApplication extends Application {

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
