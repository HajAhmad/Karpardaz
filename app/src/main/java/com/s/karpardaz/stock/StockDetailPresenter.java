package com.s.karpardaz.stock;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.stock.data.StockDataSource;
import com.s.karpardaz.stock.model.Stock;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;
import static java.util.Objects.requireNonNull;

public class StockDetailPresenter extends BasePresenter<StockDetailContract.View> implements
    StockDetailContract.Presenter {

    private final StockDataSource mRepository;

    @Inject
    public StockDetailPresenter(@NonNull StockDataSource repository) {
        this.mRepository = requireNonNull(repository);
    }

    @Override
    public void addStock(@NonNull Stock stock, boolean isDefault) {
        getView().showProgress();
        requireNonNull(stock);

        mRepository.isStockUnique(stock.getName(), new StockDataSource.IsStockUniqueCallback() {
            @Override
            public void no() {
                getView().hideProgress();
                getView().showStockFoundError();
            }

            @Override
            public void yes() {
                insertStock(stock, isDefault);
            }

            @Override
            public void onSuccess(Void result) {
                throw new NotImplementedException();
            }
        });

    }

    private void insertStock(@NonNull Stock stock, boolean isDefault) {
        mRepository.addStock(stock, new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                insertDefaultStock(stock, isDefault);
            }

            @Override
            public void onFailure(Throwable t) {
                getView().hideProgress();
                getView().insertionFailed();
            }
        });
    }

    private void insertDefaultStock(@NonNull Stock stock, boolean isDefault) {
        mRepository.isDefaultStockTableEmpty(new StockDataSource.IsTableEmptyCallback() {
            @Override
            public void yes() {
                setDefaultStock(stock);
            }

            @Override
            public void no() {
                if (isDefault) {
                    setDefaultStock(stock);
                } else {
                    getView().hideProgress();
                    getView().newStockInserted();
                }
            }

            @Override
            public void onSuccess(Void result) {
                throw new NotImplementedException();
            }
        });
    }

    private void setDefaultStock(@NonNull Stock stock) {
        mRepository.setDefaultStock(stock.getUuid(), getCurrentDateTimeUTC(),
            new BaseCallback<Void>() {
                @Override
                public void onSuccess(Void result1) {
                    AppConstants.setDefaultStockId(stock.getUuid());
                    AppConstants.setsDefaultStockCurrency(stock.getCurrency());
                    getView().hideProgress();
                    getView().newStockInserted();
                }

                @Override
                public void onFailure(Throwable t) {
                    getView().hideProgress();
                    getView().showSetDefaultStockFailed();
                }
            });
    }

    @Override
    public void getStockDetail(@NonNull String stockId) {
        getView().showProgress();
        if (AppUtil.isAnyEmpty(stockId)) throw new NullPointerException();

        mRepository.getStock(stockId, new StockDataSource.GetStockCallback() {
            @Override
            public void onSuccess(Stock result) {
                getView().showStock(result);
                mRepository.isStockDefault(result.getUuid(), new StockDataSource.IsStockDefaultCallback() {
                    @Override
                    public void yes() {
                        getView().hideProgress();
                        getView().setDefaultStock();
                    }

                    @Override
                    public void no() {
                        getView().hideProgress();
                        getView().setNotDefaultStock();
                    }

                    @Override
                    public void onSuccess(Void result) {
                        throw new NotImplementedException();
                    }
                });
            }

            @Override
            public void stockNotFound() {
                getView().hideProgress();
                getView().showStockNotFoundError();
            }
        });
    }

    @Override
    public void updateStock(Stock stock, boolean isDefault) {
        getView().showProgress();
        requireNonNull(stock);

        mRepository.updateStock(stock, __ -> {
            if (isDefault) {
                mRepository.setDefaultStock(stock.getUuid(), getCurrentDateTimeUTC(), ___ -> {
                    AppConstants.setDefaultStockId(stock.getUuid());
                    AppConstants.setsDefaultStockCurrency(stock.getCurrency());
                    getView().hideProgress();
                    getView().showStockUpdated();
                });
            }
            getView().hideProgress();
            getView().showStockUpdated();
        });
    }

    @Override
    public void disableDefaultCheck() {
        mRepository.isThereAnyStock(AppConstants.sActiveUserId, new StockDataSource.IsAnyCallback() {
            @Override
            public void yes() {

            }

            @Override
            public void no() {
                getView().disableDefaultOption();
            }
        });
    }


}
