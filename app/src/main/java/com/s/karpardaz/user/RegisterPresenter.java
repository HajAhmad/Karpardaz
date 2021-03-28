package com.s.karpardaz.user;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.KarpardazApplication;
import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import java.util.Objects;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.composeLoginPhrase;
import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements
        RegisterContract.Presenter {

    private final UserDataSource mRepository;

    @Inject
    RegisterPresenter(@NonNull UserDataSource repository) {
        mRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void register(@Nullable String name, String email, String password) {
        if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else if (AppUtil.isPasswordInvalid(password)) {
            getView().showInvalidPasswordError();
        } else {

            if (AppUtil.inNetworkAvailable(KarpardazApplication.getInstance())) {
                if (TextUtils.isEmpty(name))
                    name = email.split("@")[0];
                password = composeLoginPhrase(email, password);
                User user = new User(name, email, password, getCurrentDateTimeUTC());
                mRepository.register(user, new UserDataSource.RegisterCallback() {
                    @Override
                    public void onSuccess(String result) {
                        mRepository.insertLogin(new Login(result, getCurrentDateTimeUTC()),
                                new BaseCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        getView().proceed();
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        getView().showThrowable(t);
                                    }
                                });
                    }

                    @Override
                    public void userExists() {
                        getView().emailFound();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        getView().showThrowable(t);
                    }
                });
            } else {
                getView().networkUnavailable();
            }
        }
    }

}
