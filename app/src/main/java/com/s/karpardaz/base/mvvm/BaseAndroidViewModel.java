package com.s.karpardaz.base.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public abstract class BaseAndroidViewModel extends AndroidViewModel {

    protected final MutableLiveData<Boolean> mIsLoading;

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
        mIsLoading = new MutableLiveData<>();
    }

    public LiveData<Boolean> getLoadingLive(){
        return mIsLoading;
    }

}
