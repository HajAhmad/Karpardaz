package com.s.karpardaz.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.model.Login;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.stock.data.StockDataSource;
import com.s.karpardaz.user.data.LoginDataSource;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MainPresenter extends BasePresenter<MainContract.View>
    implements MainContract.Presenter {

    public static final String TAG = MainPresenter.class.getSimpleName();

    private final LoginDataSource mLoginDataSource;
    private final StockDataSource mStockDataSource;

    @Inject
    public MainPresenter(@NonNull LoginDataSource loginDataSource, @NonNull StockDataSource stockDataSource) {
        mLoginDataSource = requireNonNull(loginDataSource);
        mStockDataSource = requireNonNull(stockDataSource);
    }

    @Override
    public void start() {
        getView().showProgress();
        mLoginDataSource.getLoggedInUser(new LoginDataSource.GetLoggedInUserCallback() {
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
        mLoginDataSource.clearLoginInfo(__ -> getView().returnToLoginPage());
    }

    @Override
    public void checkForStocks() {
        mStockDataSource.isThereAnyStock(AppConstants.sActiveUserId,
            new StockDataSource.IsAnyCallback() {
            @Override
            public void no() {
                getView().showInsertStock();
            }

            @Override
            public void onSuccess(Void result) {
                getView().showCostFragment();
            }
        });
    }
}
