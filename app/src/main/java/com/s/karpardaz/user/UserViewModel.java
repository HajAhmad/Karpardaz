package com.s.karpardaz.user;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.mvvm.BaseAndroidViewModel;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.User;

public class UserViewModel extends BaseAndroidViewModel {

    private final MutableLiveData<User> mLoggedInUser = new MutableLiveData<>();
    private final MutableLiveData<String> mErrorMessage = new MutableLiveData<>();

    private final UserDataSource mRepository;

    public UserViewModel(Application application) {
        super(application);
        mRepository = UserModule.provideRepository(application);
    }

    public void insertNewUser(User user) {
        mRepository.insertUser(user, new BaseCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //                mRepository.
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public LiveData<User> getLoggedInUser() {
        mLoggedInUser.postValue(null);
        return mLoggedInUser;
    }

    public void login(final String email, final String password) {
        mRepository.login(email, password);
    }

    public MutableLiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public boolean isEmailInvalid(String email) {
        return !AppUtil.isEmailValid(email);
    }

    public boolean isPasswordInvalid(String password) {
        return password.length() < 4;
    }

    public void register(@Nullable String name, String email, String password) {
        if (AppUtil.inNetworkAvailable(getApplication())) {
            if (TextUtils.isEmpty(name))
                name = email.split("@")[0];
            mRepository.register(name, email, password, AppUtil.getCurrentDateTimeUTC());
        } else {
            mErrorMessage.setValue(getString(R.string.all_check_network_message));
            mIsLoading.setValue(false);
        }
    }

    private String getString(@StringRes int resId) {
        return getApplication().getString(resId);
    }
}
