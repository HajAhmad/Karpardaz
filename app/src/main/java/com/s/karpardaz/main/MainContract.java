package com.s.karpardaz.main;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;

public interface MainContract {

    interface View extends BaseViewContract<Presenter> {
        void proceed();
        void showEntranceDialog();
        void returnToLoginPage();
    }

    interface Presenter extends BasePresenterContract<View> {
        void isUserAvailable();
        void logout();
    }

}
