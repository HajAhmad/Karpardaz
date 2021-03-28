package com.s.karpardaz.main;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.user.data.UserDataSource;

import java.util.Objects;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    public static final String TAG = MainPresenter.class.getSimpleName();

    private final UserDataSource mUserRepository;

    @Inject
    public MainPresenter(@NonNull UserDataSource repository) {
        mUserRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void isUserAvailable() {
        addMaybe(mUserRepository.getLoggedInUser(),
                user -> {
                    AppConstants.sActiveUserId = user.getUserId();
                    getView().proceed();
                },
                () -> getView().showEntranceDialog());

    }

}
