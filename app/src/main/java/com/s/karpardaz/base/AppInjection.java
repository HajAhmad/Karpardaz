package com.s.karpardaz.base;

import com.s.karpardaz.base.mvvm.ViewModelFactory;

public class AppInjection {

    public static ViewModelFactory proViewModelFactory() {
        return ViewModelFactory.getInstance();
    }


}
