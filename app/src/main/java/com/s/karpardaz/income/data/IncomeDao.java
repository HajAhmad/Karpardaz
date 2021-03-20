package com.s.karpardaz.income.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.data.BaseFinDao;
import com.s.karpardaz.income.model.Income;

import java.util.List;

@Dao
public interface IncomeDao extends BaseFinDao<Income> {

    @Query("select * from income where uuid = :uuid")
    LiveData<List<Income>> getAllFins(String uuid);

    @Query("select * from income where atDate >= :startDate and atDate <= :endDate and uuid = :uuid")
    LiveData<List<Income>> getFinsByDate(String startDate, String endDate, String uuid);
}
