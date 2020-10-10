package com.s.karpardaz.shared.data.source.local;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.datasource.CostDatabase;
import com.s.karpardaz.shared.data.model.cost.CostEntity;
import com.s.karpardaz.shared.data.model.cost.CostDao;
import com.s.karpardaz.shared.data.source.CostDataSource;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class CostLocalDataSource implements CostDataSource {

    private final CostDatabase mDb;
    private final CostDao mCostDao;

    @Inject
    public CostLocalDataSource(@NonNull CostDatabase db,
            @NonNull CostDao costDao) {
        mDb = requireNonNull(db);
        mCostDao = mDb.costDao();
    }

    @Override
    public void getAllCostList(@NonNull GetAllCostListCallback callback) {
        throw new NotImplementedException();
//        final LiveData<List<Cost>> costList = mCostDao.getCostByDate(startDate, endDate);
//        callback.onSuccess(costListLive);
    }

    @Override
    public void getCostByDate(@NonNull String startDate, @NonNull String endDate,
            @NonNull GetAllCostListCallback callback) {

    }

    @Override
    public void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback) {

    }

    @Override
    public void getCostDetail(@NonNull String costId, GetCostCallback callback) {

    }

    @Override
    public void insertCost(@NonNull CostEntity cost, @NonNull InsertCostCallback callback) {

    }

    @Override
    public void updateCost(@NonNull CostEntity cost, @NonNull UpdateCostCallback callback) {

    }

    @Override
    public void deleteCost(@NonNull CostEntity cost, @NonNull DeleteCostCallback callback) {

    }

    @Override
    public void refreshList() {

    }
}
