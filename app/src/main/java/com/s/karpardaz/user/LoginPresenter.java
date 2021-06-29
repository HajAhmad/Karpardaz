package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.LoginDataSource;

import java.util.Objects;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;

public class LoginPresenter extends BasePresenter<LoginContract.View>
    implements LoginContract.Presenter {

    private final LoginDataSource mRepository;

    @Inject
    LoginPresenter(@NonNull LoginDataSource repository) {
        mRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void login(String email, String password) {
        if (AppUtil.isPasswordInvalid(password)) {
            getView().showInvalidPasswordError();
        } else if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else {
            getView().showProgress();
            mRepository.login(email, password, getCurrentDateTimeUTC(),
                new LoginDataSource.LoginCallback() {
                @Override
                public void informationNotFound() {
                    getView().showInfoNotFoundError();
                    getView().hideProgress();
                }

                @Override
                public void onSuccess(String result) {
                    AppConstants.sActiveUserId = result;
                    getView().proceed();
                    getView().hideProgress();
                }
            });
        }
    }


}
