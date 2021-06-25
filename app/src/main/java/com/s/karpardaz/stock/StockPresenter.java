package com.s.karpardaz.stock;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.stock.data.StockDataSource;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class StockPresenter extends BasePresenter<StockContract.View> implements StockContract.Presenter {

    private final StockDataSource mRepository;

    @Inject
    public StockPresenter(@NonNull StockDataSource repository) {
        this.mRepository = requireNonNull(repository);
    }

    @Override
    public void start() {
        getView().showProgress();
        mRepository.isThereAnyStock(AppConstants.sActiveUserId, new StockDataSource.IsAnyCallback() {
            @Override
            public void no() {
                getView().showNoStock();
                getView().hideProgress();
            }

            @Override
            public void onSuccess(Void result) {
                getView().hideProgress();
                getAllStocks();
            }
        });
    }

    @Override
    public void getAllStocks(){
        getView().showProgress();
        mRepository.getAllStocks(AppConstants.sActiveUserId, result -> {
            getView().setStocks(result);
            getView().hideProgress();
        });
    }

}
