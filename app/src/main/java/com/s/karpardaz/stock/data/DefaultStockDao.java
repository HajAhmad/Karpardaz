package com.s.karpardaz.stock.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.s.karpardaz.base.dao.BaseDao;
import com.s.karpardaz.stock.model.DefaultStock;

@Dao
public interface DefaultStockDao extends BaseDao<DefaultStock> {

    @Query("select count(*) from DefaultStock where uuid = :stockId limit 1")
    Integer getCount(String stockId);

    @Query("select * from DefaultStock limit 1")
    DefaultStock get();

    @Query("select count(*) from DefaultStock")
    Integer getTableCount();

    @Query("delete from DefaultStock")
    void clearTable();
}
