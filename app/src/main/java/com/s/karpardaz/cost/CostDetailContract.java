package com.s.karpardaz.cost;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;
import com.s.karpardaz.cost.model.Cost;

public interface CostDetailContract {
    interface View extends BaseViewContract<Presenter> {

        void showCostDetail(Cost cost);
        void dismissAndUpdate();
    }

    interface Presenter extends BasePresenterContract<View> {

        void showCost(String costId);
        void insertNewCost(Cost cost);
        void updateCost(String costId, Cost cost);
    }
}

