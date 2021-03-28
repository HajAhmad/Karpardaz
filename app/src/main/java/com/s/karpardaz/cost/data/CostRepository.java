package com.s.karpardaz.cost.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.cost.model.Cost;

import java.util.List;
import java.util.Objects;

public class CostRepository implements CostDataSource {

    private final CostDataSource mLocalDataSource;

    public CostRepository(@NonNull CostDataSource localDataSource) {
        mLocalDataSource = Objects.requireNonNull(localDataSource);
    }

    @Override
    public LiveData<List<Cost>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<Cost>> getByDate(@NonNull String startDate, @NonNull String endDate) {
        return null;
    }

    @Override
    public LiveData<List<Cost>> getBySubject(@NonNull String subject) {
        return null;
    }

    @Override
    public LiveData<Cost> get(@NonNull String costId) {
        return null;
    }

    @Override
    public Long insert(@NonNull Cost cost) {
        return null;
    }

    @Override
    public Integer update(@NonNull Cost cost) {
        return null;
    }

    @Override
    public Integer delete(@NonNull Cost cost) {
        return null;
    }

    @Override
    public void refreshList() {

    }
}