package com.s.karpardaz.shared.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.shared.data.model.cost.CostEntity;

import java.util.List;

public interface CostDataSource {

    void getAllCostList(GetAllCostListCallback callback);

    void getCostByDate(@NonNull String startDate, @NonNull String endDate,
                       @NonNull GetAllCostListCallback callback);

    void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback);

    void getCostDetail(@NonNull String costId, GetCostCallback callback);

    void insertCost(@NonNull CostEntity cost, @NonNull InsertCostCallback callback);

    void updateCost(@NonNull CostEntity cost, @NonNull UpdateCostCallback callback);

    void deleteCost(@NonNull CostEntity cost, @NonNull DeleteCostCallback callback);

    void refreshList();

    interface GetAllCostListCallback extends BaseCallback<LiveData<List<CostEntity>>> {
    }

    interface GetCostCallback extends BaseCallback<LiveData<CostEntity>> {
    }

    interface InsertCostCallback extends BaseCallback<String> {
        void insertCost(CostEntity cost);
    }

    interface UpdateCostCallback extends BaseCallback<String> {
    }

    interface DeleteCostCallback extends BaseCallback<Integer> {
    }

}
