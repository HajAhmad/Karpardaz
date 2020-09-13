package com.s.karpardaz.cost.data.source.remote;

import androidx.annotation.NonNull;

import com.s.karpardaz.cost.data.model.Cost;
import com.s.karpardaz.cost.data.source.CostDataSource;

import kotlin.NotImplementedError;

public class CostRemoteDataSource implements CostDataSource {

    @Override
    public void getAllCostList(GetAllCostListCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void getCostByDate(@NonNull String startDate, @NonNull String endDate, @NonNull GetAllCostListCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void getCostBySubject(@NonNull String subject, @NonNull GetCostCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void getCostDetail(@NonNull String costId, GetCostCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void insertCost(@NonNull Cost cost, @NonNull InsertCostCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void updateCost(@NonNull Cost cost, @NonNull UpdateCostCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void deleteCost(@NonNull Cost cost, @NonNull DeleteCostCallback callback) {
        throw new NotImplementedError();
    }

    @Override
    public void refreshList() {
        throw new NotImplementedError();
    }
}
