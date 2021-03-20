package com.s.karpardaz.cost.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.cost.model.Cost;

import java.util.List;

public interface CostDataSource {
    LiveData<List<Cost>> getAll();

    LiveData<List<Cost>> getByDate(@NonNull String startDate, @NonNull String endDate);

    LiveData<List<Cost>> getBySubject(@NonNull String subject);

    LiveData<Cost> get(@NonNull String costId);

    Long insert(@NonNull Cost cost);

    Integer update(@NonNull Cost cost);

    Integer delete(@NonNull Cost cost);

    void refreshList();
}
