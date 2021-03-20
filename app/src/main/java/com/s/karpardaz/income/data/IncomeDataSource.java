package com.s.karpardaz.income.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.income.model.Income;

import java.util.List;

public interface IncomeDataSource {
    LiveData<List<Income>> getAll();

    LiveData<List<Income>> getByDate(@NonNull String startDate, @NonNull String endDate);

    LiveData<List<Income>> getBySubject(@NonNull String subject);

    LiveData<Income> get(@NonNull String income);

    Long insert(@NonNull Income income);

    Integer update(@NonNull Income income);

    Integer delete(@NonNull Income income);

    void refreshList();
}
