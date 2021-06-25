package com.s.karpardaz.stock.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

public interface StockDataSource {

    void isThereAnyStock(@NonNull String userId, @NonNull IsAnyCallback callback);
    void getAllStocks(String sActiveUserId, BaseCallback<List<Stock>> listBaseCallback);

    interface IsAnyCallback extends BaseCallback<Void>{
        void no();
    }

}
