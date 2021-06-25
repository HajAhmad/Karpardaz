package com.s.karpardaz.income.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.income.model.Income;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface IncomeDao extends BaseDao<Income> {

    @Query("select * from income where uuid = :uuid")
    Flowable<List<Income>> getAll(String uuid);

    @Query("select * from income where atDate >= :startDate and atDate <= :endDate and uuid = :uuid")
    Flowable<List<Income>> getByDate(String startDate, String endDate, String uuid);
}
