package com.s.karpardaz.user;

import androidx.annotation.StringRes;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;

public interface LoginContract {

    interface View extends BaseViewContract<Presenter>{
        void showInvalidEmailError();
        void showInvalidPasswordError();
        void proceed();
        void showInfoNotFoundError();
        void showMessage(@StringRes int stringResId);
        void showMessage(String message);
        void showPasswordRecoverySucceed();
    }

    interface Presenter extends BasePresenterContract<View> {
        void login(String email, String password);
        void recoverPassword(String email);
    }

}
