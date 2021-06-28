package com.s.karpardaz.stock.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

@Dao
public interface StockDao extends BaseDao<Stock> {

    @Query("select count(*) from Stock")
    Integer getTableCount();

    @Query("select * from Stock")
    List<Stock> getAll();

    @Query("select count(*) from Stock where name = :stockName")
    Integer getSameNameCount(String stockName);

    @Query("select * from Stock where uuid = :stockId limit 1")
    Stock get(String stockId);

    @Query("update Stock set name = :name, currency = :currency, updatedAt = :updatedAt where uuid = :stockId")
    Integer update(String stockId, String name, String currency, String updatedAt);
}
