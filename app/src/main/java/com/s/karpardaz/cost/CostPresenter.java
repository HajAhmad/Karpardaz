package com.s.karpardaz.cost;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.cost.data.CostDataSource;
import com.s.karpardaz.cost.model.Cost;
import com.s.karpardaz.cost.model.CostViewItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppConstants.getDefaultStockId;
import static com.s.karpardaz.base.util.AppConstants.getsDefaultStockCurrency;
import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;
import static java.util.Objects.requireNonNull;

public class CostPresenter extends BasePresenter<CostContract.View>
    implements CostContract.Presenter {

    private final CostDataSource mCostRepository;

    @Inject
    public CostPresenter(@NonNull CostDataSource costRepository) {
        mCostRepository = requireNonNull(costRepository);
    }

    @Override
    public void start() {
        String currentDate = getCurrentDateTimeUTC().split("T")[0];
        mCostRepository.getByDay(getDefaultStockId(), currentDate, costList -> {
            BigDecimal sum = new BigDecimal(0);
            List<CostViewItem> viewItemList = new ArrayList<>();
            int order = costList.size();
            for (Cost c : costList){
                sum = sum.add(new BigDecimal(c.getAmount()));

                viewItemList.add(new CostViewItem(order, c.getUuid(), c.getAtDate(), c.getSubject(),
                    c.getAmount(), c.getCreatedAt(), c.getUpdatedAt()));

                order -= 1;
            }

            getView().setTodayTotalCost(sum.toString(), getsDefaultStockCurrency());
            getView().showTodayCostList(viewItemList);
        });
    }

}
