package com.s.karpardaz.user;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;

public interface LoginContract {

    interface View extends BaseViewContract<Presenter>{
        void showInvalidEmailError();
        void showInvalidPasswordError();
    }

    interface Presenter extends BasePresenterContract<View> {
        void login(String email, String password);
        void recoverPassword();
    }

}
