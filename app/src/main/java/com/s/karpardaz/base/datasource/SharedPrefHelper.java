package com.s.karpardaz.base.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Closeable;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

import static android.content.Context.MODE_PRIVATE;
import static java.util.Objects.requireNonNull;

public final class SharedPrefHelper implements SharedPrefDataSource, Closeable {

    private static final String PREF_NAME = "karpardaz";
    private static final String BASE_URL_KEY = "BASE_URL";
    private SharedPreferences mPref;

    @Inject
    SharedPrefHelper(@ApplicationContext Context context) {
        open(requireNonNull(context));
    }

    void open(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

    @Override
    public synchronized void close() {
        mPref = null;
    }

}

