package com.s.karpardaz.home.costlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.mvvm.BaseViewModel;
import com.s.karpardaz.cost.data.model.Cost;
import com.s.karpardaz.cost.data.source.CostDataSource;

import java.util.List;

import javax.inject.Inject;


public class CostListViewModel extends BaseViewModel{

    //    private final LiveData<List<Cost>> costListObservable;

    private CostDataSource mDataSource;

    @Inject
    public CostListViewModel(@NonNull Application application) {
        super(application);

        //        costListObservable = mDataSource.getAllCostList();
    }

    public List<Cost> getAllCostList() {
        return null;
    }


    public List<Cost> getLastTwentyCosts() {
        return null;
    }

    public String getTodayTotalCost() {
        return null;
    }
}
