package com.s.karpardaz.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.model.Login;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.user.data.LoginDataSource;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MainPresenter extends BasePresenter<MainContract.View>
    implements MainContract.Presenter {

    public static final String TAG = MainPresenter.class.getSimpleName();

    private final LoginDataSource mLoginRepository;

    @Inject
    public MainPresenter(@NonNull LoginDataSource repository) {
        mLoginRepository = requireNonNull(repository);
    }

    @Override
    public void isUserAvailable() {
        getView().showProgress();
        mLoginRepository.getLoggedInUser(new LoginDataSource.GetLoggedInUserCallback() {
            @Override
            public void notFound() {
                getView().showEntranceDialog();
                getView().hideProgress();
            }

            @Override
            public void onSuccess(Login result) {
                AppConstants.sActiveUserId = result.getUserId();
                Log.w(TAG, "isUserAvailable: " + AppConstants.sActiveUserId);
                getView().proceed();
                getView().hideProgress();
            }
        });
    }

    @Override
    public void logout() {
        mLoginRepository.clearLoginInfo(__ -> getView().returnToLoginPage());
    }
}
