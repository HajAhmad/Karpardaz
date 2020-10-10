package com.s.karpardaz.shared.data.source;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.di.scope.LocalDataSource;
import com.s.karpardaz.base.di.scope.RemoteDataSource;
import com.s.karpardaz.shared.data.model.cost.CostEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

@Singleton
public class CostRepository implements CostDataSource {

    private final CostDataSource mLocalDataSource;
    private final CostDataSource mRemoteDataSource;

    @Inject
    public CostRepository(@NonNull @LocalDataSource CostDataSource localDataSource,
            @NonNull @RemoteDataSource CostDataSource remoteDataSource) {
        mLocalDataSource = requireNonNull(localDataSource);
        mRemoteDataSource = requireNonNull(remoteDataSource);
    }

    @Override
    public void getAllCostList(GetAllCostListCallback callback) {
        mLocalDataSource.getAllCostList(requireNonNull(callback));
    }

    @Override
    public void getCostByDate(@NonNull String startDate, @NonNull String endDate,
            @NonNull GetAllCostListCallback callback) {
        isEmpty(startDate);
        isEmpty(endDate);
        mLocalDataSource.getCostByDate(startDate, endDate, requireNonNull(callback));
    }

    @Override
    public void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback) {
        isEmpty(subject);
        mLocalDataSource.getCostBySubject(subject, requireNonNull(callback));
    }

    @Override
    public void getCostDetail(@NonNull String costId, GetCostCallback callback) {
        isEmpty(costId);
        mLocalDataSource.getCostDetail(costId, requireNonNull(callback));
    }

    @Override
    public void insertCost(@NonNull CostEntity cost, @NonNull InsertCostCallback callback) {
        mLocalDataSource.insertCost(requireNonNull(cost), requireNonNull(callback));
    }

    @Override
    public void updateCost(@NonNull CostEntity cost, @NonNull UpdateCostCallback callback) {
        mLocalDataSource.updateCost(requireNonNull(cost), requireNonNull(callback));
    }

    @Override
    public void deleteCost(@NonNull CostEntity cost, @NonNull DeleteCostCallback callback) {
        mLocalDataSource.deleteCost(
                requireNonNull(cost),
                requireNonNull(callback)
        );
    }

    @Override
    public void refreshList() {
        throw new NotImplementedException();
    }
}
