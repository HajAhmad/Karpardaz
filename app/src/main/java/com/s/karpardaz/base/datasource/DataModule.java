package com.s.karpardaz.base.datasource;

import android.app.Application;

public class DataModule {

    public static KarpardazDatabase provideDatabase(Application application){
        return KarpardazDatabase.getInstance(application);
    }

}
