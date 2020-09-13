package com.s.karpardaz.cost.data.source.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.s.karpardaz.cost.data.model.Cost;
import com.s.karpardaz.cost.data.source.CostDataSource;

import java.util.ArrayList;
import java.util.List;

public class CostLocalDataSource implements CostDataSource {

    @Override
    public void getAllCostList(GetAllCostListCallback callback) {
        final MutableLiveData<List<Cost>> costListLive = new MutableLiveData<>();
        costListLive.setValue(new ArrayList<Cost>(){{
            add(Cost.Companion.getSample());
            add(Cost.Companion.getSample());
            add(Cost.Companion.getSample());
            add(Cost.Companion.getSample());
            add(Cost.Companion.getSample());
            add(Cost.Companion.getSample());
        }});
        callback.onSuccess(costListLive);
    }

    @Override
    public void getCostByDate(@NonNull String startDate, @NonNull String endDate, @NonNull GetAllCostListCallback callback) {

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
