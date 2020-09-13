package com.s.karpardaz.base.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.s.karpardaz.KarpardazApplication;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class BaseModule {
    @Provides
    public SharedPreferences providePreferences(String preferencesName){
        return KarpardazApplication.getInstance()
                .getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }
}
