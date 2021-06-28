package com.s.karpardaz.cost.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.cost.model.Cost;

import java.util.List;

public interface CostDataSource {

    void getAll(String stockId);
    void getByDay(String stockId, @NonNull String date, BaseCallback<List<Cost>> baseCallback);
    void getByDate(String stockId, @NonNull String startDate, @NonNull String endDate);
    void getBySubject(String stockId, @NonNull String subject);
    void get(@NonNull String costId, BaseCallback<Cost> baseCallback);
    void insert(@NonNull Cost cost, BaseCallback<Void> callback);
    void update(String costId, @NonNull Cost cost, BaseCallback<Void> callback);
    void delete(String stockId, @NonNull Cost cost);
    void refreshList();

}
