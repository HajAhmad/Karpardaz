package com.s.karpardaz.cost.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.data.BaseFinDao;
import com.s.karpardaz.cost.model.Cost;

import java.util.List;

@Dao
public interface CostDao extends BaseFinDao<Cost> {

    @Query("select * from cost where uuid = :uuid")
    LiveData<List<Cost>> getAllFins(String uuid);

    @Query("select * from Cost where uuid = :uuid and atDate >= :startDate and atDate <= :endDate")
    LiveData<List<Cost>> getFinsByDate(String startDate, String endDate, String uuid);


}
