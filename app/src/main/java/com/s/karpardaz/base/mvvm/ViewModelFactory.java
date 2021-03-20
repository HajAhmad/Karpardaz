package com.s.karpardaz.base.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.s.karpardaz.KarpardazApplication;
import com.s.karpardaz.user.UserViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private static volatile ViewModelFactory sInstance;

    public static ViewModelFactory getInstance() {
        if (sInstance == null) {
            synchronized (ViewModelFactory.class) {
                if (sInstance == null) {
                    sInstance = new ViewModelFactory();
                }
            }
        }
        return sInstance;
    }

    private ViewModelFactory(){

    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class))
            return (T) new UserViewModel(KarpardazApplication.getInstance());

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
