package com.s.karpardaz.income.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.income.model.Income;

import java.util.List;
import java.util.Objects;

public class IncomeRepository implements IncomeDataSource {

    private final IncomeDao mDao;

    public IncomeRepository(@NonNull IncomeDao dao) {
        mDao= Objects.requireNonNull(dao);
    }

    @Override
    public LiveData<List<Income>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<Income>> getByDate(@NonNull String startDate, @NonNull String endDate) {
        return null;
    }

    @Override
    public LiveData<List<Income>> getBySubject(@NonNull String subject) {
        return null;
    }

    @Override
    public LiveData<Income> get(@NonNull String income) {
        return null;
    }

    @Override
    public Long insert(@NonNull Income income) {
        return null;
    }

    @Override
    public Integer update(@NonNull Income income) {
        return null;
    }

    @Override
    public Integer delete(@NonNull Income income) {
        return null;
    }

    @Override
    public void refreshList() {

    }
}
