package com.s.karpardaz.stock;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

public interface StockContract {
    interface View extends BaseViewContract<Presenter>{

        void showNoStock();
        void setStocks(List<Stock> result);
    }

    interface Presenter extends BasePresenterContract<View>{

        void getAllStocks();
    }
}
