package com.s.karpardaz.cost.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.cost.model.Cost;

import java.util.List;

@Dao
public interface CostDao extends BaseDao<Cost> {

    @Query("select * from cost")
    List<Cost> getAll();

    @Query("select * from Cost where uuid = :uuid and atDate >= :startDate and atDate <= :endDate")
    List<Cost> getByDate(String startDate, String endDate, String uuid);

    @Query("select * from Cost where stockId = :stockId and date(createdAt) = date('now') order by createdAt desc")
    List<Cost> getTodayLastByCount(String stockId);

    @Query("update Cost set subject = :subject, amount = :amount, description = :description where " +
        "uuid = :costId")
    Integer update(String costId, String subject, String amount, String description);

    @Query("select * from Cost where uuid = :costId")
    Cost get(String costId);
}
