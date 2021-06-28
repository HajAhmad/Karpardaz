package com.s.karpardaz.cost;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;
import com.s.karpardaz.cost.model.CostViewItem;

import java.util.List;

public interface CostContract {
    interface View extends BaseViewContract<Presenter> {

        void setTodayTotalCost(String toString, String currency);
        void showTodayCostList(List<CostViewItem> result);
    }

    interface Presenter extends BasePresenterContract<View> {

    }
}
