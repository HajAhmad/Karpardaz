package com.s.karpardaz.user;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;

public interface RegisterContract {
    interface View extends BaseViewContract<Presenter> {
        void showInvalidPasswordError();
        void showInvalidEmailError();
        void emailFound();
        void showThrowable(Throwable t);
        void networkUnavailable();
        void proceed();
        void notFound();
    }

    interface Presenter extends BasePresenterContract<View> {
        void register(final String name, String email, String password);
    }

}
