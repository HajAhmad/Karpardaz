package com.s.karpardaz.cost.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.cost.data.model.Cost;

import java.util.List;

public interface CostDataSource {

    void getAllCostList(GetAllCostListCallback callback);

    void getCostByDate(@NonNull String startDate, @NonNull String endDate,
                       @NonNull GetAllCostListCallback callback);

    void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback);

    void getCostDetail(@NonNull String costId, GetCostCallback callback);

    void insertCost(@NonNull Cost cost, @NonNull InsertCostCallback callback);

    void updateCost(@NonNull Cost cost, @NonNull UpdateCostCallback callback);

    void deleteCost(@NonNull Cost cost, @NonNull DeleteCostCallback callback);

    void refreshList();

    interface GetAllCostListCallback extends BaseCallback<LiveData<List<Cost>>> {
    }

    interface GetCostCallback extends BaseCallback<Cost> {
    }

    interface InsertCostCallback extends BaseCallback<String> {
        void insertCost(Cost cost);
    }

    interface UpdateCostCallback extends BaseCallback<String> {
    }

    interface DeleteCostCallback extends BaseCallback<Integer> {
    }

}
