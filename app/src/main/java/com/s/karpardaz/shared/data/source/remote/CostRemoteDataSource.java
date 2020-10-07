package com.s.karpardaz.shared.data.source.remote;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.shared.data.model.Cost;
import com.s.karpardaz.shared.data.source.CostDataSource;

import javax.inject.Inject;


public class CostRemoteDataSource implements CostDataSource {

    @Inject
    public CostRemoteDataSource() {
    }

    @Override
    public void getAllCostList(GetAllCostListCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void getCostByDate(@NonNull String startDate, @NonNull String endDate,
            @NonNull GetAllCostListCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void getCostDetail(@NonNull String costId, GetCostCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void insertCost(@NonNull Cost cost, @NonNull InsertCostCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void updateCost(@NonNull Cost cost, @NonNull UpdateCostCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteCost(@NonNull Cost cost, @NonNull DeleteCostCallback callback) {
        throw new NotImplementedException();
    }

    @Override
    public void refreshList() {
        throw new NotImplementedException();
    }
}
