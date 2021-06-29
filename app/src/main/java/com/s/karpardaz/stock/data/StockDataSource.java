package com.s.karpardaz.stock.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.stock.model.DefaultStock;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

public interface StockDataSource {

    void isThereAnyStock(@NonNull String userId, @NonNull IsAnyCallback callback);
    void getAllStocks(String sActiveUserId, BaseCallback<List<Stock>> listBaseCallback);
    void addStock(Stock stock, BaseCallback<Void> callback);
    void isStockUnique(@NonNull String stockName, @NonNull IsStockUniqueCallback callback);
    void getStock(@NonNull String stockId, @NonNull GetStockCallback callback);
    void setDefaultStock(@NonNull String stockUuid, @NonNull String createdAt, @NonNull BaseCallback<Void> callback);
    void isStockDefault(@NonNull String stockId, @NonNull IsStockDefaultCallback callback);
    void updateStock(Stock stock, BaseCallback<Void> callback);
    void getDefaultStockId(@NonNull BaseCallback<DefaultStock> callback);
    void isDefaultStockTableEmpty(@NonNull IsTableEmptyCallback callback);
    void cancelAllCalls();

    interface IsTableEmptyCallback extends BaseCallback<Void>{
        void yes();
        void no();
    }

    interface IsStockDefaultCallback extends BaseCallback<Void>{
        void yes();
        void no();
    }

    interface IsStockUniqueCallback extends BaseCallback<Void> {
        void no();
        void yes();
    }

    interface IsAnyCallback extends BaseCallback<Void> {
        void yes();
        void no();

        default void onSuccess(Void result){}
    }

    interface GetAllStockCallback extends BaseCallback<List<Stock>>{
        void noStock();
    }

    interface GetStockCallback extends BaseCallback<Stock>{
        void stockNotFound();
    }

}
