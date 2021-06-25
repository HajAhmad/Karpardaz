package com.s.karpardaz.cost.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.cost.model.Cost;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CostDao extends BaseDao<Cost> {

    @Query("select * from cost where uuid = :uuid")
    Flowable<List<Cost>> getAll(String uuid);

    @Query("select * from Cost where uuid = :uuid and atDate >= :startDate and atDate <= :endDate")
    Flowable<List<Cost>> getByDate(String startDate, String endDate, String uuid);


}
