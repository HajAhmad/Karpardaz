package com.s.karpardaz.shared.data.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.s.karpardaz.base.datasource.BaseDao;

@Dao
public interface StockDao extends BaseDao<Stock> {

    @Insert
    @Override
    long insert(Stock obj);

    @Update
    @Override
    int update(Stock obj);

    @Delete
    @Override
    int delete(Stock obj);
}
