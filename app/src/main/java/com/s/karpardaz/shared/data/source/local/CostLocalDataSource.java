package com.s.karpardaz.shared.data.source.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.datasource.CostDatabase;
import com.s.karpardaz.shared.data.model.Cost;
import com.s.karpardaz.shared.data.model.CostDao;
import com.s.karpardaz.shared.data.source.CostDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void insertCost(@NonNull Cost cost, @NonNull InsertCostCallback callback) {

    }

    @Override
    public void updateCost(@NonNull Cost cost, @NonNull UpdateCostCallback callback) {

    }

    @Override
    public void deleteCost(@NonNull Cost cost, @NonNull DeleteCostCallback callback) {

    }

    @Override
    public void refreshList() {

    }
}
