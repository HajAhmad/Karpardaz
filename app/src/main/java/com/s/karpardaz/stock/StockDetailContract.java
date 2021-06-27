package com.s.karpardaz.stock;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;
import com.s.karpardaz.stock.model.Stock;

public interface StockDetailContract {

    interface View extends BaseViewContract<Presenter> {

        void insertionFailed();
        void newStockInserted();
        void showStockFoundError();
        void showStock(@NonNull Stock result);
        void showStockNotFoundError();
        void showSetDefaultStockFailed();
        void setDefaultStock();
        void setNotDefaultStock();
        void showStockUpdated();
        void disableDefaultOption();
    }

    interface Presenter extends BasePresenterContract<View> {

        void addStock(@NonNull Stock stock, boolean isDefault);
        void getStockDetail(@NonNull String stockId);
        void updateStock(Stock stock, boolean isDefault);
        void disableDefaultCheck();
    }
}
