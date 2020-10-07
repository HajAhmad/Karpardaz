package com.s.karpardaz.shared.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CostDao {

    @Insert
    long insert(Cost cost);

//    @Query("select * from cost where stockId = :stockId")
    LiveData<List<Cost>> getAllCosts(String stockId);

//    @Query("select * from cost where PurchasedAt >= :startDate and PurchasedAt <= :endDate and stockId = :stockId")
    LiveData<List<Cost>> getCostsByDate(String startDate, String endDate, String stockId);

}
