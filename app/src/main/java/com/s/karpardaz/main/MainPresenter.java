package com.s.karpardaz.main;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
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
        addMaybe(mLoginRepository.getLoggedInUser(),
                user -> {
                    AppConstants.sActiveUserId = user.getUserId();
                    getView().proceed();
                    getView().hideProgress();
                },
                () -> {
                    getView().showEntranceDialog();
                    getView().hideProgress();
                });
    }

}
