package com.s.karpardaz.shared.data.model.cost;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;

@Dao
public interface CostDao {

    @Insert
    long insert(CostEntity cost);

//    @Query("select * from cost where stockId = :stockId")
    LiveData<List<CostEntity>> getAllCosts(String stockId);

//    @Query("select * from cost where PurchasedAt >= :startDate and PurchasedAt <= :endDate and stockId = :stockId")
    LiveData<List<CostEntity>> getCostsByDate(String startDate, String endDate, String stockId);

}
