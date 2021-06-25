package com.s.karpardaz.stock.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

@Dao
public interface StockDao extends BaseDao<Stock> {

    @Query("select count(*) from Stock limit 1")
    Integer isAny();


    @Query("select * from Stock")
    List<Stock> getAll();
}
