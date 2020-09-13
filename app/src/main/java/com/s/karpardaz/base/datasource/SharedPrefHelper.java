package com.s.karpardaz.base.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import com.s.karpardaz.KarpardazApplication;

import java.io.Closeable;
import java.util.Objects;

import javax.inject.Inject;


public final class SharedPrefHelper implements Closeable {

    public static final String PREF_NAME = "karpardaz";
    private static final String BASE_URL_KEY = "BASE_URL";
    private SharedPreferences mPref;

    @Inject
    private SharedPrefHelper(SharedPreferences preferences) {
        mPref = Objects.requireNonNull(preferences);
    }

    void open() {
        mPref = KarpardazApplication.getInstance()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public synchronized void close() {
        mPref = null;
    }


}

